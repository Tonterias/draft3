package com.raro.web.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Celeb.class)
public abstract class Celeb_ {

	public static volatile SingularAttribute<Celeb, String> celebName;
	public static volatile SetAttribute<Celeb, Party> parties;
	public static volatile SingularAttribute<Celeb, Long> id;

}

