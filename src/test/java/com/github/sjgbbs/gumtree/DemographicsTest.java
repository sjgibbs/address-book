package com.github.sjgbbs.gumtree;

import com.github.sjgbbs.gumtree.model.GeneticGender;
import com.github.sjgbbs.gumtree.model.Person;
import com.github.sjgbbs.gumtree.parser.PersonLineParser;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * DemographicsTest
 *
 * @author Simon
 */
public class DemographicsTest {

	private Set<Person> people;

	@Before
	public void loadIndividuals() {

		PersonLineParser parser = new PersonLineParser();

		people = new LinkedHashSet<>();
		people.add(parser.parse(Examples.JEFF));
		people.add(parser.parse(Examples.LIAM));
		people.add(parser.parse(Examples.SAV));
		people.add(parser.parse(Examples.SUZIE));
		people.add(parser.parse(Examples.TOM));
	}

	@Test
	public void shouldReportGenderHistrogramMatchingManualCount() {
		Demographics demographics = new Demographics(people);

		Map<GeneticGender,Long> histogram = demographics.genderHistogram();
		assertThat(histogram.get(GeneticGender.MALE),is(4L));
		assertThat(histogram.get(GeneticGender.FEMALE),is(1L));
	}

	@Test
	public void shouldReportOldestPerson() {
		Demographics demographics = new Demographics(people);

		Person jeff = demographics.findOldestIndividual();

		assertThat(jeff.getFullName(),is("Jeff Briton"));
	}

}
