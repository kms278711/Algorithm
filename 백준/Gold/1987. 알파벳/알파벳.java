import java.io.*;
import java.util.*;

public class Main {
	// 행, 열, 정답
	private static int R,C, answer=Integer.MIN_VALUE;
	//상우하좌
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	//재귀 빠져나올 flag
	private static boolean flag;
	//게임판
	private static String[][] board;
	/**
	 * 최대 개수 찾기
	 * @param x  : 현재x좌표
	 * @param y  : 현재y좌표
	 * @param tmp : 지나온길
	 */
	private static void move(int x, int y, String tmp) {
		if(flag) {
			answer = Math.max(answer, tmp.length());
		} else {
			flag = true;
			for (int i = 0; i < 4; i++) {
				if(x+dx[i] >= 0 && x+dx[i] < R && y+dy[i] >= 0 && y+dy[i] < C && !tmp.contains(board[x+dx[i]][y+dy[i]])) {
					flag = false;
					move(x+dx[i], y+dy[i], tmp + board[x+dx[i]][y+dy[i]]);
				}
			}
			//flag true면 끝남
			move(x, y, tmp);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new String[R][C];
		for (int i = 0; i < R; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				board[i][j] = tmp[j];
			}
		}
		//처음 위치 넣어줌
		move(0, 0, board[0][0]);
		System.out.println(answer);
	}
}