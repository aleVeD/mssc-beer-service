package org.springframework.services.v2;

import org.springframework.web.model.v2.BeerDtov2;

import java.util.UUID;

public interface BeerServicev2 {
  BeerDtov2 getBeerById(UUID beerId);

  BeerDtov2 saveNewBeer(BeerDtov2 beerDtov2);

  void updateBeer(UUID id, BeerDtov2 beerDto);

  void deleteById(UUID beerId);
}
