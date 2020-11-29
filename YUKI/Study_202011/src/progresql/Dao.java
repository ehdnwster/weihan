/**
 * 
 */
package progresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Yuki 将制定IP地址数据导入postgresql指定数据库
 */
public class Dao {
	private static final String Driver_Name = "org.postgresql.Driver";
	private static final String JDBC_Str = "jdbc:postgresql://localhost:5432/";
	private static final String DB_Name = "Yuki Den";
	private static final String DB_User = "postgres";
	private static final String DB_PW = "606387Hero";
	private static final String DB_Connect_OK = "Opened database successfully.";
	private static final String table_Name_net="daydayup_202011.netinfo";
	private static final String table_Name_ip="daydayup_202011.ipinfo";
	private static final String table_Name_Subnet="daydayup_202011.submask";

		
	public void insert_netinfo(NetInfoEntity item) throws SQLException{
		PreparedStatement ps = null;
		//获取连接数据库的资源对象
		Connection conn = getConnection();
		try {
			//SQL指令
			String sql =new StringBuffer()
					.append("INSERT INTO "+table_Name_net)
					.append("(input_data, ip_type, subnetmask, insert_date, insert_user, update_date, update_user)")
					.append("VALUES (")
					.append("?, ")
					.append("?, ")
					.append("?, ")
					.append("?::timestamp without time zone, ")
					.append("?, ")
					.append("?::timestamp without time zone, ")
					.append("?)")
					.toString();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, item.getInput_data());
			ps.setString(2, item.getIp_type());
			ps.setString(3, item.getSubnetmask());
			ps.setString(4, item.getInsert_date());
			ps.setString(5, item.getInsert_user());
			ps.setString(6, item.getUpdate_date());
			ps.setString(7, item.getUpdate_user());
			ps.executeUpdate();
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	public Connection getConnection() {
		Connection cnc = null;
		try {
			Class.forName(Driver_Name);
			cnc = DriverManager.getConnection(JDBC_Str + DB_Name, DB_User, DB_PW);
			System.out.println(DB_Connect_OK);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
		}
		return cnc;
	}
}
