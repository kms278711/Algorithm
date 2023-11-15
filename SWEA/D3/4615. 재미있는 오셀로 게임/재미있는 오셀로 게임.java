import java.util.*;
import java.io.*;

public class Solution {
	private static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			//초기세팅
			int[][] board = new int[N+1][N+1];
			board[N/2][N/2] = 2;
			board[N/2][N/2+1] = 1;
			board[N/2+1][N/2] = 1;
			board[N/2+1][N/2+1] = 2;
			
			//8방탐색
			int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
			int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
			
			//게임진행
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine().trim());
				//놓는 x좌표
				int x = Integer.parseInt(st.nextToken());
				//놓는 y좌표
				int y = Integer.parseInt(st.nextToken());
				//놓는 색상
				int color = Integer.parseInt(st.nextToken());
				//돌 놓기
				board[x][y] = color;
				
				for (int dir = 0; dir < 8; dir++) {
					//해당방향 한칸
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					//해당방향 두칸
					int nx2 = nx + dx[dir];
					int ny2 = ny + dy[dir];
					//2칸까지 내부인지 확인
					if(!isIn(nx, ny) || !isIn(nx2, ny2)) continue;
					//2칸까지 빈칸이 아닌지 확인
					if(board[nx][ny] == 0 || board[nx2][ny2] == 0) continue;
					//다음칸이 시작칸과 색상이 다르면
					if(board[nx][ny] != color) {
						//시작칸과 같은 칸이 나올때까지 탐색
						while(true) {
							nx += dx[dir];
							ny += dy[dir];
							//게임판을 나가면 종료
							if(!isIn(nx, ny)) break;
							//다음 칸이 빈칸이면 종료
							if(board[nx][ny] == 0) break;
							//같은 칸이 나왔다면 사이에 있는 돌들 색상 변경
							if(board[nx][ny] == color) {
								nx = x + dx[dir];
								ny = y + dy[dir];
								while(board[nx][ny] != color) {
									board[nx][ny] = color;
									nx += dx[dir];
									ny += dy[dir];
								}
								break;
							}
						}
					}
				}
			}
			
			//흑돌
			int cnt1 = 0;
			//백돌
			int cnt2 = 0;
			
			//카운팅
			for (int j = 1; j < N+1; j++) {
				for (int k = 1; k < N+1; k++) {
					if(board[j][k] == 1) cnt1++; 
					if(board[j][k] == 2) cnt2++;
				}
			}
			sb.append(cnt1 + " " + cnt2).append("\n");
		}
		System.out.println(sb);
	}

	//내부인지 확인
	private static boolean isIn(int x, int y) {
		return x>0 && y>0 && x<=N && y<=N;
	}
}
