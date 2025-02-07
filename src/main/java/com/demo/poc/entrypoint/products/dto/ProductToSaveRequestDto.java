package com.demo.poc.entrypoint.products.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductToSaveRequestDto implements Serializable {

  private String name;
  private int stock;
  private Double unitPrice;
  private String category;
  private String description;
}
