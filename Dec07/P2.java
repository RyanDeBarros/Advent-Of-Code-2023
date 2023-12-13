import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;


public class P2 {

	public static void main(String args[]) {
		ArrayList<Hand> hands = new ArrayList<>();
		try (Scanner sc = new Scanner(System.in);) {
			while (true) {
				String str = sc.nextLine();
				if (str.isBlank()) break;
				hands.add(new Hand(str));
			}
		}
		Collections.sort(hands, Hand.rank);
		long sum = 0;
		for (int i = 0; i < hands.size(); i++) sum += (i + 1) * hands.get(i).bid;
		System.out.println(sum);
	}

	private static class Hand {
		private final char labels[] = new char[5];
		private final short handType;
		private final int bid;

		private final static char nonJokers[] = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A'};

		private Hand(String str) {
			boolean jokers[] = new boolean[5];
			for (int i = 0; i < 5; i++) {
				labels[i] = str.charAt(i);
				if (labels[i] == 'J') jokers[i] = true;
			}
			String temp = "";
			for (int i = 6; i < str.length(); i++) {
				temp += str.charAt(i);
			}
			bid = Integer.valueOf(temp);

			short highestType = 0;
			ArrayList<Short> types = new ArrayList<>();
			checkHigherType(0, jokers, types);
			for (Short s : types) if (s > highestType) highestType = s;

			handType = highestType;
			for (int i = 0; i < jokers.length; i++) if (jokers[i]) labels[i] = 'J';
		}

		private void checkHigherType(int i, boolean jokers[], ArrayList<Short> types) {
			if (i < 4) {
				if (jokers[i]) {
					for (int j = 0; j < nonJokers.length; j++) {
						labels[i] = nonJokers[j];
						checkHigherType(i + 1, jokers, types);
					}
				} else checkHigherType(i + 1, jokers, types);
			} else {
				if (jokers[i]) {
					for (int j = 0; j < nonJokers.length; j++) {
						labels[i] = nonJokers[j];
						types.add(handType());
					}
				} else types.add(handType());
			}
		}

		private short handType() {
			boolean checked[] = new boolean[labels.length];
			ArrayList<Integer> counts = new ArrayList<>();
			int current = 0;
			for (int i = 0; i < labels.length; i++) {
				if (!checked[i]) {
					checked[i] = true;
					current = 1;
					for (int j = i + 1; j < labels.length; j++) {
						if (labels[i] == labels[j]) {
							checked[j] = true;
							current++;
						}
					}
					counts.add(current);
					current = 0;
				}
			}
			return analyze(counts);
		}

		private static short analyze(ArrayList<Integer> counts) {
			if (counts.size() == 5) return 0;
			if (counts.size() == 4) return 1;
			if (counts.size() == 1) return 6;
			if (counts.size() == 2) {
				if (counts.get(0) == 4 || counts.get(1) == 4) return 5;
				else return 4;
			}
			if (counts.get(0) == 3 || counts.get(1) == 3 || counts.get(2) == 3) return 3;
			else return 2;
		}

		private static Comparator<Hand> rank = (h1, h2) -> {
			if (h1.handType > h2.handType) return 1;
			if (h1.handType < h2.handType) return -1;
			for (int i = 0; i < 5; i++) {
				if (num(h1.labels[i]) > num(h2.labels[i])) return 1;
				if (num(h1.labels[i]) < num(h2.labels[i])) return -1;
			}
			return 0;
		};

		private static int num(char c) {
			return switch (c) {
				case 'J' -> 1;
				case '2', '3', '4', '5', '6', '7', '8', '9' -> c - 48;
				case 'T' -> 10;
				case 'Q' -> 12;
				case 'K' -> 13;
				case 'A' -> 14;
				default -> 0;
			};
		}
	}

}
