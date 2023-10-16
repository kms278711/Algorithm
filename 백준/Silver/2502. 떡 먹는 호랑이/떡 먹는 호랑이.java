import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		loop:
		for (int i = K/2+1; i < K; i++) {
			int[] dp = new int[D+1];
			dp[D] = K;
			dp[D-1] = i;
			for (int j = D-2; j >= 1; j--) {
				dp[j] = dp[j+2] - dp[j+1];
				if(dp[j] >= dp[j+1]) continue loop;
				if(dp[j]<=0) continue loop;
			}
			System.out.println(dp[1]);
			System.out.println(dp[2]);
			break;
		}
	}
	
}
