package com.web.app.repository;

import com.web.app.domain.T_category;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the T_category entity.
 */
@SuppressWarnings("unused")
@Repository
public interface T_categoryRepository extends JpaRepository<T_category,Long> {
    
}
