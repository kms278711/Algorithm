import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		//소요 날만큼으로 배열 크기 설정
		//dp[0]은 안씀.
		int[] dp = new int[D+1];
		
		//dp[D-1]의 값의 범위는 최소 dp[D](K)의 절반보다는 커야된다.
		//따라서 넣어볼 범위는 절반부터 K전까지가 된다.
		loop:
		for (int i = K/2+1; i < K; i++) {
			dp[D] = K;
			dp[D-1] = i;
			//dp[D-2]~dp[1]까지 채우기
			for (int j = D-2; j >= 1; j--) {
				dp[j] = dp[j+2] - dp[j+1];
				//현재 채운 값이 이전값보다 크면 종료
				if(dp[j] > dp[j+1]) continue loop;
				//현재 값이 0보다 작거나 같으면 종료
				if(dp[j]<=0) continue loop;
			}
			//위의 for문을 그대로 통과했다면 dp[1],dp[2]출력
			System.out.println(dp[1]);
			System.out.println(dp[2]);
			break;
		}
	}
	
}
