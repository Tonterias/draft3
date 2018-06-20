package com.raro.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raro.web.domain.Follow;

import com.raro.web.repository.FollowRepository;
import com.raro.web.repository.search.FollowSearchRepository;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Follow.
 */
@RestController
@RequestMapping("/api")
public class FollowResource {

    private final Logger log = LoggerFactory.getLogger(FollowResource.class);

    private static final String ENTITY_NAME = "follow";

    private final FollowRepository followRepository;

    private final FollowSearchRepository followSearchRepository;

    public FollowResource(FollowRepository followRepository, FollowSearchRepository followSearchRepository) {
        this.followRepository = followRepository;
        this.followSearchRepository = followSearchRepository;
    }

    /**
     * POST  /follows : Create a new follow.
     *
     * @param follow the follow to create
     * @return the ResponseEntity with status 201 (Created) and with body the new follow, or with status 400 (Bad Request) if the follow has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/follows")
    @Timed
    public ResponseEntity<Follow> createFollow(@RequestBody Follow follow) throws URISyntaxException {
        log.debug("REST request to save Follow : {}", follow);
        if (follow.getId() != null) {
            throw new BadRequestAlertException("A new follow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Follow result = followRepository.save(follow);
        followSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/follows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /follows : Updates an existing follow.
     *
     * @param follow the follow to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated follow,
     * or with status 400 (Bad Request) if the follow is not valid,
     * or with status 500 (Internal Server Error) if the follow couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/follows")
    @Timed
    public ResponseEntity<Follow> updateFollow(@RequestBody Follow follow) throws URISyntaxException {
        log.debug("REST request to update Follow : {}", follow);
        if (follow.getId() == null) {
            return createFollow(follow);
        }
        Follow result = followRepository.save(follow);
        followSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, follow.getId().toString()))
            .body(result);
    }

    /**
     * GET  /follows : get all the follows.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of follows in body
     */
    @GetMapping("/follows")
    @Timed
    public ResponseEntity<List<Follow>> getAllFollows(Pageable pageable) {
        log.debug("REST request to get a page of Follows");
        Page<Follow> page = followRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/follows");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /follows/:id : get the "id" follow.
     *
     * @param id the id of the follow to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the follow, or with status 404 (Not Found)
     */
    @GetMapping("/follows/{id}")
    @Timed
    public ResponseEntity<Follow> getFollow(@PathVariable Long id) {
        log.debug("REST request to get Follow : {}", id);
        Follow follow = followRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(follow));
    }

    /**
     * DELETE  /follows/:id : delete the "id" follow.
     *
     * @param id the id of the follow to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/follows/{id}")
    @Timed
    public ResponseEntity<Void> deleteFollow(@PathVariable Long id) {
        log.debug("REST request to delete Follow : {}", id);
        followRepository.delete(id);
        followSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/follows?query=:query : search for the follow corresponding
     * to the query.
     *
     * @param query the query of the follow search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/follows")
    @Timed
    public ResponseEntity<List<Follow>> searchFollows(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Follows for query {}", query);
        Page<Follow> page = followSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/follows");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
