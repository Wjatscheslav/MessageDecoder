package com.viacheslav.khpi.decoder.db.derby;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DerbyDaoFactory {

	public static final String URL = "jdbc:derby:C:\\Epam_JAVA\\DecoderDb";
	public static final String DRIVER_NAME = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String CLASS_NAME = "com.viacheslav.khpi.decoder.db.derby.DerbyDaoFactory";
	private static BasicDataSource source = null;
	private static DerbyDaoFactory instance = null;

	static {
		source = new BasicDataSource();
		source.setDriverClassName(DRIVER_NAME);
		source.setUrl(URL);
	}
	
	public DerbySequenceDao getDerbySequenceDao() {
		return new DerbySequenceDao();
	}

	private DerbyDaoFactory() {
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static DerbyDaoFactory getInstance() {
		if (instance == null) {
			Class<?> daoClass;
			try {
				daoClass = Class.forName(CLASS_NAME);
				instance = (DerbyDaoFactory) daoClass.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

}
