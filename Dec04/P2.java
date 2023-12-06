import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class P2 {

	public static void main(String args[]) {
		ArrayList<Card> cards = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in);) {
			while (true) {
				String str = sc.nextLine();
				if (str.isBlank()) break;
				cards.add(new Card(str));
			}
		}
		int copies[] = new int[cards.size()];
		for (int i = 0; i < copies.length; i++) {
			copies[i]++;
			int num = cards.get(i).num();
			for (int k = 0; k < copies[i]; k++) for (int n = 0; n < num; n++) copies[i + n + 1]++;
		}
		int num = 0;
		for (int i = 0; i < copies.length; i++) num += copies[i];
		System.out.println(num);
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

		private int num() {
			int num = 0;
			for (Integer i : holding) if (winning.contains(i.intValue())) num++;
			return num;
		}

	}

}
