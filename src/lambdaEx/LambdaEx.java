package lambdaEx;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("______________________________________________________");
		System.out.println(8>>1);
		System.out.println("______________________________________________________");
		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

		List<String> treeHighCaloricDishName = menu.stream().filter(d -> d.getCalories() > 300).map(Dish::getName)
				.limit(3).collect(toList());
		System.out.println(treeHighCaloricDishName);

		List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");	
		Collections.sort(words);
		List<Integer> wordLengths = words.stream().map(String::length).collect(toList());
		System.out.println(wordLengths);
		List<String> istrue = words.stream().filter(d -> d.equals("Java8")).collect(toList());
		int[] number = { 12, 44, 5, 3, 2, 9 };
		int sum = Arrays.stream(number).sum();

		// int product = number.stream().reduce(1, (a, b) -> a * b);

		/*
		 * Operator op = num -> System.out.println("Increasing num by 2:"+(num+2));
		 * op.operate(10); List<Double> dl = Arrays.asList(0.5, 1.0, 3.0, -2.0);
		 * dl.stream().map( x -> Math.sqrt(x) ) .forEach( y -> System.out.println(y) );
		 * dl.stream().map( Math::sqrt ) .forEach( y -> System.out.println(y) );
		 * 
		 * 
		 */

		/* teste codingame */
		Consumer<Integer> print1 =( x)->{System.out.println(x);}; // ok
		
		Consumer<Integer> print2 =(Integer x)->{System.out.println(x);}; // ok
		
		
		
		System.out.println(reshape(2, "1 23 456"));
		int[] ints = new int[] { 30, 4, 9, 10, 10, 10, 10, 12, 98, -10, 10 };
		String[] strings = new String[] { "f", "o", "o", "bar" };
		System.out.println(sumRange(ints));
		System.out.println(concat(strings));
		int[] tests = filter_duplicates(ints);
		// Arrays.stream(filter_duplicates(ints)).forEach(d->System.out.println(d));
		System.out.println(compute_sum_multiple(11));
		usinConsumer();
		forEachStream();
		System.out.println(exists(ints, -10));
		// createStream();
		System.out.println(isTwin("Hello", "leLhoo"));
		System.out.println(solve(100,120,90,25));
	}
	
	// coding game

	/*
	 * La méthode reshape(n, str) retourne la chaine str sans les espaces et
	 * formatée en lignes de n caractères maximum.
	 */

	public static String reshape(int n, String chaine) {
		chaine = chaine.replaceAll(" ", "");
		StringBuilder builder = new StringBuilder();
		int length = chaine.length();
		if (n > length) {
			n = length;
		}
		int index = n;
		int i = 0;
		while (i < length) {
			builder.append(chaine.substring(i, index));
			if (index != length)
				builder.append("\n");
			// System.out.println(chaine.substring(i, index));
			i = index;
			if (index + n < length) {
				index += n;
			} else {
				index = length;
			}

		}

		return builder.toString();
	}

	/*
	 * La méthode sumRange devrait retourner la somme des entiers compris entre 10
	 * et 100 inclusifs contenus dans le tableau passé en paramètre.
	 */
	static int sumRange(int[] ints) {

		int sum = Arrays.stream(ints).sorted().filter(i -> i >= 10 && i <= 100).sum();
		return sum;
	}

	/*
	 * StringUtils.concat(String[] strings) sert à joindre des chaînes de caractères
	 * bout à bout. Par exemple, à partir d'un tableau contenant "f", "o", "o",
	 * "bar", concat devrait retourner "foobar". Données : strings contient toujours
	 * au moins un élément.
	 */

	static String concat(String[] strings) {

		String rep = String.join("", strings);

		return rep;
	}

	// renvoyer le tableau sans les dublon

	static int[] filter_duplicates(int[] ints) {
		return Arrays.stream(ints).distinct().toArray();
	}

	// la fonction compute_sum_multiple(n) doit renvoyer la somme des multiples
	// positif de 3 5 ou 7 strictement
	// inferieur a n

	static int compute_sum_multiple(int n) {
		int[] arr = IntStream.rangeClosed(1, n).toArray();
		int sum = Arrays.stream(arr).filter(d -> d % 3 == 0 || d % 5 == 0 || d % 7 == 0).sum();
		// System.out.println(Arrays.toString(arr));
		return sum;
	}

	// renvoie true si un des valeurs est egale à 1 ou la somme

	static boolean is_bool(int i, int j) {
		if (i == 1 || j == 1 || i + j == 1)
			return true;
		return false;
	}

	static boolean is_on_even_position(int[] tabs, int value) {

		return false;
	}

	// l'element plus proche que zero dans un tableau

	static int plus_proche0(int[] ints) {
		Arrays.sort(ints);
		int length =ints.length;
		if (ints[0] > 0) {
			return ints[0];
		}
		if (ints[length-1]< 0) {
			return ints[length-1];
		}

		int val = 0;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > 0) {
				val = ints[i];
				if (Math.abs(ints[i - 1]) < val) {
					return ints[i - 1];
				} else {
					return val;
				}
			}
		}
		return 0;
	}

	// return true if element is present or else

	static boolean exists(int[] ints, int k) {

		// Arrays.parallelSort(ints); pas besoin de trier le tableau
		return Arrays.binarySearch(ints, k) > -1 ? true : false;
	}

	// Creating consumer action
	static void usinConsumer() {
		List<String> names = Arrays.asList("Alex", "Brian", "Charles");

		Consumer<String> makeUpperCase = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t.toUpperCase());
			}
		};

		names.forEach(makeUpperCase);
	}

	static void usingBiConsumer() {
		BiConsumer<String, Integer> action = (a, b) -> {
			// Process the entry here as per business
			System.out.println("Key is : " + a);
			System.out.println("Value is : " + b);
		};

		Map<String, Integer> map = new HashMap<>();

		map.put("A", 1);
		map.put("B", 2);
		map.put("C", 3);

		map.forEach(action);
	}

	// Java 8 forEach over stream elements

	static void forEachStream() {
		List<Integer> numberList = Arrays.asList(1, 6, 2, 3, 4, 5);

		Consumer<Integer> action = System.out::println;

		numberList.stream().filter(n -> n % 2 == 0).forEach(action);

		numberList.stream().filter(n -> n % 2 == 0).parallel().forEachOrdered(action);
	}

	// create Stream from arrays and list

	static void createStream() {
		// Arrays
		// 1
		Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
		// stream.forEach(p -> System.out.println(p));
		// 2
		Stream<Integer> stream2 = Stream.of(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		// stream2.forEach(p -> System.out.println(p));

		// list

		List<Integer> list = new ArrayList<Integer>();

		for (int i = 1; i < 10; i++) {
			list.add(i);
		}

		Stream<Integer> stream3 = list.stream();
		// stream3.forEach(p -> System.out.println(p));

		// Stream of String chars or tokens
		IntStream stream4 = "12345_abcdefg".chars();
		stream4.forEach(p -> System.out.println(p));

		// OR

		Stream<String> stream5 = Stream.of("A$B$C".split("\\$"));
		stream5.forEach(p -> System.out.println(p));

		// Collect Stream elements to an Array
		List<Integer> list1 = new ArrayList<Integer>();

		for (int i = 1; i < 10; i++) {
			list1.add(i);
		}

		Stream<Integer> stream6 = list1.stream();
		Integer[] evenNumbersArr = stream6.filter(i -> i % 2 == 0).toArray(Integer[]::new);
		System.out.print(evenNumbersArr);

	}

	// empecher les objets indesirable d setObjectInputFilter

	static int sumRang(int[] ints) {
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] >= 10 && ints[i] <= 100)
				sum += ints[i];
		}
		return sum;
	}

	// is Twin
	static boolean isTwin(String a, String b) {

		String sortedA =
			    Stream.of(a.toLowerCase().split(""))
			        .sorted()
			        .collect(Collectors.joining());
		String sortedB =
			    Stream.of(b.toLowerCase().split(""))
			        .sorted()
			        .collect(Collectors.joining());
		System.out.println(sortedA+" "+sortedA);
		
		String text = "This is some text \n that can be assigned to a variable.\n we can program.";
		List<String> srt=text.lines().collect(toList());

		return sortedA.contentEquals(sortedB);
	}
	
	// utiliser le bras des robotique de l'usine pour trier les colis STANDARD SPECIAL REJECTED
	
	static String solve(int width, int height, int length, int mass) {
		
		if(width<20 || width >200 || height<20 || height >200 || length<20 || length >200 || mass<10 || mass>1000) {
			return " Contrainte:\n 20<=width,height,length<=200 \n 10<=mass<=1000";
		}
		int volume = width*height*length;
		boolean encombrant = false,lourd=false;
		if(width>=150 || height>=150 || length>=150 || volume >=1000000) {
			encombrant = true;
		}
		if(mass>=20) {
			lourd = true;
		}
		
		if(encombrant && lourd) {
			return "REJECTED";
		}else if(encombrant || lourd) {
			return "SPECIAL";
		}
		
		return "STANDARD";
	}
	
	
}
