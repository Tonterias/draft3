package com.raro.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raro.web.domain.Celeb;

import com.raro.web.repository.CelebRepository;
import com.raro.web.repository.search.CelebSearchRepository;
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
 * REST controller for managing Celeb.
 */
@RestController
@RequestMapping("/api")
public class CelebResource {

    private final Logger log = LoggerFactory.getLogger(CelebResource.class);

    private static final String ENTITY_NAME = "celeb";

    private final CelebRepository celebRepository;

    private final CelebSearchRepository celebSearchRepository;

    public CelebResource(CelebRepository celebRepository, CelebSearchRepository celebSearchRepository) {
        this.celebRepository = celebRepository;
        this.celebSearchRepository = celebSearchRepository;
    }

    /**
     * POST  /celebs : Create a new celeb.
     *
     * @param celeb the celeb to create
     * @return the ResponseEntity with status 201 (Created) and with body the new celeb, or with status 400 (Bad Request) if the celeb has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/celebs")
    @Timed
    public ResponseEntity<Celeb> createCeleb(@Valid @RequestBody Celeb celeb) throws URISyntaxException {
        log.debug("REST request to save Celeb : {}", celeb);
        if (celeb.getId() != null) {
            throw new BadRequestAlertException("A new celeb cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Celeb result = celebRepository.save(celeb);
        celebSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/celebs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /celebs : Updates an existing celeb.
     *
     * @param celeb the celeb to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated celeb,
     * or with status 400 (Bad Request) if the celeb is not valid,
     * or with status 500 (Internal Server Error) if the celeb couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/celebs")
    @Timed
    public ResponseEntity<Celeb> updateCeleb(@Valid @RequestBody Celeb celeb) throws URISyntaxException {
        log.debug("REST request to update Celeb : {}", celeb);
        if (celeb.getId() == null) {
            return createCeleb(celeb);
        }
        Celeb result = celebRepository.save(celeb);
        celebSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, celeb.getId().toString()))
            .body(result);
    }

    /**
     * GET  /celebs : get all the celebs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of celebs in body
     */
    @GetMapping("/celebs")
    @Timed
    public ResponseEntity<List<Celeb>> getAllCelebs(Pageable pageable) {
        log.debug("REST request to get a page of Celebs");
        Page<Celeb> page = celebRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/celebs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /celebs/:id : get the "id" celeb.
     *
     * @param id the id of the celeb to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the celeb, or with status 404 (Not Found)
     */
    @GetMapping("/celebs/{id}")
    @Timed
    public ResponseEntity<Celeb> getCeleb(@PathVariable Long id) {
        log.debug("REST request to get Celeb : {}", id);
        Celeb celeb = celebRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(celeb));
    }

    /**
     * DELETE  /celebs/:id : delete the "id" celeb.
     *
     * @param id the id of the celeb to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/celebs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCeleb(@PathVariable Long id) {
        log.debug("REST request to delete Celeb : {}", id);
        celebRepository.delete(id);
        celebSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/celebs?query=:query : search for the celeb corresponding
     * to the query.
     *
     * @param query the query of the celeb search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/celebs")
    @Timed
    public ResponseEntity<List<Celeb>> searchCelebs(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Celebs for query {}", query);
        Page<Celeb> page = celebSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/celebs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
