package training.lab;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static training.lab.IndividualType.ADULT;
import static training.lab.IndividualType.CHILD;

public class ServiceTest {

    private final Service service = new Service();

    @Test
    public void computeForSingle() {
        final Household household = new Household();
        household.add(new Individual(ADULT, new BigDecimal("32000")));
        assertEquals(0, new BigDecimal("3893.26").compareTo(this.service.compute(household)));
    }

    @Test
    public void computeForCoupleAndTwoChildren() {
        final Household household = new Household();
        household.add(new Individual(ADULT, new BigDecimal("32000")));
        household.add(new Individual(ADULT, new BigDecimal("23950")));
        household.add(new Individual(CHILD, new BigDecimal("0")));
        household.add(new Individual(CHILD, new BigDecimal("0")));
        assertEquals(0, new BigDecimal("1238.02").compareTo(this.service.compute(household)));
    }

    @Test
    public void computeForCoupleAndThreeChildren() {
        final Household household = new Household();
        household.add(new Individual(ADULT, new BigDecimal("70000")));
        household.add(new Individual(ADULT, new BigDecimal("80950")));
        household.add(new Individual(CHILD, new BigDecimal("0")));
        household.add(new Individual(CHILD, new BigDecimal("0")));
        household.add(new Individual(CHILD, new BigDecimal("0")));
        assertEquals(0, new BigDecimal("7231.83143").compareTo(this.service.compute(household)));
    }

    @Test
    public void computeForCoupleAndOneChildren() {
        final Household household = new Household();
        household.add(new Individual(ADULT, new BigDecimal("70000")));
        household.add(new Individual(ADULT, new BigDecimal("80950")));
        household.add(new Individual(CHILD, new BigDecimal("0")));
        assertEquals(0, new BigDecimal("12407.26000").compareTo(this.service.compute(household)));
    }

    @Test
    public void computeForCoupleWithoutChildren() {
        final Household household = new Household();
        household.add(new Individual(ADULT, new BigDecimal("70000")));
        household.add(new Individual(ADULT, new BigDecimal("80950")));
        assertEquals(0, new BigDecimal("17250.14000").compareTo(this.service.compute(household)));
    }

    @Test
    public void computeSingleWithoutChildren() {
        final Household household = new Household();
        household.add(new Individual(ADULT, new BigDecimal("170000")));
        assertEquals(0, new BigDecimal("56654.07000").compareTo(this.service.compute(household)));
    }

}
