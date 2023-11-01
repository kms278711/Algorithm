import java.util.*;
import java.io.*;

public class Main {
	//구역수, 정답
	private static int N, E, v1, v2;
	private static int[][] disArr;
	private static int[] dis;
	private static boolean[] visited;
	private static boolean noRoute;
	
	static class Status {
		int point, dis;

		public Status(int point, int dis) {
			super();
			this.point = point;
			this.dis = dis;
		}		
	}
	
	private static int findDis(int start, int end) {
		if(start==end) return 0;
		PriorityQueue<Status> queue = new PriorityQueue<>(new Comparator<Status>() {
			@Override
			public int compare(Status o1, Status o2) {
				return o1.dis-o2.dis;
			}
		});
		
		queue.offer(new Status(start, 0));
		visited = new boolean[N+1];
		visited[start] = true;
		dis = new int[N+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		while(!queue.isEmpty()) {
			Status cur = queue.poll();
			if(cur.point==end) break;
			for (int i = 1; i <= N; i++) {
				if(disArr[cur.point][i] != 0 && !visited[i] && dis[i] > cur.dis + disArr[cur.point][i]) {
					dis[i] = cur.dis + disArr[cur.point][i];
					queue.offer(new Status(i, dis[i]));
				}
			}
			visited[cur.point] = true;
		}
		if(dis[end] == Integer.MAX_VALUE) noRoute = true;
		return dis[end];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		disArr = new int[N+1][N+1];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int tmp = Integer.parseInt(st.nextToken());
			disArr[start][end] = tmp;
			disArr[end][start] = tmp;
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		//1-> v1 -> v2 -> N
		int dis1 = findDis(1, v1) + findDis(v1, v2) + findDis(v2, N);
		//1-> v1 -> v2 -> N
		int dis2 = findDis(1, v2) + findDis(v2, v1) + findDis(v1, N);
		System.out.println((noRoute) ? -1:Math.min(dis1,dis2));
	}
	
}
