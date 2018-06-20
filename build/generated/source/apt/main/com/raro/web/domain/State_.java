package com.raro.web.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(State.class)
public abstract class State_ {

	public static volatile SingularAttribute<State, Country> country;
	public static volatile SetAttribute<State, City> cities;
	public static volatile SingularAttribute<State, String> stateName;
	public static volatile SingularAttribute<State, Long> id;

}

