package com.web.app.repository;

import com.web.app.domain.T_dictionary;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the T_dictionary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface T_dictionaryRepository extends JpaRepository<T_dictionary,Long> {
    
}
