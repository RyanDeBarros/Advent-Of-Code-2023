import java.util.Scanner;
import java.util.ArrayList;

public class P1 {

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
		char engine[][] = new char[strs.size()][];
		for (int i = 0; i < engine.length; i++) {
			engine[i] = strs.get(i).toCharArray();
		}

		int sum = 0;
		for (int i = 0; i < engine.length; i++) {
			sum += checkRow(i, engine);
		}
		System.out.println(sum);
	}

	private static int checkRow(int i, char engine[][]) {
		int sum = 0;
		String add = "";
		boolean include = false;
		for (int j = 0; j < engine[i].length; j++) {
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
		if (i == 0) {
			if (j == 0)
				return isPart(engine[i][j + 1]) || isPart(engine[i + 1][j + 1]) || isPart(engine[i + 1][j]);
			else if (j == engine[i].length - 1)
				return isPart(engine[0][j - 1]) || isPart(engine[1][j]) || isPart(engine[1][j - 1]);
			else
				return isPart(engine[0][j - 1]) || isPart(engine[0][j + 1]) || isPart(engine[1][j - 1])
					|| isPart(engine[1][j]) || isPart(engine[1][j + 1]);
		} else if (i == engine.length - 1) {
			if (j == 0)
				return isPart(engine[i][j + 1]) || isPart(engine[i - 1][j]) || isPart(engine[i - 1][j + 1]);
			else if (j == engine[i].length - 1)
				return isPart(engine[i][j - 1]) || isPart(engine[i - 1][j]) || isPart(engine[i - 1][j - 1]);
			else
				return isPart(engine[i - 1][j - 1]) || isPart(engine[i - 1][j]) || isPart(engine[i - 1][j + 1])
					|| isPart(engine[i][j - 1]) || isPart(engine[i][j + 1]);
		} else {
			if (j == 0)
				return isPart(engine[i - 1][j]) || isPart(engine[i - 1][j + 1]) || isPart(engine[i][j + 1])
					|| isPart(engine[i + 1][j]) || isPart(engine[i + 1][j + 1]);
			else if (j == engine[i].length - 1)
				return isPart(engine[i - 1][j - 1]) || isPart(engine[i - 1][j]) || isPart(engine[i][j - 1])
					|| isPart(engine[i + 1][j - 1]) || isPart(engine[i + 1][j]);
			else
				return isPart(engine[i - 1][j - 1]) || isPart(engine[i - 1][j]) || isPart(engine[i - 1][j + 1])
					|| isPart(engine[i][j - 1]) || isPart(engine[i][j + 1]) || isPart(engine[i + 1][j - 1])
					|| isPart(engine[i + 1][j]) || isPart(engine[i + 1][j + 1]);
		}
	}

	private static boolean isPart(char c) {
		return !isDigit(c) && c != '.';
	}

}
