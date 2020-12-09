/**
 * 
 */
package ExceptionPractice;

/**
 * @author Yuki
 *
 */
public class ExceptionPac_ValueExceeded {

	public static void main(String[] args) {
		int i=1000;//定义一个整数类型变量i,赋值为1000;
		//对math()方法进行try/catch异常系捕获;
		try {
			System.out.println(math(i));//打印出math()方法的结果;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 *  
	 */
	private static int math(int i) throws ValueExceeded{
			if(i!=1000) {
				System.out.println("i的值不是1000");//打印输出;
			}else{
				throw new ValueExceeded("i的值是1000");//打印输出ValueExeeded的异常内容;
			}
		return i;
	}

}
