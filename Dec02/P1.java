import java.util.ArrayList;
import java.util.Scanner;

public class P1 {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Game> games = new ArrayList<>();
		while (true) {
			String str = sc.nextLine();
			if (str.isBlank())
				break;
			games.add(new Game(str));
		}
		sc.close();
		int sum = 0;
		for (int i = 0; i < games.size(); i++) if (games.get(i).isPossible(12, 13, 14)) sum += i + 1;
		System.out.println(sum);
	}

	private static class Game {

		private int red = 0, green = 0, blue = 0;
		private String str;
		
		private Game(String str) {
			this.str = str;
			String round = "";
			for (char c : str.substring(str.indexOf(':')).toCharArray()) {
				if (c == ';') {
					int res[] = res(round);
					if (res[0] > red) red = res[0];
					if (res[1] > green) green = res[1];
					if (res[2] > blue) blue = res[2];
					round = "";
				} else {
					round += c;
				}
			}
			int res[] = res(round);
			if (res[0] > red) red = res[0];
			if (res[1] > green) green = res[1];
			if (res[2] > blue) blue = res[2];
		}
		
		private int[] res(String round) {
			int red = 0;
			int green = 0;
			int blue = 0;
			String num = "", type = "";
			for (char c : round.toCharArray()) {
				if (c >= 48 && c <= 57) {
					num += c;
				} else if (c == ',') {
					switch (type) {
						case "red" -> {
							red = Integer.valueOf(num);
						}
						case "green" -> {
							green = Integer.valueOf(num);
						}
						case "blue" -> {
							blue = Integer.valueOf(num);
						}
					}
					num = "";
					type = "";
				} else if (c != ' ' && c != ':') {
					type += c;
				}
			}
			switch (type) {
				case "red" -> {
					red = Integer.valueOf(num);
				}
				case "green" -> {
					green = Integer.valueOf(num);
				}
				case "blue" -> {
					blue = Integer.valueOf(num);
				}
			}
			return new int[]{red, green, blue};
		}

		private boolean isPossible(int red, int green, int blue) {
			return this.red <= red && this.green <= green && this.blue <= blue;
		}

	}

}
