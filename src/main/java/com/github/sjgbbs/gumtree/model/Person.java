package com.github.sjgbbs.gumtree.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.Temporal;

/**
 * Person
 *
 * @author Simon
 */
public class Person {
	private GeneticGender geneticGender;
	private String fullName;
	private LocalDate dateOfBirth;

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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Person person = (Person) o;

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
	public long ageDifferenceInDays(Person olderOther) {

    Temporal olderDobAsInstant = olderOther.dateOfBirth.atStartOfDay();
    Temporal youngerDobAsInstant = this.dateOfBirth.atStartOfDay();

    return Duration.between(olderDobAsInstant, youngerDobAsInstant).toDays();

	}
}
