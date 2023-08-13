import java.io.*;
import java.util.*;

public class Main {
	static int N,S, answer = 0, sum = 0;
	static int[] arr;
	//DFS
	public static void DFS(int cnt) {
		//부분수열로 합했을 때 현재 합이 제시된 것과 같다면 카운팅
		if(cnt == N) {
			if(sum == S) {
				answer++;
			}
			return;
		} else {
			//해당 배열의 수를 사용하는 경우
			sum += arr[cnt];
			DFS(cnt+1);
			//해당 배열의 수를 사용하지 않는 경우
			sum -= arr[cnt];
			DFS(cnt+1);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		DFS(0);
		//sum의 초기값이 0으로 했기때문에 S가 0인경우에만 하나도 선택하지 않은 경우 제외
		if(S == 0) System.out.println(answer-1);
		else System.out.println(answer);
	}
}