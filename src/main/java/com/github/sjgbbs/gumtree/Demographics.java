package com.github.sjgbbs.gumtree;

import com.github.sjgbbs.gumtree.model.GeneticGender;
import com.github.sjgbbs.gumtree.model.Person;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Demographics
 *
 * @author Simon
 */
public class Demographics {

	/*
	 * Watch this fail to scale
	 */
	private Set<Person> individuals;

	public Demographics(Set<Person> individuals) {
		this.individuals = individuals;
	}

	public Map<GeneticGender, Long> genderHistogram() {

		Map<GeneticGender, Long> histogram  = individuals.stream()
			.collect(
				groupingBy(
					Person::getGeneticGender,
					Collectors.counting()
				)
			);

		return histogram;
	}

	public double averageAge(LocalDate asAt) {

		int totalAge = 0;
		for(Person person : individuals) {
			totalAge += person.calculateAgeInWholeYears(asAt);
		}

		return totalAge / ((double) individuals.size());

	}


}
