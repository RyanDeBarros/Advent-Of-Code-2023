import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class P1 {

	public static void main(String args[]) {
		ArrayList<Card> cards = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in);) {
			while (true) {
				String str = sc.nextLine();
				if (str.isBlank()) break;
				cards.add(new Card(str));
			}
		}

		int sum = 0;
		for (Card card : cards) sum += card.value();
		System.out.println(sum);
	}

	private static class Card {

		private final HashSet<Integer> winning = new HashSet<>(), holding = new HashSet<>();

		private Card(String str) {
			str = str.substring(str.indexOf(':') + 1);
			String temp = "";
			boolean first = true;
			for (char c : str.toCharArray()) {
				switch (c) {
					case ' ' -> {
						if (!temp.isEmpty()) {
							if (first) winning.add(Integer.valueOf(temp));
							else holding.add(Integer.valueOf(temp));
							temp = "";
						}
					}
					case '|' -> {first = false;}
					default -> {
						temp += c;
					}
				}
			}
			if (!temp.isEmpty()) {
				if (first) winning.add(Integer.valueOf(temp));
				else holding.add(Integer.valueOf(temp));
			}
		}

		private int value() {
			int value = 0;
			for (Integer i : holding) if (winning.contains(i.intValue())) {
				if (value == 0) value = 1;
				else value *= 2;
			}
			return value;
		}

	}

}
