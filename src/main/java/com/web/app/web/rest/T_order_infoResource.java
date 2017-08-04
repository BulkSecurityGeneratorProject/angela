package com.web.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.web.app.domain.T_order_info;

import com.web.app.repository.T_order_infoRepository;
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
 * REST controller for managing T_order_info.
 */
@RestController
@RequestMapping("/api")
public class T_order_infoResource {

    private final Logger log = LoggerFactory.getLogger(T_order_infoResource.class);

    private static final String ENTITY_NAME = "t_order_info";

    private final T_order_infoRepository t_order_infoRepository;

    public T_order_infoResource(T_order_infoRepository t_order_infoRepository) {
        this.t_order_infoRepository = t_order_infoRepository;
    }

    /**
     * POST  /t-order-infos : Create a new t_order_info.
     *
     * @param t_order_info the t_order_info to create
     * @return the ResponseEntity with status 201 (Created) and with body the new t_order_info, or with status 400 (Bad Request) if the t_order_info has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-order-infos")
    @Timed
    public ResponseEntity<T_order_info> createT_order_info(@RequestBody T_order_info t_order_info) throws URISyntaxException {
        log.debug("REST request to save T_order_info : {}", t_order_info);
        if (t_order_info.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new t_order_info cannot already have an ID")).body(null);
        }
        T_order_info result = t_order_infoRepository.save(t_order_info);
        return ResponseEntity.created(new URI("/api/t-order-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-order-infos : Updates an existing t_order_info.
     *
     * @param t_order_info the t_order_info to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated t_order_info,
     * or with status 400 (Bad Request) if the t_order_info is not valid,
     * or with status 500 (Internal Server Error) if the t_order_info couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-order-infos")
    @Timed
    public ResponseEntity<T_order_info> updateT_order_info(@RequestBody T_order_info t_order_info) throws URISyntaxException {
        log.debug("REST request to update T_order_info : {}", t_order_info);
        if (t_order_info.getId() == null) {
            return createT_order_info(t_order_info);
        }
        T_order_info result = t_order_infoRepository.save(t_order_info);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, t_order_info.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-order-infos : get all the t_order_infos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of t_order_infos in body
     */
    @GetMapping("/t-order-infos")
    @Timed
    public List<T_order_info> getAllT_order_infos() {
        log.debug("REST request to get all T_order_infos");
        return t_order_infoRepository.findAll();
    }

    /**
     * GET  /t-order-infos/:id : get the "id" t_order_info.
     *
     * @param id the id of the t_order_info to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the t_order_info, or with status 404 (Not Found)
     */
    @GetMapping("/t-order-infos/{id}")
    @Timed
    public ResponseEntity<T_order_info> getT_order_info(@PathVariable Long id) {
        log.debug("REST request to get T_order_info : {}", id);
        T_order_info t_order_info = t_order_infoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(t_order_info));
    }

    /**
     * DELETE  /t-order-infos/:id : delete the "id" t_order_info.
     *
     * @param id the id of the t_order_info to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-order-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteT_order_info(@PathVariable Long id) {
        log.debug("REST request to delete T_order_info : {}", id);
        t_order_infoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
