package org.springframework.web.model.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.model.BeerStyleEnum;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDtov2 {
  private UUID id;
  private String beerName;
  private BeerStyleEnum beerStyle;
  private Long upc;
}