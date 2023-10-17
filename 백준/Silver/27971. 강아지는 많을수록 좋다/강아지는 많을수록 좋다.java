import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//원하는 강아지의 수
		int N = Integer.parseInt(st.nextToken());
		//닫힌구간의 개수
		int M = Integer.parseInt(st.nextToken());
		//A마법 생성 수
		int A = Integer.parseInt(st.nextToken());
		//B마법 생성 수
		int B = Integer.parseInt(st.nextToken());
		//사용할 수 없는 구간 표시
		boolean[] cantUse = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			//해당 영역 사용할수 없다고 표시
			for (int j = start; j <= end; j++) {
				cantUse[j] = true;
			}
		}
		
		//-1로 초기화
		int answer = -1;

		//횟수, 총량
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {0,0});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			//다음 계산된 값이 N이면 종료
			if(cur[1] + A  == N|| cur[1] + B == N) {
				answer = cur[0] + 1;
				break;
			}
			//다음 값이 N보다 작고 사용할 수 있는 구간에 있는 값이라면 큐에 추가
			//현재 횟수 + 1, 현재 값 + 사용한 마법에 따른 강아지 수
			if(cur[1]+A < N && !cantUse[cur[1]+A]) {
				queue.offer(new int[] {cur[0]+1, cur[1] + A});
				//해당 강아지 수 다음 경우에는 탐색 필요없음.
				cantUse[cur[1]+A] = true;
			}
			if(cur[1]+B < N && !cantUse[cur[1]+B]) {
				queue.offer(new int[] {cur[0]+1, cur[1] + B});
				//해당 강아지 수 다음 경우에는 탐색 필요없음.
				cantUse[cur[1]+B] = true;
			}
		}
		System.out.println(answer);
	}
	
}
