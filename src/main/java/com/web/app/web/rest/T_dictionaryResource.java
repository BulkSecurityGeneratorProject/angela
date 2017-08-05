package com.web.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.web.app.domain.T_dictionary;

import com.web.app.repository.T_dictionaryRepository;
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
 * REST controller for managing T_dictionary.
 */
@RestController
@RequestMapping("/api")
public class T_dictionaryResource {

    private final Logger log = LoggerFactory.getLogger(T_dictionaryResource.class);

    private static final String ENTITY_NAME = "t_dictionary";

    private final T_dictionaryRepository t_dictionaryRepository;

    public T_dictionaryResource(T_dictionaryRepository t_dictionaryRepository) {
        this.t_dictionaryRepository = t_dictionaryRepository;
    }

    /**
     * POST  /t-dictionaries : Create a new t_dictionary.
     *
     * @param t_dictionary the t_dictionary to create
     * @return the ResponseEntity with status 201 (Created) and with body the new t_dictionary, or with status 400 (Bad Request) if the t_dictionary has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-dictionaries")
    @Timed
    public ResponseEntity<T_dictionary> createT_dictionary(@RequestBody T_dictionary t_dictionary) throws URISyntaxException {
        log.debug("REST request to save T_dictionary : {}", t_dictionary);
        if (t_dictionary.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new t_dictionary cannot already have an ID")).body(null);
        }
        T_dictionary result = t_dictionaryRepository.save(t_dictionary);
        return ResponseEntity.created(new URI("/api/t-dictionaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-dictionaries : Updates an existing t_dictionary.
     *
     * @param t_dictionary the t_dictionary to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated t_dictionary,
     * or with status 400 (Bad Request) if the t_dictionary is not valid,
     * or with status 500 (Internal Server Error) if the t_dictionary couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-dictionaries")
    @Timed
    public ResponseEntity<T_dictionary> updateT_dictionary(@RequestBody T_dictionary t_dictionary) throws URISyntaxException {
        log.debug("REST request to update T_dictionary : {}", t_dictionary);
        if (t_dictionary.getId() == null) {
            return createT_dictionary(t_dictionary);
        }
        T_dictionary result = t_dictionaryRepository.save(t_dictionary);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, t_dictionary.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-dictionaries : get all the t_dictionaries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of t_dictionaries in body
     */
    @GetMapping("/t-dictionaries")
    @Timed
    public List<T_dictionary> getAllT_dictionaries() {
        log.debug("REST request to get all T_dictionaries");
        return t_dictionaryRepository.findAll();
    }

    /**
     * GET  /t-dictionaries/:id : get the "id" t_dictionary.
     *
     * @param id the id of the t_dictionary to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the t_dictionary, or with status 404 (Not Found)
     */
    @GetMapping("/t-dictionaries/{id}")
    @Timed
    public ResponseEntity<T_dictionary> getT_dictionary(@PathVariable Long id) {
        log.debug("REST request to get T_dictionary : {}", id);
        T_dictionary t_dictionary = t_dictionaryRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(t_dictionary));
    }

    /**
     * DELETE  /t-dictionaries/:id : delete the "id" t_dictionary.
     *
     * @param id the id of the t_dictionary to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-dictionaries/{id}")
    @Timed
    public ResponseEntity<Void> deleteT_dictionary(@PathVariable Long id) {
        log.debug("REST request to delete T_dictionary : {}", id);
        t_dictionaryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
