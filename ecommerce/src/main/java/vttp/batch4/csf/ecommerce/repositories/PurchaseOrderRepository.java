package vttp.batch4.csf.ecommerce.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch4.csf.ecommerce.models.Cart;
import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;

@Repository
public class PurchaseOrderRepository {

  @Autowired
  private JdbcTemplate template;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  // You may only add Exception to the method's signature
  public void create(Order order) {
    // TODO Task 3
    template.update(Queries.SQL_INSERT_PURCHASE_ORDER,
          order.getOrderId(),
          order.getName(),
          order.getAddress(),
          order.getPriority(),
          order.getComments()
          );

    Cart cart = order.getCart();
    
    for (LineItem item : cart.getLineItems()) {
      template.update(Queries.SQL_INSERT_CART_ITEMS,
          item.getProductId(),
          item.getName(),
          item.getQuantity(),
          item.getPrice(),
          order.getOrderId()
          );
    };
  }
}
