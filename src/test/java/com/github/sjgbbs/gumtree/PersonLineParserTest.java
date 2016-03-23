package com.github.sjgbbs.gumtree;

import com.github.sjgbbs.gumtree.model.GeneticGender;
import com.github.sjgbbs.gumtree.model.Person;
import com.github.sjgbbs.gumtree.parser.PersonLineParser;
import com.github.sjgbbs.gumtree.parser.PersonParser;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * PersonLineParserTest
 *
 * @author Simon
 */
public class PersonLineParserTest {

	public static final LocalDate BASE_DATE = LocalDate.of(2016, 01, 26);

	@Test
	public void shouldRecogniseAMale() {
		Person result = parseAnExample(Examples.JEFF);
		assertThat(result.getGeneticGender(),is(GeneticGender.MALE));
	}

	private Person parseAnExample(String record) {
		PersonParser parser = new PersonLineParser();
		return parser.parse(record);
	}

	@Test
	public void shouldRecogniseAFemale() {
		Person result = parseAnExample(Examples.SUZIE);
		assertThat(result.getGeneticGender(),is(GeneticGender.FEMALE));
	}

	@Test
	public void shouldParseAFullName() {
		Person result = parseAnExample(Examples.SAV);
		assertThat(result.getFullName(), is("Sav Purell"));
	}

	@Test
	public void shouldParseADob() {
		Person result = parseAnExample(Examples.SAV);

		// 22/09/1978
		LocalDate expectedDob = LocalDate.of(1978, 9, 22);

		assertThat(result.getDateOfBirth(),equalTo(expectedDob));
	}

	@Test
	public void shouldReportThatTwoUniqueExamplesAreDifferent() {
		Person liam = parseAnExample(Examples.LIAM);
		Person suzie = parseAnExample(Examples.SUZIE);
		assertThat(liam,not(equalTo(suzie)));
	}

	@Test
	public void shouldReportDuplicateExampleAsEqual() {
		Person tom = parseAnExample(Examples.TOM);
		Person tomAgain = parseAnExample(Examples.TOM);
		assertThat(tom, equalTo(tomAgain));
	}


	@Test
	public void shouldCalculateAgeInWholeYears() {
		Person liam = parseAnExample(Examples.LIAM);
		assertThat(liam.calculateAgeInWholeYears(BASE_DATE),is(35)); // same age as me
	}

	@Test
	public void shouldCalculateAgeDifferenceInDays() {
		// 3. How many days older is Jeff Briton than Tom Soyer?

		Person jeff = parseAnExample(Examples.JEFF);
		Person tom = parseAnExample(Examples.TOM);

		// on my knuckles march has 31 days
		// 31 minus the days-not-lived i.e. birth date is
		// 31-16 = 15
		// plus the additional days in April lived by Tom (15)
		// 15 + 15 = 30

		// see also http://www.timeanddate.com/calendar/?year=1977&country=9

		assertThat(tom.ageDifferenceInDays(jeff),is(30L));

	}

	@Test
	public void shouldCalculateAgeDifferenceInDaysWithArgumentsReversed() {
		// This should do /something/ sensible.

		Person jeff = parseAnExample(Examples.JEFF);
		Person tom = parseAnExample(Examples.TOM);

		assertThat(jeff.ageDifferenceInDays(tom),is(-30L));

	}

  @Test
  public void shouldCalculateAgeDifferenceInDaysLargeGap() {
    // This should do /something/ sensible.

    Person jeff = parseAnExample(Examples.JEFF);   // 16/03/77
    Person suzie = parseAnExample(Examples.SUZIE);   // 17/08/81

    assertThat(suzie.ageDifferenceInDays(jeff),is(1615L));

  }


}
