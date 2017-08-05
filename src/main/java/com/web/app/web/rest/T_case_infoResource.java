package com.web.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.web.app.domain.T_case_info;

import com.web.app.repository.T_case_infoRepository;
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
 * REST controller for managing T_case_info.
 */
@RestController
@RequestMapping("/api")
public class T_case_infoResource {

    private final Logger log = LoggerFactory.getLogger(T_case_infoResource.class);

    private static final String ENTITY_NAME = "t_case_info";

    private final T_case_infoRepository t_case_infoRepository;

    public T_case_infoResource(T_case_infoRepository t_case_infoRepository) {
        this.t_case_infoRepository = t_case_infoRepository;
    }

    /**
     * POST  /t-case-infos : Create a new t_case_info.
     *
     * @param t_case_info the t_case_info to create
     * @return the ResponseEntity with status 201 (Created) and with body the new t_case_info, or with status 400 (Bad Request) if the t_case_info has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-case-infos")
    @Timed
    public ResponseEntity<T_case_info> createT_case_info(@RequestBody T_case_info t_case_info) throws URISyntaxException {
        log.debug("REST request to save T_case_info : {}", t_case_info);
        if (t_case_info.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new t_case_info cannot already have an ID")).body(null);
        }
        T_case_info result = t_case_infoRepository.save(t_case_info);
        return ResponseEntity.created(new URI("/api/t-case-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-case-infos : Updates an existing t_case_info.
     *
     * @param t_case_info the t_case_info to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated t_case_info,
     * or with status 400 (Bad Request) if the t_case_info is not valid,
     * or with status 500 (Internal Server Error) if the t_case_info couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-case-infos")
    @Timed
    public ResponseEntity<T_case_info> updateT_case_info(@RequestBody T_case_info t_case_info) throws URISyntaxException {
        log.debug("REST request to update T_case_info : {}", t_case_info);
        if (t_case_info.getId() == null) {
            return createT_case_info(t_case_info);
        }
        T_case_info result = t_case_infoRepository.save(t_case_info);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, t_case_info.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-case-infos : get all the t_case_infos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of t_case_infos in body
     */
    @GetMapping("/t-case-infos")
    @Timed
    public List<T_case_info> getAllT_case_infos() {
        log.debug("REST request to get all T_case_infos");
        return t_case_infoRepository.findAll();
    }

    /**
     * GET  /t-case-infos/:id : get the "id" t_case_info.
     *
     * @param id the id of the t_case_info to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the t_case_info, or with status 404 (Not Found)
     */
    @GetMapping("/t-case-infos/{id}")
    @Timed
    public ResponseEntity<T_case_info> getT_case_info(@PathVariable Long id) {
        log.debug("REST request to get T_case_info : {}", id);
        T_case_info t_case_info = t_case_infoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(t_case_info));
    }

    /**
     * DELETE  /t-case-infos/:id : delete the "id" t_case_info.
     *
     * @param id the id of the t_case_info to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-case-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteT_case_info(@PathVariable Long id) {
        log.debug("REST request to delete T_case_info : {}", id);
        t_case_infoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
