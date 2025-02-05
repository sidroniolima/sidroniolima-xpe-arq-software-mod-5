package br.com.sidroniolima.xpe.controller.product;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record ProductResponse(
    @JsonProperty("id") String id,
    @JsonProperty("description") String description,
    @JsonProperty("is_active") Boolean active,
    @JsonProperty("price") Double price,
    @JsonProperty("created_at") Instant createdAt,
    @JsonProperty("updated_at") Instant updatedAt,
    @JsonProperty("deleted_at") Instant deletedAt
){
}
