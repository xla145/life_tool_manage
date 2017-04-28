/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package cn.net.xulian.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.dialect.SQLServer2008Dialect;

public class Hibernates {
	public static void initLazyProperty(Object proxyedPropertyValue) {
		Hibernate.initialize(proxyedPropertyValue);
	}

	public static String getDialect(DataSource dataSource) {
		String jdbcUrl = getJdbcUrlFromDataSource(dataSource);

		if (StringUtils.contains(jdbcUrl, ":h2:"))
			return H2Dialect.class.getName();
		if (StringUtils.contains(jdbcUrl, ":mysql:"))
			return MySQL5InnoDBDialect.class.getName();
		if (StringUtils.contains(jdbcUrl, ":oracle:"))
			return Oracle10gDialect.class.getName();
		if (StringUtils.contains(jdbcUrl, ":postgresql:"))
			return PostgreSQL82Dialect.class.getName();
		if (StringUtils.contains(jdbcUrl, ":sqlserver:")) {
			return SQLServer2008Dialect.class.getName();
		}
		throw new IllegalArgumentException("Unknown Database of " + jdbcUrl);
	}

	private static String getJdbcUrlFromDataSource(DataSource dataSource) {
		Connection connection = null;
		String str = null;
		try {
			connection = dataSource.getConnection();
			if (connection == null) {
				throw new IllegalStateException("Connection returned by DataSource [" + dataSource + "] was null");
			}
			str = connection.getMetaData().getURL();

			return str;
		} catch (SQLException e) {
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
				}
		}
		return str;
	}
	
}