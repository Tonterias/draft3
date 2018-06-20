package com.raro.web.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Album.class)
public abstract class Album_ {

	public static volatile SingularAttribute<Album, Long> id;
	public static volatile SingularAttribute<Album, Instant> creationDate;
	public static volatile SingularAttribute<Album, String> title;
	public static volatile SetAttribute<Album, Photo> photos;
	public static volatile SingularAttribute<Album, Party> party;

}

