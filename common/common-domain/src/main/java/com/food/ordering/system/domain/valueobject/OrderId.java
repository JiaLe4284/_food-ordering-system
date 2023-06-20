package com.food.ordering.system.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record OrderId(UUID id) {
  // compact constructor
  public OrderId {
    id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
  }
}
