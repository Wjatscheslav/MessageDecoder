package com.viacheslav.khpi.decoder.logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.viacheslav.khpi.decoder.db.derby.DerbyDaoFactory;
import com.viacheslav.khpi.decoder.db.derby.DerbySequenceDao;
import com.viacheslav.khpi.decoder.entity.Sequence;
import com.viacheslav.khpi.decoder.entity.SequenceKind;

public class SequenceContainer {

	private List<Sequence> sequences = new ArrayList<>();

	public SequenceContainer(String path, SequenceKind kind) throws IOException {
		String in;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "Cp1251"));
		while ((in = br.readLine()) != null) {
			addSequenceToDB(in, kind);
		}
		br.close();
	}

	public SequenceContainer() {
	}

	private void addSequenceToDB(String in, SequenceKind kind) {
		Scanner sc = new Scanner(in);
		String s;
		Sequence seq;
		while ((s = sc.findInLine(kind.getPattern())) != null) {
			s = s.toLowerCase();
			DerbyDaoFactory factory = DerbyDaoFactory.getInstance();
			DerbySequenceDao dao = factory.getDerbySequenceDao();
			if ((seq = dao.read(kind, s)) != null) {
				seq.setFrequency(seq.getFrequency() + 1);
				dao.update(kind, seq);
			} else {
				seq = new Sequence();
				seq.setItem(s);
				seq.setFrequency(1);
				dao.create(kind, seq);
			}
		}
		sc.close();
	}

	public void addSequencesToContainer(String in, SequenceKind kind) {
		Scanner sc = new Scanner(in);
		String s;
		while ((s = sc.findInLine(kind.getPattern())) != null) {
			s = s.toLowerCase();
			Sequence testSequence = new Sequence(s, 1);
			int index = sequences.indexOf(testSequence);
			if (index != -1) {
				sequences.get(index).incFrequency();
			} else {
				sequences.add(testSequence);
			}
		}
		sc.close();
	}

	public List<Sequence> getSequences() {
		Collections.sort(sequences, (Sequence s1, Sequence s2) -> s2.getFrequency() - s1.getFrequency());
		return sequences;
	}

	public void setSequences(List<Sequence> sequences) {
		this.sequences = sequences;
	}

}
