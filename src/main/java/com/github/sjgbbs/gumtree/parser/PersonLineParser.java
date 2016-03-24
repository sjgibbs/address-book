package com.github.sjgbbs.gumtree.parser;

import com.github.sjgbbs.gumtree.model.GeneticGender;
import com.github.sjgbbs.gumtree.model.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * PersonLineParser
 *
 * @author Simon
 */
public class PersonLineParser implements PersonParser {
	public Person parse(String record) {

		if(record == null || record.isEmpty()) {
			throw new IllegalArgumentException("Record was unexpectedly null or empty");
		}

		String[] fieldValues = record.split(",");

		for(int i=0;i<fieldValues.length;i++) {
			fieldValues[i] = fieldValues[i].trim();
		}

		Person result = new Person();

		result.setFullName(fieldValues[0]);
		result.setGeneticGender(parseGender(fieldValues[1]));
		result.setDateOfBirth(parseDob(fieldValues[2]));


		return result;
	}

	private GeneticGender parseGender(String fieldValue) {
		return GeneticGender.valueOf(fieldValue.toUpperCase(Locale.UK)); // UK locale gives *known* behaviour
	}

	private LocalDate parseDob(String fieldValue) {
		DateTimeFormatter ddMMyyFormatter = new DateTimeFormatterBuilder()
				.appendPattern("dd/MM")
				.appendLiteral('/')
				.appendValueReduced(ChronoField.YEAR,2,2,1936)
				.toFormatter();
		return LocalDate.parse(fieldValue,ddMMyyFormatter);
	}
}
