package com.raro.web.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Party.class)
public abstract class Party_ {

	public static volatile SingularAttribute<Party, byte[]> image;
	public static volatile SetAttribute<Party, Comment> comments;
	public static volatile SingularAttribute<Party, Address> address;
	public static volatile SetAttribute<Party, Blockeduser> blockingusers;
	public static volatile SetAttribute<Party, Follow> followeds;
	public static volatile SingularAttribute<Party, String> partyname;
	public static volatile SetAttribute<Party, Blog> blogs;
	public static volatile SingularAttribute<Party, Album> album;
	public static volatile SingularAttribute<Party, Instant> creationDate;
	public static volatile SingularAttribute<Party, Boolean> isActive;
	public static volatile SingularAttribute<Party, String> partydescription;
	public static volatile SetAttribute<Party, Follow> followings;
	public static volatile SetAttribute<Party, Blockeduser> blockedusers;
	public static volatile SetAttribute<Party, Activity> activities;
	public static volatile SingularAttribute<Party, String> imageContentType;
	public static volatile SetAttribute<Party, Message> messages;
	public static volatile SingularAttribute<Party, Long> id;
	public static volatile SetAttribute<Party, Interest> interests;
	public static volatile SingularAttribute<Party, User> user;
	public static volatile SetAttribute<Party, Celeb> celebs;

}

