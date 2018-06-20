package com.raro.web.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Photo.class)
public abstract class Photo_ {

	public static volatile SingularAttribute<Photo, byte[]> image;
	public static volatile SingularAttribute<Photo, Album> album;
	public static volatile SingularAttribute<Photo, String> imageContentType;
	public static volatile SingularAttribute<Photo, Long> id;
	public static volatile SingularAttribute<Photo, Instant> creationDate;

}

