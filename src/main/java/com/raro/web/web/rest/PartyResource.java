package com.raro.web.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.raro.web.domain.Party;

import com.raro.web.repository.PartyRepository;
import com.raro.web.repository.search.PartySearchRepository;
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
 * REST controller for managing Party.
 */
@RestController
@RequestMapping("/api")
public class PartyResource {

    private final Logger log = LoggerFactory.getLogger(PartyResource.class);

    private static final String ENTITY_NAME = "party";

    private final PartyRepository partyRepository;

    private final PartySearchRepository partySearchRepository;

    public PartyResource(PartyRepository partyRepository, PartySearchRepository partySearchRepository) {
        this.partyRepository = partyRepository;
        this.partySearchRepository = partySearchRepository;
    }

    /**
     * POST  /parties : Create a new party.
     *
     * @param party the party to create
     * @return the ResponseEntity with status 201 (Created) and with body the new party, or with status 400 (Bad Request) if the party has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/parties")
    @Timed
    public ResponseEntity<Party> createParty(@Valid @RequestBody Party party) throws URISyntaxException {
        log.debug("REST request to save Party : {}", party);
        if (party.getId() != null) {
            throw new BadRequestAlertException("A new party cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Party result = partyRepository.save(party);
        partySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/parties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /parties : Updates an existing party.
     *
     * @param party the party to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated party,
     * or with status 400 (Bad Request) if the party is not valid,
     * or with status 500 (Internal Server Error) if the party couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/parties")
    @Timed
    public ResponseEntity<Party> updateParty(@Valid @RequestBody Party party) throws URISyntaxException {
        log.debug("REST request to update Party : {}", party);
        if (party.getId() == null) {
            return createParty(party);
        }
        Party result = partyRepository.save(party);
        partySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, party.getId().toString()))
            .body(result);
    }

    /**
     * GET  /parties : get all the parties.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of parties in body
     */
    @GetMapping("/parties")
    @Timed
    public ResponseEntity<List<Party>> getAllParties(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("address-is-null".equals(filter)) {
            log.debug("REST request to get all Partys where address is null");
            return new ResponseEntity<>(StreamSupport
                .stream(partyRepository.findAll().spliterator(), false)
                .filter(party -> party.getAddress() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        if ("album-is-null".equals(filter)) {
            log.debug("REST request to get all Partys where album is null");
            return new ResponseEntity<>(StreamSupport
                .stream(partyRepository.findAll().spliterator(), false)
                .filter(party -> party.getAlbum() == null)
                .collect(Collectors.toList()), HttpStatus.OK);
        }
        log.debug("REST request to get a page of Parties");
        Page<Party> page = partyRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/parties");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /parties/:id : get the "id" party.
     *
     * @param id the id of the party to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the party, or with status 404 (Not Found)
     */
    @GetMapping("/parties/{id}")
    @Timed
    public ResponseEntity<Party> getParty(@PathVariable Long id) {
        log.debug("REST request to get Party : {}", id);
        Party party = partyRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(party));
    }

    /**
     * DELETE  /parties/:id : delete the "id" party.
     *
     * @param id the id of the party to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/parties/{id}")
    @Timed
    public ResponseEntity<Void> deleteParty(@PathVariable Long id) {
        log.debug("REST request to delete Party : {}", id);
        partyRepository.delete(id);
        partySearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/parties?query=:query : search for the party corresponding
     * to the query.
     *
     * @param query the query of the party search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/parties")
    @Timed
    public ResponseEntity<List<Party>> searchParties(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Parties for query {}", query);
        Page<Party> page = partySearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/parties");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
