package com.raro.web.web.rest;

import com.raro.web.SkeletonApp;

import com.raro.web.domain.Profile;
import com.raro.web.domain.User;
import com.raro.web.repository.ProfileRepository;
import com.raro.web.repository.search.ProfileSearchRepository;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.raro.web.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.raro.web.domain.enumeration.Gender;
import com.raro.web.domain.enumeration.CivilStatus;
import com.raro.web.domain.enumeration.Gender;
import com.raro.web.domain.enumeration.Purpose;
import com.raro.web.domain.enumeration.Physical;
import com.raro.web.domain.enumeration.Religion;
import com.raro.web.domain.enumeration.EthnicGroup;
import com.raro.web.domain.enumeration.Studies;
import com.raro.web.domain.enumeration.Eyes;
import com.raro.web.domain.enumeration.Smoker;
import com.raro.web.domain.enumeration.Children;
import com.raro.web.domain.enumeration.FutureChildren;
/**
 * Test class for the ProfileResource REST controller.
 *
 * @see ProfileResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SkeletonApp.class)
public class ProfileResourceIntTest {

    private static final Instant DEFAULT_CREATION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_BIO = "AAAAAAAAAA";
    private static final String UPDATED_BIO = "BBBBBBBBBB";

    private static final Instant DEFAULT_BIRTHDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_BIRTHDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final CivilStatus DEFAULT_CIVIL_STATUS = CivilStatus.SINGLE;
    private static final CivilStatus UPDATED_CIVIL_STATUS = CivilStatus.MARRIED;

    private static final Gender DEFAULT_LOOKING_FOR = Gender.MALE;
    private static final Gender UPDATED_LOOKING_FOR = Gender.FEMALE;

    private static final Purpose DEFAULT_PURPOSE = Purpose.NOT_INTERESTED;
    private static final Purpose UPDATED_PURPOSE = Purpose.FRIENDSHIP;

    private static final Physical DEFAULT_PHYSICAL = Physical.NA;
    private static final Physical UPDATED_PHYSICAL = Physical.THIN;

    private static final Religion DEFAULT_RELIGION = Religion.NA;
    private static final Religion UPDATED_RELIGION = Religion.ATHEIST;

    private static final EthnicGroup DEFAULT_ETHNIC_GROUP = EthnicGroup.NA;
    private static final EthnicGroup UPDATED_ETHNIC_GROUP = EthnicGroup.MIXED;

    private static final Studies DEFAULT_STUDIES = Studies.NA;
    private static final Studies UPDATED_STUDIES = Studies.PRIMARY;

    private static final Integer DEFAULT_SIBBLINGS = 0;
    private static final Integer UPDATED_SIBBLINGS = 1;

    private static final Eyes DEFAULT_EYES = Eyes.NA;
    private static final Eyes UPDATED_EYES = Eyes.BLUE;

    private static final Smoker DEFAULT_SMOKER = Smoker.NA;
    private static final Smoker UPDATED_SMOKER = Smoker.YES;

    private static final Children DEFAULT_CHILDREN = Children.NA;
    private static final Children UPDATED_CHILDREN = Children.YES;

    private static final FutureChildren DEFAULT_FUTURE_CHILDREN = FutureChildren.NA;
    private static final FutureChildren UPDATED_FUTURE_CHILDREN = FutureChildren.YES;

    private static final Boolean DEFAULT_PET = false;
    private static final Boolean UPDATED_PET = true;

    private static final Long DEFAULT_USER_POINTS = 100L;
    private static final Long UPDATED_USER_POINTS = 101L;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileSearchRepository profileSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProfileMockMvc;

    private Profile profile;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProfileResource profileResource = new ProfileResource(profileRepository, profileSearchRepository);
        this.restProfileMockMvc = MockMvcBuilders.standaloneSetup(profileResource)
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
    public static Profile createEntity(EntityManager em) {
        Profile profile = new Profile()
            .creationDate(DEFAULT_CREATION_DATE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .gender(DEFAULT_GENDER)
            .phone(DEFAULT_PHONE)
            .bio(DEFAULT_BIO)
            .birthdate(DEFAULT_BIRTHDATE)
            .civilStatus(DEFAULT_CIVIL_STATUS)
            .lookingFor(DEFAULT_LOOKING_FOR)
            .purpose(DEFAULT_PURPOSE)
            .physical(DEFAULT_PHYSICAL)
            .religion(DEFAULT_RELIGION)
            .ethnicGroup(DEFAULT_ETHNIC_GROUP)
            .studies(DEFAULT_STUDIES)
            .sibblings(DEFAULT_SIBBLINGS)
            .eyes(DEFAULT_EYES)
            .smoker(DEFAULT_SMOKER)
            .children(DEFAULT_CHILDREN)
            .futureChildren(DEFAULT_FUTURE_CHILDREN)
            .pet(DEFAULT_PET)
            .userPoints(DEFAULT_USER_POINTS);
        // Add required entity
        User user = UserResourceIntTest.createEntity(em);
        em.persist(user);
        em.flush();
        profile.setUser(user);
        return profile;
    }

    @Before
    public void initTest() {
        profileSearchRepository.deleteAll();
        profile = createEntity(em);
    }

    @Test
    @Transactional
    public void createProfile() throws Exception {
        int databaseSizeBeforeCreate = profileRepository.findAll().size();

        // Create the Profile
        restProfileMockMvc.perform(post("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profile)))
            .andExpect(status().isCreated());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeCreate + 1);
        Profile testProfile = profileList.get(profileList.size() - 1);
        assertThat(testProfile.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testProfile.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testProfile.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
        assertThat(testProfile.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testProfile.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testProfile.getBio()).isEqualTo(DEFAULT_BIO);
        assertThat(testProfile.getBirthdate()).isEqualTo(DEFAULT_BIRTHDATE);
        assertThat(testProfile.getCivilStatus()).isEqualTo(DEFAULT_CIVIL_STATUS);
        assertThat(testProfile.getLookingFor()).isEqualTo(DEFAULT_LOOKING_FOR);
        assertThat(testProfile.getPurpose()).isEqualTo(DEFAULT_PURPOSE);
        assertThat(testProfile.getPhysical()).isEqualTo(DEFAULT_PHYSICAL);
        assertThat(testProfile.getReligion()).isEqualTo(DEFAULT_RELIGION);
        assertThat(testProfile.getEthnicGroup()).isEqualTo(DEFAULT_ETHNIC_GROUP);
        assertThat(testProfile.getStudies()).isEqualTo(DEFAULT_STUDIES);
        assertThat(testProfile.getSibblings()).isEqualTo(DEFAULT_SIBBLINGS);
        assertThat(testProfile.getEyes()).isEqualTo(DEFAULT_EYES);
        assertThat(testProfile.getSmoker()).isEqualTo(DEFAULT_SMOKER);
        assertThat(testProfile.getChildren()).isEqualTo(DEFAULT_CHILDREN);
        assertThat(testProfile.getFutureChildren()).isEqualTo(DEFAULT_FUTURE_CHILDREN);
        assertThat(testProfile.isPet()).isEqualTo(DEFAULT_PET);
        assertThat(testProfile.getUserPoints()).isEqualTo(DEFAULT_USER_POINTS);

        // Validate the Profile in Elasticsearch
        Profile profileEs = profileSearchRepository.findOne(testProfile.getId());
        assertThat(profileEs).isEqualToIgnoringGivenFields(testProfile);
    }

    @Test
    @Transactional
    public void createProfileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = profileRepository.findAll().size();

        // Create the Profile with an existing ID
        profile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfileMockMvc.perform(post("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profile)))
            .andExpect(status().isBadRequest());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCreationDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setCreationDate(null);

        // Create the Profile, which fails.

        restProfileMockMvc.perform(post("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profile)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setGender(null);

        // Create the Profile, which fails.

        restProfileMockMvc.perform(post("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profile)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = profileRepository.findAll().size();
        // set the field null
        profile.setPhone(null);

        // Create the Profile, which fails.

        restProfileMockMvc.perform(post("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profile)))
            .andExpect(status().isBadRequest());

        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProfiles() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get all the profileList
        restProfileMockMvc.perform(get("/api/profiles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profile.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].civilStatus").value(hasItem(DEFAULT_CIVIL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].lookingFor").value(hasItem(DEFAULT_LOOKING_FOR.toString())))
            .andExpect(jsonPath("$.[*].purpose").value(hasItem(DEFAULT_PURPOSE.toString())))
            .andExpect(jsonPath("$.[*].physical").value(hasItem(DEFAULT_PHYSICAL.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].ethnicGroup").value(hasItem(DEFAULT_ETHNIC_GROUP.toString())))
            .andExpect(jsonPath("$.[*].studies").value(hasItem(DEFAULT_STUDIES.toString())))
            .andExpect(jsonPath("$.[*].sibblings").value(hasItem(DEFAULT_SIBBLINGS)))
            .andExpect(jsonPath("$.[*].eyes").value(hasItem(DEFAULT_EYES.toString())))
            .andExpect(jsonPath("$.[*].smoker").value(hasItem(DEFAULT_SMOKER.toString())))
            .andExpect(jsonPath("$.[*].children").value(hasItem(DEFAULT_CHILDREN.toString())))
            .andExpect(jsonPath("$.[*].futureChildren").value(hasItem(DEFAULT_FUTURE_CHILDREN.toString())))
            .andExpect(jsonPath("$.[*].pet").value(hasItem(DEFAULT_PET.booleanValue())))
            .andExpect(jsonPath("$.[*].userPoints").value(hasItem(DEFAULT_USER_POINTS.intValue())));
    }

    @Test
    @Transactional
    public void getProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);

        // Get the profile
        restProfileMockMvc.perform(get("/api/profiles/{id}", profile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(profile.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE.toString()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.toString()))
            .andExpect(jsonPath("$.bio").value(DEFAULT_BIO.toString()))
            .andExpect(jsonPath("$.birthdate").value(DEFAULT_BIRTHDATE.toString()))
            .andExpect(jsonPath("$.civilStatus").value(DEFAULT_CIVIL_STATUS.toString()))
            .andExpect(jsonPath("$.lookingFor").value(DEFAULT_LOOKING_FOR.toString()))
            .andExpect(jsonPath("$.purpose").value(DEFAULT_PURPOSE.toString()))
            .andExpect(jsonPath("$.physical").value(DEFAULT_PHYSICAL.toString()))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION.toString()))
            .andExpect(jsonPath("$.ethnicGroup").value(DEFAULT_ETHNIC_GROUP.toString()))
            .andExpect(jsonPath("$.studies").value(DEFAULT_STUDIES.toString()))
            .andExpect(jsonPath("$.sibblings").value(DEFAULT_SIBBLINGS))
            .andExpect(jsonPath("$.eyes").value(DEFAULT_EYES.toString()))
            .andExpect(jsonPath("$.smoker").value(DEFAULT_SMOKER.toString()))
            .andExpect(jsonPath("$.children").value(DEFAULT_CHILDREN.toString()))
            .andExpect(jsonPath("$.futureChildren").value(DEFAULT_FUTURE_CHILDREN.toString()))
            .andExpect(jsonPath("$.pet").value(DEFAULT_PET.booleanValue()))
            .andExpect(jsonPath("$.userPoints").value(DEFAULT_USER_POINTS.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProfile() throws Exception {
        // Get the profile
        restProfileMockMvc.perform(get("/api/profiles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);
        profileSearchRepository.save(profile);
        int databaseSizeBeforeUpdate = profileRepository.findAll().size();

        // Update the profile
        Profile updatedProfile = profileRepository.findOne(profile.getId());
        // Disconnect from session so that the updates on updatedProfile are not directly saved in db
        em.detach(updatedProfile);
        updatedProfile
            .creationDate(UPDATED_CREATION_DATE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .gender(UPDATED_GENDER)
            .phone(UPDATED_PHONE)
            .bio(UPDATED_BIO)
            .birthdate(UPDATED_BIRTHDATE)
            .civilStatus(UPDATED_CIVIL_STATUS)
            .lookingFor(UPDATED_LOOKING_FOR)
            .purpose(UPDATED_PURPOSE)
            .physical(UPDATED_PHYSICAL)
            .religion(UPDATED_RELIGION)
            .ethnicGroup(UPDATED_ETHNIC_GROUP)
            .studies(UPDATED_STUDIES)
            .sibblings(UPDATED_SIBBLINGS)
            .eyes(UPDATED_EYES)
            .smoker(UPDATED_SMOKER)
            .children(UPDATED_CHILDREN)
            .futureChildren(UPDATED_FUTURE_CHILDREN)
            .pet(UPDATED_PET)
            .userPoints(UPDATED_USER_POINTS);

        restProfileMockMvc.perform(put("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProfile)))
            .andExpect(status().isOk());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeUpdate);
        Profile testProfile = profileList.get(profileList.size() - 1);
        assertThat(testProfile.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testProfile.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testProfile.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
        assertThat(testProfile.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testProfile.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testProfile.getBio()).isEqualTo(UPDATED_BIO);
        assertThat(testProfile.getBirthdate()).isEqualTo(UPDATED_BIRTHDATE);
        assertThat(testProfile.getCivilStatus()).isEqualTo(UPDATED_CIVIL_STATUS);
        assertThat(testProfile.getLookingFor()).isEqualTo(UPDATED_LOOKING_FOR);
        assertThat(testProfile.getPurpose()).isEqualTo(UPDATED_PURPOSE);
        assertThat(testProfile.getPhysical()).isEqualTo(UPDATED_PHYSICAL);
        assertThat(testProfile.getReligion()).isEqualTo(UPDATED_RELIGION);
        assertThat(testProfile.getEthnicGroup()).isEqualTo(UPDATED_ETHNIC_GROUP);
        assertThat(testProfile.getStudies()).isEqualTo(UPDATED_STUDIES);
        assertThat(testProfile.getSibblings()).isEqualTo(UPDATED_SIBBLINGS);
        assertThat(testProfile.getEyes()).isEqualTo(UPDATED_EYES);
        assertThat(testProfile.getSmoker()).isEqualTo(UPDATED_SMOKER);
        assertThat(testProfile.getChildren()).isEqualTo(UPDATED_CHILDREN);
        assertThat(testProfile.getFutureChildren()).isEqualTo(UPDATED_FUTURE_CHILDREN);
        assertThat(testProfile.isPet()).isEqualTo(UPDATED_PET);
        assertThat(testProfile.getUserPoints()).isEqualTo(UPDATED_USER_POINTS);

        // Validate the Profile in Elasticsearch
        Profile profileEs = profileSearchRepository.findOne(testProfile.getId());
        assertThat(profileEs).isEqualToIgnoringGivenFields(testProfile);
    }

    @Test
    @Transactional
    public void updateNonExistingProfile() throws Exception {
        int databaseSizeBeforeUpdate = profileRepository.findAll().size();

        // Create the Profile

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProfileMockMvc.perform(put("/api/profiles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(profile)))
            .andExpect(status().isCreated());

        // Validate the Profile in the database
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);
        profileSearchRepository.save(profile);
        int databaseSizeBeforeDelete = profileRepository.findAll().size();

        // Get the profile
        restProfileMockMvc.perform(delete("/api/profiles/{id}", profile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean profileExistsInEs = profileSearchRepository.exists(profile.getId());
        assertThat(profileExistsInEs).isFalse();

        // Validate the database is empty
        List<Profile> profileList = profileRepository.findAll();
        assertThat(profileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchProfile() throws Exception {
        // Initialize the database
        profileRepository.saveAndFlush(profile);
        profileSearchRepository.save(profile);

        // Search the profile
        restProfileMockMvc.perform(get("/api/_search/profiles?query=id:" + profile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(profile.getId().intValue())))
            .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE.toString())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.toString())))
            .andExpect(jsonPath("$.[*].bio").value(hasItem(DEFAULT_BIO.toString())))
            .andExpect(jsonPath("$.[*].birthdate").value(hasItem(DEFAULT_BIRTHDATE.toString())))
            .andExpect(jsonPath("$.[*].civilStatus").value(hasItem(DEFAULT_CIVIL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].lookingFor").value(hasItem(DEFAULT_LOOKING_FOR.toString())))
            .andExpect(jsonPath("$.[*].purpose").value(hasItem(DEFAULT_PURPOSE.toString())))
            .andExpect(jsonPath("$.[*].physical").value(hasItem(DEFAULT_PHYSICAL.toString())))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION.toString())))
            .andExpect(jsonPath("$.[*].ethnicGroup").value(hasItem(DEFAULT_ETHNIC_GROUP.toString())))
            .andExpect(jsonPath("$.[*].studies").value(hasItem(DEFAULT_STUDIES.toString())))
            .andExpect(jsonPath("$.[*].sibblings").value(hasItem(DEFAULT_SIBBLINGS)))
            .andExpect(jsonPath("$.[*].eyes").value(hasItem(DEFAULT_EYES.toString())))
            .andExpect(jsonPath("$.[*].smoker").value(hasItem(DEFAULT_SMOKER.toString())))
            .andExpect(jsonPath("$.[*].children").value(hasItem(DEFAULT_CHILDREN.toString())))
            .andExpect(jsonPath("$.[*].futureChildren").value(hasItem(DEFAULT_FUTURE_CHILDREN.toString())))
            .andExpect(jsonPath("$.[*].pet").value(hasItem(DEFAULT_PET.booleanValue())))
            .andExpect(jsonPath("$.[*].userPoints").value(hasItem(DEFAULT_USER_POINTS.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Profile.class);
        Profile profile1 = new Profile();
        profile1.setId(1L);
        Profile profile2 = new Profile();
        profile2.setId(profile1.getId());
        assertThat(profile1).isEqualTo(profile2);
        profile2.setId(2L);
        assertThat(profile1).isNotEqualTo(profile2);
        profile1.setId(null);
        assertThat(profile1).isNotEqualTo(profile2);
    }
}
