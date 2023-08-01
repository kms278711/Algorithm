import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Solution {
	public static StringBuilder sb = new StringBuilder();	
	public static int solution(int[][] board) {
		for (int i = 0; i < 100; i++) {
			int[][] tmp = new int[100][100];
			for (int j = 0; j < 100; j++) {
				for (int k = 0; k < 100; k++) {
					tmp[j][k] = board[j][k];
				}
			}
			if(board[0][i] != 1) continue;
			int curX = 0;
			int curY = i;
			boolean flag = false;
			while(curX != 99) {
				tmp[curX][curY] = 0;
				if(curY-1 >= 0) {
					if(tmp[curX][curY-1] == 1) {
						curY -= 1;
						continue;
					}
				}
				if(curY+1<100) {
					if(tmp[curX][curY+1] == 1) {
						curY += 1;
						continue;
					}
				}
				curX += 1;
				if(curX == 99 && tmp[curX][curY] == 2) flag = true;
			}
			if(flag) return i;
		}
		return -1;
	}
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 10; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append("#").append(n).append(" ");
			int[][] board = new int[100][100];
			for (int j = 0; j < 100; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 100; k++) {
					board[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append(solution(board)).append("\n");
		}
		System.out.println(sb);
	}
}