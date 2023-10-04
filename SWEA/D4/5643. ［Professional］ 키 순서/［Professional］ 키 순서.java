import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			//출력설정, 입력 및 초기화
			int answer = 0;
			sb.append("#").append(i).append(" ");
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			//진입, 진출 여부
			boolean[][] arr = new boolean[N][N];
			for (int j = 0; j < M; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				//더 작은 친구
				int small = Integer.parseInt(st.nextToken())-1;
				//더 큰 친구
				int big = Integer.parseInt(st.nextToken())-1;
				//[작은친구][큰친구] = true
				arr[small][big] = true;
			}
			//확실히 알 수 있는 친구 카운트
			int[] cnt = new int[N];
			for (int j = 0; j < N; j++) {
				//방문배열
				boolean[] visited = new boolean[N];
				//자기자신 방문표시
				visited[j] = true;
				Queue<Integer> queue = new ArrayDeque<>();
				//자기보다 작은 친구 큐에 초기세팅
				for (int k = 0; k < N; k++) {
					if(arr[j][k]) {
						queue.offer(k);
						//방문표시 및 친구 수 카운팅
						visited[k] = true;
						cnt[j]++;
					}
				}
				
				//계속해서 큐에서 꺼내온 값의 작은 친구만 탐색
				while(!queue.isEmpty()) {
					int cur = queue.poll();
					for (int k = 0; k < N; k++) {
						if(arr[cur][k] && !visited[k]) {
							queue.offer(k);
							visited[k] = true;
							cnt[j]++;
						}
					}
				}
				
				//자기보다 큰 친구 큐에 초기세팅
				for (int k = 0; k < N; k++) {
					if(arr[k][j]) {
						queue.offer(k);
						visited[k] = true;
						cnt[j]++;
					}
				}
				
				//계속해서 큐에서 꺼내온 값의 큰 친구만 탐색
				while(!queue.isEmpty()) {
					int cur = queue.poll();
					for (int k = 0; k < N; k++) {
						if(arr[k][cur] && !visited[k]) {
							queue.offer(k);
							visited[k] = true;
							cnt[j]++;
						}
					}
				}
				//자기자신을 제외하고 인원수가 같다면 정답 카운팅
				if(cnt[j] == N-1) answer++;
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
}
