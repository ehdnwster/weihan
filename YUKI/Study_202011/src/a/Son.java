package a;

public class Son {
	protected int age;
	String name;

public int getAge() {
	return age;
}

public void setAge(int newAge) {
	age=newAge;
	
}

public void getPlay(int newAge,String newName) {
	Son s1=new Son();
	s1.play(newAge, newName);
	
}

private void play(int newAge,String newName){
	age=newAge;
	name=newName;
	System.out.println("This boy called "+name+", is "+age+" years old. "+"He is plyaing with his toy.");
}

public void study() {
	System.out.println("He will go to school with his toy friend.");
}
}