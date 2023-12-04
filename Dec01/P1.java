import java.util.ArrayList;
import java.util.Scanner;

public class P1 {

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
		int firstDigit = 0, secondDigit = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
				firstDigit = s.charAt(i) - 48;
				break;
			}
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) >= 48 && s.charAt(i) <= 57) {
				secondDigit = s.charAt(i) - 48;
				break;
			}
		}
		return 10 * firstDigit + secondDigit;
	}

}
