package org.springframework.web.controller.v2;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.services.v2.BeerServicev2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.model.v2.BeerDtov2;

import java.util.UUID;
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerv2 {

  private final BeerServicev2 beerService;

  public BeerControllerv2(BeerServicev2 beerService) {
    this.beerService = beerService;
  }

  @GetMapping("/{beerId}")
  public ResponseEntity<BeerDtov2> getBeer(@PathVariable("beerId") UUID beerId){
    return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BeerDtov2> handlePost(@RequestBody BeerDtov2 beerDto){
    BeerDtov2 savedBeer = beerService.saveNewBeer(beerDto);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("location", "/api/v1/beer" + beerDto.getId().toString());
    return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
  }
  @PutMapping("/{beerId}")
  public ResponseEntity handleUpdate(@PathVariable("beerId") UUID id,@RequestBody BeerDtov2 beerDto){
    beerService.updateBeer(id, beerDto);
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{beerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBeer(@PathVariable("beerId") UUID beerId){
    beerService.deleteById(beerId);
  }
}
