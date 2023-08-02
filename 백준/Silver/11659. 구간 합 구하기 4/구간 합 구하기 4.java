import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int[] sum = new int[N];
		
		st = new StringTokenizer(br.readLine());
		//N개의 수 담기
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		sum[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			sum[i] = sum[i-1] + arr[i];
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			//시작idx
			int start = Integer.parseInt(st.nextToken())-1;
			//마지막으로 더해야 하는 idx
			int end = Integer.parseInt(st.nextToken())-1;
			//0일때는 그냥 출력
			if(start==0) sb.append(sum[end]).append("\n");
			//0이 아닐때는 end까지 더한 값에서 start-1까지 더한 값을 빼줌
			else sb.append(sum[end]-sum[start-1]).append("\n");
		}
		System.out.println(sb);
	}
}