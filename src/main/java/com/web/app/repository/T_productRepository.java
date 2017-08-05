package com.web.app.repository;

import com.web.app.domain.T_product;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the T_product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface T_productRepository extends JpaRepository<T_product,Long> {
    
}
