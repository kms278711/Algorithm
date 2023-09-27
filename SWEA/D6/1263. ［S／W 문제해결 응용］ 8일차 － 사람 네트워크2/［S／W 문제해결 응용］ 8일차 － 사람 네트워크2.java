import java.util.*;
import java.io.*;

public class Solution {
	private static int N;
	private static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			//출력설정, 입력 및 초기화
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
					//자기자신의 인접행렬이 아니고 연결이 안되어 있다면 플로이드-워샬 이용을 위해 적당한 수로 초기화
					if(j!=k && arr[j][k] == 0) arr[j][k] = Integer.MAX_VALUE/2;
				}
			}
			
			//플로이드-워샬
			//j 경유지 k 출발지 h 도착지
			//정점 하나씩 경유한다고 생각하고 계속 갱신
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					//경유지와 출발지 같은 경우 제외
					if(j==k) continue;
					for (int h = 0; h < N; h++) {
						//경유지와 도착지 같은 경우, 출발지와 도착지 같은 경우 제외
						if(j==h || k==h) continue;
						//경유해서 가는것과 직접 가는것을 비교해서 갱신
						arr[k][h] = Math.min(arr[k][h], arr[k][j]+arr[j][h]);
					}
				}
			}
			
			//정답 도출을 위한 초기화
			int answer = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				//각 정점까지들의 거리 합. 초기화
				int sum = 0;
				for (int k = 0; k < N; k++) {
					//거리 각각 더하기
					sum += arr[j][k];
				}
				//정답 갱신
				answer = Math.min(answer, sum);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
}
