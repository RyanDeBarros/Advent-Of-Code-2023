import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class P2 {

	private static final HashMap<String, Node> map = new HashMap<>();
	private static final HashMap<String, ZNode> zNodes = new HashMap<>();

	public static void main(String args[]) {
		String steps;
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

		ArrayList<String> currentIDs = new ArrayList<>();
		for (String id : map.keySet()) if (id.charAt(2) == 'A') currentIDs.add(id);
		for (String id : map.keySet()) if (id.charAt(2) == 'Z') zNodes.put(id, createZNode(map.get(id), steps));
		ArrayList<Long> counts = new ArrayList<>();
		for (int i = 0; i < currentIDs.size(); i++) {
			counts.add(i, untilZ(map.get(currentIDs.get(i)), steps));
			currentIDs.set(i, firstZ(currentIDs.get(i), steps));
		}

		while (!allEqual(counts)) {
			int i = smallest(counts);
			ZNode z = zNodes.get(currentIDs.get(i));
			counts.set(i, counts.get(i) + z.steps);
			currentIDs.set(i, z.next);
		}

		System.out.println(counts.get(0));
	}
	
	private static int smallest(ArrayList<Long> counts) {
		int smol = 0;
		long l = Long.MAX_VALUE;
		for (int i = 0; i < counts.size(); i++) {
			if (counts.get(i) < l) {
				smol = i;
				l = counts.get(i);
			}
		}
		return smol;
	}

	private static String firstZ(String id, String steps) {
		long count = 0;
		Node current = map.get(id);
		do {
			char step = steps.charAt((int) (count % (long) steps.length()));
			if (step == 'L') current = map.get(current.left);
			else current = map.get(current.right);
			count++;
		} while (current.id.charAt(2) != 'Z');
		return current.id;
	}

	private static boolean allEqual(ArrayList<Long> counts) {
		long count = -1;
		for (Long l : counts) {
			if (count == -1) count = l;
			else if (count != l) return false;
		}
		return true;
	}

	private static ZNode createZNode(Node node, String steps) {
		long count = 0;
		Node current = node;
		do {
			char step = steps.charAt((int) (count % (long) steps.length()));
			if (step == 'L') current = map.get(current.left);
			else current = map.get(current.right);
			count++;
		} while (current.id.charAt(2) != 'Z');
		Node last = map.get(current.id);
		ZNode z = new ZNode(node.id, last.id, count);
		return z;
	}

	private static long untilZ(Node node, String steps) {
		long count = 0;
		Node current = node;
		do {
			char step = steps.charAt((int) (count % (long) steps.length()));
			if (step == 'L') current = map.get(current.left);
			else current = map.get(current.right);
			count++;
		} while (current.id.charAt(2) != 'Z');
		return count;
	}

	private static class Node {
		
		private final String id, left, right;

		private Node(String str) {
			id = str.substring(0, 3);
			left = str.substring(7, 10);
			right = str.substring(12, 15);
		}

	}

	private static class ZNode {

		private final String id, next;
		private final long steps;

		private ZNode(String id, String next, long steps) {
			this.id = id;
			this.next = next;
			this.steps = steps;
		}

	}

}
