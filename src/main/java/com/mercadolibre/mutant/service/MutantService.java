package com.mercadolibre.mutant.service;

import com.mercadolibre.mutant.domain.StatusEnt;
import com.mercadolibre.mutant.model.Dna;
import com.mercadolibre.mutant.model.StatusModel;
import com.mercadolibre.mutant.repository.IStatusRepository;
import com.mercadolibre.mutant.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Service
public class MutantService {

    private static final Logger LOG = LoggerFactory.getLogger(MutantService.class);

    @Autowired
    private StatusRepository statusRepository;

    public StatusModel status(){

        StatusModel status = null;

        int mutan= statusRepository.findByMutant(1).size();
        int human= statusRepository.findByMutant(0).size();


        status.setCount_mutant_dna(mutan);
        status.setCount_mutant_dna(human);

        double ration = mutan/human;
        status.setRatio(ration);






        return status;
    }

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

    private void save(Dna dna, boolean mutant) {

        String cdna = "";
        int mut= 0;

        for (int i = 0 ; i<dna.getDna().length ; i++){
            cdna= cdna + dna.getDna()[i];
        }

        if(mutant){
            mut=1;
        }

        StatusEnt statusEnt = new StatusEnt();
        statusEnt.setDna(cdna);
        statusEnt.setMutant(mut);

        statusRepository.save(statusEnt);

    }

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

    private boolean checkDiagonal(Dna dna, String[] chars){
        int aux= 0;
        int ban=0;
        for (int l = 0; l < chars.length; l++) {
            aux = 0;

            for (int i = 1 - dna.getDna().length ; i < chars.length; i++) {
                aux = 0;
                for (int j = -min(0, i), k = max(0,i); j < dna.getDna().length && k < dna.getDna().length ; j++ , k++) {

                LOG.info(chars[l] + " = " + dna.getDna()[j].charAt(k) + " -> " + j + "," + (k));
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
