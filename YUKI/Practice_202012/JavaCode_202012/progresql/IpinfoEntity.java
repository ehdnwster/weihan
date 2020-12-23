/**
 * 
 */
package progresql;

/**
 * @author Yuki
 *
 */
public class IpinfoEntity {
	private String id;
	private String available_ip_address;
	private String net_ip_address;
	private String broadcast_ip_address;
	private String insert_date;
	private String insert_user;
	private String update_date;
	private String update_user;
	private String netinfo_id;
	private String subnet_id;
	
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAvailable_ip_address() {
		return available_ip_address;
	}


	public void setAvailable_ip_address(String available_ip_address) {
		this.available_ip_address = available_ip_address;
	}


	public String getNet_ip_address() {
		return net_ip_address;
	}


	public void setNet_ip_address(String net_ip_address) {
		this.net_ip_address = net_ip_address;
	}


	public String getBroadcast_ip_address() {
		return broadcast_ip_address;
	}


	public void setBroadcast_ip_address(String broadcast_ip_address) {
		this.broadcast_ip_address = broadcast_ip_address;
	}


	public String getInsert_date() {
		return insert_date;
	}


	public void setInsert_date(String insert_date) {
		this.insert_date = insert_date;
	}


	public String getInsert_user() {
		return insert_user;
	}


	public void setInsert_user(String inser_user) {
		this.insert_user = inser_user;
	}


	public String getUpdate_date() {
		return update_date;
	}


	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}


	public String getUpdate_user() {
		return update_user;
	}


	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}


	public String getNetinfo_id() {
		return netinfo_id;
	}


	public void setNetinfo_id(String netinfo_id) {
		this.netinfo_id = netinfo_id;
	}


	public String getSubnet_id() {
		return subnet_id;
	}


	public void setSubnet_id(String subnet_id) {
		this.subnet_id = subnet_id;
	}


	/**
	 * 
	 */
	public IpinfoEntity() {

	}

}

//INSERT INTO daydayup_202011.ipinfo(
//id, available_ip_address, net_ip_address, broadcast_ip_address, insert_date, insert_user,
//update_date, update_user, netinfo_id, subnet_id)
//VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);