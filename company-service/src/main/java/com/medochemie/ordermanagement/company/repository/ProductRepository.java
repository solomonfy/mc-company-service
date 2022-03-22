package com.medochemie.ordermanagement.company.repository;

import com.medochemie.ordermanagement.company.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'genericName' : 1, 'formulation' : 1, 'brandName': 1, 'strength':1, 'packSize':1 }")
    List<Product> findAllProductsById(List<String> ids);


    //    returns a product with some fields only
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'genericName' : 1, 'formulation' : 1, 'brandName': 1, 'strength':1, 'packSize':1, 'unitPrice': 1 }")
    Product findProductById(String id);

    @Query(value = "{ '_id' : {'$in' : ?0 } }", fields = "{'active': 0, 'productionSites': 0 , 'createdOn': 0 , 'createdBy': 0 }")
    Iterable<Product> findProductsByIds(Iterable<String> ids);

}
