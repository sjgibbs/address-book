package com.github.sjgbbs.gumtree.model;

import java.time.LocalDate;

/**
 * Person
 *
 * @author Simon
 */
public class Person {
	private GeneticGender geneticGender;
	private String fullName;
	private LocalDate dateOfBirth;
	private int age;

	public GeneticGender getGeneticGender() {
		return geneticGender;
	}

	public void setGeneticGender(GeneticGender geneticGender) {
		this.geneticGender = geneticGender;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Person person = (Person) o;

		if (age != person.age) return false;
		if (!dateOfBirth.equals(person.dateOfBirth)) return false;
		if (!fullName.equals(person.fullName)) return false;
		if (geneticGender != person.geneticGender) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = geneticGender.hashCode();
		result = 31 * result + fullName.hashCode();
		result = 31 * result + dateOfBirth.hashCode();
		result = 31 * result + age;
		return result;
	}

	public int calculateAgeInWholeYears(LocalDate asAt) {
		return dateOfBirth.until(asAt).getYears();
	}

	/**
	 * How much older is the other person?
	 * @param olderOther another individual
	 * @return the number of days the older person is older. Negative if the older person is actually younger.
	 */
	public int ageDifferenceInDays(Person olderOther) {
		return olderOther.dateOfBirth.until(this.dateOfBirth).getDays();
	}
}
