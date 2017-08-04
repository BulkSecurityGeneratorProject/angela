package com.web.app.repository;

import com.web.app.domain.T_case_info;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the T_case_info entity.
 */
@SuppressWarnings("unused")
@Repository
public interface T_case_infoRepository extends JpaRepository<T_case_info,Long> {
    
}
