package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Restaurant extends AggregateRoot<RestaurantId> {
  private final Map<ProductId, Product> products;
  private boolean active;

  private Restaurant(Builder builder) {
    super.setId(builder.restaurantId);
    products = builder.products;
    active = builder.active;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Product getProduct(ProductId productId) {
    return products.get(productId);
  }

  public List<Product> getProducts() {
    return products.entrySet().stream()
        .flatMap(set -> Stream.of(set.getValue()))
        .collect(Collectors.toList());
  }

  public boolean isActive() {
    return active;
  }

  public static final class Builder {
    private RestaurantId restaurantId;
    private Map<ProductId, Product> products;
    private boolean active;

    private Builder() {}

    public static Builder newBuilder() {
      return new Builder();
    }

    public Builder restaurantId(RestaurantId val) {
      restaurantId = val;
      return this;
    }

    public Builder products(Map<ProductId, Product> val) {
      products = val;
      return this;
    }

    public Builder active(boolean val) {
      active = val;
      return this;
    }

    public Restaurant build() {
      return new Restaurant(this);
    }
  }
}
