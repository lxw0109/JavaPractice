public class StaticBlock{
	{
		//只有创建该类的对象时才会执行
		System.out.println("2");
	}
	static {
		System.out.println("1");
	}
	public static void main(String[] args){
		Test test = new Test();
		System.out.println("3");
	} 
}
