import java.io.*;
import java.util.*;

class Point {
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	public static String[][] arr;
	//체크배열
	public static int[][] check;
	//가로 새로
	public static int R,C;
	// 총 양 수, 총 늑대 수, 한 구역 양 수, 한 구역 늑대 수
	public static int sheep, wolf, sheepTmp, wolfTmp;
	
	
	//BFS
	public static void BFS(int x, int y) {
		//초기화
		wolfTmp = 0;
		sheepTmp = 0;
		//4방 탐색
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		Queue<Point> queue = new ArrayDeque<Point>();
		//처음값 넣기
		queue.offer(new Point(x, y));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//이미 탐색한 곳이면 패스
			if(check[cur.x][cur.y] == 1) continue;
			else check[cur.x][cur.y] = 1;
			//현재 값이 o,v면 카운팅
			if(arr[cur.x][cur.y].equals("o")) sheepTmp ++;
			if(arr[cur.x][cur.y].equals("v")) wolfTmp ++;
			for (int i = 0; i < 4; i++) {
				//배열 내부 검사
				if(cur.x+dx[i] >= 0 && cur.y+dy[i] >= 0 && cur.x+dx[i]<R && cur.y+dy[i]<C) {
					//다음 갈 곳의 좌표가 탐색 안한 곳이면서 울타리가 아니라면 큐에 추가
					if(check[cur.x+dx[i]][cur.y+dy[i]]==0 && !arr[cur.x+dx[i]][cur.y+dy[i]].equals("#"))
						queue.offer(new Point(cur.x+dx[i], cur.y+dy[i]));
				}
			}
		}
		//양이 더많으면 늑대 0
		if(wolfTmp<sheepTmp) wolfTmp = 0;
		else sheepTmp = 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new String[R][C];
		check = new int[R][C];
		for (int i = 0; i < R; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				arr[i][j] = tmp[j];
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				//울타리가 아니면서 검사안한곳이면 해당 좌표부터 BFS돌리기
				if(arr[i][j] != "#" && check[i][j] == 0) {
					BFS(i,j);
					//해당 구역 남은 동물 수 더해주기
					wolf += wolfTmp;
					sheep += sheepTmp;
				}
			}
		}
		System.out.println(sheep +" " + wolf);
	}
}