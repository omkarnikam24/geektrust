package com.geektrust.meetthefamily.model;

import java.util.HashSet;
import java.util.Set;

import com.geektrust.meetthefamily.enums.Gender;

public class Person {

	private String name;
	private Gender gender;

	private Person mother;
	private Person father;
	private Person spouse;

	private Set<Person> children = new HashSet<>();

	public Person(String name, Gender gender, Person mother, Person father) {
		super();
		this.name = name;
		this.gender = gender;
		this.mother = mother;
		this.father = father;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public Set<Person> getChildren() {
		return children;
	}

	public void setChildren(Set<Person> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + "]";
	}
}