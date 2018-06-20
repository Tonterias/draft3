package com.raro.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raro.web.domain.Urllink;

import com.raro.web.repository.UrllinkRepository;
import com.raro.web.repository.search.UrllinkSearchRepository;
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
 * REST controller for managing Urllink.
 */
@RestController
@RequestMapping("/api")
public class UrllinkResource {

    private final Logger log = LoggerFactory.getLogger(UrllinkResource.class);

    private static final String ENTITY_NAME = "urllink";

    private final UrllinkRepository urllinkRepository;

    private final UrllinkSearchRepository urllinkSearchRepository;

    public UrllinkResource(UrllinkRepository urllinkRepository, UrllinkSearchRepository urllinkSearchRepository) {
        this.urllinkRepository = urllinkRepository;
        this.urllinkSearchRepository = urllinkSearchRepository;
    }

    /**
     * POST  /urllinks : Create a new urllink.
     *
     * @param urllink the urllink to create
     * @return the ResponseEntity with status 201 (Created) and with body the new urllink, or with status 400 (Bad Request) if the urllink has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/urllinks")
    @Timed
    public ResponseEntity<Urllink> createUrllink(@Valid @RequestBody Urllink urllink) throws URISyntaxException {
        log.debug("REST request to save Urllink : {}", urllink);
        if (urllink.getId() != null) {
            throw new BadRequestAlertException("A new urllink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Urllink result = urllinkRepository.save(urllink);
        urllinkSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/urllinks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /urllinks : Updates an existing urllink.
     *
     * @param urllink the urllink to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated urllink,
     * or with status 400 (Bad Request) if the urllink is not valid,
     * or with status 500 (Internal Server Error) if the urllink couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/urllinks")
    @Timed
    public ResponseEntity<Urllink> updateUrllink(@Valid @RequestBody Urllink urllink) throws URISyntaxException {
        log.debug("REST request to update Urllink : {}", urllink);
        if (urllink.getId() == null) {
            return createUrllink(urllink);
        }
        Urllink result = urllinkRepository.save(urllink);
        urllinkSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, urllink.getId().toString()))
            .body(result);
    }

    /**
     * GET  /urllinks : get all the urllinks.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of urllinks in body
     */
    @GetMapping("/urllinks")
    @Timed
    public ResponseEntity<List<Urllink>> getAllUrllinks(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("usefullinks1-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where usefulLinks1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getUsefulLinks1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("usefullinks2-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where usefulLinks2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getUsefulLinks2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("usefullinks3-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where usefulLinks3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getUsefulLinks3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("usefullinks4-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where usefulLinks4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getUsefulLinks4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("usefullinks5-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where usefulLinks5 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getUsefulLinks5() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("usefullinks6-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where usefulLinks6 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getUsefulLinks6() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentvideos1-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where recentVideos1 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getRecentVideos1() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentvideos2-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where recentVideos2 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getRecentVideos2() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentvideos3-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where recentVideos3 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getRecentVideos3() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentvideos4-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where recentVideos4 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getRecentVideos4() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentvideos5-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where recentVideos5 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getRecentVideos5() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("recentvideos6-is-null".equals(filter)) {
            log.debug("REST request to get all Urllinks where recentVideos6 is null");
            return new ResponseEntity<>(StreamSupport
                .stream(urllinkRepository.findAll().spliterator(), false)
                .filter(urllink -> urllink.getRecentVideos6() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        log.debug("REST request to get a page of Urllinks");
        Page<Urllink> page = urllinkRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/urllinks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /urllinks/:id : get the "id" urllink.
     *
     * @param id the id of the urllink to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the urllink, or with status 404 (Not Found)
     */
    @GetMapping("/urllinks/{id}")
    @Timed
    public ResponseEntity<Urllink> getUrllink(@PathVariable Long id) {
        log.debug("REST request to get Urllink : {}", id);
        Urllink urllink = urllinkRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(urllink));
    }

    /**
     * DELETE  /urllinks/:id : delete the "id" urllink.
     *
     * @param id the id of the urllink to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/urllinks/{id}")
    @Timed
    public ResponseEntity<Void> deleteUrllink(@PathVariable Long id) {
        log.debug("REST request to delete Urllink : {}", id);
        urllinkRepository.delete(id);
        urllinkSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/urllinks?query=:query : search for the urllink corresponding
     * to the query.
     *
     * @param query the query of the urllink search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/urllinks")
    @Timed
    public ResponseEntity<List<Urllink>> searchUrllinks(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Urllinks for query {}", query);
        Page<Urllink> page = urllinkSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/urllinks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
