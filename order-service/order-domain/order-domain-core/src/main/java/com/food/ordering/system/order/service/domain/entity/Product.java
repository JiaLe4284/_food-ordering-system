package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.BaseEntity;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
  private String name;
  private Money price;

  public Product(ProductId id) {
    super.setId(id);
  }

  public Product(ProductId id, String name, Money money) {
    super.setId(id);
    this.name = name;
    this.price = money;
  }

  public String name() {
    return name;
  }

  public Money price() {
    return price;
  }

  public void updateWithConfirmedNameAndPrice(String name, Money price) {
    this.name = name;
    this.price = price;
  }
}
