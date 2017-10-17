package com.dayao.prep18.web.rest;

import com.dayao.prep18.CapturaValidacion18App;

import com.dayao.prep18.domain.Encabezado_acta;
import com.dayao.prep18.repository.Encabezado_actaRepository;
import com.dayao.prep18.web.rest.errors.ExceptionTranslator;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Encabezado_actaResource REST controller.
 *
 * @see Encabezado_actaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CapturaValidacion18App.class)
public class Encabezado_actaResourceIntTest {

    private static final Integer DEFAULT_DISTRITO = 1;
    private static final Integer UPDATED_DISTRITO = 2;

    private static final Integer DEFAULT_TIPO_ACTA = 1;
    private static final Integer UPDATED_TIPO_ACTA = 2;

    private static final Integer DEFAULT_SECCION = 1;
    private static final Integer UPDATED_SECCION = 2;

    private static final String DEFAULT_CASILLA = "AAAAAAAAAA";
    private static final String UPDATED_CASILLA = "BBBBBBBBBB";

    private static final String DEFAULT_DIGITALIZACION = "AAAAAAAAAA";
    private static final String UPDATED_DIGITALIZACION = "BBBBBBBBBB";

    private static final Integer DEFAULT_ESTATUS = 1;
    private static final Integer UPDATED_ESTATUS = 2;

    @Autowired
    private Encabezado_actaRepository encabezado_actaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEncabezado_actaMockMvc;

