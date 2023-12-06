import java.util.Scanner;
import java.util.ArrayList;

public class P1 {
	
	public static void main(String args[]) {
		ArrayList<Integer> times = new ArrayList<>(), distances = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in);) {
			times.addAll(parseList(sc.nextLine()));
			distances.addAll(parseList(sc.nextLine()));
		}
		int mult = 1;
		for (int i = 0; i < times.size(); i++) {
			mult *= rangeOf(times.get(i), distances.get(i));
		}
		System.out.println(mult);
	}

	private static ArrayList<Integer> parseList(String row) {
		ArrayList<Integer> list = new ArrayList<>();
		String temp = "";
		for (char c : row.toCharArray()) {
			if (c == ' ') {
				if (!temp.isEmpty()) {
					list.add(Integer.valueOf(temp));
					temp = "";
				}
			} else temp += c;
		}
		if (!temp.isEmpty()) {
			list.add(Integer.valueOf(temp));
			temp = "";
		}
		return list;
	}

	private static int rangeOf(int time, int distance) {
		double sqrt = Math.sqrt(time * time - 4 * distance);
		double x1 = 0.5 * (time - sqrt), x2 = 0.5 * (time + sqrt);
		if (x1 % 1 == 0) x1++;
		if (x2 % 1 == 0) x2--;
		return 1 + (int) (Math.floor(x2) - Math.ceil(x1));
	}

}
