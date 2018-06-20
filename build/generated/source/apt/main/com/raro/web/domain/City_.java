package com.raro.web.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(City.class)
public abstract class City_ {

	public static volatile SetAttribute<City, Address> addresses;
	public static volatile SingularAttribute<City, String> cityName;
	public static volatile SingularAttribute<City, Long> id;
	public static volatile SingularAttribute<City, State> state;

}

