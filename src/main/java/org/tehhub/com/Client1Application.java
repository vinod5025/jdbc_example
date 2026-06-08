package org.tehhub.com;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import DBConfig.DBConfig;

public class Client1Application {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate template = (JdbcTemplate) context.getBean("template");
		if (template != null) {
			System.out.println("Enter EID ");
			final int id = sc.nextInt();
			PreparedStatementSetter stmt = new PreparedStatementSetter() {

				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, id);

				}
			};
			RowMapper r = new RowMapper<Employee>() {

				@Override
				public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
					Employee emp = new Employee();
					emp.setId(rs.getInt(1));
					emp.setName(rs.getString(2));
					emp.setSal(rs.getInt(3));
					return emp;

				}

			};
			List<Employee> list = template.query("select *from employee where eid=?", stmt, r);
			for (Employee e : list) {
				System.out.println(e.getId() + " " + e.getName() + " " + e.getSal());
			}

		} else {
			System.out.println("Database not connected");
		}
	}

}
