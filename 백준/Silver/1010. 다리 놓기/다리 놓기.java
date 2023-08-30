import java.io.*;
import java.util.*;

public class Main {
	//dp를 이용하는 memo배열
	static int[][] memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			//조합 값 넣어줄 배열
			memo = new int[M+1][N+1];
			for (int j = 0; j <= M; j++) {
				//j까지만. j보다 N이 작으면 N까지만
				for (int k = 0, end=Math.min(j, N); k <= end; k++) {
					//nC0과 nCn 인경우 1로 넣기
					if(k==0 || j==k) memo[j][k] = 1;
					//아니라면 nCk = n-1Ck-1 + n-1Ck
					else memo[j][k] = memo[j-1][k-1] + memo[j-1][k];
				}
			}
			sb.append(memo[M][N]).append("\n");
		}
		System.out.println(sb);
	} 
}
