import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new ArrayDeque<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		int[] inDegree = new int[N];
		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			if(map.containsKey(from)) {
				List<Integer> list = map.get(from);
				list.add(to);
				map.put(from, list);				
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(to);
				map.put(from, list);
			}
			inDegree[to]++;
		}
		
		for (int i = 0; i < N; i++) {
			if(inDegree[i] == 0) queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			List<Integer> list = map.get(cur);
			if(list != null) {
				for (Integer num : list) {
					if(--inDegree[num] == 0) {
						queue.offer(num);
					}
				}
			}
			sb.append(cur+1).append(" ");
		}
		System.out.println(sb);
	}
}