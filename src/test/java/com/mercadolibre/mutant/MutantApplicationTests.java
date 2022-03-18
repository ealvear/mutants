package com.mercadolibre.mutant;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.mutant.domain.StatusEnt;
import com.mercadolibre.mutant.model.Dna;
import com.mercadolibre.mutant.model.StatusModel;
import com.mercadolibre.mutant.repository.StatusRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {MutantApplication.class})


@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MutantApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StatusRepository statusRepository;

    List<StatusEnt> statusEntLis = new ArrayList<>();
    List<StatusModel> statusModelList = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    Dna dna = new Dna();
    StatusModel statusModel = new StatusModel();

    @Before
    public void setUp(){

        dna.setDna(new String[]{"ATGCGA",
                "ATGTAG",
                "CTACTT",
                "AGTAAA",
                "CACTAA",
                "TCACTG"});

        StatusEnt statusEnt = new StatusEnt();

        statusEnt.setDna("ATGCGAATGAAGTCTTTTAGTAAACACCAATCACTG");
        statusEnt.setMutant("S");

        statusEntLis.add(statusEnt);


        statusModel.setCount_human_dna(1);
        statusModel.setCount_mutant_dna(1);
        statusModel.setRatio((float) 1.0);

    }

    @Test
    public void IsMutant() throws Exception {
        Mockito.when(statusRepository.save(Mockito.any())).thenReturn(
                true
        );

        String jsonRSExpected = "{}" ;
        String jsonRQExpected = mapper.writeValueAsString(dna) ;

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRQExpected)

                )
                .andDo(print())
                .andExpect(status().isOk());
        Mockito.verify(statusRepository).save(Mockito.any());

    }

    @Test
    public void IsNotMutant() throws Exception {
        Mockito.when(statusRepository.save(Mockito.any())).thenReturn(
                true
        );

        dna.setDna(new String[]{
                "ATGCGA",
                "ATGTAG",
                "CCACTT",
                "AGTAAA",
                "CACCAA",
                "TCACTG"});

        String jsonRSExpected = "{}" ;
        String jsonRQExpected = mapper.writeValueAsString(dna) ;

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/mutant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRQExpected)

                )
                .andDo(print())
                .andExpect(status().isForbidden());
        Mockito.verify(statusRepository).save(Mockito.any());

    }

    @Test
    public void stats() throws Exception {
        Mockito.when(statusRepository.findByMutant("S")).thenReturn(
                statusEntLis
        );

        Mockito.when(statusRepository.findByMutant("N")).thenReturn(
                statusEntLis
        );

        String jsonRSExpected = mapper.writeValueAsString(statusModel);
        String jsonRQExpected = "{}"  ;


        mockMvc.perform(MockMvcRequestBuilders.get("/v1/stats")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonRSExpected));
        Mockito.verify(statusRepository).findByMutant("S");
        Mockito.verify(statusRepository).findByMutant("N");



    }

}

