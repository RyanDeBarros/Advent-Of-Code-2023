import java.util.Scanner;
import java.util.ArrayList;

public class P1 {

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

			ArrayList<Long> locations = new ArrayList<>();
			for (Long seed : seeds) {
				long n = seed;
				for (Mapping mapping : mappings) {
					n = mapping.destinationOf(n);
				}
				locations.add(n);
			}

			long min = Long.MAX_VALUE;
			for (Long l : locations) if (l < min) min = l;
			System.out.println(min);
		}

	}

	private static class Mapping {
		
		private final ArrayList<MappingElement> elements = new ArrayList<>();

		private Mapping(ArrayList<String> rows) {
			rows.forEach(row -> elements.add(new MappingElement(row)));
		}

		private long destinationOf(long source) {
			for (MappingElement element : elements) {
				if (element.sourceInRange(source)) return element.destinationOf(source);
			}
			return source;
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

		private boolean sourceInRange(long source) {
			long diff = source - sourceStart;
			return diff < rangeL && diff >= 0;
		}

		private long destinationOf(long source) {
			return destStart + source - sourceStart;
		}

	}

}
