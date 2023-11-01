import java.util.*;
import java.io.*;

public class Main {
	//정점 개수, 간선 개수, 거쳐야 하는 정점1, 거쳐야 하는 정점2
	private static int N, E, v1, v2;
	//인접행렬
	private static int[][] disArr;
	//길이 없는지 체크
	private static boolean noRoute;
	
	/**
	 * 상태 클래스 
	 * 현재 정점, 해당 정점까지의 거리
	 */
	static class Status {
		int point, dis;

		public Status(int point, int dis) {
			super();
			this.point = point;
			this.dis = dis;
		}		
	}
	
	//시작정점과 끝정점으로 다익스트라 진행
	private static int findDis(int start, int end) {
		//같으면 0리턴하고 종료
		if(start==end) return 0;
		//경로의 길이가 작은 것부터 앞으로 오게 pq사용
		PriorityQueue<Status> queue = new PriorityQueue<>(new Comparator<Status>() {
			@Override
			public int compare(Status o1, Status o2) {
				return o1.dis-o2.dis;
			}
		});
		
		//시작정점 길이0으로 초기값으로 넣기
		queue.offer(new Status(start, 0));
		
		//방문배열 초기화
		boolean[] visited = new boolean[N+1];
		visited[start] = true;
		
		//최단거리 배열 초기화
		int[] dis = new int[N+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		
		//다익스트라 진행
		while(!queue.isEmpty()) {
			Status cur = queue.poll();
			//끝정점 도착하면 종료
			if(cur.point==end) break;
			for (int i = 1; i <= N; i++) {
				//정점이 i정점과 연결되어있고
				//방문안했으면서
				//구해진 최단거리보다 거리가 짧으면 갱신 후 큐에 넣기
				if(disArr[cur.point][i] != 0 && !visited[i] && dis[i] > cur.dis + disArr[cur.point][i]) {
					dis[i] = cur.dis + disArr[cur.point][i];
					queue.offer(new Status(i, dis[i]));
				}
			}
			//해당 정점 방문표시
			visited[cur.point] = true;
		}
		
		//end정점까지의 거리가 갱신이 되지 않았다면 경로가 없다고 표시
		if(dis[end] == Integer.MAX_VALUE) noRoute = true;
		//start정점부터 end정점까지의 최단 거리 리턴
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
			//양방향
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
		//두 경우 중 더 짧은 거리를 정답으로 출력
		System.out.println((noRoute) ? -1:Math.min(dis1,dis2));
	}
	
}
