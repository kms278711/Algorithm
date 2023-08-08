import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			//정답 초기화
			int answer = -1;
			st = new StringTokenizer(br.readLine());		
			int[] arr = new int[N];
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			for (int lt = 0; lt < N-1; lt++) {
				for (int rt = lt+1; rt < N; rt++) {
					if(arr[lt] + arr[rt] <= M)
						answer = Math.max(arr[lt]+arr[rt], answer);
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}