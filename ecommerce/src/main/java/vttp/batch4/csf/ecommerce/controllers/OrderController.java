package vttp.batch4.csf.ecommerce.controllers;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vttp.batch4.csf.ecommerce.models.LineItem;
import vttp.batch4.csf.ecommerce.models.Order;
import vttp.batch4.csf.ecommerce.services.PurchaseOrderService;

@Controller
@RequestMapping("/api")
public class OrderController {

  @Autowired
  private PurchaseOrderService poSvc;

  // IMPORTANT: DO NOT MODIFY THIS METHOD.
  // If this method is changed, any assessment task relying on this method will
  // not be marked
  @PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
  @ResponseBody
  @CrossOrigin
  public ResponseEntity<String> postOrder(@RequestBody String jsonBody) {

    // TODO Task 3
    System.out.println(jsonBody);

    try (JsonReader jReader = Json.createReader(new StringReader(jsonBody))) {
      JsonObject jsonObject = jReader.readObject();

        Order order = new Order();
        order.setName(jsonObject.getString("name"));
        order.setAddress(jsonObject.getString("address"));
        order.setPriority(jsonObject.getBoolean("priority"));
        order.setComments(jsonObject.getString("comments"));

        JsonObject cartObject = jsonObject.getJsonObject("cart");
        cartObject.getJsonArray("lineItems").forEach(item -> {
            JsonObject lineItemObject = (JsonObject) item;
            LineItem lineItem = new LineItem();
            lineItem.setProductId(lineItemObject.getString("prodId"));
            lineItem.setName(lineItemObject.getString("name"));
            lineItem.setQuantity(lineItemObject.getInt("quantity"));
            lineItem.setPrice((float) lineItemObject.getJsonNumber("price").doubleValue());

            order.getCart().addLineItem(lineItem);
        });

        // System.out.println("--------------------------------------------------------------- " + order);
        poSvc.createNewPurchaseOrder(order);

        JsonObject okResponse = Json.createObjectBuilder()
            .add("orderId", order.getOrderId())
            .build();

        return ResponseEntity.ok(okResponse.toString());
      
    } catch (Exception e) {
      JsonObject badResponse = Json.createObjectBuilder()
            .add("message", "Order failed")
            .build();
      ResponseEntity.badRequest().body(badResponse.toString());
    }
    
	 
	 return null;
  }
}