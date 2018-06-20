package com.raro.web.domain;

import com.raro.web.domain.enumeration.ProposalType;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Proposal.class)
public abstract class Proposal_ {

	public static volatile SingularAttribute<Proposal, Post> post;
	public static volatile SingularAttribute<Proposal, Instant> releaseDate;
	public static volatile SetAttribute<Proposal, Vote> votes;
	public static volatile SingularAttribute<Proposal, Long> id;
	public static volatile SingularAttribute<Proposal, String> functionality;
	public static volatile SingularAttribute<Proposal, ProposalType> proposalType;
	public static volatile SingularAttribute<Proposal, Instant> creationDate;
	public static volatile SingularAttribute<Proposal, Party> party;

}

