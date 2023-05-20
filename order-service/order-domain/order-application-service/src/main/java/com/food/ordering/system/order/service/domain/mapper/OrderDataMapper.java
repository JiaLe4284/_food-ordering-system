package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.entity.Order;
import com.food.ordering.system.order.service.domain.entity.OrderItem;
import com.food.ordering.system.order.service.domain.entity.Product;
import com.food.ordering.system.order.service.domain.entity.Restaurant;
import com.food.ordering.system.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {
  public Restaurant toRestaurant(CreateOrderCommand createOrderCommand) {
    return Restaurant.builder()
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
        .products(
            createOrderCommand.getItems().stream().collect(Collectors.toMap(
                oi -> new ProductId(oi.getProductId()),
                oi -> new Product(new ProductId(oi.getProductId())),
                (existing, replacement) -> existing,
                HashMap::new
            )))
        .build();
  }

  public Order toOrder(CreateOrderCommand createOrderCommand) {
    return Order.builder()
        .customerId(new CustomerId(createOrderCommand.getCustomerId()))
        .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
        .deliverAddress(toStreetAddress(createOrderCommand.getAddress()))
        .price(new Money(createOrderCommand.getPrice()))
        .items(toOrderItemEntities(createOrderCommand.getItems()))
        .build();
  }

  public CreateOrderResponse toCreateOrderResponse(Order order) {
    return CreateOrderResponse.builder()
        .orderTrackingId(order.getTrackingId().id())
        .orderStatus(order.getOrderStatus())
        .build();
  }

  private StreetAddress toStreetAddress(OrderAddress orderAddress) {
    return new StreetAddress(
        UUID.randomUUID(),
        orderAddress.getStreet(),
        orderAddress.getPostalCode(),
        orderAddress.getCity());
  }

  private List<OrderItem> toOrderItemEntities(
      List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> orderItems) {
    return orderItems
        .stream()
        .map(item -> OrderItem.builder()
            .product(new Product(new ProductId(item.getProductId())))
            .price(new Money(item.getPrice()))
            .quantity(item.getQuantity())
            .subTotal(new Money(item.getSubTotal()))
            .build())
        .collect(Collectors.toList());
  }
}
