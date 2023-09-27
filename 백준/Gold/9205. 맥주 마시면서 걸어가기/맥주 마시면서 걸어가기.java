import java.util.*;
import java.io.*;

public class Main {
	private static Point start, end;
	private static Point[] store;
	private static boolean[] visited;
	private static StringBuilder sb;
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static void BFS() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(start);
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if(Math.abs(cur.x-end.x) + Math.abs(cur.y-end.y) <= 1000) {
				sb.append("happy");
				return;
			}
			for (int i = 0; i < store.length; i++) {
				if(!visited[i] &&Math.abs(cur.x-store[i].x) + Math.abs(cur.y-store[i].y) <= 1000) {
					visited[i] = true;
					queue.offer(store[i]);
				}
			} 
		}
		sb.append("sad");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			store = new Point[N];
			visited = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				store[j] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			BFS();
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
