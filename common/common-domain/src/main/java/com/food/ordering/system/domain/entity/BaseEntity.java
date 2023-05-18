package com.food.ordering.system.domain.entity;

import java.util.Objects;

public abstract class BaseEntity<T> {
  private T id;

  public T id() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }

  public T getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseEntity<?> that = (BaseEntity<?>) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
