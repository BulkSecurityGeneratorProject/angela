package com.web.app.repository;

import com.web.app.domain.T_pictures;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the T_pictures entity.
 */
@SuppressWarnings("unused")
@Repository
public interface T_picturesRepository extends JpaRepository<T_pictures,Long> {
    
}
