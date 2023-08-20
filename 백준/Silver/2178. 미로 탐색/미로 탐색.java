import java.io.*;
import java.util.*;

/**
 * 좌표 클래스
 * x좌표, y좌표, 해당 칸까지의 칸수
 */
class Point{
	public int x,y, value;
	public Point(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}

public class Main {
	//미로 게임판
	private static int[][] board;
	//방문배열
	private static boolean[][] visited;
	//행,열
	private static int N,M;
	//상 우 하 좌
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	private static int answer;
	/**
	 * 탐색함수
	 */
	private static void BFS() {
		Queue<Point> queue = new ArrayDeque<>();
		//첫칸 넣어주기
		queue.offer(new Point(0,0,1));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//도착했다면 break;
			if(cur.x == N-1 && cur.y == M-1) {
				answer = cur.value;
				break;
			}
			//사방탐색
			for (int i = 0; i < 4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				//게임판 내부인지
				//갈 수 있는 장소인지
				//방문 안한 곳인지
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && board[nx][ny] == 1 && !visited[nx][ny]) {
					queue.offer(new Point(cur.x+dx[i], cur.y+dy[i], cur.value+1));
					visited[nx][ny] = true;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		BFS();
		System.out.println(answer);
	}
}