
public class Classtwo extends Classone {

	
    public static void main(String[] args) {
		
	
//    	Classone classone = new Classone();
    	
    	Classtwo classtwo = new Classtwo();
    	
    	classtwo.test(10 , "String");
    	
    	classtwo.test(100 , "hemal",10);
    	
    	classtwo.test2(10 , "String");
    	
    	classtwo.test2(100 , "hemal",10);
    	
	}
    
	
public void test2(int i , String j){
	
	System.out.println("Testing for inheritance 2 ");
	
	System.out.println(i +" "+j);
}

public void test2(int i , String j,int l){
	
	System.out.println("Testing for inheritance 2 ");
	
	System.out.println(i +" "+j);
}
   
}
