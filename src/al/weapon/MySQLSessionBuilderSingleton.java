package al.weapon;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySQLSessionBuilderSingleton {
	
	private SqlSessionFactory sql_session_factory;
	
	private MySQLSessionBuilderSingleton() {

		String file_path = "mybatis-mysql-config.xml";
		try { sql_session_factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(file_path)); } 
		catch (IOException e) { sql_session_factory = null; /*e.printStackTrace();*/ }
	}
	
	public static MySQLSessionBuilderSingleton GetInstance() { return SQLSessionFactoryBuilder.session_builder; }

	public SqlSessionFactory GetSqlSessionFactory() { return sql_session_factory; }
	
	private static class SQLSessionFactoryBuilder { private static MySQLSessionBuilderSingleton session_builder = new MySQLSessionBuilderSingleton(); }
}
