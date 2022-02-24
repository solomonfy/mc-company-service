package com.medochemie.ordermanagement.OrderService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medochemie.ordermanagement.OrderService.VO.Product;
import com.medochemie.ordermanagement.OrderService.VO.ProductIdsWithQuantity;
import com.medochemie.ordermanagement.OrderService.entity.Order;
import com.medochemie.ordermanagement.OrderService.entity.Response;
import com.medochemie.ordermanagement.OrderService.enums.Status;
import com.medochemie.ordermanagement.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;

import static com.google.common.collect.ImmutableMap.of;
import static java.time.LocalDateTime.now;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost:3000/"})
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    private final static Logger LOGGER = Logger.getLogger("");
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private OrderRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/list")
    public ResponseEntity<Response> getOrders(){
        LOGGER.info("Return all orders");
        Map<String, List<Order>> data = new HashMap<>();
        data.put("orders", repository.findAll());
        try{
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .message("All orders retrieved")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .data(data)
                            .build()
            );
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Response> getOrder(@PathVariable String id){
        LOGGER.info("Returning an order with an id " + id);
        Map<String, Optional<Order>> data = new HashMap<>();
        data.put("order", repository.findById(id));
        try{
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .message("Returning an order with an id " + id)
                            .data(data)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message(e.getMessage())
                            .data(of())
                            .build()
            );
        }
    }

    @GetMapping("/list/{id}/products")
    public ResponseEntity<Response> returnCustomResponse(@PathVariable String id){
        LOGGER.info("Inside returnCustomResponse method of OrderController, found an order of id " + id);
        Optional<Order> optionalEntity = repository.findById(id);
        Order order = optionalEntity.get();

        List<Product> productList = new ArrayList();
        List<ProductIdsWithQuantity> productIdsWithQuantities = order.getProductIdsWithQuantities();

        Double total = 0D;
        order.setAmount(total);
        try{
            if (productIdsWithQuantities.toArray().length > 0 ) {
                for (ProductIdsWithQuantity productIdWithQty : productIdsWithQuantities) {
//                System.out.println(productIdWithQty.getProductId());
                    String productId = productIdWithQty.getProductId();
                    Integer productQty = productIdWithQty.getQuantity();
                    Response response = restTemplate.getForObject("http://MC-COMPANY-SERVICE/products/list/" + productId, Response.class);
                    Product product = mapper.convertValue(response.getData().values().toArray()[0], Product.class);
                    total += product.getUnitPrice() * productQty;
                    product.setQuantity(productQty);
                    productList.add(product);
                }
                return ResponseEntity.ok(
                        Response.builder()
                                .timeStamp(now())
                                .message("List of products in the order id " + order.getId())
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .data(of("products", productList))
                                .build()
                );
            }
            else{
                return ResponseEntity.ok(
                        Response.builder()
                                .timeStamp(now())
                                .status(HttpStatus.BAD_REQUEST)
                                .statusCode(HttpStatus.BAD_REQUEST.value())
                                .message("No product found in the order!")
                                .data(of())
                                .build()
                );
            }
        }
        catch (Exception e){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .status(HttpStatus.BAD_REQUEST)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(e.getMessage().toString())
                        .data(of())
                        .build()
        );
        }
    }


    @PostMapping("/createOrder")
    public ResponseEntity<Response> createOrder(@RequestBody Order order){


        Double total = 0D;

        Date today = new Date();

        String countryCode = "ET";
        String agentName = firstTwoChars(order.getAgent().getAgentName());
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);



        if(order.getProductIdsWithQuantities().toArray().length > 0) {
            LOGGER.info("Adding a new order for " + order.getAgent().getAgentName());
            List<ProductIdsWithQuantity> productIdsWithQuantity = order.getProductIdsWithQuantities();

            for (ProductIdsWithQuantity productIdWithQty : productIdsWithQuantity) {
                String productId = productIdWithQty.getProductId();
                Integer productQty = productIdWithQty.getQuantity();

                Response response = restTemplate.getForObject("http://MC-COMPANY-SERVICE/products/list/" + productId, Response.class);
                Product product = mapper.convertValue(response.getData().values().toArray()[0], Product.class);
                total += product.getUnitPrice() * productQty;
            }

            order.setOrderNumber(countryCode + "/" + agentName +"/" + currentYear);
            order.setStatus(Status.Draft);
            order.setCreatedOn(today);
            order.setAmount(total);
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .message("New order has been created, with id " + order.getId())
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .data(of("order", repository.insert(order)))
                            .build()
            );
        }
        else{
            LOGGER.info("Order can't be created!");
            return ResponseEntity.ok(
                    Response.builder()
                            .timeStamp(now())
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("Order can't be created!")
                            .data(of())
                            .build()
            );
        }
    }


    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") String id, @RequestBody Order order){
        LOGGER.info("Updating an order with id " + order.getId());
        Optional<Order> foundOrder = repository.findById(id);
        if (foundOrder.isPresent()){
            Order updatedOrder = foundOrder.get();
            updatedOrder.setAmount(order.getAmount());
            updatedOrder.setProductIdsWithQuantities(order.getProductIdsWithQuantities());
            updatedOrder.setShipment(order.getShipment());
            updatedOrder.setCreatedOn(order.getCreatedOn());
            return new ResponseEntity(repository.save(order), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ("/{id}")
    public String deleteOrder(@PathVariable String id){
        LOGGER.info("Order number " + id + " has been deleted!");
        repository.deleteById(id);
        return "Order number " + id + " has been deleted!";
    }

    @GetMapping("/page")
    public Map<String, Object> getAllOrdersInPage(

            @RequestParam(name = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy
    ){
        LOGGER.info("Returning in pages");
        return repository.getAllOrdersInPage(pageNo, pageSize, sortBy);
    }


//    @PutMapping("/{update/{id}")
//    public ResponseEntity<?> updateOrder(@RequestBody Order order){
//        try {
//            Order updatedOrder = repository.updateOrder(order);
//            return new ResponseEntity<>(updatedOrder, null, HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @GetMapping(value = "/getAllOrdersByIdList")
//    public ResponseEntity<List<Order>> getAllOrderByOrderIdList(@RequestParam("orderIdList") List<String> orderIdList) {
//        try {
//            List<Order> orderListToBeReturned = new ArrayList<Order>();
//            List<Order> fetchedOrderList = repository.getOrderListByIdList(orderIdList);
//            if(fetchedOrderList.size() > 0) {
//                orderListToBeReturned.addAll(fetchedOrderList);
//            }
//            return new ResponseEntity(orderListToBeReturned, null, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
//        }
//    }

    public String firstTwoChars(String str) {
        return str.length() < 2 ? str : str.substring(0, 2);
    }

}