    private Encabezado_acta encabezado_acta;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Encabezado_actaResource encabezado_actaResource = new Encabezado_actaResource(encabezado_actaRepository);
        this.restEncabezado_actaMockMvc = MockMvcBuilders.standaloneSetup(encabezado_actaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Encabezado_acta createEntity(EntityManager em) {
        Encabezado_acta encabezado_acta = new Encabezado_acta()
            .distrito(DEFAULT_DISTRITO)
            .tipo_acta(DEFAULT_TIPO_ACTA)
            .seccion(DEFAULT_SECCION)
            .casilla(DEFAULT_CASILLA)
            .digitalizacion(DEFAULT_DIGITALIZACION)
            .estatus(DEFAULT_ESTATUS);
        return encabezado_acta;
    }

    @Before
    public void initTest() {
        encabezado_acta = createEntity(em);
    }

    @Test
    @Transactional
    public void createEncabezado_acta() throws Exception {
        int databaseSizeBeforeCreate = encabezado_actaRepository.findAll().size();

        // Create the Encabezado_acta
        restEncabezado_actaMockMvc.perform(post("/api/encabezado-actas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(encabezado_acta)))
            .andExpect(status().isCreated());

        // Validate the Encabezado_acta in the database
        List<Encabezado_acta> encabezado_actaList = encabezado_actaRepository.findAll();
        assertThat(encabezado_actaList).hasSize(databaseSizeBeforeCreate + 1);
        Encabezado_acta testEncabezado_acta = encabezado_actaList.get(encabezado_actaList.size() - 1);
        assertThat(testEncabezado_acta.getDistrito()).isEqualTo(DEFAULT_DISTRITO);
        assertThat(testEncabezado_acta.getTipo_acta()).isEqualTo(DEFAULT_TIPO_ACTA);
        assertThat(testEncabezado_acta.getSeccion()).isEqualTo(DEFAULT_SECCION);
        assertThat(testEncabezado_acta.getCasilla()).isEqualTo(DEFAULT_CASILLA);
        assertThat(testEncabezado_acta.getDigitalizacion()).isEqualTo(DEFAULT_DIGITALIZACION);
        assertThat(testEncabezado_acta.getEstatus()).isEqualTo(DEFAULT_ESTATUS);
    }

    @Test
    @Transactional
    public void createEncabezado_actaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = encabezado_actaRepository.findAll().size();

        // Create the Encabezado_acta with an existing ID
        encabezado_acta.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEncabezado_actaMockMvc.perform(post("/api/encabezado-actas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(encabezado_acta)))
            .andExpect(status().isBadRequest());

        // Validate the Encabezado_acta in the database
        List<Encabezado_acta> encabezado_actaList = encabezado_actaRepository.findAll();
        assertThat(encabezado_actaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEncabezado_actas() throws Exception {
        // Initialize the database
        encabezado_actaRepository.saveAndFlush(encabezado_acta);

        // Get all the encabezado_actaList
        restEncabezado_actaMockMvc.perform(get("/api/encabezado-actas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(encabezado_acta.getId().intValue())))
            .andExpect(jsonPath("$.[*].distrito").value(hasItem(DEFAULT_DISTRITO)))
            .andExpect(jsonPath("$.[*].tipo_acta").value(hasItem(DEFAULT_TIPO_ACTA)))
            .andExpect(jsonPath("$.[*].seccion").value(hasItem(DEFAULT_SECCION)))
            .andExpect(jsonPath("$.[*].casilla").value(hasItem(DEFAULT_CASILLA.toString())))
            .andExpect(jsonPath("$.[*].digitalizacion").value(hasItem(DEFAULT_DIGITALIZACION.toString())))
            .andExpect(jsonPath("$.[*].estatus").value(hasItem(DEFAULT_ESTATUS)));
    }

    @Test
    @Transactional
    public void getEncabezado_acta() throws Exception {
        // Initialize the database
        encabezado_actaRepository.saveAndFlush(encabezado_acta);

        // Get the encabezado_acta
        restEncabezado_actaMockMvc.perform(get("/api/encabezado-actas/{id}", encabezado_acta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(encabezado_acta.getId().intValue()))
            .andExpect(jsonPath("$.distrito").value(DEFAULT_DISTRITO))
            .andExpect(jsonPath("$.tipo_acta").value(DEFAULT_TIPO_ACTA))
            .andExpect(jsonPath("$.seccion").value(DEFAULT_SECCION))
            .andExpect(jsonPath("$.casilla").value(DEFAULT_CASILLA.toString()))
            .andExpect(jsonPath("$.digitalizacion").value(DEFAULT_DIGITALIZACION.toString()))
            .andExpect(jsonPath("$.estatus").value(DEFAULT_ESTATUS));
    }

    @Test
    @Transactional
    public void getNonExistingEncabezado_acta() throws Exception {
        // Get the encabezado_acta
        restEncabezado_actaMockMvc.perform(get("/api/encabezado-actas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEncabezado_acta() throws Exception {
        // Initialize the database
        encabezado_actaRepository.saveAndFlush(encabezado_acta);
        int databaseSizeBeforeUpdate = encabezado_actaRepository.findAll().size();

        // Update the encabezado_acta
        Encabezado_acta updatedEncabezado_acta = encabezado_actaRepository.findOne(encabezado_acta.getId());
        updatedEncabezado_acta
            .distrito(UPDATED_DISTRITO)
            .tipo_acta(UPDATED_TIPO_ACTA)
            .seccion(UPDATED_SECCION)
            .casilla(UPDATED_CASILLA)
            .digitalizacion(UPDATED_DIGITALIZACION)
            .estatus(UPDATED_ESTATUS);

        restEncabezado_actaMockMvc.perform(put("/api/encabezado-actas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEncabezado_acta)))
            .andExpect(status().isOk());

        // Validate the Encabezado_acta in the database
        List<Encabezado_acta> encabezado_actaList = encabezado_actaRepository.findAll();
        assertThat(encabezado_actaList).hasSize(databaseSizeBeforeUpdate);
        Encabezado_acta testEncabezado_acta = encabezado_actaList.get(encabezado_actaList.size() - 1);
        assertThat(testEncabezado_acta.getDistrito()).isEqualTo(UPDATED_DISTRITO);
        assertThat(testEncabezado_acta.getTipo_acta()).isEqualTo(UPDATED_TIPO_ACTA);
        assertThat(testEncabezado_acta.getSeccion()).isEqualTo(UPDATED_SECCION);
        assertThat(testEncabezado_acta.getCasilla()).isEqualTo(UPDATED_CASILLA);
        assertThat(testEncabezado_acta.getDigitalizacion()).isEqualTo(UPDATED_DIGITALIZACION);
        assertThat(testEncabezado_acta.getEstatus()).isEqualTo(UPDATED_ESTATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingEncabezado_acta() throws Exception {
        int databaseSizeBeforeUpdate = encabezado_actaRepository.findAll().size();

        // Create the Encabezado_acta

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEncabezado_actaMockMvc.perform(put("/api/encabezado-actas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(encabezado_acta)))
            .andExpect(status().isCreated());

        // Validate the Encabezado_acta in the database
        List<Encabezado_acta> encabezado_actaList = encabezado_actaRepository.findAll();
        assertThat(encabezado_actaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEncabezado_acta() throws Exception {
        // Initialize the database
        encabezado_actaRepository.saveAndFlush(encabezado_acta);
        int databaseSizeBeforeDelete = encabezado_actaRepository.findAll().size();

        // Get the encabezado_acta
        restEncabezado_actaMockMvc.perform(delete("/api/encabezado-actas/{id}", encabezado_acta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Encabezado_acta> encabezado_actaList = encabezado_actaRepository.findAll();
        assertThat(encabezado_actaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Encabezado_acta.class);
        Encabezado_acta encabezado_acta1 = new Encabezado_acta();
        encabezado_acta1.setId(1L);
        Encabezado_acta encabezado_acta2 = new Encabezado_acta();
        encabezado_acta2.setId(encabezado_acta1.getId());
        assertThat(encabezado_acta1).isEqualTo(encabezado_acta2);
        encabezado_acta2.setId(2L);
        assertThat(encabezado_acta1).isNotEqualTo(encabezado_acta2);
        encabezado_acta1.setId(null);
        assertThat(encabezado_acta1).isNotEqualTo(encabezado_acta2);
    }
}
