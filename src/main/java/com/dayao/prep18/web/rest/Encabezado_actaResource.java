package com.dayao.prep18.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.dayao.prep18.domain.Encabezado_acta;

import com.dayao.prep18.repository.Encabezado_actaRepository;
import com.dayao.prep18.web.rest.util.HeaderUtil;
import com.dayao.prep18.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
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

/**
 * REST controller for managing Encabezado_acta.
 */
@RestController
@RequestMapping("/api")
public class Encabezado_actaResource {

    private final Logger log = LoggerFactory.getLogger(Encabezado_actaResource.class);

    private static final String ENTITY_NAME = "encabezado_acta";

    private final Encabezado_actaRepository encabezado_actaRepository;

    public Encabezado_actaResource(Encabezado_actaRepository encabezado_actaRepository) {
        this.encabezado_actaRepository = encabezado_actaRepository;
    }

    /**
     * POST  /encabezado-actas : Create a new encabezado_acta.
     *
     * @param encabezado_acta the encabezado_acta to create
     * @return the ResponseEntity with status 201 (Created) and with body the new encabezado_acta, or with status 400 (Bad Request) if the encabezado_acta has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/encabezado-actas")
    @Timed
    public ResponseEntity<Encabezado_acta> createEncabezado_acta(@RequestBody Encabezado_acta encabezado_acta) throws URISyntaxException {
        log.debug("REST request to save Encabezado_acta : {}", encabezado_acta);
        if (encabezado_acta.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new encabezado_acta cannot already have an ID")).body(null);
        }
        Encabezado_acta result = encabezado_actaRepository.save(encabezado_acta);
        return ResponseEntity.created(new URI("/api/encabezado-actas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /encabezado-actas : Updates an existing encabezado_acta.
     *
     * @param encabezado_acta the encabezado_acta to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated encabezado_acta,
     * or with status 400 (Bad Request) if the encabezado_acta is not valid,
     * or with status 500 (Internal Server Error) if the encabezado_acta couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/encabezado-actas")
    @Timed
    public ResponseEntity<Encabezado_acta> updateEncabezado_acta(@RequestBody Encabezado_acta encabezado_acta) throws URISyntaxException {
        log.debug("REST request to update Encabezado_acta : {}", encabezado_acta);
        if (encabezado_acta.getId() == null) {
            return createEncabezado_acta(encabezado_acta);
        }
        Encabezado_acta result = encabezado_actaRepository.save(encabezado_acta);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, encabezado_acta.getId().toString()))
            .body(result);
    }

    /**
     * GET  /encabezado-actas : get all the encabezado_actas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of encabezado_actas in body
     */
    @GetMapping("/encabezado-actas")
    @Timed
    public ResponseEntity<List<Encabezado_acta>> getAllEncabezado_actas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Encabezado_actas");
        Page<Encabezado_acta> page = encabezado_actaRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/encabezado-actas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /encabezado-actas/:id : get the "id" encabezado_acta.
     *
     * @param id the id of the encabezado_acta to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the encabezado_acta, or with status 404 (Not Found)
     */
    @GetMapping("/encabezado-actas/{id}")
    @Timed
    public ResponseEntity<Encabezado_acta> getEncabezado_acta(@PathVariable Long id) {
        log.debug("REST request to get Encabezado_acta : {}", id);
        Encabezado_acta encabezado_acta = encabezado_actaRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(encabezado_acta));
    }

    /**
     * DELETE  /encabezado-actas/:id : delete the "id" encabezado_acta.
     *
     * @param id the id of the encabezado_acta to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/encabezado-actas/{id}")
    @Timed
    public ResponseEntity<Void> deleteEncabezado_acta(@PathVariable Long id) {
        log.debug("REST request to delete Encabezado_acta : {}", id);
        encabezado_actaRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
