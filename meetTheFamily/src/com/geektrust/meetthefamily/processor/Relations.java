package com.geektrust.meetthefamily.processor;

import com.geektrust.meetthefamily.enums.Gender;
import com.geektrust.meetthefamily.model.Person;
import com.geektrust.meetthefamily.utility.Constants;

/**
 * @author onikam
 */
public class Relations {

	/**
	 * @param person
	 * @param relationship
	 * @return
	 */
	public String findRelative(Person person, String relationship) {
		String relations = null;
		switch (relationship) {

		case Constants.DAUGHTER:
			relations = searchChild(person, Gender.Female);
			break;

		case Constants.SON:
			relations = searchChild(person, Gender.Male);
			break;

		case Constants.SIBLINGS:
			relations = searchSiblings(person);
			break;

		case Constants.SISTER_IN_LAW:
			relations = searchSisterInLaws(person);
			break;

		case Constants.BROTHER_IN_LAW:
			relations = searchBrotherInLaws(person);
			break;

		case Constants.MATERNAL_AUNT:
			if (null != person.getMother())
				relations = searchAuntOrUncle(person.getMother(), Gender.Female);
			break;

		case Constants.PATERNAL_AUNT:
			if (null != person.getFather())
				relations = searchAuntOrUncle(person.getFather(), Gender.Female);
			break;

		case Constants.MATERNAL_UNCLE:
			if (null != person.getMother())
				relations = searchAuntOrUncle(person.getMother(), Gender.Male);
			break;

		case Constants.PATERNAL_UNCLE:
			if (null != person.getFather())
				relations = searchAuntOrUncle(person.getFather(), Gender.Male);
			break;

		default:
			relations = Constants.INVALID_RELATION;
			break;
		}

		return (null == relations) ? Constants.NONE : relations;
	}

	private String searchChild(Person person, Gender gender) {
		StringBuilder sb = new StringBuilder();
		for (Person p : person.getChildren()) {
			if (p.getGender() == gender)
				sb.append(p.getName()).append(" ");
		}
		if (sb.toString().trim().length() > 0)
			return sb.toString().trim();
		return Constants.NONE;
	}

	private String searchSiblings(Person person) {
		StringBuilder sb = new StringBuilder();
		if (null == person.getMother())
			return Constants.NONE;
		for (Person p : person.getMother().getChildren()) {
			if (!p.getName().equalsIgnoreCase(person.getName()))
				sb.append(p.getName()).append(" ");
		}
		return sb.toString().trim();
	}

	private String searchSiblings(Person person, Gender gender) {
		StringBuilder sb = new StringBuilder();
		if (null == person.getMother())
			return Constants.NONE;
		for (Person p : person.getMother().getChildren()) {
			if (!p.getName().equalsIgnoreCase(person.getName()) && gender == p.getGender())
				sb.append(p.getName()).append(" ");
		}
		return sb.toString().trim();
	}

	private String searchSisterInLaws(Person person) {
		StringBuilder sb = new StringBuilder();
		// Get Spouse's sisters
		if (null != person.getSpouse() && null != person.getSpouse().getMother()) {
			for (Person p : person.getSpouse().getMother().getChildren()) {
				if (null != p && p.getGender() == Gender.Female)
					sb.append(p.getName()).append(" ");
			}
		}

		// Get Wives of Siblings
		if (null != person.getMother()) {
			for (Person p : person.getMother().getChildren()) {
				if (null != p && p.getSpouse().getGender() == Gender.Male)
					sb.append(p.getName()).append(" ");
			}
		}
		if (sb.toString().trim().length() > 0)
			return sb.toString().trim();
		return Constants.NONE;
	}

	private String searchBrotherInLaws(Person person) {
		StringBuilder sb = new StringBuilder();
		// Get Spouse's brothers
		if (null != person.getSpouse() && null != person.getSpouse().getMother()) {
			for (Person p : person.getSpouse().getMother().getChildren()) {
				if (null != p && p.getGender() == Gender.Male)
					sb.append(p.getName()).append(" ");
			}
		}

		// Get Wives of Siblings
		if (null != person.getMother()) {
			for (Person p : person.getMother().getChildren()) {
				if (null != p && p.getSpouse().getGender() == Gender.Female)
					sb.append(p.getName()).append(" ");
			}
		}
		if (sb.toString().trim().length() > 0)
			return sb.toString().trim();
		return Constants.NONE;
	}

	private String searchAuntOrUncle(Person parent, Gender gender) {
		if (null == parent.getMother() || null == parent.getMother().getChildren())
			return Constants.NONE;
		return gender == Gender.Female ? searchSiblings(parent, Gender.Female) : searchSiblings(parent, Gender.Male);
	}
}
