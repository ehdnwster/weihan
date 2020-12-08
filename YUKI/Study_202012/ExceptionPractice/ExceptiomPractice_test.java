/**
 * 
 */
package ExceptionPractice;

/**
 * @author Yuki
 *
 */
public class ExceptiomPractice_test {
	public static void main(String[] args) {
        System.out.println(test()); //打印出test()方法的结果;
    }
   
	/**
	 * 测试方法
	 * 
	 */
	public static int test(){
       int i = 1; //定义一个值为1的变量i;
       try{
           i++; //i自增为2,传递给i,i=2;
           System.out.println("try block, i = "+i);//打印出try block, i = 2; 
           //没有发生异常系,所以不执行25-28行代码;
       }catch(Exception e){
           i ++;
           System.out.println("catch block i = "+i);
       //finally关键字,顺序执行finally后代码 ;   
       }finally{
           i = 10;
           System.out.println("finally block i = "+i);
       }
       return i;//返回i的值10;
   }

}
