package algorithm;

/**
 * @author Yuki RecursionAlgorithm 递归算法实例
 */
public class RecursionAlgorithm {
	public static void main(String[] args) {
		int i = 4;
		System.out.println(num(i)); // 打印输出结果
	}
	
	/**
	 * num 自定义输出指定方法结果值,采用递归算法
	 * @param i给定值
	 */
	private static int num(int i) {
		int n = 1;
		if (i >= 0) { // 保证递归算法有意义
			if (i == 1 || i == 0) { // 递归方法的核心条件
				return n;
			}
			return num(i - 1) + num(i - 2);// 递归算法实际运用
		} else {
			return i;
		}
	}
}

/**
 * 运行思路:
 *num(4)=num(3)+num(2) 即3+2=5;
 *num(3)=num(2)+num(1) 即2+1=3;⬆️
 *num(2)=num(1)+num(0) 即1+1=2;⬆️
*/