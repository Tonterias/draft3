package com.raro.web.web.rest;

import com.raro.web.SkeletonApp;

import com.raro.web.domain.Urllink;
import com.raro.web.repository.UrllinkRepository;
import com.raro.web.repository.search.UrllinkSearchRepository;
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
 * Test class for the UrllinkResource REST controller.
 *
 * @see UrllinkResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkeletonApp.class)
public class UrllinkResourceIntTest {

    private static final String DEFAULT_LINK_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_LINK_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_LINK_URL = "AAAAAAAAAA";
    private static final String UPDATED_LINK_URL = "BBBBBBBBBB";

    @Autowired
    private UrllinkRepository urllinkRepository;

    @Autowired
    private UrllinkSearchRepository urllinkSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUrllinkMockMvc;

    private Urllink urllink;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UrllinkResource urllinkResource = new UrllinkResource(urllinkRepository, urllinkSearchRepository);
        this.restUrllinkMockMvc = MockMvcBuilders.standaloneSetup(urllinkResource)
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
    public static Urllink createEntity(EntityManager em) {
        Urllink urllink = new Urllink()
            .linkText(DEFAULT_LINK_TEXT)
            .linkURL(DEFAULT_LINK_URL);
        return urllink;
    }

    @Before
    public void initTest() {
        urllinkSearchRepository.deleteAll();
        urllink = createEntity(em);
    }

