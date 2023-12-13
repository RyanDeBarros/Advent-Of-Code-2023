import java.util.Scanner;
import java.util.HashMap;

public class P1 {

	public static void main(String args[]) {
		String steps;
		HashMap<String, Node> map = new HashMap<>();
		try (Scanner sc = new Scanner(System.in);) {
			steps = sc.nextLine();
			sc.nextLine();
			while (true) {
				String str = sc.nextLine();
				if (str.isBlank()) break;
				Node node = new Node(str);
				map.put(node.id, node);
			}
		}
		assert map.keySet().contains("ZZZ") && map.keySet().contains("AAA");
		Node current = map.get("AAA");
		int count = 0;
		do {
			char step = steps.charAt(count % steps.length());
			if (step == 'L') current = map.get(current.left);
			else current = map.get(current.right);
			count++;
		} while (!current.id.equals("ZZZ"));

		System.out.println(count);
	}

	private static class Node {
		
		private final String id, left, right;

		private Node(String str) {
			id = str.substring(0, 3);
			left = str.substring(7, 10);
			right = str.substring(12, 15);
		}

	}

}
