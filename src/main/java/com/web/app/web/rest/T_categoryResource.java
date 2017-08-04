package com.web.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.web.app.domain.T_category;

import com.web.app.repository.T_categoryRepository;
import com.web.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing T_category.
 */
@RestController
@RequestMapping("/api")
public class T_categoryResource {

    private final Logger log = LoggerFactory.getLogger(T_categoryResource.class);

    private static final String ENTITY_NAME = "t_category";

    private final T_categoryRepository t_categoryRepository;

    public T_categoryResource(T_categoryRepository t_categoryRepository) {
        this.t_categoryRepository = t_categoryRepository;
    }

    /**
     * POST  /t-categories : Create a new t_category.
     *
     * @param t_category the t_category to create
     * @return the ResponseEntity with status 201 (Created) and with body the new t_category, or with status 400 (Bad Request) if the t_category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-categories")
    @Timed
    public ResponseEntity<T_category> createT_category(@RequestBody T_category t_category) throws URISyntaxException {
        log.debug("REST request to save T_category : {}", t_category);
        if (t_category.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new t_category cannot already have an ID")).body(null);
        }
        T_category result = t_categoryRepository.save(t_category);
        return ResponseEntity.created(new URI("/api/t-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-categories : Updates an existing t_category.
     *
     * @param t_category the t_category to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated t_category,
     * or with status 400 (Bad Request) if the t_category is not valid,
     * or with status 500 (Internal Server Error) if the t_category couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-categories")
    @Timed
    public ResponseEntity<T_category> updateT_category(@RequestBody T_category t_category) throws URISyntaxException {
        log.debug("REST request to update T_category : {}", t_category);
        if (t_category.getId() == null) {
            return createT_category(t_category);
        }
        T_category result = t_categoryRepository.save(t_category);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, t_category.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-categories : get all the t_categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of t_categories in body
     */
    @GetMapping("/t-categories")
    @Timed
    public List<T_category> getAllT_categories() {
        log.debug("REST request to get all T_categories");
        return t_categoryRepository.findAll();
    }

    /**
     * GET  /t-categories/:id : get the "id" t_category.
     *
     * @param id the id of the t_category to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the t_category, or with status 404 (Not Found)
     */
    @GetMapping("/t-categories/{id}")
    @Timed
    public ResponseEntity<T_category> getT_category(@PathVariable Long id) {
        log.debug("REST request to get T_category : {}", id);
        T_category t_category = t_categoryRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(t_category));
    }

    /**
     * DELETE  /t-categories/:id : delete the "id" t_category.
     *
     * @param id the id of the t_category to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteT_category(@PathVariable Long id) {
        log.debug("REST request to delete T_category : {}", id);
        t_categoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
