package com.viacheslav.khpi.decoder.db.derby;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.viacheslav.khpi.decoder.entity.Sequence;
import com.viacheslav.khpi.decoder.entity.SequenceKind;

public class DerbySequenceDao {

	public void create(SequenceKind kind, Sequence sequence) {
		String request = "INSERT INTO " + kind.name() + " (name, frequency) VALUES (?, ?)";
		DerbyDaoFactory factory = DerbyDaoFactory.getInstance();
		Connection conn = factory.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(request, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, sequence.getItem());
			statement.setInt(2, sequence.getFrequency());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Sequence read(SequenceKind kind, String name) {
		String request = "SELECT * FROM " + kind.name() + " WHERE NAME = ?";
		Sequence sequence = new Sequence();
		DerbyDaoFactory factory = DerbyDaoFactory.getInstance();
		Connection conn = factory.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(request, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			rs.beforeFirst();
			rs.first();
			sequence.setItem(rs.getString("name"));
			sequence.setFrequency(rs.getInt("frequency"));
		} catch (SQLException e) {
			sequence = null;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sequence;
	}

	public List<Sequence> readAll(SequenceKind kind) {
		String request = "SELECT * FROM " + kind.name() + " ORDER BY frequency DESC";
		List<Sequence> sequences = new ArrayList<>();
		Sequence sequence = new Sequence();
		DerbyDaoFactory factory = DerbyDaoFactory.getInstance();
		Connection conn = factory.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(request);
			while (rs.next()) {
				sequence = new Sequence();
				sequence.setItem(rs.getString("name"));
				sequence.setFrequency(rs.getInt("frequency"));
				sequences.add(sequence);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sequences;
	}

	public void update(SequenceKind kind, Sequence seq) {
		String request = "UPDATE " + kind.name() + " SET frequency = ? WHERE name = ?";
		DerbyDaoFactory factory = DerbyDaoFactory.getInstance();
		Connection conn = factory.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(request, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, seq.getFrequency());
			statement.setString(2, seq.getItem());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
