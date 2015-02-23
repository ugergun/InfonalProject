package com.umutgergun.infonal.converter;

import org.bson.types.ObjectId;

import com.umutgergun.infonal.model.Person;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class PersonConverter {

	public static DBObject toDBObject(Person p) {

		BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
				.append("firstName", p.getFirstName())
				.append("lastName", p.getLastName())
				.append("phoneNumber", p.getPhoneNumber());
		if (p.getId() != null)
			builder = builder.append("_id", new ObjectId(p.getId()));
		return builder.get();
	}

	public static Person toPerson(DBObject doc) {
		Person p = new Person();
		p.setFirstName((String) doc.get("firstName"));
		p.setLastName((String) doc.get("lastName"));
		p.setPhoneNumber((String) doc.get("phoneNumber"));
		ObjectId id = (ObjectId) doc.get("_id");
		p.setId(id.toString());
		return p;

	}

}