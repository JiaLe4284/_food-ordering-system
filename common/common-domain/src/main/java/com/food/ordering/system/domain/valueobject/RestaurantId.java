package com.food.ordering.system.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public record RestaurantId(UUID id) {
  // compact constructor
  public RestaurantId {
    id = Objects.requireNonNullElseGet(id, UUID::randomUUID);
  }
}
