package com.web.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.web.app.domain.T_pictures;

import com.web.app.repository.T_picturesRepository;
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
 * REST controller for managing T_pictures.
 */
@RestController
@RequestMapping("/api")
public class T_picturesResource {

    private final Logger log = LoggerFactory.getLogger(T_picturesResource.class);

    private static final String ENTITY_NAME = "t_pictures";

    private final T_picturesRepository t_picturesRepository;

    public T_picturesResource(T_picturesRepository t_picturesRepository) {
        this.t_picturesRepository = t_picturesRepository;
    }

    /**
     * POST  /t-pictures : Create a new t_pictures.
     *
     * @param t_pictures the t_pictures to create
     * @return the ResponseEntity with status 201 (Created) and with body the new t_pictures, or with status 400 (Bad Request) if the t_pictures has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-pictures")
    @Timed
    public ResponseEntity<T_pictures> createT_pictures(@RequestBody T_pictures t_pictures) throws URISyntaxException {
        log.debug("REST request to save T_pictures : {}", t_pictures);
        if (t_pictures.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new t_pictures cannot already have an ID")).body(null);
        }
        T_pictures result = t_picturesRepository.save(t_pictures);
        return ResponseEntity.created(new URI("/api/t-pictures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-pictures : Updates an existing t_pictures.
     *
     * @param t_pictures the t_pictures to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated t_pictures,
     * or with status 400 (Bad Request) if the t_pictures is not valid,
     * or with status 500 (Internal Server Error) if the t_pictures couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-pictures")
    @Timed
    public ResponseEntity<T_pictures> updateT_pictures(@RequestBody T_pictures t_pictures) throws URISyntaxException {
        log.debug("REST request to update T_pictures : {}", t_pictures);
        if (t_pictures.getId() == null) {
            return createT_pictures(t_pictures);
        }
        T_pictures result = t_picturesRepository.save(t_pictures);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, t_pictures.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-pictures : get all the t_pictures.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of t_pictures in body
     */
    @GetMapping("/t-pictures")
    @Timed
    public List<T_pictures> getAllT_pictures() {
        log.debug("REST request to get all T_pictures");
        return t_picturesRepository.findAll();
    }

    /**
     * GET  /t-pictures/:id : get the "id" t_pictures.
     *
     * @param id the id of the t_pictures to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the t_pictures, or with status 404 (Not Found)
     */
    @GetMapping("/t-pictures/{id}")
    @Timed
    public ResponseEntity<T_pictures> getT_pictures(@PathVariable Long id) {
        log.debug("REST request to get T_pictures : {}", id);
        T_pictures t_pictures = t_picturesRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(t_pictures));
    }

    /**
     * DELETE  /t-pictures/:id : delete the "id" t_pictures.
     *
     * @param id the id of the t_pictures to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-pictures/{id}")
    @Timed
    public ResponseEntity<Void> deleteT_pictures(@PathVariable Long id) {
        log.debug("REST request to delete T_pictures : {}", id);
        t_picturesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
