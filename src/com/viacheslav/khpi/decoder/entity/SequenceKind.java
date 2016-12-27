package com.viacheslav.khpi.decoder.entity;

public enum SequenceKind {
	
	WORD("[a-zA-Z]{1,}-*[a-zA-Z]{1,}"),
	BIGRAMM("[a-zA-Z]{2}"),
	LETTER("[A-Za-z]{1}");
	
	private String pattern;
	
	SequenceKind(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
}
