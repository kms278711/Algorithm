import java.io.*;
import java.util.*;

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
	private static int W,H,answer=-1;
	private static int[][] board;
	private static boolean[][][] visited;
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
		visited = new boolean[K+1][H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(0, 0, 0 ,K));
		int[] horseDx = {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] horseDy = {1, 2, 2, 1, -1, -2, -2, -1};
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if(cur.x == H-1 && cur.y == W-1) {
				answer = cur.cnt;
				break;
			}
			
			if(cur.k != 0) {
				for (int i = 0; i < 8;i++) {
					int nx = cur.x + horseDx[i];
					int ny = cur.y + horseDy[i];
					if(isPossible(cur.k-1, nx, ny)) {
						visited[cur.k-1][nx][ny] = true;	
						queue.offer(new Point(nx, ny, cur.cnt+1, cur.k-1));
					}
				}
			}
			
			for (int i = 0; i < 4;i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				if(isPossible(cur.k, nx, ny)) {
					visited[cur.k][nx][ny] = true;	
					queue.offer(new Point(nx, ny, cur.cnt+1, cur.k));
				}
			}
		}
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
