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
 * 
 */
public class Dao {
	private static final String Driver_Name = "org.postgresql.Driver";
	private static final String JDBC_Str = "jdbc:postgresql://localhost:5432/";
	private static final String DB_Name = "Yuki Den";
	private static final String DB_User = "postgres";
	private static final String DB_PW = "606387Hero";
	private static final String DB_Connect_OK = "Opened database successfully.";
	private static final String table_Name_net = "daydayup_202011.netinfo";
	private static final String table_Name_ip = "daydayup_202011.ipinfo";
	private static final String table_Name_Subnet = "daydayup_202011.submask";

	public void insert_netinfo(NetInfoEntity item) throws SQLException {
		//数据库的状态值接收变量
		PreparedStatement ps = null;
		// 获取连接数据库的状态信息
		Connection conn = getConnection();
		try {
			// 插入net表格的SQL指令
			String sql = new StringBuffer()
					.append("INSERT INTO " + table_Name_net)
					.append("(input_data, ip_type, subnetmask, insert_date, insert_user, update_date, update_user)")
					.append("VALUES (").append("?, ")
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

	public void insert_ipinfo(IpinfoEntity ip) throws SQLException {
		//数据库的状态值接收变量
		PreparedStatement ps = null;
		// 获取连接数据库的状态信息
		Connection conn = getConnection();
		try {
			// 插入ipinfo表格的SQL指令  
			String sql = new StringBuffer()
					.append("INSERT INTO " + table_Name_ip)
					.append("(id, available_ip_address, net_ip_address, broadcast_ip_address,"
							+ " insert_user, update_date, update_user, netinfo_id, subnet_id)")
					.append("VALUES (")
					.append("?, ")
					.append("?, ")
					.append("?, ")
					.append("?, ")
					.append("?::timestamp without time zone, ")
					.append("?, ")
					.append("?::timestamp without time zone, ")
					.append("?, ")
					.append("?, ")
					.append("?)")
					.toString();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, ip.getId());
			ps.setString(2, ip.getAvailable_ip_address());
			ps.setString(3, ip.getNet_ip_address());
			ps.setString(4, ip.getBroadcast_ip_address());
			ps.setString(5, ip.getInsert_date());
			ps.setString(6, ip.getInsert_user());
			ps.setString(7, ip.getUpdate_date());
			ps.setString(8, ip.getUpdate_user());
			ps.setString(9, ip.getNetinfo_id());
			ps.setString(10, ip.getSubnet_id());
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void insert_submaskNetInfo(SubmaskEntity submasknet) throws SQLException {
		//数据库的状态值接收变量
		PreparedStatement ps = null;
		// 获取连接数据库的状态信息
		Connection conn = getConnection();
		try {
			// 插入submask表格的SQL指令
			String sql = new StringBuffer()
					.append("INSERT INTO " + table_Name_Subnet)
					.append("(id, netinfo_id, subnet_id)")
					.append("VALUES (")
					.append("?, ")
					.append("?, ")
					.append("?)")
					.toString();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, submasknet.getId());
			ps.setString(2, submasknet.getNetinfo_id());
			ps.setString(3, submasknet.getSubnet_id());
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
