package SCA_GA;

/**
 * Created by jiao.xue on 2017/02/03.
 */
public class Population {
    Individual[] individuals;

    // populationを作る
    //populationSize：populationの中の個体数

    public Population(int populationSize, boolean initialise) {
        //initialise: 初期化かどうか
        individuals = new Individual[populationSize];
        // 初始化种群
        if (initialise) {
            for (int i = 0; i < size(); i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return individuals[index];
    }

/*
*/

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