    @Test
    @Transactional
    public void createUrllink() throws Exception {
        int databaseSizeBeforeCreate = urllinkRepository.findAll().size();

        // Create the Urllink
        restUrllinkMockMvc.perform(post("/api/urllinks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(urllink)))
            .andExpect(status().isCreated());

        // Validate the Urllink in the database
        List<Urllink> urllinkList = urllinkRepository.findAll();
        assertThat(urllinkList).hasSize(databaseSizeBeforeCreate + 1);
        Urllink testUrllink = urllinkList.get(urllinkList.size() - 1);
        assertThat(testUrllink.getLinkText()).isEqualTo(DEFAULT_LINK_TEXT);
        assertThat(testUrllink.getLinkURL()).isEqualTo(DEFAULT_LINK_URL);

        // Validate the Urllink in Elasticsearch
        Urllink urllinkEs = urllinkSearchRepository.findOne(testUrllink.getId());
        assertThat(urllinkEs).isEqualToIgnoringGivenFields(testUrllink);
    }

    @Test
    @Transactional
    public void createUrllinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = urllinkRepository.findAll().size();

        // Create the Urllink with an existing ID
        urllink.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUrllinkMockMvc.perform(post("/api/urllinks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(urllink)))
            .andExpect(status().isBadRequest());

        // Validate the Urllink in the database
        List<Urllink> urllinkList = urllinkRepository.findAll();
        assertThat(urllinkList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLinkTextIsRequired() throws Exception {
        int databaseSizeBeforeTest = urllinkRepository.findAll().size();
        // set the field null
        urllink.setLinkText(null);

        // Create the Urllink, which fails.

        restUrllinkMockMvc.perform(post("/api/urllinks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(urllink)))
            .andExpect(status().isBadRequest());

        List<Urllink> urllinkList = urllinkRepository.findAll();
        assertThat(urllinkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLinkURLIsRequired() throws Exception {
        int databaseSizeBeforeTest = urllinkRepository.findAll().size();
        // set the field null
        urllink.setLinkURL(null);

        // Create the Urllink, which fails.

        restUrllinkMockMvc.perform(post("/api/urllinks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(urllink)))
            .andExpect(status().isBadRequest());

        List<Urllink> urllinkList = urllinkRepository.findAll();
        assertThat(urllinkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUrllinks() throws Exception {
        // Initialize the database
        urllinkRepository.saveAndFlush(urllink);

        // Get all the urllinkList
        restUrllinkMockMvc.perform(get("/api/urllinks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(urllink.getId().intValue())))
            .andExpect(jsonPath("$.[*].linkText").value(hasItem(DEFAULT_LINK_TEXT.toString())))
            .andExpect(jsonPath("$.[*].linkURL").value(hasItem(DEFAULT_LINK_URL.toString())));
    }

    @Test
    @Transactional
    public void getUrllink() throws Exception {
        // Initialize the database
        urllinkRepository.saveAndFlush(urllink);

        // Get the urllink
        restUrllinkMockMvc.perform(get("/api/urllinks/{id}", urllink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(urllink.getId().intValue()))
            .andExpect(jsonPath("$.linkText").value(DEFAULT_LINK_TEXT.toString()))
            .andExpect(jsonPath("$.linkURL").value(DEFAULT_LINK_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUrllink() throws Exception {
        // Get the urllink
        restUrllinkMockMvc.perform(get("/api/urllinks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUrllink() throws Exception {
        // Initialize the database
        urllinkRepository.saveAndFlush(urllink);
        urllinkSearchRepository.save(urllink);
        int databaseSizeBeforeUpdate = urllinkRepository.findAll().size();

        // Update the urllink
        Urllink updatedUrllink = urllinkRepository.findOne(urllink.getId());
        // Disconnect from session so that the updates on updatedUrllink are not directly saved in db
        em.detach(updatedUrllink);
        updatedUrllink
            .linkText(UPDATED_LINK_TEXT)
            .linkURL(UPDATED_LINK_URL);

        restUrllinkMockMvc.perform(put("/api/urllinks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUrllink)))
            .andExpect(status().isOk());

        // Validate the Urllink in the database
        List<Urllink> urllinkList = urllinkRepository.findAll();
        assertThat(urllinkList).hasSize(databaseSizeBeforeUpdate);
        Urllink testUrllink = urllinkList.get(urllinkList.size() - 1);
        assertThat(testUrllink.getLinkText()).isEqualTo(UPDATED_LINK_TEXT);
        assertThat(testUrllink.getLinkURL()).isEqualTo(UPDATED_LINK_URL);

        // Validate the Urllink in Elasticsearch
        Urllink urllinkEs = urllinkSearchRepository.findOne(testUrllink.getId());
        assertThat(urllinkEs).isEqualToIgnoringGivenFields(testUrllink);
    }

    @Test
    @Transactional
    public void updateNonExistingUrllink() throws Exception {
        int databaseSizeBeforeUpdate = urllinkRepository.findAll().size();

        // Create the Urllink

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUrllinkMockMvc.perform(put("/api/urllinks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(urllink)))
            .andExpect(status().isCreated());

        // Validate the Urllink in the database
        List<Urllink> urllinkList = urllinkRepository.findAll();
        assertThat(urllinkList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUrllink() throws Exception {
        // Initialize the database
        urllinkRepository.saveAndFlush(urllink);
        urllinkSearchRepository.save(urllink);
        int databaseSizeBeforeDelete = urllinkRepository.findAll().size();

        // Get the urllink
        restUrllinkMockMvc.perform(delete("/api/urllinks/{id}", urllink.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean urllinkExistsInEs = urllinkSearchRepository.exists(urllink.getId());
        assertThat(urllinkExistsInEs).isFalse();

        // Validate the database is empty
        List<Urllink> urllinkList = urllinkRepository.findAll();
        assertThat(urllinkList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchUrllink() throws Exception {
        // Initialize the database
        urllinkRepository.saveAndFlush(urllink);
        urllinkSearchRepository.save(urllink);

        // Search the urllink
        restUrllinkMockMvc.perform(get("/api/_search/urllinks?query=id:" + urllink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(urllink.getId().intValue())))
            .andExpect(jsonPath("$.[*].linkText").value(hasItem(DEFAULT_LINK_TEXT.toString())))
            .andExpect(jsonPath("$.[*].linkURL").value(hasItem(DEFAULT_LINK_URL.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Urllink.class);
        Urllink urllink1 = new Urllink();
        urllink1.setId(1L);
        Urllink urllink2 = new Urllink();
        urllink2.setId(urllink1.getId());
        assertThat(urllink1).isEqualTo(urllink2);
        urllink2.setId(2L);
        assertThat(urllink1).isNotEqualTo(urllink2);
        urllink1.setId(null);
        assertThat(urllink1).isNotEqualTo(urllink2);
    }
}
