package com.raro.web.web.rest;

import com.raro.web.SkeletonApp;

import com.raro.web.domain.Celeb;
import com.raro.web.repository.CelebRepository;
import com.raro.web.repository.search.CelebSearchRepository;
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
import java.util.List;

import static com.raro.web.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the CelebResource REST controller.
 *
 * @see CelebResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkeletonApp.class)
public class CelebResourceIntTest {

    private static final String DEFAULT_CELEB_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CELEB_NAME = "BBBBBBBBBB";

    @Autowired
    private CelebRepository celebRepository;

    @Autowired
    private CelebSearchRepository celebSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCelebMockMvc;

    private Celeb celeb;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CelebResource celebResource = new CelebResource(celebRepository, celebSearchRepository);
        this.restCelebMockMvc = MockMvcBuilders.standaloneSetup(celebResource)
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
    public static Celeb createEntity(EntityManager em) {
        Celeb celeb = new Celeb()
            .celebName(DEFAULT_CELEB_NAME);
        return celeb;
    }

    @Before
    public void initTest() {
        celebSearchRepository.deleteAll();
        celeb = createEntity(em);
    }

    @Test
    @Transactional
    public void createCeleb() throws Exception {
        int databaseSizeBeforeCreate = celebRepository.findAll().size();

        // Create the Celeb
        restCelebMockMvc.perform(post("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celeb)))
            .andExpect(status().isCreated());

        // Validate the Celeb in the database
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeCreate + 1);
        Celeb testCeleb = celebList.get(celebList.size() - 1);
        assertThat(testCeleb.getCelebName()).isEqualTo(DEFAULT_CELEB_NAME);

        // Validate the Celeb in Elasticsearch
        Celeb celebEs = celebSearchRepository.findOne(testCeleb.getId());
        assertThat(celebEs).isEqualToIgnoringGivenFields(testCeleb);
    }

    @Test
    @Transactional
    public void createCelebWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = celebRepository.findAll().size();

        // Create the Celeb with an existing ID
        celeb.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCelebMockMvc.perform(post("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celeb)))
            .andExpect(status().isBadRequest());

        // Validate the Celeb in the database
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCelebNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = celebRepository.findAll().size();
        // set the field null
        celeb.setCelebName(null);

        // Create the Celeb, which fails.

        restCelebMockMvc.perform(post("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celeb)))
            .andExpect(status().isBadRequest());

        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCelebs() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        // Get all the celebList
        restCelebMockMvc.perform(get("/api/celebs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(celeb.getId().intValue())))
            .andExpect(jsonPath("$.[*].celebName").value(hasItem(DEFAULT_CELEB_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCeleb() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);

        // Get the celeb
        restCelebMockMvc.perform(get("/api/celebs/{id}", celeb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(celeb.getId().intValue()))
            .andExpect(jsonPath("$.celebName").value(DEFAULT_CELEB_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCeleb() throws Exception {
        // Get the celeb
        restCelebMockMvc.perform(get("/api/celebs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCeleb() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);
        celebSearchRepository.save(celeb);
        int databaseSizeBeforeUpdate = celebRepository.findAll().size();

        // Update the celeb
        Celeb updatedCeleb = celebRepository.findOne(celeb.getId());
        // Disconnect from session so that the updates on updatedCeleb are not directly saved in db
        em.detach(updatedCeleb);
        updatedCeleb
            .celebName(UPDATED_CELEB_NAME);

        restCelebMockMvc.perform(put("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCeleb)))
            .andExpect(status().isOk());

        // Validate the Celeb in the database
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeUpdate);
        Celeb testCeleb = celebList.get(celebList.size() - 1);
        assertThat(testCeleb.getCelebName()).isEqualTo(UPDATED_CELEB_NAME);

        // Validate the Celeb in Elasticsearch
        Celeb celebEs = celebSearchRepository.findOne(testCeleb.getId());
        assertThat(celebEs).isEqualToIgnoringGivenFields(testCeleb);
    }

    @Test
    @Transactional
    public void updateNonExistingCeleb() throws Exception {
        int databaseSizeBeforeUpdate = celebRepository.findAll().size();

        // Create the Celeb

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCelebMockMvc.perform(put("/api/celebs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(celeb)))
            .andExpect(status().isCreated());

        // Validate the Celeb in the database
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCeleb() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);
        celebSearchRepository.save(celeb);
        int databaseSizeBeforeDelete = celebRepository.findAll().size();

        // Get the celeb
        restCelebMockMvc.perform(delete("/api/celebs/{id}", celeb.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean celebExistsInEs = celebSearchRepository.exists(celeb.getId());
        assertThat(celebExistsInEs).isFalse();

        // Validate the database is empty
        List<Celeb> celebList = celebRepository.findAll();
        assertThat(celebList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCeleb() throws Exception {
        // Initialize the database
        celebRepository.saveAndFlush(celeb);
        celebSearchRepository.save(celeb);

        // Search the celeb
        restCelebMockMvc.perform(get("/api/_search/celebs?query=id:" + celeb.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(celeb.getId().intValue())))
            .andExpect(jsonPath("$.[*].celebName").value(hasItem(DEFAULT_CELEB_NAME.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Celeb.class);
        Celeb celeb1 = new Celeb();
        celeb1.setId(1L);
        Celeb celeb2 = new Celeb();
        celeb2.setId(celeb1.getId());
        assertThat(celeb1).isEqualTo(celeb2);
        celeb2.setId(2L);
        assertThat(celeb1).isNotEqualTo(celeb2);
        celeb1.setId(null);
        assertThat(celeb1).isNotEqualTo(celeb2);
    }
}
