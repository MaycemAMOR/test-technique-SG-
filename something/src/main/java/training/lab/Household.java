package training.lab;

import java.util.ArrayList;
import java.util.List;

public class Household {

    private final List<Individual> individuals;

    public Household() {
        this.individuals = new ArrayList<>();
    }

    public void add(final Individual individual) {
        this.individuals.add(individual);
    }

    public List<Individual> getIndividuals() {
        return this.individuals;
    }
}
