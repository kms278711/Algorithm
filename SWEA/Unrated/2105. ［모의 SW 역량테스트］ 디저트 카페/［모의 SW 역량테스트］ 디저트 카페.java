import java.util.*;
import java.io.*;

public class Solution {
	//맵 길이, 정답, 시작 위치
	private static int N, answer, startX, startY;
	//맵
	private static int[][] map;
	//디저트 방문 배열(위치가 아님)
	private static boolean[] visited;
	//대각선 4방탐색
	private static int[] dx = {1, 1, -1, -1};
	private static int[] dy = {1, -1, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			N = Integer.parseInt(br.readLine());
			answer = -1;
			map = new int[N][N];
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			//시작 좌표를 설정하고 dfs 시작.
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					startX = j;
					startY = k;
					//방문배열 초기화
					visited = new boolean[101];
					find(0, 0, j, k);
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	//디저트 종류 찾기(길이, 방향, 현재 좌표)
	//방향을 넘기는 이유 : 이전 탐색 방향으로 가지 않고 현재 탐색 방향부터 탐색을 위해,
	//					사각형 만드는 조건(그려보면 암)
	private static void find(int len, int dir, int x, int y) {
		//기저조건 : 시작좌표로 돌아오면
		if(x == startX && y == startY) {
			//사각형의 최소길이인 3이상부터 정답 갱신
			if(len > 2) {
				//정답 갱신
				answer = Math.max(len, answer);
				return;
			}
		}
		
		//4방탐색
		for (int i = dir; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			//내부인지 체크
			if(!isIn(nx,ny)) continue;
			
			//방문했으면 다음 방향 탐색
			if(visited[map[nx][ny]]) continue;
			
			//방문했다고 표시하고 다음 분기 진행
			visited[map[nx][ny]] = true;
			find(len+1, i, nx, ny);
			//방문표시 풀기
			visited[map[nx][ny]] = false;
		}
	}

	//내부인지 체크 로직
	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
}
