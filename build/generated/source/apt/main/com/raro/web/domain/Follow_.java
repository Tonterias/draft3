package com.raro.web.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Follow.class)
public abstract class Follow_ {

	public static volatile SingularAttribute<Follow, Party> following;
	public static volatile SingularAttribute<Follow, Long> id;
	public static volatile SingularAttribute<Follow, Instant> creationDate;
	public static volatile SingularAttribute<Follow, Party> followed;

}

