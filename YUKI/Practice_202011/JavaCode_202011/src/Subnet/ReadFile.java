package Subnet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

	public static ArrayList<String> toArrayByFileReader1(String path) {
		// TODO Auto-generated method stub
		ArrayList<String> arrayList=new ArrayList<> ();
		try {
			FileReader fr=new FileReader(path);
			BufferedReader bf=new BufferedReader(fr);
			String str;
			//按行读取字符串
			while((str=bf.readLine())!=null) {
				arrayList.add(str);
			}
			bf.close();
			fr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return arrayList;
		//返回数组;

	}





}
