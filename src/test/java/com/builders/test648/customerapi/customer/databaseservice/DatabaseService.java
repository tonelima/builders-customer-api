package com.builders.test648.customerapi.customer.databaseservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService {
	
	@Autowired
	private DataSource dataSource;

	private void executeSql(String sql) {
		Statement statement = null;
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			statement.addBatch(sql);
			statement.executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void cleanDatabase() {
		executeSql("drop table if exists customer cascade");
		executeSql("drop sequence if exists hibernate_sequence");
		
		executeSql("create sequence hibernate_sequence start 1 increment 1");
		executeSql("create table customer (id int8 not null, name varchar(255), address varchar(255), "
				+ "birth_date date, document_number varchar(100), document_type varchar(50), "
				+ "phone_number varchar(50), primary key (id))");
	}
}
