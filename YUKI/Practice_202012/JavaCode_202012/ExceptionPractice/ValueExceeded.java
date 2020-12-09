/**
 * 
 */
package ExceptionPractice;

/**
 * @author Yuki
 * 创建自定义异常系类,命名为ValueExceeded,并继承于Exception
 */
@SuppressWarnings("serial")
public class ValueExceeded extends Exception {
	public ValueExceeded(String ErrorException) { //构造函数;
		super(ErrorException);//父类构造方法;
	}

}
