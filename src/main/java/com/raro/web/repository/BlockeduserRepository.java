package com.raro.web.repository;

import com.raro.web.domain.Blockeduser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Blockeduser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BlockeduserRepository extends JpaRepository<Blockeduser, Long> {

}
