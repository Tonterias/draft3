package com.raro.web.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vote.class)
public abstract class Vote_ {

	public static volatile SingularAttribute<Vote, Proposal> proposal;
	public static volatile SingularAttribute<Vote, Integer> numberOfPoints;
	public static volatile SingularAttribute<Vote, Long> id;
	public static volatile SingularAttribute<Vote, Instant> creationDate;
	public static volatile SingularAttribute<Vote, User> user;

}

