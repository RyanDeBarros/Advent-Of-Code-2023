import java.util.Scanner;
import java.util.ArrayList;

public class P1a {

	public static void main(String args[]) {
		ArrayList<String> strs = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in);) {
			while (true) {
				String str = sc.nextLine();
				if (str.isBlank())
					break;
				strs.add(str);
			}
		}
		char engine[][] = new char[strs.size() + 2][strs.get(0).length() + 2];
		for (int j = 0; j < strs.get(0).length(); j++) {
			engine[0][j] = '.';
			engine[engine.length - 1][j] = '.';
		}
		for (int i = 0; i < strs.size(); i++) {
			engine[i + 1][0] = '.';
			engine[i + 1][engine[i].length - 1] = '.';
			for (int j = 0; j < strs.get(i).length(); j++)
				engine[i + 1][j + 1] = strs.get(i).charAt(j);
		}

		int sum = 0;
		for (int i = 1; i < engine.length - 1; i++) {
			sum += checkRow(i, engine);
		}
		System.out.println(sum);
	}

	private static int checkRow(int i, char engine[][]) {
		int sum = 0;
		String add = "";
		boolean include = false;
		for (int j = 1; j < engine[i].length - 1; j++) {
			if (isDigit(engine[i][j])) {
				add += engine[i][j];
				if (hasPart(i, j, engine)) include = true;
			} else {
				if (!add.isEmpty() && include) sum += Integer.valueOf(add);
				add = "";
				include = false;
			}
		}
		if (!add.isEmpty() && include) sum += Integer.valueOf(add);
		return sum;
	}

	private static boolean isDigit(char c) {
		return c >= 48 && c<= 57;
	}

	private static boolean hasPart(int i, int j, char engine[][]) {
		return isPart(engine[i - 1][j - 1]) || isPart(engine[i - 1][j]) || isPart(engine[i - 1][j + 1])	|| isPart(engine[i][j - 1])
			|| isPart(engine[i][j + 1]) || isPart(engine[i + 1][j - 1]) || isPart(engine[i + 1][j]) || isPart(engine[i + 1][j + 1]);
	}

	private static boolean isPart(char c) {
		return !isDigit(c) && c != '.';
	}

}
