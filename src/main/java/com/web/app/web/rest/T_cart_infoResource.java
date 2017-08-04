package com.web.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.web.app.domain.T_cart_info;

import com.web.app.repository.T_cart_infoRepository;
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
 * REST controller for managing T_cart_info.
 */
@RestController
@RequestMapping("/api")
public class T_cart_infoResource {

    private final Logger log = LoggerFactory.getLogger(T_cart_infoResource.class);

    private static final String ENTITY_NAME = "t_cart_info";

    private final T_cart_infoRepository t_cart_infoRepository;

    public T_cart_infoResource(T_cart_infoRepository t_cart_infoRepository) {
        this.t_cart_infoRepository = t_cart_infoRepository;
    }

    /**
     * POST  /t-cart-infos : Create a new t_cart_info.
     *
     * @param t_cart_info the t_cart_info to create
     * @return the ResponseEntity with status 201 (Created) and with body the new t_cart_info, or with status 400 (Bad Request) if the t_cart_info has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-cart-infos")
    @Timed
    public ResponseEntity<T_cart_info> createT_cart_info(@RequestBody T_cart_info t_cart_info) throws URISyntaxException {
        log.debug("REST request to save T_cart_info : {}", t_cart_info);
        if (t_cart_info.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new t_cart_info cannot already have an ID")).body(null);
        }
        T_cart_info result = t_cart_infoRepository.save(t_cart_info);
        return ResponseEntity.created(new URI("/api/t-cart-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-cart-infos : Updates an existing t_cart_info.
     *
     * @param t_cart_info the t_cart_info to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated t_cart_info,
     * or with status 400 (Bad Request) if the t_cart_info is not valid,
     * or with status 500 (Internal Server Error) if the t_cart_info couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-cart-infos")
    @Timed
    public ResponseEntity<T_cart_info> updateT_cart_info(@RequestBody T_cart_info t_cart_info) throws URISyntaxException {
        log.debug("REST request to update T_cart_info : {}", t_cart_info);
        if (t_cart_info.getId() == null) {
            return createT_cart_info(t_cart_info);
        }
        T_cart_info result = t_cart_infoRepository.save(t_cart_info);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, t_cart_info.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-cart-infos : get all the t_cart_infos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of t_cart_infos in body
     */
    @GetMapping("/t-cart-infos")
    @Timed
    public List<T_cart_info> getAllT_cart_infos() {
        log.debug("REST request to get all T_cart_infos");
        return t_cart_infoRepository.findAll();
    }

    /**
     * GET  /t-cart-infos/:id : get the "id" t_cart_info.
     *
     * @param id the id of the t_cart_info to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the t_cart_info, or with status 404 (Not Found)
     */
    @GetMapping("/t-cart-infos/{id}")
    @Timed
    public ResponseEntity<T_cart_info> getT_cart_info(@PathVariable Long id) {
        log.debug("REST request to get T_cart_info : {}", id);
        T_cart_info t_cart_info = t_cart_infoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(t_cart_info));
    }

    /**
     * DELETE  /t-cart-infos/:id : delete the "id" t_cart_info.
     *
     * @param id the id of the t_cart_info to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-cart-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteT_cart_info(@PathVariable Long id) {
        log.debug("REST request to delete T_cart_info : {}", id);
        t_cart_infoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
