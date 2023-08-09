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
	public static int N;
	public static int[][] arr;
	//BFS
	public static int BFS(int x, int y) {
		//사방탐색
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		//카운팅변수
		int cnt = 0;
		Queue<Point> queue = new ArrayDeque<Point>();
		//들어온 좌표 넣기
		queue.offer(new Point(x,y));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//중복이면 다음
			if(arr[cur.x][cur.y] == 0) continue;
			else arr[cur.x][cur.y] = 0;
			//카운팅
			cnt++;
			//사방탐색
			for (int i = 0; i < 4; i++) {
				if(cur.x+dx[i]>=0 && cur.x+dx[i]<N && cur.y+dy[i]>=0 && cur.y+dy[i]<N) {					
					//건물있는곳만
					if(arr[cur.x+dx[i]][cur.y+dy[i]] == 1) 
						queue.offer(new Point(cur.x+dx[i], cur.y+dy[i]));
				}
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//정답 배열
		List<Integer> list = new ArrayList<Integer>();
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(arr[i][j] == 1) {
					list.add(BFS(i,j));
				}
			}
		}
		
		sb.append(list.size()).append("\n");
		//오름차순 정렬
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append("\n");
		}
		
		System.out.println(sb);
	}
}