package com.food.ordering.system.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record CustomerId(UUID id) {
  // compact constructor
  public CustomerId {
    id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
  }
}
