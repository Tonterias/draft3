package com.raro.web.web.rest;

import com.raro.web.SkeletonApp;

import com.raro.web.domain.Frontpageconfig;
import com.raro.web.repository.FrontpageconfigRepository;
import com.raro.web.repository.search.FrontpageconfigSearchRepository;
import com.raro.web.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.raro.web.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FrontpageconfigResource REST controller.
 *
 * @see FrontpageconfigResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkeletonApp.class)
public class FrontpageconfigResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FrontpageconfigRepository frontpageconfigRepository;

    @Autowired
    private FrontpageconfigSearchRepository frontpageconfigSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFrontpageconfigMockMvc;

    private Frontpageconfig frontpageconfig;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FrontpageconfigResource frontpageconfigResource = new FrontpageconfigResource(frontpageconfigRepository, frontpageconfigSearchRepository);
        this.restFrontpageconfigMockMvc = MockMvcBuilders.standaloneSetup(frontpageconfigResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frontpageconfig createEntity(EntityManager em) {
        Frontpageconfig frontpageconfig = new Frontpageconfig()
            .creationDate(DEFAULT_CREATION_DATE);
        return frontpageconfig;
    }

    @Before
    public void initTest() {
        frontpageconfigSearchRepository.deleteAll();
        frontpageconfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createFrontpageconfig() throws Exception {
        int databaseSizeBeforeCreate = frontpageconfigRepository.findAll().size();

        // Create the Frontpageconfig
        restFrontpageconfigMockMvc.perform(post("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfig)))
            .andExpect(status().isCreated());

        // Validate the Frontpageconfig in the database
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeCreate + 1);
        Frontpageconfig testFrontpageconfig = frontpageconfigList.get(frontpageconfigList.size() - 1);
        assertThat(testFrontpageconfig.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);

        // Validate the Frontpageconfig in Elasticsearch
        Frontpageconfig frontpageconfigEs = frontpageconfigSearchRepository.findOne(testFrontpageconfig.getId());
        assertThat(frontpageconfigEs).isEqualToIgnoringGivenFields(testFrontpageconfig);
    }

    @Test
    @Transactional
    public void createFrontpageconfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = frontpageconfigRepository.findAll().size();

        // Create the Frontpageconfig with an existing ID
        frontpageconfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFrontpageconfigMockMvc.perform(post("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfig)))
            .andExpect(status().isBadRequest());

        // Validate the Frontpageconfig in the database
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = frontpageconfigRepository.findAll().size();
        // set the field null
        frontpageconfig.setCreationDate(null);

        // Create the Frontpageconfig, which fails.

        restFrontpageconfigMockMvc.perform(post("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfig)))
            .andExpect(status().isBadRequest());

        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFrontpageconfigs() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get all the frontpageconfigList
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frontpageconfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getFrontpageconfig() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);

        // Get the frontpageconfig
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs/{id}", frontpageconfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(frontpageconfig.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFrontpageconfig() throws Exception {
        // Get the frontpageconfig
        restFrontpageconfigMockMvc.perform(get("/api/frontpageconfigs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFrontpageconfig() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);
        frontpageconfigSearchRepository.save(frontpageconfig);
        int databaseSizeBeforeUpdate = frontpageconfigRepository.findAll().size();

        // Update the frontpageconfig
        Frontpageconfig updatedFrontpageconfig = frontpageconfigRepository.findOne(frontpageconfig.getId());
        // Disconnect from session so that the updates on updatedFrontpageconfig are not directly saved in db
        em.detach(updatedFrontpageconfig);
        updatedFrontpageconfig
            .creationDate(UPDATED_CREATION_DATE);

        restFrontpageconfigMockMvc.perform(put("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedFrontpageconfig)))
            .andExpect(status().isOk());

        // Validate the Frontpageconfig in the database
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeUpdate);
        Frontpageconfig testFrontpageconfig = frontpageconfigList.get(frontpageconfigList.size() - 1);
        assertThat(testFrontpageconfig.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);

        // Validate the Frontpageconfig in Elasticsearch
        Frontpageconfig frontpageconfigEs = frontpageconfigSearchRepository.findOne(testFrontpageconfig.getId());
        assertThat(frontpageconfigEs).isEqualToIgnoringGivenFields(testFrontpageconfig);
    }

    @Test
    @Transactional
    public void updateNonExistingFrontpageconfig() throws Exception {
        int databaseSizeBeforeUpdate = frontpageconfigRepository.findAll().size();

        // Create the Frontpageconfig

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFrontpageconfigMockMvc.perform(put("/api/frontpageconfigs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(frontpageconfig)))
            .andExpect(status().isCreated());

        // Validate the Frontpageconfig in the database
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFrontpageconfig() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);
        frontpageconfigSearchRepository.save(frontpageconfig);
        int databaseSizeBeforeDelete = frontpageconfigRepository.findAll().size();

        // Get the frontpageconfig
        restFrontpageconfigMockMvc.perform(delete("/api/frontpageconfigs/{id}", frontpageconfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean frontpageconfigExistsInEs = frontpageconfigSearchRepository.exists(frontpageconfig.getId());
        assertThat(frontpageconfigExistsInEs).isFalse();

        // Validate the database is empty
        List<Frontpageconfig> frontpageconfigList = frontpageconfigRepository.findAll();
        assertThat(frontpageconfigList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchFrontpageconfig() throws Exception {
        // Initialize the database
        frontpageconfigRepository.saveAndFlush(frontpageconfig);
        frontpageconfigSearchRepository.save(frontpageconfig);

        // Search the frontpageconfig
        restFrontpageconfigMockMvc.perform(get("/api/_search/frontpageconfigs?query=id:" + frontpageconfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frontpageconfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Frontpageconfig.class);
        Frontpageconfig frontpageconfig1 = new Frontpageconfig();
        frontpageconfig1.setId(1L);
        Frontpageconfig frontpageconfig2 = new Frontpageconfig();
        frontpageconfig2.setId(frontpageconfig1.getId());
        assertThat(frontpageconfig1).isEqualTo(frontpageconfig2);
        frontpageconfig2.setId(2L);
        assertThat(frontpageconfig1).isNotEqualTo(frontpageconfig2);
        frontpageconfig1.setId(null);
        assertThat(frontpageconfig1).isNotEqualTo(frontpageconfig2);
    }
}
