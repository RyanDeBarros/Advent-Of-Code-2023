import java.util.Scanner;
import java.util.ArrayList;

public class P2 {

	public static void main(String args[]) {
		try (Scanner sc = new Scanner(System.in);) {
			ArrayList<Long> seeds = new ArrayList<>();
			String seedRow = sc.nextLine(), temp = "";
			for (char c : seedRow.toCharArray()) {
				if (c == ' ' && !temp.isEmpty()) {
					seeds.add(Long.valueOf(temp));
					temp = "";
				} else temp += c;
			}
			if (!temp.isEmpty()) seeds.add(Long.valueOf(temp));

			ArrayList<Mapping> mappings = new ArrayList<>();
			ArrayList<String> rows = new ArrayList<>();
			String str = sc.nextLine();
			while (true) {
				while (true) {
					if (str.isBlank()) {
						mappings.add(new Mapping(rows));
						rows.clear();
						break;
					} else rows.add(str);
					str = sc.nextLine();
				}
				str = sc.nextLine();
				if (str.isBlank()) break;
			}

			long min = Long.MAX_VALUE;
			for (long l = 0; l < min; l++) {
				long n = l;
				for (int i = mappings.size() - 1; i >= 0; i--) n = mappings.get(i).sourceOf(n);
				if (inSeeds(n, seeds)) {
					min = l;
					break;
				}
			}
			System.out.println(min);
		}
	}

	private static boolean inSeeds(long n, ArrayList<Long> seeds) {
		for (int i = 0; i < seeds.size(); i += 2) {
			if (n >= seeds.get(i) && n < seeds.get(i) + seeds.get(i + 1)) return true;
		}
		return false;
	}
	
	private static class Mapping {
		
		private final ArrayList<MappingElement> elements = new ArrayList<>();

		private Mapping(ArrayList<String> rows) {
			rows.forEach(row -> elements.add(new MappingElement(row)));
		}

		private long sourceOf(long destination) {
			for (MappingElement element : elements) {
				if (element.destinationInRange(destination)) return element.sourceOf(destination);
			}
			return destination;
		}

	}

	private static class MappingElement {

		private long destStart, sourceStart, rangeL;

		private MappingElement(String row) {
			int arg = 0;
			String temp = "";
			for (char c : row.toCharArray()) {
				if (c == ' ' && !temp.isEmpty()) {
					switch (arg) {
						case 0 -> destStart = Long.valueOf(temp);
						case 1 -> sourceStart = Long.valueOf(temp);
					}
					arg++;
					temp = "";
				} else temp += c;
			}
			if (!temp.isEmpty()) rangeL = Long.valueOf(temp);
		}

		private boolean destinationInRange(long destination) {
			long diff = destination - destStart;
			return diff < rangeL && diff >= 0;
		}

		private long sourceOf(long destination) {
			return sourceStart + destination - destStart;
		}

	}

}
