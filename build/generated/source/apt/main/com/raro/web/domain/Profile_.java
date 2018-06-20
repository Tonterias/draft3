package com.raro.web.domain;

import com.raro.web.domain.enumeration.Children;
import com.raro.web.domain.enumeration.CivilStatus;
import com.raro.web.domain.enumeration.EthnicGroup;
import com.raro.web.domain.enumeration.Eyes;
import com.raro.web.domain.enumeration.FutureChildren;
import com.raro.web.domain.enumeration.Gender;
import com.raro.web.domain.enumeration.Physical;
import com.raro.web.domain.enumeration.Purpose;
import com.raro.web.domain.enumeration.Religion;
import com.raro.web.domain.enumeration.Smoker;
import com.raro.web.domain.enumeration.Studies;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profile.class)
public abstract class Profile_ {

	public static volatile SingularAttribute<Profile, byte[]> image;
	public static volatile SingularAttribute<Profile, Instant> birthdate;
	public static volatile SingularAttribute<Profile, Gender> gender;
	public static volatile SingularAttribute<Profile, Purpose> purpose;
	public static volatile SingularAttribute<Profile, String> bio;
	public static volatile SingularAttribute<Profile, Gender> lookingFor;
	public static volatile SingularAttribute<Profile, Instant> creationDate;
	public static volatile SingularAttribute<Profile, Eyes> eyes;
	public static volatile SingularAttribute<Profile, CivilStatus> civilStatus;
	public static volatile SingularAttribute<Profile, Religion> religion;
	public static volatile SingularAttribute<Profile, Smoker> smoker;
	public static volatile SingularAttribute<Profile, Long> userPoints;
	public static volatile SingularAttribute<Profile, String> phone;
	public static volatile SingularAttribute<Profile, Children> children;
	public static volatile SingularAttribute<Profile, String> imageContentType;
	public static volatile SingularAttribute<Profile, Studies> studies;
	public static volatile SingularAttribute<Profile, Long> id;
	public static volatile SingularAttribute<Profile, Physical> physical;
	public static volatile SingularAttribute<Profile, EthnicGroup> ethnicGroup;
	public static volatile SingularAttribute<Profile, Integer> sibblings;
	public static volatile SingularAttribute<Profile, User> user;
	public static volatile SingularAttribute<Profile, Boolean> pet;
	public static volatile SingularAttribute<Profile, FutureChildren> futureChildren;

}

