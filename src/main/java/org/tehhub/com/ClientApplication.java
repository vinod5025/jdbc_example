package org.tehhub.com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import DBConfig.DBConfig;

public class ClientApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
		JdbcTemplate template = (JdbcTemplate) context.getBean("template");
		if (template != null) {
			System.out.println("Database connected");
			RowMapper r=new RowMapper<Employee>() 
					{

						@Override
						public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
							Employee emp=new Employee();
							emp.setId(rs.getInt(1));
							emp.setName(rs.getString(2));
							emp.setSal(rs.getInt(3));
							return emp;
						}
				
					};
					List<Employee> list=template.query("select *from employee", r);
					for(Employee e:list)
					{
						System.out.println(e.getId()+"\t"+e.getName()+"\t"+e.getSal());
					}
			
		} else {
			System.out.println("database not connected");
		}
	}
}
