package ucas.lxw.java.lambda;

import java.util.Arrays;
import java.util.List;

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
						"Stanislas Wawrinka",
						"David Ferrer",
						"Roger Federer",
						"Andy Murray",
						"Tomas Berdych",
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

		//Demo 2: (Java 8)
		players.forEach(System.out::println);

		/*
		//Demo 3: ÔÚGUIÖÐ£¬Ê¹ÓÃ"lambda"À´´úÌæ"ÄäÃûÄÚ²¿Àà"
		// Ê¹ÓÃÄäÃûÄÚ²¿Àà  
		btn.setOnAction(new EventHandler<ActionEvent>() {  
          @Override  
          public void handle(ActionEvent event) {  
              System.out.println("Hello World!");   
          }  
    	});  
		// »òÕßÊ¹ÓÃ lambda expression  
		btn.setOnAction(event -> System.out.println("Hello World!"));  
		*/
	}
}
/*Reference:
Java Lambda±í´ïÊ½ÈëÃÅ: http://blog.csdn.net/renfufei/article/details/24600507
*/
