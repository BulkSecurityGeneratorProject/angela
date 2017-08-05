package com.web.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.web.app.domain.T_review;

import com.web.app.repository.T_reviewRepository;
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
 * REST controller for managing T_review.
 */
@RestController
@RequestMapping("/api")
public class T_reviewResource {

    private final Logger log = LoggerFactory.getLogger(T_reviewResource.class);

    private static final String ENTITY_NAME = "t_review";

    private final T_reviewRepository t_reviewRepository;

    public T_reviewResource(T_reviewRepository t_reviewRepository) {
        this.t_reviewRepository = t_reviewRepository;
    }

    /**
     * POST  /t-reviews : Create a new t_review.
     *
     * @param t_review the t_review to create
     * @return the ResponseEntity with status 201 (Created) and with body the new t_review, or with status 400 (Bad Request) if the t_review has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/t-reviews")
    @Timed
    public ResponseEntity<T_review> createT_review(@RequestBody T_review t_review) throws URISyntaxException {
        log.debug("REST request to save T_review : {}", t_review);
        if (t_review.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new t_review cannot already have an ID")).body(null);
        }
        T_review result = t_reviewRepository.save(t_review);
        return ResponseEntity.created(new URI("/api/t-reviews/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /t-reviews : Updates an existing t_review.
     *
     * @param t_review the t_review to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated t_review,
     * or with status 400 (Bad Request) if the t_review is not valid,
     * or with status 500 (Internal Server Error) if the t_review couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/t-reviews")
    @Timed
    public ResponseEntity<T_review> updateT_review(@RequestBody T_review t_review) throws URISyntaxException {
        log.debug("REST request to update T_review : {}", t_review);
        if (t_review.getId() == null) {
            return createT_review(t_review);
        }
        T_review result = t_reviewRepository.save(t_review);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, t_review.getId().toString()))
            .body(result);
    }

    /**
     * GET  /t-reviews : get all the t_reviews.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of t_reviews in body
     */
    @GetMapping("/t-reviews")
    @Timed
    public List<T_review> getAllT_reviews() {
        log.debug("REST request to get all T_reviews");
        return t_reviewRepository.findAll();
    }

    /**
     * GET  /t-reviews/:id : get the "id" t_review.
     *
     * @param id the id of the t_review to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the t_review, or with status 404 (Not Found)
     */
    @GetMapping("/t-reviews/{id}")
    @Timed
    public ResponseEntity<T_review> getT_review(@PathVariable Long id) {
        log.debug("REST request to get T_review : {}", id);
        T_review t_review = t_reviewRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(t_review));
    }

    /**
     * DELETE  /t-reviews/:id : delete the "id" t_review.
     *
     * @param id the id of the t_review to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/t-reviews/{id}")
    @Timed
    public ResponseEntity<Void> deleteT_review(@PathVariable Long id) {
        log.debug("REST request to delete T_review : {}", id);
        t_reviewRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
