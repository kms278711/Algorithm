import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//값 배열
		int[][] arr = new int[N][N];
		//누적합 배열
		int[][] sum = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//초기 세팅
		sum[0][0] = arr[0][0];
		//제일 윗줄
		for (int i = 1; i < N; i++) {
			sum[0][i] = sum[0][i-1]+arr[0][i];
		}
		//제일 왼쪽세로줄
		for (int i = 1; i < N; i++) {
			sum[i][0] = sum[i-1][0]+arr[i][0];
		}
		//0,0 에서 좌표까지 사각형모양으로 더한 값 넣기
		for (int i = 1; i < sum.length; i++) {
			for (int j = 1; j < sum.length; j++) {
				sum[i][j] = sum[i-1][j] + sum[i][j-1] + arr[i][j] - sum[i-1][j-1];
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y1 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			//시작점이 0,0 일경우
			if(x1==0 && y1==0) sb.append(sum[x2][y2]).append("\n");
			//시작점 하나의 좌표가 0일 경우
			else if(x1==0) sb.append(sum[x2][y2] - sum[x2][y1-1]).append("\n");
			else if(y1==0) sb.append(sum[x2][y2]- sum[x1-1][y2]).append("\n");
			//시작점에 0이 없을 경우
			else sb.append(sum[x2][y2] - sum[x1-1][y2] -sum[x2][y1-1] + sum[x1-1][y1-1]).append("\n");
		}
		System.out.println(sb);
	}
}