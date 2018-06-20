package com.raro.web.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Comment.class)
public abstract class Comment_ {

	public static volatile SingularAttribute<Comment, Post> post;
	public static volatile SingularAttribute<Comment, Boolean> isOffensive;
	public static volatile SingularAttribute<Comment, Long> id;
	public static volatile SingularAttribute<Comment, Instant> creationDate;
	public static volatile SingularAttribute<Comment, String> commentText;
	public static volatile SingularAttribute<Comment, Party> party;

}

