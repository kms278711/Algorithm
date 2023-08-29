import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//집의 수
		int N = Integer.parseInt(br.readLine());
		//최솟값으로 한줄마다 갱신할 배열
		int[] memo = new int[3];
		//입력받을 배열
		int[][] arr = new int[N][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//첫줄입력으로 초기화해주기
		for (int i = 0; i < 3; i++) {
			memo[i] = arr[0][i];
		}
		
		for (int i = 1; i < N; i++) {
			//현재 memo 배열 상태 저장
			int[] tmp = Arrays.copyOf(memo, 3);
			//초록색, 파란색 사용 시의 최소값과 현재 집을 빨간색으로 칠하는경우
			memo[0] = Math.min(tmp[1],tmp[2])+arr[i][0];
			//빨간색, 파란색 사용 시의 최소값과 현재 집을 초록색으로 칠하는경우
			memo[1] = Math.min(tmp[0],tmp[2])+arr[i][1];
			//빨간색, 초록색 사용 시의 최소값과 현재 집을 파란색으로 칠하는경우
			memo[2] =Math.min(tmp[0],tmp[1])+arr[i][2];
		}
		
		//최솟값 출력
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			answer = Math.min(answer, memo[i]);
		}
		System.out.println(answer);
	}
}
