package com.food.ordering.system.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ProductId(UUID id) {  // compact constructor
  public ProductId {
    id = Objects.requireNonNullElseGet(id,  UUID::randomUUID);
  }
}