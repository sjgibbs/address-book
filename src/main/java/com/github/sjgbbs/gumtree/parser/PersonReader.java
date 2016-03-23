package com.github.sjgbbs.gumtree.parser;

import com.github.sjgbbs.gumtree.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * PersonReader
 *
 * @author Simon
 */
public class PersonReader {

	private PersonParser parser;

	public PersonReader(PersonParser parser) {
		this.parser = parser;
	}

	public Set<Person> readAll(BufferedReader bufferedReader, boolean headers) {
		if(headers) {
			try {
				bufferedReader.readLine();
			} catch (IOException e) {
				throw new ReaderException(e);
			}
		}

		String line;
		Set<Person> results = new LinkedHashSet<Person>();
		try {
			while ((line = bufferedReader.readLine()) != null) {
				results.add(parser.parse(line));
			}
		} catch (IOException e) {
			throw new ReaderException(e);
		}

		return results;
	}

}
