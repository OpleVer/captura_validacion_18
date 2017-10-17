package com.dayao.prep18.repository;

import com.dayao.prep18.domain.Encabezado_acta;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Encabezado_acta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Encabezado_actaRepository extends JpaRepository<Encabezado_acta, Long> {

}
