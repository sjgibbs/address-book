package com.github.sjgbbs.gumtree;

import com.github.sjgbbs.gumtree.model.GeneticGender;
import com.github.sjgbbs.gumtree.model.Person;
import com.github.sjgbbs.gumtree.parser.PersonLineParser;
import com.github.sjgbbs.gumtree.parser.PersonParser;
import com.github.sjgbbs.gumtree.parser.PersonReader;
import com.github.sjgbbs.gumtree.parser.ReaderException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Set;


public class AddressBookApplication {

	public static void main(String[] args) {

		PersonParser parser = new PersonLineParser();

		PersonReader reader = new PersonReader(parser);

		File file = new File("AddressBook");
		System.out.println(file.getName() + " file exists = " + file.exists());

		Set<Person> people = null;
		BufferedReader bufferedReader = null;

		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			people = reader.readAll(bufferedReader, false);

			System.out.printf("loaded %d people", people.size());
			System.out.println();

			Demographics demographics = new Demographics(people);

			System.out.printf("%d were male", demographics.genderHistogram().get(GeneticGender.MALE));
			System.out.println();

			Person bill = findPerson(people,"Bill McKnight");
			Person paul = findPerson(people,"Paul Robinson");

			// 3. How many days older is X than Y?
      long difference = paul.ageDifferenceInDays(bill);
			System.out.printf("%s is %d days older than %s", bill.getFullName(), difference, paul.getFullName());
			System.out.println();
      System.out.printf("(about %d years)", (int) Math.round(difference / 365.25));
      System.out.println();


		} catch (ReaderException re) {
			System.err.println("The developer who wrote this knows about catch blocks, but didn't stop this error occurring");
			re.printStackTrace(System.err);   // he also knows about err :-)
		} catch (FileNotFoundException e) {
			System.err.println(file.getAbsolutePath() + " does not exist");
			// fine judgement call: the exception stack trace is less useful that the line above. One to talk about in review.
		} finally {
			try {
				if(bufferedReader!=null) {
					bufferedReader.close();
				}
			} catch (IOException ioe) {
				System.err.println("The developer who wrote this knows that the constructor and close methods can also throw an exception, but didn't stop either doing so");
				ioe.printStackTrace(System.err);
			}
		}

	}

	private static Person findPerson(Set<Person> people, String fullName) {
		for(Person person : people) {
			if(person.getFullName().equals(fullName)) {
				return person;
			}
		}
		throw new IllegalArgumentException("Not found: " + fullName);
	}


}