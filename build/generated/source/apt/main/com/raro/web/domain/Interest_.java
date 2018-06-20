package com.raro.web.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Interest.class)
public abstract class Interest_ {

	public static volatile SetAttribute<Interest, Party> parties;
	public static volatile SingularAttribute<Interest, Long> id;
	public static volatile SingularAttribute<Interest, String> interestName;

}

