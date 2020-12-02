package progresql;

public class NetInfoEntity {
	// 原始数据
	private String input_data;
	public String getInput_data() {
		return input_data;
	}

	public void setInput_data(String input_data) {
		this.input_data = input_data;
	}

	public String getIp_type() {
		return ip_type;
	}

	public void setIp_type(String ip_type) {
		this.ip_type = ip_type;
	}

	public String getSubnetmask() {
		return subnetmask;
	}

	public void setSubnetmask(String subnetmask) {
		this.subnetmask = subnetmask;
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

	public void setInsert_user(String insert_user) {
		this.insert_user = insert_user;
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

	private String ip_type;
	private String subnetmask;
	private String insert_date;
	private String insert_user;
	private String update_date;
	private String update_user;

	public NetInfoEntity() {

	}

}
