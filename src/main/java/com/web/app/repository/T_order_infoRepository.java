package com.web.app.repository;

import com.web.app.domain.T_order_info;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the T_order_info entity.
 */
@SuppressWarnings("unused")
@Repository
public interface T_order_infoRepository extends JpaRepository<T_order_info,Long> {
    
}
