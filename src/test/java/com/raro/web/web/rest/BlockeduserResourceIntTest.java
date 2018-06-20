package com.raro.web.web.rest;

import com.raro.web.SkeletonApp;

import com.raro.web.domain.Blockeduser;
import com.raro.web.repository.BlockeduserRepository;
import com.raro.web.repository.search.BlockeduserSearchRepository;
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
 * Test class for the BlockeduserResource REST controller.
 *
 * @see BlockeduserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkeletonApp.class)
public class BlockeduserResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private BlockeduserRepository blockeduserRepository;

    @Autowired
    private BlockeduserSearchRepository blockeduserSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBlockeduserMockMvc;

    private Blockeduser blockeduser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BlockeduserResource blockeduserResource = new BlockeduserResource(blockeduserRepository, blockeduserSearchRepository);
        this.restBlockeduserMockMvc = MockMvcBuilders.standaloneSetup(blockeduserResource)
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
    public static Blockeduser createEntity(EntityManager em) {
        Blockeduser blockeduser = new Blockeduser()
            .creationDate(DEFAULT_CREATION_DATE);
        return blockeduser;
    }

    @Before
    public void initTest() {
        blockeduserSearchRepository.deleteAll();
        blockeduser = createEntity(em);
    }

    @Test
    @Transactional
    public void createBlockeduser() throws Exception {
        int databaseSizeBeforeCreate = blockeduserRepository.findAll().size();

        // Create the Blockeduser
        restBlockeduserMockMvc.perform(post("/api/blockedusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockeduser)))
            .andExpect(status().isCreated());

        // Validate the Blockeduser in the database
        List<Blockeduser> blockeduserList = blockeduserRepository.findAll();
        assertThat(blockeduserList).hasSize(databaseSizeBeforeCreate + 1);
        Blockeduser testBlockeduser = blockeduserList.get(blockeduserList.size() - 1);
        assertThat(testBlockeduser.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);

        // Validate the Blockeduser in Elasticsearch
        Blockeduser blockeduserEs = blockeduserSearchRepository.findOne(testBlockeduser.getId());
        assertThat(blockeduserEs).isEqualToIgnoringGivenFields(testBlockeduser);
    }

    @Test
    @Transactional
    public void createBlockeduserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = blockeduserRepository.findAll().size();

        // Create the Blockeduser with an existing ID
        blockeduser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBlockeduserMockMvc.perform(post("/api/blockedusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockeduser)))
            .andExpect(status().isBadRequest());

        // Validate the Blockeduser in the database
        List<Blockeduser> blockeduserList = blockeduserRepository.findAll();
        assertThat(blockeduserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBlockedusers() throws Exception {
        // Initialize the database
        blockeduserRepository.saveAndFlush(blockeduser);

        // Get all the blockeduserList
        restBlockeduserMockMvc.perform(get("/api/blockedusers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blockeduser.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getBlockeduser() throws Exception {
        // Initialize the database
        blockeduserRepository.saveAndFlush(blockeduser);

        // Get the blockeduser
        restBlockeduserMockMvc.perform(get("/api/blockedusers/{id}", blockeduser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(blockeduser.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBlockeduser() throws Exception {
        // Get the blockeduser
        restBlockeduserMockMvc.perform(get("/api/blockedusers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBlockeduser() throws Exception {
        // Initialize the database
        blockeduserRepository.saveAndFlush(blockeduser);
        blockeduserSearchRepository.save(blockeduser);
        int databaseSizeBeforeUpdate = blockeduserRepository.findAll().size();

        // Update the blockeduser
        Blockeduser updatedBlockeduser = blockeduserRepository.findOne(blockeduser.getId());
        // Disconnect from session so that the updates on updatedBlockeduser are not directly saved in db
        em.detach(updatedBlockeduser);
        updatedBlockeduser
            .creationDate(UPDATED_CREATION_DATE);

        restBlockeduserMockMvc.perform(put("/api/blockedusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBlockeduser)))
            .andExpect(status().isOk());

        // Validate the Blockeduser in the database
        List<Blockeduser> blockeduserList = blockeduserRepository.findAll();
        assertThat(blockeduserList).hasSize(databaseSizeBeforeUpdate);
        Blockeduser testBlockeduser = blockeduserList.get(blockeduserList.size() - 1);
        assertThat(testBlockeduser.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);

        // Validate the Blockeduser in Elasticsearch
        Blockeduser blockeduserEs = blockeduserSearchRepository.findOne(testBlockeduser.getId());
        assertThat(blockeduserEs).isEqualToIgnoringGivenFields(testBlockeduser);
    }

    @Test
    @Transactional
    public void updateNonExistingBlockeduser() throws Exception {
        int databaseSizeBeforeUpdate = blockeduserRepository.findAll().size();

        // Create the Blockeduser

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBlockeduserMockMvc.perform(put("/api/blockedusers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(blockeduser)))
            .andExpect(status().isCreated());

        // Validate the Blockeduser in the database
        List<Blockeduser> blockeduserList = blockeduserRepository.findAll();
        assertThat(blockeduserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBlockeduser() throws Exception {
        // Initialize the database
        blockeduserRepository.saveAndFlush(blockeduser);
        blockeduserSearchRepository.save(blockeduser);
        int databaseSizeBeforeDelete = blockeduserRepository.findAll().size();

        // Get the blockeduser
        restBlockeduserMockMvc.perform(delete("/api/blockedusers/{id}", blockeduser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean blockeduserExistsInEs = blockeduserSearchRepository.exists(blockeduser.getId());
        assertThat(blockeduserExistsInEs).isFalse();

        // Validate the database is empty
        List<Blockeduser> blockeduserList = blockeduserRepository.findAll();
        assertThat(blockeduserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchBlockeduser() throws Exception {
        // Initialize the database
        blockeduserRepository.saveAndFlush(blockeduser);
        blockeduserSearchRepository.save(blockeduser);

        // Search the blockeduser
        restBlockeduserMockMvc.perform(get("/api/_search/blockedusers?query=id:" + blockeduser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(blockeduser.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Blockeduser.class);
        Blockeduser blockeduser1 = new Blockeduser();
        blockeduser1.setId(1L);
        Blockeduser blockeduser2 = new Blockeduser();
        blockeduser2.setId(blockeduser1.getId());
        assertThat(blockeduser1).isEqualTo(blockeduser2);
        blockeduser2.setId(2L);
        assertThat(blockeduser1).isNotEqualTo(blockeduser2);
        blockeduser1.setId(null);
        assertThat(blockeduser1).isNotEqualTo(blockeduser2);
    }
}
