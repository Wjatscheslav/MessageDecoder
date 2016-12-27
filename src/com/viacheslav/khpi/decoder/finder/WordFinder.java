package com.viacheslav.khpi.decoder.finder;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class WordFinder {

	public static final String WORD_CHECKER = "(\"?[A-Za-z]{4,}\"?\\.?\\,?)|(\"?[A-Za-z]*-[A-Za-z]*\"?\\.?\\,?)";

	private SortedSet<String> words = new TreeSet<>();

	public SortedSet<String> getWords() {
		return words;
	}

	public void setWords(SortedSet<String> words) {
		this.words = words;
	}

	public void addString(String in) {
		Scanner sc = new Scanner(in);
		String out;
		sc.useDelimiter("[ \n]");
		while (sc.hasNext()) {
			out = sc.next();
			if (out.matches(WORD_CHECKER)) {
				words.add(out);
			}
		}
		sc.close();
	}
}
