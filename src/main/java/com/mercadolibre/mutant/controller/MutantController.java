package com.mercadolibre.mutant.controller;

import com.mercadolibre.mutant.model.Dna;
import com.mercadolibre.mutant.model.StatusModel;
import com.mercadolibre.mutant.service.MutantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**

 * Esta clase define el Controlador Mutans, la recepcion, de cuerpos json o parametros,llamado de los servicio , procesamiento y devuelta de la respuesta solicitada

 * @author: Edison A. Alvear Pabon

 * @version: 18/03/2022/

 */



@RestController
@RequestMapping("/v1/")

public class MutantController {

    //Campos de la clase
    @Autowired
    private MutantService mutantService;

    private static final Logger LOG = LoggerFactory.getLogger(MutantController.class);


    /**

     * Constructor para la validacion y el almacenamiento , de los dna , definicion de mutantes

     * @param dna  El parámetro dna define el array , contenedor de el dna a validar.

     */
    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE, path = "/mutant")
    public ResponseEntity<String> mutant(@RequestBody Dna dna ) {

        if (mutantService.isMutant(dna)) {
            return new ResponseEntity<String>( HttpStatus.OK);
        } else {

            return new ResponseEntity<String>(HttpStatus.FORBIDDEN);

        }
    }

    /**

     * Constructor para la validacion y construccion de reporte de status

     */
    @GetMapping(path = "/stats")
    public ResponseEntity<StatusModel> status() {
        StatusModel status;
        status= mutantService.status();

        if (true) {
            return new ResponseEntity<StatusModel>(status, HttpStatus.OK);
        } else {

            return new ResponseEntity<StatusModel>(HttpStatus.FORBIDDEN);

        }
    }

}

