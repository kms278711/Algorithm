import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//1부터 시작하기 위해 N+1로 입력받기
		int[][] map = new int[N+1][N+1];
		//마찬가지로 인덱스1부터 입력받음
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		//유형, 파이프 끝부분행, 파이프 끝부분열
		//0 : 가로 , 1: 세로, 2:대각선
		long[][][] memo = new long[3][N+1][N+1];
		memo[0][1][2] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				//다음 좌표가 벽이면 다음
				if(map[i][j] == 1) continue;
				
				//가로로 놓을 수 있는 경우의 수 = 이전좌표를 끝으로 가로로 놓는 경우 + 이전좌표를 끝으로 대각선으로 놓는 경우
				memo[0][i][j] = memo[0][i][j-1] + memo[2][i][j-1];
				//세로로 놓을 수 있는 경우의 수 = 이전좌표를 끝으로 세로로 놓는 경우 + 이전좌표를 끝으로 대각선으로 놓는 경우
				memo[1][i][j] = memo[1][i-1][j] + memo[2][i-1][j];
				
				//다음 가려는 좌표의 왼쪽 위가 벽이 아니라면 대각선으로 놓는 것이 가능하다.
				if(map[i-1][j] == 0 && map[i][j-1] == 0) 
					//대각선으로 놓을 수 있는 경우의 수 = 이전좌표를 끝으로 가로로 놓는 경우  + 이전좌표를 끝으로 세로로 놓는 경우 + 이전좌표를 끝으로 대각선으로 놓는 경우
					memo[2][i][j] = memo[0][i-1][j-1] + memo[1][i-1][j-1] + memo[2][i-1][j-1];
			}
		}
		
		//마지막 좌표에 가로, 세로, 대각선으로 놓는 경우의 수를 합하면 정답
		System.out.println(memo[0][N][N] + memo[1][N][N] + memo[2][N][N]);
	} 
}
