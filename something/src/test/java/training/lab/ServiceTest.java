package training.lab;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static training.lab.IndividualType.ADULT;
import static training.lab.IndividualType.CHILD;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        household.add(new Individual(CHILD, null));
        household.add(new Individual(CHILD, null));
        assertEquals(0, new BigDecimal("3714.06").compareTo(this.service.compute(household)));
    }

}
