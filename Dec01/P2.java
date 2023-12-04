import java.util.ArrayList;
import java.util.Scanner;

public class P2 {
	
	private static String digits[] = {
		"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
	};

	public static void main(String args[]) {
		ArrayList<String> inputs = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			String str = sc.nextLine();
			if (str.isBlank())
				break;
			inputs.add(str);
		}
		sc.close();
		int sum = 0;
		for (String s : inputs) {
			sum += func(s);
		}
		System.out.println(sum);
	}

	private static int func(String s) {
		int firstDigit = 0, firstIndex = s.length(), secondDigit = 0, secondIndex = -1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
				firstDigit = s.charAt(i) - 48;
				firstIndex = i;
				break;
			}
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
				secondDigit = s.charAt(i) - 48;
				secondIndex = i;
				break;
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int n = 0; n < s.length() - digits[i].length() + 1; n++) {
				if (s.substring(n, n + digits[i].length()).equals(digits[i])) {
					if (n < firstIndex) {
						firstIndex = n;
						firstDigit = i;
					}
					if (n + digits[i].length() - 1 > secondIndex) {
						secondIndex = n + digits[i].length() - 1;
						secondDigit = i;
					}
				}
			}
		}
		return 10 * firstDigit + secondDigit;
	}

}
