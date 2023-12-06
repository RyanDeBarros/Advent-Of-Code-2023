import java.util.Scanner;

public class P2 {
	
	public static void main(String args[]) {
		long time = 0, distance = 0;
		try (Scanner sc = new Scanner(System.in);) {
			time = parseList(sc.nextLine());
			distance = parseList(sc.nextLine());
		}
		System.out.println(rangeOf(time, distance));
	}

	private static long parseList(String row) {
		long parse = 0;
		String temp = "";
		for (char c : row.toCharArray()) if (c != ' ') temp += c;
		if (!temp.isEmpty()) parse = Long.valueOf(temp);
		return parse;
	}

	private static long rangeOf(long time, long distance) {
		double sqrt = Math.sqrt(time * time - 4 * distance);
		double x1 = 0.5 * (time - sqrt), x2 = 0.5 * (time + sqrt);
		if (x1 % 1 == 0) x1++;
		if (x2 % 1 == 0) x2--;
		return 1 + (long) (Math.floor(x2) - Math.ceil(x1));
	}

}
