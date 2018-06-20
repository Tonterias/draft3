package com.raro.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raro.web.domain.Blockeduser;

import com.raro.web.repository.BlockeduserRepository;
import com.raro.web.repository.search.BlockeduserSearchRepository;
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
 * REST controller for managing Blockeduser.
 */
@RestController
@RequestMapping("/api")
public class BlockeduserResource {

    private final Logger log = LoggerFactory.getLogger(BlockeduserResource.class);

    private static final String ENTITY_NAME = "blockeduser";

    private final BlockeduserRepository blockeduserRepository;

    private final BlockeduserSearchRepository blockeduserSearchRepository;

    public BlockeduserResource(BlockeduserRepository blockeduserRepository, BlockeduserSearchRepository blockeduserSearchRepository) {
        this.blockeduserRepository = blockeduserRepository;
        this.blockeduserSearchRepository = blockeduserSearchRepository;
    }

    /**
     * POST  /blockedusers : Create a new blockeduser.
     *
     * @param blockeduser the blockeduser to create
     * @return the ResponseEntity with status 201 (Created) and with body the new blockeduser, or with status 400 (Bad Request) if the blockeduser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/blockedusers")
    @Timed
    public ResponseEntity<Blockeduser> createBlockeduser(@RequestBody Blockeduser blockeduser) throws URISyntaxException {
        log.debug("REST request to save Blockeduser : {}", blockeduser);
        if (blockeduser.getId() != null) {
            throw new BadRequestAlertException("A new blockeduser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Blockeduser result = blockeduserRepository.save(blockeduser);
        blockeduserSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/blockedusers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /blockedusers : Updates an existing blockeduser.
     *
     * @param blockeduser the blockeduser to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated blockeduser,
     * or with status 400 (Bad Request) if the blockeduser is not valid,
     * or with status 500 (Internal Server Error) if the blockeduser couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/blockedusers")
    @Timed
    public ResponseEntity<Blockeduser> updateBlockeduser(@RequestBody Blockeduser blockeduser) throws URISyntaxException {
        log.debug("REST request to update Blockeduser : {}", blockeduser);
        if (blockeduser.getId() == null) {
            return createBlockeduser(blockeduser);
        }
        Blockeduser result = blockeduserRepository.save(blockeduser);
        blockeduserSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, blockeduser.getId().toString()))
            .body(result);
    }

    /**
     * GET  /blockedusers : get all the blockedusers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of blockedusers in body
     */
    @GetMapping("/blockedusers")
    @Timed
    public ResponseEntity<List<Blockeduser>> getAllBlockedusers(Pageable pageable) {
        log.debug("REST request to get a page of Blockedusers");
        Page<Blockeduser> page = blockeduserRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/blockedusers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /blockedusers/:id : get the "id" blockeduser.
     *
     * @param id the id of the blockeduser to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the blockeduser, or with status 404 (Not Found)
     */
    @GetMapping("/blockedusers/{id}")
    @Timed
    public ResponseEntity<Blockeduser> getBlockeduser(@PathVariable Long id) {
        log.debug("REST request to get Blockeduser : {}", id);
        Blockeduser blockeduser = blockeduserRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(blockeduser));
    }

    /**
     * DELETE  /blockedusers/:id : delete the "id" blockeduser.
     *
     * @param id the id of the blockeduser to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/blockedusers/{id}")
    @Timed
    public ResponseEntity<Void> deleteBlockeduser(@PathVariable Long id) {
        log.debug("REST request to delete Blockeduser : {}", id);
        blockeduserRepository.delete(id);
        blockeduserSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/blockedusers?query=:query : search for the blockeduser corresponding
     * to the query.
     *
     * @param query the query of the blockeduser search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/blockedusers")
    @Timed
    public ResponseEntity<List<Blockeduser>> searchBlockedusers(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Blockedusers for query {}", query);
        Page<Blockeduser> page = blockeduserSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/blockedusers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
