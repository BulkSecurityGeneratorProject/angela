package com.web.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.web.app.domain.T_product;

import com.web.app.repository.T_productRepository;
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
 * REST controller for managing T_product.
 */
@RestController
@RequestMapping("/api")
public class T_productResource {

    private final Logger log = LoggerFactory.getLogger(T_productResource.class);

    private static final String ENTITY_NAME = "t_product";

    private final T_productRepository t_productRepository;

    public T_productResource(T_productRepository t_productRepository) {
        this.t_productRepository = t_productRepository;
    }

    /**
     * POST  /t-products : Create a new t_product.
     *
     * @param t_product the t_product to create
     * @return the ResponseEntity with status 201 (Created) and with body the new t_product, or with status 400 (Bad Request) if the t_product has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-products")
    @Timed
    public ResponseEntity<T_product> createT_product(@RequestBody T_product t_product) throws URISyntaxException {
        log.debug("REST request to save T_product : {}", t_product);
        if (t_product.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new t_product cannot already have an ID")).body(null);
        }
        T_product result = t_productRepository.save(t_product);
        return ResponseEntity.created(new URI("/api/t-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-products : Updates an existing t_product.
     *
     * @param t_product the t_product to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated t_product,
     * or with status 400 (Bad Request) if the t_product is not valid,
     * or with status 500 (Internal Server Error) if the t_product couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-products")
    @Timed
    public ResponseEntity<T_product> updateT_product(@RequestBody T_product t_product) throws URISyntaxException {
        log.debug("REST request to update T_product : {}", t_product);
        if (t_product.getId() == null) {
            return createT_product(t_product);
        }
        T_product result = t_productRepository.save(t_product);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, t_product.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-products : get all the t_products.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of t_products in body
     */
    @GetMapping("/t-products")
    @Timed
    public List<T_product> getAllT_products() {
        log.debug("REST request to get all T_products");
        return t_productRepository.findAll();
    }

    /**
     * GET  /t-products/:id : get the "id" t_product.
     *
     * @param id the id of the t_product to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the t_product, or with status 404 (Not Found)
     */
    @GetMapping("/t-products/{id}")
    @Timed
    public ResponseEntity<T_product> getT_product(@PathVariable Long id) {
        log.debug("REST request to get T_product : {}", id);
        T_product t_product = t_productRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(t_product));
    }

    /**
     * DELETE  /t-products/:id : delete the "id" t_product.
     *
     * @param id the id of the t_product to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-products/{id}")
    @Timed
    public ResponseEntity<Void> deleteT_product(@PathVariable Long id) {
        log.debug("REST request to delete T_product : {}", id);
        t_productRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
