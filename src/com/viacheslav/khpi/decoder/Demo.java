package com.viacheslav.khpi.decoder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.viacheslav.khpi.decoder.entity.SequenceKind;
import com.viacheslav.khpi.decoder.logic.Replacer;
import com.viacheslav.khpi.decoder.logic.SequenceContainer;

public class Demo {

	public static void main(String[] args) throws IOException {

		String path2 = "test.txt";
		String in;
		SequenceContainer contLetter = new SequenceContainer();
		SequenceKind kind = SequenceKind.LETTER;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path2), "UTF-8"));
		while ((in = br.readLine()) != null) {
			contLetter.addSequencesToContainer(in, kind);
			System.out.println(in);
			Replacer replacer = new Replacer();
			String res = replacer.replace(contLetter.getSequences(), in, kind);
			System.out.println(res);
		}
		br.close();
		}

	public static void analyzeFile(String path) throws IOException {
		SequenceKind kind1 = SequenceKind.LETTER;
		SequenceContainer cont1 = new SequenceContainer(path, kind1);
		System.out.println(cont1.getSequences());
		SequenceKind kind2 = SequenceKind.BIGRAMM;
		SequenceContainer cont2 = new SequenceContainer(path, kind2);
		System.out.println(cont2.getSequences());
	}
}
