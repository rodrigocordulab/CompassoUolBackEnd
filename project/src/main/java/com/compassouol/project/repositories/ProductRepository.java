package com.compassouol.project.repositories;

import com.compassouol.project.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select * from TB_PRODUCT pr where (:query is null or (pr.name = :query or pr.description = :query)) " +
            "and (:minPrice is null or pr.price >= :minPrice) and  (:maxPrice is null or pr.price <= :maxPrice)",
            nativeQuery = true)
    List<Product> getBySearchQuery(@Param("query") String query,
                                   @Param("minPrice") Double minPrice,
                                   @Param("maxPrice") Double maxPrice);
}
