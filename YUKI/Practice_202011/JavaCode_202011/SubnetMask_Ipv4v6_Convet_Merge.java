package Subnet;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SubnetMask_Ipv4v6_Convet_Merge {
	private static int itemCount = 0;
	private static int errorCount = 0;

	/**
	 * System.out.println(list);
	 */
	public static void main(String[] args)  {
		
		ArrayList<String> list = ReadFile.toArrayByFileReader1(args[0]);
		for (int i = 0; i < list.size(); i++) {
			itemCount += 1;
			try {
				System.out.println("IP Address:" + String.format("%30s", list.get(i)));
				String str = calculate(list.get(i).split("/"));
				System.out.println(str+"\n"+"\n");
				//input_data, ip_type, subnetmask, insert_date, insert_user, update_date, update_user
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 输出batch日志;
		Writelog("ItemCount=" + itemCount + "\n" + "ErrorCount=" + errorCount);
	}

	/**
	 * calculate 对对象IP地址计算求出子网掩码,子网数,可用子网数目,各子网的ip地址
	 * 
	 * @param ipSet IP地址与子网借位组合
	 * 
	 */
	private static String calculate(String[] ipSet) {
		String ret = null;
		String subNet_1 = ipSet[0];
		String[] ip = subNet_1.split("\\.");
		ret = checkIp(ip, ipSet);
		while (ret == "") {
			// A,B,C类IP地址判断
			if (Integer.parseInt(ip[0]) < 224) {
				// 生成子网掩码
				ret = "Subnet Mask: " + submask(ipSet[1], ip[0]);
				System.out.println(ret);
				// 生成子网数目
				ret = "Total Amount of Subnet: " + amountOfSubnet(ipSet[1]);
				System.out.println(ret);
				// 生成可用子网数
				ret = "Amount of Available Subnet: " + amountOfAvailableSubnet(ipSet[1]);
				System.out.println(ret);
				// 所产生各子网的网络地址,广播地址,各子网IP地址
				subnetIpAddress(ipSet[1], ip[0], ip[1], ip[2]);
				ret="当前子网ip计算结束.";

			}
			// D类IP地址判断
			else if (Integer.parseInt(ip[0]) < 240) {
				ret = "当前IP地址为D类IP地址.";
				ret = "v6IpNetAddress is " + v6IpConventer(subNet_1) + ".";

			}
			// E类IP地址判断
			else {
				ret = "当前IP地址为E类IP地址.";
				ret = "v6IpNetAddress is " + v6IpConventer(subNet_1) + ".";
			}
		}
		return ret;
	}

	/**
	 * checkIp 检查IP地址是否符合规格
	 * 
	 * @param ip    IP地址
	 * @param ipSet 子网借位数
	 * 
	 */
	private static String checkIp(String[] ip, String[] ipSet) {
		String ret = "";
		if (!(ip.length == 4)) {
			errorCount += 1;
			System.out.println("ip地址必须为点号分割的4组数字组成.例:192.15.22.23");
			ret = "Args Error";
		} else if ((Integer.parseInt(ipSet[1])) < 9 || (Integer.parseInt(ipSet[1])) > 31) {
			errorCount += 1;
			System.out.println("子网借位数必须介于9与31之间,包括9和31.例:192.15.22.23/(8~31)");
			ret = "Args Error";
		} else if (Integer.parseInt(ip[0]) < 1) {
			errorCount += 1;
			System.out.println("ip地址的第一组不可以小于1");
			ret = "Args Error";
		} else if (Integer.parseInt(ip[3]) > 255) {
			errorCount += 1;
			System.out.println("ip地址的第一组不可以大于255");
			ret = "Args Error";
		}
		return ret;
	}
	
	/**
	 * v6IpConventer 将IPv4格式IP地址地址转换为ipv6格式
	 * 
	 * @param subNet_1 v4格式IP地址
	 * 
	 * 
	 */
	private static String v6IpConventer(String subNet_1) {
		String ret = "";
		String[] ip = subNet_1.split("\\.");
		String v6IpNet = "";
		String v6IpNetAddress = "";
		ArrayList<String> v6IpNetHost_sub = new ArrayList<String>();
		for (int i = 0; i < ip.length; i++) {
			v6IpNet = de2Hex(ip[i]);
			v6IpNetHost_sub.add(v6IpNet);
		}
		v6IpNetAddress = "::" + v6IpNetHost_sub.get(0) + v6IpNetHost_sub.get(1) + ":" + v6IpNetHost_sub.get(2)
				+ v6IpNetHost_sub.get(3);

		ret = v6IpNetAddress;
		return ret;
	}

	/**
	 * de2Hex 十进制转16进制
	 * @param ip_bites v4格式IP地址的某一组
	 * 
	 */
	private static String de2Hex(String ip_bites) {
		long ipbits_No = Long.parseLong(ip_bites);
		String bit = Long.toHexString(ipbits_No);
		return bit;
	}


	/**
	 * subnetAddress 输出所有的子网ip地址
	 * 
	 * @param subNetLength 子网的借位长度
	 * @param ip_sub1      ip地址的第一组
	 * @param ip_sub2      ip地址的第二组
	 * @param ip_sub3      ip地址的第三组
	 */
	private static void subnetIpAddress(String subNetLength, String ip_sub1, String ip_sub2, String ip_sub3) {
		int subNetIdBits = Integer.parseInt(subNetLength);
		subNetIdBits %= 8;
		int hostIdBits = 8 - subNetIdBits;
		int ipSub1 = Integer.parseInt(ip_sub1);
		ArrayList<String> idList = subNetIdList(subNetIdBits);
		// A类IP地址
		if (ipSub1 < 127) {
			String ipPrefix = ip_sub1;
			for (int h = 0; h < idList.size(); h++) {
				int startIp = bi2De(idList.get(h) + getBits(hostIdBits, "0"));
				int endIp = bi2De(idList.get(h) + getBits(hostIdBits, "1"));
				System.out.println("sub net id _ " +((bi2De(idList.get(h))+1)));
				System.out.println("net ip is " + ipPrefix + startIp);
				System.out.println("net ip(v6) is " + v6IpConventer(ipPrefix + startIp));
				for (int avIp = startIp + 1; avIp < endIp; avIp++) {
					System.out.println("availabel ip is " + ipPrefix + avIp);
					System.out.println("availabel ip(v6) is " + v6IpConventer(ipPrefix + avIp));
				}
				System.out.println("broadcast ip is " + ipPrefix + endIp + ".0.0");
				System.out.println("broadcast ip(v6) is " + v6IpConventer(ipPrefix + endIp)+".0.0"+ "\n");
			}
			// B类IP地址
		} else if (ipSub1 < 192) {
			String ipPrefix = ip_sub1 + ip_sub2;
			for (int h = 0; h < idList.size(); h++) {
				int startIp = bi2De(idList.get(h) + getBits(hostIdBits, "0"));
				int endIp = bi2De(idList.get(h) + getBits(hostIdBits, "1"));
				System.out.println("sub net id _ " +(bi2De(idList.get(h))+1));
				System.out.println("net ip is " + ipPrefix + startIp);
				System.out.println("net ip(v6) is " + v6IpConventer(ipPrefix + startIp));
				for (int avIp = startIp + 1; avIp < endIp; avIp++) {
					System.out.println("availabel ip is " + ipPrefix + avIp);
				}
				System.out.println("broadcast ip is " + ipPrefix + endIp + ".0" );
				System.out.println("broadcast ip(v6) is " + v6IpConventer(ipPrefix + endIp)+".0"+ "\n");
			}
			// C类IP地址
		} else if (ipSub1 < 224) {
			String ipPrefix = ip_sub1 + "." + ip_sub2 + "." + ip_sub3 + ".";
			for (int h = 0; h < idList.size(); h++) {
				int startIp = bi2De(idList.get(h) + getBits(hostIdBits, "0"));
				int endIp = bi2De(idList.get(h) + getBits(hostIdBits, "1"));
				System.out.println("sub net id _ " + (bi2De(idList.get(h))+1));
				System.out.println("net ip is " + ipPrefix + startIp);
				System.out.println("net ip(v6) is " + v6IpConventer(ipPrefix + startIp));
				for (int avIp = startIp + 1; avIp < endIp; avIp++) {
					System.out.println("availabel ip is " + ipPrefix + avIp);
					System.out.println("availabel ip(v6) is " + v6IpConventer(ipPrefix + avIp));
				}
				System.out.println("broadcast ip is " + ipPrefix + endIp );
				System.out.println("broadcastIp_v6 is " + v6IpConventer(ipPrefix + endIp) +"\n");
			}
		}
	}

	/**
	 * subNetIdList 子网网络部分排列组合
	 * 
	 * @param subNetIdBits 子网借位数
	 * 
	 * 
	 */
	private static ArrayList<String> subNetIdList(int subNetIdBits) {
		String ret = "";
		ArrayList<String> subNetIdList = new ArrayList<String>();
		int i, j, k, l, m, n, o;
		for (i = 0; i < (subNetIdBits > 6 ? 2 : 1); i++) {
			for (j = 0; j < (subNetIdBits > 5 ? 2 : 1); j++) {
				for (k = 0; k < (subNetIdBits > 4 ? 2 : 1); k++) {
					for (l = 0; l < (subNetIdBits > 3 ? 2 : 1); l++) {
						for (m = 0; m < (subNetIdBits > 2 ? 2 : 1); m++) {
							for (n = 0; n < (subNetIdBits > 1 ? 2 : 1); n++) {
								for (o = 0; o < (subNetIdBits > 0 ? 2 : 1); o++) {
									ret = i + "" + j + "" + k + "" + l + "" + m + "" + n + "" + o;
									// System.out.println(ret.substring(ret.length() - subNetIdBits, ret.length()));
									subNetIdList.add(ret.substring(ret.length() - subNetIdBits, ret.length()));
								}
							}
						}
					}
				}
			}
		}
		return subNetIdList;
	}

	public static void checkNumber(String number) {
		String regexp = "^\\d+$";
		if (null == number || !number.matches(regexp)) {
			throw new IllegalArgumentException("input is not a number");
		}
	}

	/**
	 * getBits 获取主机位数的0,1形式的字符串
	 * 
	 * @param len 0或1的位数
	 * @param val 组成主机位的0或1
	 */
	public static String getBits(int len, String val) {
		String ret = "";
		for (int i = 0; i < len; i++) {
			ret += val;
		}
		return ret;
	}

	/**
	 * amountOfAvailableSubnet 计算每个子网可用主机数
	 * 
	 * @parap subIpBits 子网位数
	 * 
	 */
	private static int amountOfAvailableSubnet(String subIpBits) {
		// 可用子网个数
		int a = 2;
		int b = Integer.parseInt(subIpBits);
		b %= 8;
		int i = (int) Math.pow(a, (8 - b)) - 2;
		return i;

	}

	private static int amountOfSubnet(String string) {
		// 生成子网个数
		int a = 2;
		int d = Integer.parseInt(string);
		d %= 8;
		int i = (int) Math.pow(a, d);
		return i;
	}

	/**
	 * submask 获取子网掩码
	 * 
	 * @param subIp  子网掩码的某一段
	 * @param subIp2 判断ip类型(A,B,C)用参数
	 */
	private static String submask(String subIp, String subIp2) {
		// 子网掩码计算
		String mask = null;
		int subnetBits = Integer.parseInt(subIp);
		subnetBits %= 8;
		String bits = getBits1((8 - subnetBits), "1", "0");
		int e = Integer.parseInt(subIp2);
		// A类ip地址
		if (e < 127) {
			mask = "255." + bi2De(bits) + ".0.0";
			// B类ip地址
		} else if (e < 192) {
			mask = "255.255." + bi2De(bits) + ".0";
			// C类ip地址
		} else if (e < 224) {
			mask = "255.255.255." + bi2De(bits);
		} else {
		}
		return mask;
	}

	/**
	 * getBits1 计算子网掩码的子网部分
	 * 
	 * @param length 子网借位数
	 * @param val1   子网的网络部分补足的1
	 * @param val2   子网网络部分补足的0
	 */
	private static String getBits1(int length, String val1, String val2) {
		String ret = "";
		for (int j = 0; j < (8 - length); j++) {
			ret += val1;
		}
		for (int i = 0; i < length; i++) {
			ret += val2;
		}
		return ret;
	}

	/**
	 * bi2De 对子网借位数进行2进制转10进制,从而得出子网掩码缺失位部分0/1补足后数值
	 * 
	 * @param subIpBits 子网所借位数
	 * 
	 */
	private static int bi2De(String subIpBits) {
		int total = 0;
		String[] ch = subIpBits.split("");
		int chLength = ch.length;
		for (int i = 0; i < chLength; i++) {
			total += Integer.valueOf(ch[i]) * Math.pow(2, chLength - 1 - i);

		}
		return total;
	}

	private static void Writelog(String string) {
		// 生成日志
		String file = "C:\\Users\\xuexue\\Desktop\\batchLog.txt";
		BufferedWriter out = null;
		try {
			FileOutputStream fos = new FileOutputStream(file, true);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			out = new BufferedWriter(osw);
			out.write((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
			out.write("\r\n");
			out.write(string);
			out.write("\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
