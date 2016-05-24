//package ucas.lxw.java.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

/*
Basic syntax:
(parameters) -> expression
or
(parameters) -> { statements; }

Demos:
/*
() -> 5
x -> 2* x
(x, y) -> x - y
(int x, int y) -> x + y
(String s) -> System.out.println(s)
* /
*/
public class LambdaDemo{
	public static void main(String[] args){
		String[] atp = {"Rafael Nadal",
				"Novak Djokovic", 
				"Roger Federer",
				"Andy Murray",
				"Juan Martin",
				"Del Potro"
				};
		List<String> players = Arrays.asList(atp);

		//Normal for()
		for(String player : players){
			System.out.print(player + "; ");
		}
		System.out.println();
		
		//Demo 1: lambda (Java 8)
		players.forEach((player) -> System.out.print(player + "; "));
		System.out.println();

		//Demo 2: (Java 8)
		//表达式 System.out::println 是一个方法引用，等同于 lambda 表达式 x -> System.out.println(x);
		//Math::pow 等同于 (x, y) -> Math.pow(x, y);
		players.forEach(System.out::println);

		/*
		//Demo 3: 在GUI中，使用"lambda"来代替"匿名内部类"
		// 使用匿名内部类  
		btn.setOnAction(new EventHandler<ActionEvent>() {  
	          	@Override  
	          	public void handle(ActionEvent event) {  
	          		System.out.println("Hello World!");   
	          	}
    		}); 
		// 或者使用 lambda expression  
		btn.setOnAction(event -> System.out.println("Hello World!"));  
		*/

		//Tips:
		//如果 lambda 表达式的参数类型可以被推导，那么可以省略掉。
		Comparator<String> comparator = (String first, String second) -> Integer.compare(first.length(), second.length());
		comparator = (first, second) -> Integer.compare(first.length(), second.length());
		//上面的例子当中会推导出 first 和 second 的类型是 String， 因为表达式赋值给了一个字符串比较器。

		/*
		//Notes
		在 lambda 表达式当中不允许声明一个与局部变量同名的参数或者局部变量。 
		String first = ""; 
		Comparator<String> comparator = (first, second) -> Integer.compare(first.length(), second.length()); //编译会出错
		*/
	}
}
/*Reference:
Java Lambda表达式入门: http://blog.csdn.net/renfufei/article/details/24600507
Java 8 Lambda 表达式学习心得总结: http://www.codeceo.com/article/java8-lambda-usage.html#0-tsina-1-7723-397232819ff9a47a7b7e80a40613cfe1
*/
