package ExceptionPractice;

/**
 * @author Yuki
 */
public class ExceptionPractice_test_finally {
	public static void main(String[] args) {
        System.out.println(test());//打印出test()方法的结果 
    }
  
	/**
	 * 测试方法
	 */
	public static int test(){
       int i = 1;
       try{
           i++;
           System.out.println("try block, i = "+i);
           return i;
       }catch(Exception e){
           i ++;
           System.out.println("catch block i = "+i);
           return i;
       }finally{
           i = 10;
           System.out.println("finally block i = "+i);
       }  
   }
}

/**把return放到try块中和catch块中️
 *程序是先return呢还是先finally呢？➡️回答:先finally,运行顺序为 finally➡(try block)return;
*️返回值i会改变吗？➡️回答:会,放到哪个结构中,就是输出那个结构时i的值,当前代码会输出try部分i的值2; 
 *如果认为先执行return，那么，是先执行哪个return？ try里的还是catch里的？
 *➡️回答:️ 先执行try里的return,因为当前保护代码不会抛出异常系️,不会执行20-23行catch部分代码
 *在finally里放入return➡️回答:输出
  try block, i = 2
  finally block i = 10
  10 
  *debug验证方法: 先分别在21,26行打断点,运行完后再在19行打断点
  *各自断点的选择原因:
  *   1. 21行断点:测试运行完try block后,是否进入catch block➡️结果:不运行catch block
      2. 26行断点:测试运行完try block后,是否进入finally block➡️:结果:运行finally block
      3. 19行断点:测试代码在运行完26行finally block部分后,是否返回try block运行return i➡️结果:返回
  */
 