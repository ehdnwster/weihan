/**
 * 
 */
package progresql;

/**
 * @author Yuki
 *
 */
public class SubmaskEntity {
	private String id;
	private String netinfo_id;
	private String subnet_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public SubmaskEntity() {

	}

}

//INSERT INTO daydayup_202011.subnet(
//id, netinfo_id, subnet_id)
//VALUES (?, ?, ?);
