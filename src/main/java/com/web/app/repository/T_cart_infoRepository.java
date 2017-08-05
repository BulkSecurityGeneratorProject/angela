package com.web.app.repository;

import com.web.app.domain.T_cart_info;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the T_cart_info entity.
 */
@SuppressWarnings("unused")
@Repository
public interface T_cart_infoRepository extends JpaRepository<T_cart_info,Long> {
    
}
