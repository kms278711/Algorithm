import java.util.*;
import java.io.*;

public class Main {
	//행, 열
	static int N,M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//초기화 및 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//1부터 시작하기 위해 N+1로 -> 메모이제이션 합연산을 위해 N+2로
		int[][] memo = new int[N+2][M+2];
		
		//끝좌표부터 1,1까지 진행
		for (int i = N; i >= 1; i--) {
			for (int j = M; j >= 1; j--) {
				//해당 좌표기준 우,하, 우하 비교하여 최대경우의 수로 갱신
				memo[i][j] = Math.max(memo[i][j], memo[i][j+1]);
				memo[i][j] = Math.max(memo[i][j], memo[i+1][j+1]);;
				memo[i][j] = Math.max(memo[i][j], memo[i+1][j]);
				//해당 좌표 경우의수  = 도착지에서 해당 좌표까지 모을 수 있는 선물의 최대경우의수 + 해당 칸 선물 개수
				memo[i][j] += map[i][j];
			}
		}
		//1,1로 오는 최대 경우의 수
		System.out.println(memo[1][1]);
	} 
}
