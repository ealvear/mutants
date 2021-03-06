package com.mercadolibre.mutant.model;

/**

 * Clase StatusModel, modelo de respuesta status

 * @author: Edison A. Alvear Pabon

 * @version: 18/03/2022/

 */

public class StatusModel {

    int count_mutant_dna;
    int count_human_dna;
    float ratio;

    public int getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(int count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public int getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(int count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
