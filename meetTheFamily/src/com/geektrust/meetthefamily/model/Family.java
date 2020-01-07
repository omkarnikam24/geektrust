package com.geektrust.meetthefamily.model;

import java.util.HashSet;
import java.util.Set;

import com.geektrust.meetthefamily.enums.Gender;
import com.geektrust.meetthefamily.processor.Relations;
import com.geektrust.meetthefamily.utility.Constants;

/**
 * @author onikam
 */
public class Family {

	private Person familyHead = null;

	private static final String FEMALE = "Female";
	private static final String MALE = "Male";

	/**
	 * 
	 * @param name
	 * @param gender
	 * @return
	 */
	public String addFamilyHead(String name, String gender) {
		if (null != familyHead)
			return Constants.FAMILY_HEAD_ALREADY_EXISTS;
		Gender g = FEMALE.equalsIgnoreCase(gender) ? Gender.Female : Gender.Male;
		this.familyHead = new Person(name, g, null, null);

		return Constants.FAMILY_HEAD_ADDITION_SUCCEEDED;
	}
	
	/**
	 * 
	 * @param name
	 * @param spouseName
	 * @param gender
	 * @return
	 */
	public String addSpouse(String name, String spouseName, String gender) {
		if (null == name || null == spouseName || (!FEMALE.equalsIgnoreCase(gender) && !MALE.equalsIgnoreCase(gender)))
			return Constants.INVALID_COMMAND;
		Person person = findPerson(familyHead, name);
		if (null != person && person.getSpouse() == null && !person.getGender().toString().equalsIgnoreCase(gender)) {
			Gender g = FEMALE.equalsIgnoreCase(gender) ? Gender.Female : Gender.Male;
			Person spouse = new Person(spouseName, g, null, null);
			spouse.setSpouse(person);
			person.setSpouse(spouse);
		} else
			return Constants.SPOUSE_ADDITION_FAILED;
		return Constants.SPOUSE_ADDITION_SUCCEEDED;
	}

	/**
	 * 
	 * @param motherName
	 * @param childName
	 * @param gender
	 * @return
	 */
	public String addchild(String motherName, String childName, String gender) {
		if (null == motherName || null == childName
				|| (!FEMALE.equalsIgnoreCase(gender) && !MALE.equalsIgnoreCase(gender)))
			return Constants.INVALID_COMMAND;
		String result;
		Person mother = findPerson(familyHead, motherName);
		if (null == mother)
			result = Constants.PERSON_NOT_FOUND;
		else if (null == childName || null == gender)
			result = Constants.CHILD_ADDITION_FAILED;
		else if (mother.getGender() == Gender.Female) {
			Gender g = FEMALE.equalsIgnoreCase(gender) ? Gender.Female : Gender.Male;
			Person child = new Person(childName, g, mother, mother.getSpouse());
			mother.getChildren().add(child);
			result = Constants.CHILD_ADDITION_SUCCEEDED;
		} else
			result = Constants.CHILD_ADDITION_FAILED;

		return result;
	}

	/**
	 * 
	 * @param personName
	 * @param relationship
	 * @return
	 */
	public String getRelationship(String personName, String relationship) {
		String result = null;
		Person person = findPerson(familyHead, personName);
		if(null == person)
			result = Constants.PERSON_NOT_FOUND;
		else if (null == relationship)
			result = Constants.INVALID_RELATION;
		else {
			Relations relation = new Relations();
			result = relation.findRelative(person, relationship);
		}
		return result;
	}

	/**
	 * 
	 * @param head
	 * @param personName
	 * @return
	 */
	private Person findPerson(Person head, String personName) {
		Person person = null;
		if (personName.equals(head.getName()))
			return head;
		else if (null != head.getSpouse() && personName.equals(head.getSpouse().getName()))
			return head.getSpouse();
		Set<Person> children = new HashSet<>();
		if (head.getGender() == Gender.Female)
			children = head.getChildren();
		else if (null != head.getSpouse())
			children = head.getSpouse().getChildren();

		for (Person p : children) {
			person = findPerson(p, personName);
			if (person != null)
				break;
		}
		return person;
	}
}
