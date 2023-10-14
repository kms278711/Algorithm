import java.util.*;
import java.io.*;

public class Main {
	static List<Queue> list;
	static boolean check() {
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).size() != 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			Queue<String> queue = new ArrayDeque<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(st.hasMoreElements()) {
				queue.offer(st.nextToken());
			}
			list.add(queue);
		}
		String[] result = br.readLine().split(" ");
		int idx = 0;
		
		loop :
		while(true) {
			int curIdx = idx;
			for (int i = 0; i < list.size(); i++) {
				Queue<String> cur = list.get(i);
				while(!cur.isEmpty() && idx!=result.length) {
					if(cur.peek().equals(result[idx])) {
						idx ++;
						cur.poll();
						if(idx==result.length) {
							if(check()) {
								System.out.println("Possible");
							} else {
								System.out.println("Impossible");
							}
							break loop;
						}
					} else {
						break;
					}
				}
				
			}
			if(curIdx == idx) {
				System.out.println("Impossible");
				break;
			}
		}
	} 
}
