package vttp.batch4.csf.ecommerce.repositories;

public class Queries {
    public static final String SQL_INSERT_PURCHASE_ORDER = """
        insert into orders (orderId, orderDate, name, address, priority, comments) 
        values (?, now(), ?, ?, ?, ?)
   """;


   public static final String SQL_INSERT_CART_ITEMS = """
        insert into cart_items (productId, name, quantity, price, orderId) 
        values (?, ?, ?, ?, ?)
   """;
}