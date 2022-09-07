package com.medochemie.ordermanagement.company.controller;

import com.medochemie.ordermanagement.company.entity.Product;
import com.medochemie.ordermanagement.company.entity.Response;
import com.medochemie.ordermanagement.company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static com.google.common.collect.ImmutableMap.of;
import static java.time.LocalDateTime.now;


@Controller
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = {"http://localhost:4200/", "http://localhost:3000/", "http://localhost:3001/"})
public class ProductController {

    private static final Logger log = Logger.getLogger("");

    @Autowired
    private ProductRepository repository;


    @GetMapping("/list")
    public ResponseEntity<Response> getAllProducts() throws InterruptedException {
        log.info("Retrieving all products");
//        TimeUnit.SECONDS.sleep(3);
        List<Product> foundProducts = repository.findAll();
        int productCount = foundProducts.toArray().length;
        return ResponseEntity.ok(
              Response.builder()
                      .timeStamp(now())
                      .message(productCount == 1 ? "One product retrieved" :"(" + productCount + ")"+ " products retrieved!")
                      .status(HttpStatus.OK)
                      .statusCode(HttpStatus.OK.value())
                      .data(of("products", foundProducts))
                      .build()
        );
    }

//    returns a product with some fields only
    @GetMapping("/list/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable("id") String id){
        log.info("Retrieving a product with id " + id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Product retrieved with id " + id)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(of("Product", repository.findProductById(id)))
                        .build()
        );
    }

    @GetMapping("/list/find/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") String id) {
        Product product = repository.findById(id).get();

                log.info("Returning " + product.getBrandName());
                return new ResponseEntity(product, HttpStatus.OK);

    }

    @GetMapping("/list/find/{id}/detail")
    public ResponseEntity<Response> getProductByIdWithSpecificValues(@PathVariable("id") String id){
        log.info("Retrieving product detail for product id " + id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Product retrieved with id " + id)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(of("Product", repository.findById(id)))
                        .build()
        );
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Response> addProduct(@RequestBody Product product){
        log.info("Adding " + product.getBrandName());
        Date creationDate = new Date();
        product.setCreatedOn(creationDate);
        product.setCreatedBy("Solomon");
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message(product.getBrandName() + " "+ product.getStrength()+ product.getFormulation() + " is added!")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .data(of("Product", repository.save(product)))
                        .build()
        );
    }


    @GetMapping("/findAllById/{ids}")
    public List<Product> productsById(@PathVariable List<String> ids) {
        log.info("Returning all products with ids" + ids);
        System.out.println(ids);
        return (List<Product>) repository.findProductsByIds(ids);
    }

//    @GetMapping("/findAllById/{ids}")
//    public ResponseEntity<List<Product>> getAllProductsById(@PathVariable List<String> ids){
//        log.info("Returning all products with ids" + ids);
//
//        List<Product> listOfProducts = new ArrayList<Product>();
//        System.out.println(listOfProducts);
//
//        List<Product> foundProducts = repository.findByIds(ids, Sort.by("id").descending());
//            System.out.println(foundProducts);
//
//        if (foundProducts != null) {
//            listOfProducts.addAll(foundProducts);
//            System.out.println(listOfProducts);
//            return new ResponseEntity(listOfProducts, null, HttpStatus.OK);
//
//        } else {
//            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    };

    @PutMapping("/inactivate_product/{id}")
    public ResponseEntity<Response> inactivateProduct(@PathVariable("id") String id){
        Product foundProduct = repository.findById(id).get();

        try {
            if (foundProduct != null & foundProduct.isActive()) {
                foundProduct.setActive(false);
            }
        } catch(Exception e) {

        }

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message(!foundProduct.isActive() ? "Product is inactivated" : "Product inactivation failed")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(of("Product", repository.save(foundProduct)))
                        .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProductById(@PathVariable("id") String id){
        log.info("Deleting a product with id" + id);
        repository.deleteById(id);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .message("Deleted a product with id " + id)
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .data(of("deleted ", true))
                        .build()
        );
    }

}
