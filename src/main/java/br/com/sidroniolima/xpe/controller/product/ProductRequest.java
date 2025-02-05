package br.com.sidroniolima.xpe.controller.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductRequest(
    @JsonProperty("description") String description,
    @JsonProperty("is_active") Boolean active,
    @JsonProperty("price") Double price
){
}
