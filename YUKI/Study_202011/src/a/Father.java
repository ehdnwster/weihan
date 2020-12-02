package a;

public class Father {
	private int age;
	String name;
	
	public void work(String newName,int newAge) {
		age=newAge;
		name=newName;
		System.out.println("His father called "+name+", is "+age+" years old. "+"\n"+"He has worked as a doctor for ten years in this hospital. ");
	}
	
	public void drive() {
		System.out.println("He goes to work by car every morning. ");
	}

}

