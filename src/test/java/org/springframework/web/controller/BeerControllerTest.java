package org.springframework.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.services.BeerService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.model.BeerDto;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

  @MockBean
  BeerService beerService;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  BeerDto validBeer;

  @BeforeEach
  public void setUp() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer" + UUID.randomUUID().toString())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  public void getByBeerId() throws Exception {
    given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
            .andExpect(jsonPath("$.beerName", is("Beer1")));
  }

  @Test
  public void postNewBeer() throws Exception {
    //given
    BeerDto beerDto = validBeer;
    beerDto.setId(null);
    BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
    String beerDtoJson = objectMapper.writeValueAsString(beerDto);

    given(beerService.saveNewBeer(any())).willReturn(savedDto);

    mockMvc.perform(post("/api/v1/beer/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
            .andExpect(status().isCreated());

  }

  @Test
  public void updateByBeerId() throws Exception {
    //given
    BeerDto beerDto = validBeer;
    String beerDtoJson = objectMapper.writeValueAsString(beerDto);

    //when
    mockMvc.perform(put("/api/v1/beer/" + validBeer.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
            .andExpect(status().isNoContent());

    then(beerService).should().updateBeer(any(), any());

  }



  @Test
  void deleteByBeerId() {
  }
}