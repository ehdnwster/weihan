package Subnet;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class BinaryDecimalConverter {

	private static int itemCount;
	private static int errorCount;

	private static String[] types = new String[] { "2", "10" };

	public static void main(String[] args) {
		ArrayList<String> list = ReadFile.toArrayByFileReader1(args[0]);
		//System.out.print(list);
		for (int i = 0; i < list.size(); i++) {
			itemCount += 1;
			try {
				System.out.println("Input Data: " + String.format("%30s", list.get(i)));
				System.out.println("Out   Data: " + String.format("%30s", calculate(list.get(i).split(" "))));
				System.out.println(" ");
			} catch (Exception e) {
				//e.printStackTrace();
				errorCount += 1;
			}
		}

		writeLog("ItemCount=" + itemCount + "      ErrorCount=" + errorCount);

		//calculate(args);

	}

	public static String calculate(String[] args) {
		if (args.length < 2 || !Arrays.asList(types).contains(args[0])) {
			errorCount += 1;
			System.out.println("args error");
			return "Args Error";
		}

		if (Integer.parseInt(args[0]) == 2) {
			//jdk::Binary to decimal
			//System.out.println(Integer.parseInt(args[1], 2));

			return "Decimal Value = " + binary2Decimal(args[1]) + "";
		} else {
			// jdk::Decimal to binary
			//System.out.println(Integer.toBinaryString(Integer.parseInt(args[1])));

			return "Binary Value = " + decimal2Binary(Integer.parseInt(args[1]));
		}
	}

	public static int binary2Decimal(String number) {
		return scale2Decimal(number, 2);
	}

	public static String decimal2Binary(int number) {
		return decimal2Scale(number, 2);
	}

	public static String decimal2Scale(int number, int scale) {
		if (scale < 2 || scale > 32) {
			throw new IllegalArgumentException("scale is not in range");
		}
		String result = "";
		while (0 != number) {
			result = number % scale + result;
			number = number / scale;
		}

		return result;
	}

	public static int scale2Decimal(String number, int scale) {
		checkNumber(number);
		if (2 > scale || scale > 32) {
			throw new IllegalArgumentException("scale is not in range");
		}

		int total = 0;
		String[] ch = number.split("");
		int chLength = ch.length;
		for (int i = 0; i < chLength; i++) {
			total += Integer.valueOf(ch[i]) * Math.pow(scale, chLength - 1 - i);
		}
		return total;

	}

	public static void checkNumber(String number) {
		String regexp = "^\\d+$";
		if (null == number || !number.matches(regexp)) {
			throw new IllegalArgumentException("input is not a number");
		}
	}

	public static void writeLog(String str) {
		String file = "C:\\Users\\cyou\\Desktop\\batchLog.txt";
		BufferedWriter out = null;
		try {
			FileOutputStream fos = new FileOutputStream(file, true);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			out = new BufferedWriter(osw);
			out.write((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
			out.write("\r\n");
			out.write(str);
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

