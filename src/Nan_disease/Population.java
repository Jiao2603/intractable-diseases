package Nan_disease;

/**
 * Created by jiao on 2017/03/29.
 */
public class Population {
    Individual[] individuals;
    /*
     * 個体
     */
    // 群を作る
    public Population(int populationSize, boolean initialise) {
        individuals = new Individual[populationSize];
        // 初期化
        if (initialise) {
            System.out.println(size());
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                //System.out.println(i);
                saveIndividual(i, newIndividual);

            }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return individuals.length;
    }
    // Save individual
    public void saveIndividual(int index, Individual indiv) {
        individuals[index] = indiv;
    }
}
