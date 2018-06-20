package com.raro.web.domain;

import com.raro.web.domain.enumeration.NotificationReason;
import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Notification.class)
public abstract class Notification_ {

	public static volatile SingularAttribute<Notification, String> notificationText;
	public static volatile SingularAttribute<Notification, Boolean> isDeliverd;
	public static volatile SingularAttribute<Notification, NotificationReason> notificationReason;
	public static volatile SingularAttribute<Notification, Long> id;
	public static volatile SingularAttribute<Notification, Instant> creationDate;
	public static volatile SingularAttribute<Notification, User> user;
	public static volatile SingularAttribute<Notification, Instant> notificationDate;

}

