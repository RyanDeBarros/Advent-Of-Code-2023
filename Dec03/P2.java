import java.util.Scanner;
import java.util.ArrayList;

public class P2 {

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
		for (int i = 0; i < engine.length; i++)
			engine[i] = strs.get(i).toCharArray();

		int sum = 0;
		for (int i = 0; i < engine.length; i++)
			for (int j = 0; j < engine[i].length; j++)
				if (isGear(i, j, engine)) sum += gearRatio(i, j, engine);

		System.out.println(sum);
	}

	private static boolean isGear(int i, int j, char engine[][]) {
		if (engine[i][j] != '*') return false;
		if (i == 0) {
			if (j == 0) return 2 == nums(engine[i][j + 1]) + nums(engine[i + 1][j], engine[i + 1][j + 1]);
			else if (j == engine[i].length - 1) return 2 == nums(engine[i][j - 1]) + nums(engine[i + 1][j - 1], engine[i + 1][j]);
			else return 2 == nums(engine[i][j - 1]) + nums(engine[i][j + 1]) + nums(engine[i + 1][j - 1], engine[i + 1][j], engine[i + 1][j + 1]);
		} else if (i == engine.length - 1) {
			if (j == 0) return 2 == nums(engine[i - 1][j], engine[i - 1][j + 1]) + nums(engine[i][j + 1]);
			else if (j == engine[i].length - 1) return 2 == nums(engine[i - 1][j - 1], engine[i - 1][j]) + nums(engine[i][j - 1]);
			else return 2 == nums(engine[i - 1][j - 1], engine[i - 1][j], engine[i - 1][j + 1]) + nums(engine[i][j - 1]) + nums(engine[i][j + 1]);
		} else {
			if (j == 0) return 2 == nums(engine[i - 1][j], engine[i - 1][j + 1]) + nums(engine[i][j + 1]) + nums(engine[i + 1][j], engine[i + 1][j + 1]);
			else if (j == engine[i].length - 1) return 2 == nums(engine[i - 1][j - 1], engine[i - 1][j]) + nums(engine[i][j - 1])
				+ nums(engine[i + 1][j - 1], engine[i + 1][j]);
			else return 2 == nums(engine[i - 1][j - 1], engine[i - 1][j], engine[i - 1][j + 1])
				+ nums(engine[i][j - 1]) + nums(engine[i][j + 1]) + nums(engine[i + 1][j - 1], engine[i + 1][j], engine[i + 1][j + 1]);
		}
	}

	private static int nums(char... cs) {
		int nums = 0;
		boolean newNum = true;
		for (int i = 0; i < cs.length; i++) {
			if (isDigit(cs[i])) {
				if (newNum) {
					nums++;
					newNum = false;
				}
			} else {
				newNum = true;
			}
		}
		return nums;
	}

	private static boolean isDigit(char c) {
		return c >= 48 && c<= 57;
	}

	private static int gearRatio(int i, int j, char engine[][]) {
		int firstNum = -1, secondNum = -1;
		if (j > 0 && isDigit(engine[i][j - 1])) {
			String str = "";
			for (int n = j - 1; n >= 0; n--) {
				if (isDigit(engine[i][n])) str = engine[i][n] + str;
				else break;
			}
			firstNum = Integer.valueOf(str);
		}
		if (j < engine[i].length - 1 && isDigit(engine[i][j + 1])) {
			String str = "";
			for (int n = j + 1; n < engine[i].length; n++) {
				if (isDigit(engine[i][n])) str += engine[i][n];
				else break;
			}
			if (firstNum == -1) firstNum = Integer.valueOf(str);
			else secondNum = Integer.valueOf(str);
			if (secondNum != -1) return firstNum * secondNum;
		}
		if (i > 0) {
			if (j > 0 && isDigit(engine[i - 1][j - 1])) {
				if (firstNum == -1) firstNum = numAt(i - 1, j - 1, engine);
				else secondNum = numAt(i - 1, j - 1, engine);
				if (secondNum != -1) return firstNum * secondNum;
			}
			if (isDigit(engine[i - 1][j]) && (j == 0 || !isDigit(engine[i - 1][j - 1]))) {
				if (firstNum == -1) firstNum = numAt(i - 1, j, engine);
				else secondNum = numAt(i - 1, j, engine);
				if (secondNum != -1) return firstNum * secondNum;
			}
			if (j < engine[i].length - 1 && isDigit(engine[i - 1][j + 1]) && !isDigit(engine[i - 1][j])) {
				if (firstNum == -1) firstNum = numAt(i - 1, j + 1, engine);
				else secondNum = numAt(i - 1, j + 1, engine);
				if (secondNum != -1) return firstNum * secondNum;
			}
		}
		if (i < engine.length - 1) {
			if (j > 0 && isDigit(engine[i + 1][j - 1])) {
				if (firstNum == -1) firstNum = numAt(i + 1, j - 1, engine);
				else secondNum = numAt(i + 1, j - 1, engine);
				if (secondNum != -1) return firstNum * secondNum;
			}
			if (isDigit(engine[i + 1][j]) && (j == 0 || !isDigit(engine[i + 1][j - 1]))) {
				if (firstNum == -1) firstNum = numAt(i + 1, j, engine);
				else secondNum = numAt(i + 1, j, engine);
				if (secondNum != -1) return firstNum * secondNum;
			}
			if (j < engine[i].length - 1 && isDigit(engine[i + 1][j + 1]) && !isDigit(engine[i + 1][j])) {
				if (firstNum == -1) firstNum = numAt(i + 1, j + 1, engine);
				else secondNum = numAt(i + 1, j + 1, engine);
				if (secondNum != -1) return firstNum * secondNum;
			}
		}
		return firstNum * secondNum;
	}

	private static int numAt(int i, int j, char engine[][]) {
		String str = "";
		int k = 0;
		for (int n = j; n >= 0; n--) {
			if (!isDigit(engine[i][n])) {
				k = n + 1;
				break;
			}
		}
		for (int n = k; n < engine[i].length; n++) {
			if (isDigit(engine[i][n])) str += engine[i][n];
			else break;
		}
		return Integer.valueOf(str);
	}

}
