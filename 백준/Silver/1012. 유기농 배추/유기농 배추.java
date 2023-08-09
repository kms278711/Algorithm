import java.util.*;
import java.io.*;

class Point {
	public int x;
	public int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	public static int M,N,K;
	public static int[][] arr;
	
	//BFS
	public static void BFS(int x, int y) {
		//사방탐색
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(x,y));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//이미 탐색한 곳이면 패스
			if(arr[cur.x][cur.y] == 0) continue;
			else arr[cur.x][cur.y] = 0;
			for (int i = 0; i < 4; i++) {
				//arr 범위 안나가는지 체크
				if(cur.x+dx[i]>=0 && cur.x+dx[i]<M && cur.y+dy[i]>=0 && cur.y+dy[i]<N) {					
					//배추가 있는 곳이면 큐에 넣기
					if(arr[cur.x+dx[i]][cur.y+dy[i]] == 1) 
						queue.offer(new Point(cur.x+dx[i], cur.y+dy[i]));
				}
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());			
			arr = new int[M][N];
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				arr[x][y] = 1;
			}
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					if(arr[j][k] == 1) {
						answer++;
						BFS(j, k);
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}