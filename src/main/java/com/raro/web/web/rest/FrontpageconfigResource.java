package com.raro.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raro.web.domain.Frontpageconfig;

import com.raro.web.repository.FrontpageconfigRepository;
import com.raro.web.repository.search.FrontpageconfigSearchRepository;
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
 * REST controller for managing Frontpageconfig.
 */
@RestController
@RequestMapping("/api")
public class FrontpageconfigResource {

    private final Logger log = LoggerFactory.getLogger(FrontpageconfigResource.class);

    private static final String ENTITY_NAME = "frontpageconfig";

    private final FrontpageconfigRepository frontpageconfigRepository;

    private final FrontpageconfigSearchRepository frontpageconfigSearchRepository;

    public FrontpageconfigResource(FrontpageconfigRepository frontpageconfigRepository, FrontpageconfigSearchRepository frontpageconfigSearchRepository) {
        this.frontpageconfigRepository = frontpageconfigRepository;
        this.frontpageconfigSearchRepository = frontpageconfigSearchRepository;
    }

    /**
     * POST  /frontpageconfigs : Create a new frontpageconfig.
     *
     * @param frontpageconfig the frontpageconfig to create
     * @return the ResponseEntity with status 201 (Created) and with body the new frontpageconfig, or with status 400 (Bad Request) if the frontpageconfig has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/frontpageconfigs")
    @Timed
    public ResponseEntity<Frontpageconfig> createFrontpageconfig(@Valid @RequestBody Frontpageconfig frontpageconfig) throws URISyntaxException {
        log.debug("REST request to save Frontpageconfig : {}", frontpageconfig);
        if (frontpageconfig.getId() != null) {
            throw new BadRequestAlertException("A new frontpageconfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Frontpageconfig result = frontpageconfigRepository.save(frontpageconfig);
        frontpageconfigSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/frontpageconfigs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /frontpageconfigs : Updates an existing frontpageconfig.
     *
     * @param frontpageconfig the frontpageconfig to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated frontpageconfig,
     * or with status 400 (Bad Request) if the frontpageconfig is not valid,
     * or with status 500 (Internal Server Error) if the frontpageconfig couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/frontpageconfigs")
    @Timed
    public ResponseEntity<Frontpageconfig> updateFrontpageconfig(@Valid @RequestBody Frontpageconfig frontpageconfig) throws URISyntaxException {
        log.debug("REST request to update Frontpageconfig : {}", frontpageconfig);
        if (frontpageconfig.getId() == null) {
            return createFrontpageconfig(frontpageconfig);
        }
        Frontpageconfig result = frontpageconfigRepository.save(frontpageconfig);
        frontpageconfigSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, frontpageconfig.getId().toString()))
            .body(result);
    }

    /**
     * GET  /frontpageconfigs : get all the frontpageconfigs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of frontpageconfigs in body
     */
    @GetMapping("/frontpageconfigs")
    @Timed
    public ResponseEntity<List<Frontpageconfig>> getAllFrontpageconfigs(Pageable pageable) {
        log.debug("REST request to get a page of Frontpageconfigs");
        Page<Frontpageconfig> page = frontpageconfigRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/frontpageconfigs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /frontpageconfigs/:id : get the "id" frontpageconfig.
     *
     * @param id the id of the frontpageconfig to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the frontpageconfig, or with status 404 (Not Found)
     */
    @GetMapping("/frontpageconfigs/{id}")
    @Timed
    public ResponseEntity<Frontpageconfig> getFrontpageconfig(@PathVariable Long id) {
        log.debug("REST request to get Frontpageconfig : {}", id);
        Frontpageconfig frontpageconfig = frontpageconfigRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(frontpageconfig));
    }

    /**
     * DELETE  /frontpageconfigs/:id : delete the "id" frontpageconfig.
     *
     * @param id the id of the frontpageconfig to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/frontpageconfigs/{id}")
    @Timed
    public ResponseEntity<Void> deleteFrontpageconfig(@PathVariable Long id) {
        log.debug("REST request to delete Frontpageconfig : {}", id);
        frontpageconfigRepository.delete(id);
        frontpageconfigSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/frontpageconfigs?query=:query : search for the frontpageconfig corresponding
     * to the query.
     *
     * @param query the query of the frontpageconfig search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/frontpageconfigs")
    @Timed
    public ResponseEntity<List<Frontpageconfig>> searchFrontpageconfigs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Frontpageconfigs for query {}", query);
        Page<Frontpageconfig> page = frontpageconfigSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/frontpageconfigs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
