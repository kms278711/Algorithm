import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution {
	public static int N;
	public static int[][] arr, check;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};
	public static int BFS(int x, int y) {
		int cnt = 0;
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x,y));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if(check[cur.x][cur.y] == 1) continue;
			else check[cur.x][cur.y] = 1;		
			cnt++;
			for (int i = 0; i < 4; i++) {
				//배열 나가는지 검사
				if(cur.x+dx[i] >= 0 && cur.y+dy[i] >= 0 && cur.x+dx[i] < N && cur.y+dy[i] < N) {
					//다음갈 곳이 현재있는 배열의 값보다 +1인지 검사
					if(arr[cur.x+dx[i]][cur.y+dy[i]] == arr[cur.x][cur.y] + 1 && check[cur.x+dx[i]][cur.y+dy[i]] == 0) {
						queue.offer(new Point(cur.x+dx[i], cur.y+dy[i]));
					}
				}
			}
		}
		return cnt;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			//방번호
			int answer1 = Integer.MAX_VALUE;
			//최대 탐색 개수
			int answer2 = 0;
			sb.append("#").append(i).append(" ");
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			//값 넣기
			for (int j = 0; j < N; j++) {				
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					check = new int[N][N];
					//현재 방번호에서 갈수 있는 방개수
					int tmp = BFS(j,k);
					if(answer2<tmp) {
						//답	갱신
						answer1 = arr[j][k];
						answer2 = tmp;				
					} else if(answer2==tmp){
						answer1 = Math.min(answer1, arr[j][k]);
					}
				}
			}
			sb.append(answer1).append(" ").append(answer2).append("\n");
		}
		System.out.println(sb);
	}
}