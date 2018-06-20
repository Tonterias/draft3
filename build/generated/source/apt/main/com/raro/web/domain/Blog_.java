package com.raro.web.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Blog.class)
public abstract class Blog_ {

	public static volatile SingularAttribute<Blog, byte[]> image;
	public static volatile SingularAttribute<Blog, String> imageContentType;
	public static volatile SingularAttribute<Blog, Long> id;
	public static volatile SingularAttribute<Blog, Instant> creationDate;
	public static volatile SingularAttribute<Blog, String> title;
	public static volatile SetAttribute<Blog, Post> posts;
	public static volatile SingularAttribute<Blog, Party> party;

}

