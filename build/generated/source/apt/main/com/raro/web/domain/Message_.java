package com.raro.web.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ {

	public static volatile SingularAttribute<Message, String> messageText;
	public static volatile SingularAttribute<Message, Boolean> isDeliverd;
	public static volatile SingularAttribute<Message, Long> id;
	public static volatile SingularAttribute<Message, Instant> creationDate;
	public static volatile SingularAttribute<Message, Party> party;

}

