package training.lab;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static training.lab.IndividualType.ADULT;
import static training.lab.IndividualType.CHILD;

public class Service {

    private static final BigDecimal HALF = BigDecimal.valueOf(0.5);
    private static final BigDecimal ZERO = BigDecimal.ZERO;
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private static final int SCALE = 5;


    private static final List<BigDecimal> BASIS_INCOME_SCALE_LIST = Arrays.asList(
            BigDecimal.valueOf(9807),
            BigDecimal.valueOf(27086),
            BigDecimal.valueOf(72617),
            BigDecimal.valueOf(153783)
    );

    private static final List<BigDecimal> GROSS_TAX_LIST = Arrays.asList(
            BigDecimal.valueOf(14),
            BigDecimal.valueOf(30),
            BigDecimal.valueOf(41),
            BigDecimal.valueOf(45)
    );

    private static int computeGrossTaxIncomeRange(BigDecimal basis) {
        int grossTaxIncomeRange = 0;
        for (int i = 0; i < BASIS_INCOME_SCALE_LIST.size(); i++) {
            if (basis.compareTo(BASIS_INCOME_SCALE_LIST.get(i)) > 0) {
                grossTaxIncomeRange = i;
            }
        }
        return grossTaxIncomeRange;
    }

    private static BigDecimal computeTaxIncome(int grossTaxIncomeRange, BigDecimal basis) {
        BigDecimal taxIncome = ZERO;
        if (grossTaxIncomeRange > 0) {
            for (int i = 0; i <= grossTaxIncomeRange; i++) {
                if (i != grossTaxIncomeRange) {
                    taxIncome = taxIncome
                            .add(((BASIS_INCOME_SCALE_LIST.get(i + 1)
                                    .subtract(BASIS_INCOME_SCALE_LIST.get(i)))
                                    .multiply(GROSS_TAX_LIST.get(i)))
                                    .divide(HUNDRED, SCALE, RoundingMode.CEILING));
                }
                if (i == grossTaxIncomeRange) {
                    taxIncome = taxIncome
                            .add(((basis.subtract(BASIS_INCOME_SCALE_LIST.get(i)))
                                    .multiply(GROSS_TAX_LIST.get(i)))
                                    .divide(HUNDRED, SCALE, RoundingMode.CEILING));
                }
            }
        } else {
            taxIncome = taxIncome
                    .add(((basis.subtract(BASIS_INCOME_SCALE_LIST.get(0)))
                            .multiply(GROSS_TAX_LIST.get(0)))
                            .divide(HUNDRED, SCALE, RoundingMode.CEILING));
        }
        return taxIncome;
    }

    public BigDecimal compute(final Household household) {
        // compute total revenues
        BigDecimal totalRevenues = household.getIndividuals().stream()
                .map(Individual::getRevenue)
                .reduce(ZERO, BigDecimal::add);
        // compute family quotient

        long child = household.getIndividuals().stream()
                .filter(x -> x.getType().equals(CHILD))
                .count();
        long adult = household.getIndividuals().stream()
                .filter(x -> x.getType() == ADULT)
                .count();

        BigDecimal familyQuotient = BigDecimal.valueOf(adult).add(BigDecimal.valueOf(child).multiply(HALF));
        System.out.println("familyQuotient : " + familyQuotient);
        System.out.println("totalRevenues : " + totalRevenues);
        BigDecimal basis = totalRevenues.divide(familyQuotient, SCALE, RoundingMode.CEILING);

        System.out.println("basis : " + basis);
        System.out.println("GrossTaxIncomeRange : " + (computeGrossTaxIncomeRange(basis) + 2) + " Range");

        // compute the taxIncome
        BigDecimal taxIncome = computeTaxIncome(computeGrossTaxIncomeRange(basis), basis);

        System.out.println("taxIncome : " + taxIncome);

        return taxIncome;
    }
}
