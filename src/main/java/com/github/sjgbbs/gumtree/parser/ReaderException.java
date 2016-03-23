package com.github.sjgbbs.gumtree.parser;

/**
 * A standard exception class, reflecting the fashion to not compel exception handling in the caller.
 *
 * I believe most (all?) exceptions would be IOExceptions to this is of little real value given you had IOException on
 * main() but I wanted to demonstrate I new the pattern,
 *
 * @author Simon
 */
public class ReaderException extends RuntimeException {
	public ReaderException(Exception cause) {
		super(cause);
	}
}
