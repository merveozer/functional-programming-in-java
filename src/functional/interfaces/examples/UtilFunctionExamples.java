package functional.interfaces.examples;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//Consumer -> no result
//Predicate -> boolean result
//Function -> input and output
//Supplier -> no input only
class Employee{
	private int age;
	public int getAge(){return age;} public void setAge(int age) {this.age = age;}
}


public class UtilFunctionExamples {

	public static void main(String... args) {
		function();
	}
	
	public static void function () {
		
		String myString = "Merve";
		Employee e1 = new Employee();
		
		System.out.println();
		System.out.println("Consumer");
		Consumer<Employee> setAge = e -> e.setAge(20);
		Consumer<Employee> getAge = e -> System.out.println(e.getAge());
		
		setAge.accept(e1);
		System.out.println("e1's age is: " + e1.getAge());
		
		System.out.println("Use andThen: ");
		setAge.andThen(getAge).accept(e1);
		
		System.out.println("--------------------------------------");
		System.out.println("Predicate");
		System.out.println();
		
		Predicate<Integer> checkEven = num -> num % 2 == 0;
		Predicate<Integer> largerThan10 = num -> num > 10;
		//boolean v = checkEven.test(23);
		System.out.println("checkEven's result: " + checkEven.test(15));
		System.out.println("checkEven's result: " + checkEven.test(10));
		System.out.println("checkEven's result: " + checkEven.test(myString.length()));
		System.out.println("Result: "  + checkEven.and(largerThan10).test(myString.length())); // false & false == false
		System.out.println("Result1: " + checkEven.and(largerThan10).test(20)); //true && true == true
		System.out.println("Result2: " + checkEven.or(largerThan10).test(8)); //false || true == true
		System.out.println("Result3: " + checkEven.negate().test(8)); 
		
		System.out.println("--------------------------------------");
		System.out.println("Function");
		System.out.println();
		
		final int i = 2;
		
		Function<Integer, Double> square = (num) -> Math.sqrt(num);
		System.out.println(square.apply(16));
		
		Function<Double, Double> divider = num -> num / i;
		System.out.println("Divider's result: " + divider.apply(16.0));
		
		Function<Double, Double> multiplier = num -> num * i;
		System.out.println("Multiplier result: " + multiplier.apply(16.0));
		
		//divider = divider.andThen(num -> num * 3);
		//System.out.println("And Then: " + divider.apply(30.0));
		
		divider = divider.compose(num -> num * 3);
		System.out.println("Compose: " + divider.apply(20.0));
		
		Function<Integer, Integer> identity = Function.identity();
		System.out.println("Identity: " + identity.apply(10));
	
		
		System.out.println("--------------------------------------");
		System.out.println("Supplier");
		System.out.println();
		
		Supplier<Double> randomValue = () -> Math.random();
		System.out.println(randomValue.get());
	}
}
