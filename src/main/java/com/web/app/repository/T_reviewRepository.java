package com.web.app.repository;

import com.web.app.domain.T_review;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the T_review entity.
 */
@SuppressWarnings("unused")
@Repository
public interface T_reviewRepository extends JpaRepository<T_review,Long> {
    
}
