package org.springframework.services;

import org.springframework.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
  BeerDto getBeerById(UUID beerId);

  BeerDto saveNewBeer(BeerDto beerDto);

  void updateBeer(UUID id, BeerDto beerDto);

  void deleteById(UUID beerId);
}
