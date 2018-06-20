package com.raro.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raro.web.domain.Interest;

import com.raro.web.repository.InterestRepository;
import com.raro.web.repository.search.InterestSearchRepository;
import com.raro.web.web.rest.errors.BadRequestAlertException;
import com.raro.web.web.rest.util.HeaderUtil;
import com.raro.web.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Interest.
 */
@RestController
@RequestMapping("/api")
public class InterestResource {

    private final Logger log = LoggerFactory.getLogger(InterestResource.class);

    private static final String ENTITY_NAME = "interest";

    private final InterestRepository interestRepository;

    private final InterestSearchRepository interestSearchRepository;

    public InterestResource(InterestRepository interestRepository, InterestSearchRepository interestSearchRepository) {
        this.interestRepository = interestRepository;
        this.interestSearchRepository = interestSearchRepository;
    }

    /**
     * POST  /interests : Create a new interest.
     *
     * @param interest the interest to create
     * @return the ResponseEntity with status 201 (Created) and with body the new interest, or with status 400 (Bad Request) if the interest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/interests")
    @Timed
    public ResponseEntity<Interest> createInterest(@Valid @RequestBody Interest interest) throws URISyntaxException {
        log.debug("REST request to save Interest : {}", interest);
        if (interest.getId() != null) {
            throw new BadRequestAlertException("A new interest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Interest result = interestRepository.save(interest);
        interestSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/interests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /interests : Updates an existing interest.
     *
     * @param interest the interest to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated interest,
     * or with status 400 (Bad Request) if the interest is not valid,
     * or with status 500 (Internal Server Error) if the interest couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/interests")
    @Timed
    public ResponseEntity<Interest> updateInterest(@Valid @RequestBody Interest interest) throws URISyntaxException {
        log.debug("REST request to update Interest : {}", interest);
        if (interest.getId() == null) {
            return createInterest(interest);
        }
        Interest result = interestRepository.save(interest);
        interestSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, interest.getId().toString()))
            .body(result);
    }

    /**
     * GET  /interests : get all the interests.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of interests in body
     */
    @GetMapping("/interests")
    @Timed
    public ResponseEntity<List<Interest>> getAllInterests(Pageable pageable) {
        log.debug("REST request to get a page of Interests");
        Page<Interest> page = interestRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/interests");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /interests/:id : get the "id" interest.
     *
     * @param id the id of the interest to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the interest, or with status 404 (Not Found)
     */
    @GetMapping("/interests/{id}")
    @Timed
    public ResponseEntity<Interest> getInterest(@PathVariable Long id) {
        log.debug("REST request to get Interest : {}", id);
        Interest interest = interestRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(interest));
    }

    /**
     * DELETE  /interests/:id : delete the "id" interest.
     *
     * @param id the id of the interest to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/interests/{id}")
    @Timed
    public ResponseEntity<Void> deleteInterest(@PathVariable Long id) {
        log.debug("REST request to delete Interest : {}", id);
        interestRepository.delete(id);
        interestSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/interests?query=:query : search for the interest corresponding
     * to the query.
     *
     * @param query the query of the interest search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/interests")
    @Timed
    public ResponseEntity<List<Interest>> searchInterests(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Interests for query {}", query);
        Page<Interest> page = interestSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/interests");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
