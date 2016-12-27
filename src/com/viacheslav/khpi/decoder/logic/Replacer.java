package com.viacheslav.khpi.decoder.logic;

import java.util.List;

import com.viacheslav.khpi.decoder.db.derby.DerbyDaoFactory;
import com.viacheslav.khpi.decoder.db.derby.DerbySequenceDao;
import com.viacheslav.khpi.decoder.entity.Sequence;
import com.viacheslav.khpi.decoder.entity.SequenceKind;

public class Replacer {

	public String replace(List<Sequence> sequence, String str, SequenceKind kind) {
		StringBuilder sb = new StringBuilder(str);
		DerbyDaoFactory factory = DerbyDaoFactory.getInstance();
		DerbySequenceDao dao = factory.getDerbySequenceDao();
		List<Sequence> sequenceFromDB = dao.readAll(kind);
		System.out.println(sequence);
		System.out.println(sequenceFromDB);
		for (int i = 0; i < sb.length(); i++) {
			char source = sb.charAt(i);
			if (source != ' ') {
				String sourceStr = String.valueOf(source);
				int index = sequence.indexOf(new Sequence(sourceStr, 1));
				sb.replace(i, i + 1, sequenceFromDB.get(index).getItem());
			}
		}
		return sb.toString();
	}

}
