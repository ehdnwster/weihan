package netinfo.ip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author cyou
 * PostgreSQLJDBC 数据库操作
 */
public class PostgreSQLJDBC {
	//Driver
	private static final String DRIVER_NAME = "org.postgresql.Driver";
	//	连接JDBC
	private static final String JDBC_STR = "jdbc:postgresql://localhost:5432/";
	//	数据库名字
	private static final String DB_NAME= "weihan";
	//	用户名
	private static final String DB_USER="postgres";
	//	密码
	private static final String DB_PWD="99605329";
    //连接结果
	private static final String DB_CONNECT_OK="Opened database successfully";

	public static void main(String args[]) {
		PostgreSQLJDBC db = new PostgreSQLJDBC();
		try {
			db.insert();
			db.select();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * SELECT SQL TEST
	 */
	private void select() throws SQLException{
		Statement stmt = null;
		//获取连接数据库的资源对象
		Connection conn = getConnection();
		try {
			stmt = conn.createStatement();
			//SQL指令
			String sql = "select * from zb.corona";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//输出需要的字段的值
				System.out.println(rs.getString(1) + "   " + rs.getString(2) + "   " + rs.getString(3));
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * INSERT SQL TEST
	 */
	private void insert() throws SQLException {
		PreparedStatement ps = null;
		Connection conn = getConnection();
		try {
			StringBuffer sql = new StringBuffer();
			//表名
			String table = "zb.corona";
			//SQL组装
			sql.append("INSERT INTO "+table+" (c_date, total, insert_date)VALUES(");
			sql.append("now(),");
			sql.append("?,");
			sql.append("now()");
			sql.append(")");

			//设定Sql的值
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1,2535);
			//执行SQL
			int ret = ps.executeUpdate();
			//输出结果信息
			System.out.println(ret +"条记录插入数据表:"+table);
			//关闭资源
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Connection对象生成方法
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_STR+DB_NAME, DB_USER, DB_PWD);
			System.out.println(DB_CONNECT_OK);
			return conn;
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return conn;
	}
}