import java.io.*;
import java.util.*;

class Shark{
	public int x,y,speed, dirIdx, size;

	public Shark(int speed, int dirIdx, int size) {
		super();
		this.speed = speed;
		this.dirIdx = dirIdx;
		this.size = size;
	}
	
}
public class Main {
	private static Shark[][] board;
	private static int R,C;
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,1,-1};
	private static void move() {
		Shark[][] tmp = new Shark[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(board[i][j] != null) {
					Shark cur = board[i][j];
					int x = i;
					int y = j;
					for (int cnt = 0; cnt < cur.speed; cnt++) {
						int nx = x + dx[cur.dirIdx];
						int ny = y + dy[cur.dirIdx];
						if(isIn(nx,ny)) {
							x += dx[cur.dirIdx];
							y += dy[cur.dirIdx];
						} else {
							x -= dx[cur.dirIdx];
							y -= dy[cur.dirIdx];
							cur.dirIdx = ((cur.dirIdx==0 || cur.dirIdx==1) ? 1:5) - cur.dirIdx;
						}
					}
					if(tmp[x][y] == null) {
						tmp[x][y] = cur;
					} else {
						if(tmp[x][y].size < cur.size) tmp[x][y] = cur; 
					}
				}
			}
		}
		for (int i = 0; i < R; i++) {
			board[i] = Arrays.copyOf(tmp[i], C);
		}
	}
	private static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<R && y<C) return true;
		return false;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		board = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int speed = Integer.parseInt(st.nextToken());
			int dirIdx = Integer.parseInt(st.nextToken())-1;
			int size = Integer.parseInt(st.nextToken());
			Shark tmp = new Shark(speed, dirIdx, size);
			board[x][y] = tmp;
		}

		for (int pos = 0; pos < C; pos++) {
			for (int i = 0; i < R; i++) {
				if(board[i][pos] != null) {
					answer += board[i][pos].size;
					board[i][pos] = null;
					break;
				}
			}
			move();
		}
		
		System.out.println(answer);
	}
}
