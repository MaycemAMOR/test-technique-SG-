package training.lab;

import java.math.BigDecimal;

public class Individual {

    private final IndividualType type;
    private final BigDecimal revenue;

    public Individual(final IndividualType type, final BigDecimal revenue) {
        this.type = type;
        this.revenue = revenue;
    }

    public IndividualType getType() {
        return this.type;
    }

    public BigDecimal getRevenue() {
        return this.revenue;
    }
}
