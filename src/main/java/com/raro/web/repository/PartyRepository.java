package com.raro.web.repository;

import com.raro.web.domain.Party;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the Party entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PartyRepository extends JpaRepository<Party, Long> {

    @Query("select party from Party party where party.user.login = ?#{principal.username}")
    List<Party> findByUserIsCurrentUser();

}
