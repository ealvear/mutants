package com.mercadolibre.mutant.service;

import com.mercadolibre.mutant.domain.StatusEnt;
import com.mercadolibre.mutant.model.Dna;
import com.mercadolibre.mutant.model.StatusModel;
import com.mercadolibre.mutant.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**

 * Clase Service  MutantService , se encarga de los procesos , de almacenamiento y calculo del status

 * @author: Edison A. Alvear Pabon

 * @version: 18/03/2022/

 */

@Service
public class MutantService {

    private static final Logger LOG = LoggerFactory.getLogger(MutantService.class);

    @Autowired
    private StatusRepository statusRepository;



    /**

     * Metodo status() , Consulta en base de datos , realiza el count , y calcula el ration.

     */
    public StatusModel status(){

        StatusModel status = new StatusModel();

        int mutan= statusRepository.findByMutant("S").size();

        int human= statusRepository.findByMutant("N").size();


        status.setCount_mutant_dna(mutan);

        status.setCount_human_dna(human);


        float ration = (float) (Math.round((float) mutan/human* 100) / 100d);
        status.setRatio(ration);


        return status;
    }


    /**
     * Metodo isMutant , Realiza el diagnostico de identificar si se encuentra un mutante en el array entregado.
     * @param dna, array , con el dna
     */

    public boolean isMutant(Dna dna){


        String[] chars = {"A","T","G","C"};

        if(checkhorizontal(dna,chars)) {
            save(dna,true);
            return true;
        }
        if(checkVertical(dna,chars)) {
            save(dna,true);
            return true;
        }
        if(checkDiagonal(dna,chars)) {
            save(dna,true);
            return true;
        }
        save(dna,false);
        return false;
    }

    /**
     * Metodo save() ,realiza el almacenamiento de el dna y si es mutante en la base de datos
     * @param dna, ,  array , con el dna
     * @param mutant, , boolean  , con la verificacion de si es o no mutante
     */
    private void save(Dna dna, boolean mutant) {

        String cdna = "";

        for (int i = 0 ; i<dna.getDna().length ; i++){
            cdna= cdna + dna.getDna()[i];
        }
        String mut = "N";
        if (mutant){
            mut="S";
        }
        StatusEnt statusEnt = new StatusEnt();
        statusEnt.setDna(cdna);
        statusEnt.setMutant(mut);
        boolean band= statusRepository.save(statusEnt);

    }

    /**
     * Metodo checkhorizontal() , verifica si se encuentra un mutante de manera horizontal
     * @param dna, ,  array , con el dna
     * @param chars, , array  , con los caracteres a comparar para la distincion de mutantes
     */
    private boolean checkhorizontal(Dna dna, String[] chars) {
        int aux=0;
        for (int i = 0; i < chars.length; i++) {
            aux = 0;
            for (int j = 0; j < dna.getDna().length; j++) {
                for (int k = 0; k < dna.getDna().length; k++) {


                    if (chars[i].equals(""+dna.getDna()[j].charAt(k))) {
                        aux++;
                        if(aux==4){
                            return true;
                        }
                    } else {
                        aux = 0;
                    }
                }
            }
        }
        return false ;
    }

    /**
     * Metodo checkVertical() , verifica si se encuentra un mutante de manera Vertical
     * @param dna, ,  array , con el dna
     * @param chars, , array  , con los caracteres a comparar para la distincion de mutantes
     */
    private boolean checkVertical(Dna dna, String[] chars){

        int aux= 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < dna.getDna().length; j++) {
                for (int k = 0; k < dna.getDna().length; k++) {
                    //LOG.info(chars[i] +" -> "+ dna.getDna()[k].charAt(j) );
                    if (chars[i].equals(""+dna.getDna()[k].charAt(j))){
                        aux++;
                        if(aux==4){
                            return true;
                        }
                    }else {
                        aux=0;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Metodo checkDiagonal() , verifica si se encuentra un mutante de manera Diagonal
     * @param dna, ,  array , con el dna
     * @param chars, , array  , con los caracteres a comparar para la distincion de mutantes
     */
    private boolean checkDiagonal(Dna dna, String[] chars){
        int aux= 0;
        int ban=0;
        for (int l = 0; l < chars.length; l++) {
            aux = 0;

            for (int i = 1 - dna.getDna().length ; i < chars.length; i++) {
                aux = 0;
                for (int j = -min(0, i), k = max(0,i); j < dna.getDna().length && k < dna.getDna().length ; j++ , k++) {

                //LOG.info(chars[l] + " = " + dna.getDna()[j].charAt(k) + " -> " + j + "," + (k));
                if (chars[l].equals("" + dna.getDna()[j].charAt(k))) {
                    aux++;
                    if (aux == 4) {
                        return true;
                    }
                } else {
                    aux = 0;
                }
            }
            }
        }
        return false;

    }






}
