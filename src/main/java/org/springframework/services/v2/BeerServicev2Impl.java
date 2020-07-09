package org.springframework.services.v2;

import org.springframework.web.model.v2.BeerDtov2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServicev2Impl implements BeerServicev2{
  @Override
  public BeerDtov2 getBeerById(UUID beerId) {
    return BeerDtov2.builder().id(UUID.randomUUID())
            .beerName("Cristal")

            .build();
  }

  @Override
  public BeerDtov2 saveNewBeer(BeerDtov2 beerDto) {
    return BeerDtov2.builder().id(UUID.randomUUID()).build();
  }

  @Override
  public void updateBeer(UUID id, BeerDtov2 beerDto) {

  }

  @Override
  public void deleteById(UUID beerId) {

  }
}
