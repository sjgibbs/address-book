package com.github.sjgbbs.gumtree.parser;

import com.github.sjgbbs.gumtree.model.Person;

/**
 * PersonParser
 *
 * @author Simon
 */
public interface PersonParser {

	Person parse(String record);

}
