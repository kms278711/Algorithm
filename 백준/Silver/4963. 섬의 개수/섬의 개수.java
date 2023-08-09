import java.util.*;
import java.io.*;
//좌표 클래스
class Point {
	public int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static int[][] arr;
	// 너비 높이
	public static int w,h;
	//BFS
	public static void BFS(int x, int y) {
		//8방향 탐색(대각선 이동 가능/ 시계방향)
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dy = {0, 1, 1, 1, 0 , -1, -1, -1};
		Queue<Point> queue = new ArrayDeque<Point>();
		//초기 값 넣기
		queue.offer(new Point(x, y));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//이미 탐색한 곳이면 pass
			if(arr[cur.x][cur.y] == 0) continue;
			else arr[cur.x][cur.y] = 0;
			for (int i = 0; i < 8; i++) {
				//배열 내부인지 검사
				if(cur.x+dx[i] >= 0 && cur.y+dy[i] >= 0 && cur.x+dx[i] < h && cur.y+dy[i] < w) {
					//땅이 있다면 추가
					if(arr[cur.x+dx[i]][cur.y+dy[i]] == 1)
						queue.offer(new Point(cur.x+dx[i], cur.y+dy[i]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			int cnt = 0 ;
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());			
			h = Integer.parseInt(st.nextToken());
			if(w==0 && h==0) break;
			arr = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(arr[i][j] == 1) {
						BFS(i,j);
						//섬 카운팅
						cnt++;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}