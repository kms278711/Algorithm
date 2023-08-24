import java.io.*;
import java.util.*;

//좌표 클래스
class Point{
	public int x,y,cnt, k;
	public Point(int x, int y, int cnt, int k) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.k = k;
	}
}

public class Main {	
	//가로, 세로, 정답
	private static int W,H,answer=-1;
	//게임판
	private static int[][] board;
	//방문배열(남은횟수, 가로, 세로)
	private static boolean[][][] visited;
	
	//게임판 안에 있는지, 방문하지 않은 곳인지, 1이 아닌지 체크
	private static boolean isPossible(int k, int x, int y) {
		if(x>=0 && x<H && y>=0 && y<W && !visited[k][x][y] && board[x][y] != 1) return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		board = new int[H][W];
		//K번에서 0번까지 사용하기 때문에 K+1
		visited = new boolean[K+1][H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Point> queue = new ArrayDeque<>();
		//처음 위치 입력
		queue.offer(new Point(0, 0, 0 ,K));
		//말처럼 이동
		int[] horseDx = {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] horseDy = {1, 2, 2, 1, -1, -2, -2, -1};
		//인접이동
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//도착점이면 정답갱신하고 break
			if(cur.x == H-1 && cur.y == W-1) {
				answer = cur.cnt;
				break;
			}
			
			//말이동횟수가 남았으면
			if(cur.k != 0) {
				for (int i = 0; i < 8;i++) {
					int nx = cur.x + horseDx[i];
					int ny = cur.y + horseDy[i];
					if(isPossible(cur.k-1, nx, ny)) {
						//말처럼이동횟수 줄이고 방문체크
						visited[cur.k-1][nx][ny] = true;	
						queue.offer(new Point(nx, ny, cur.cnt+1, cur.k-1));
					}
				}
			}
			
			//횟수가 남지않았거나 말이동 하지 않았을 경우
			for (int i = 0; i < 4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(isPossible(cur.k, nx, ny)) {
					visited[cur.k][nx][ny] = true;	
					queue.offer(new Point(nx, ny, cur.cnt+1, cur.k));
				}
			}
		}
		//정답 갱신이 되지 않았다면 -1출력
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
