import java.util.*;
import java.io.*;

public class Solution {
	//맵 길이, 시작좌표, 정답(최대 디저트 종류 개수)
	private static int N, startX, startY, answer;
	//맵
	private static int map[][];
	//디저트 종류별 방문 여부
	private static boolean[] visited;
	//대각선 4방탐색
	private static int[] dx = {1, 1, -1, -1};
	private static int[] dy = {1, -1, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			N = Integer.parseInt(br.readLine().trim());			
			map = new int[N][N];
			answer = -1;
			for (int x = 0; x < N; x++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int y = 0; y < N; y++) {
					map[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			
			//모든 지점에서 탐색 시작
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					//방문배열 초기화(최대 종류 100개)
					visited = new boolean[101];
					//시작좌표 세팅
					startX = x;
					startY = y;
					find(x,y,0,0);
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	//디저트 사각형 찾기
	//4방탐색을 dir부터 시작하게 하여 사각형을 만듬
	private static void find(int x, int y, int len, int dir) {
		//최대 3개이상 들르고 시작 지점으로 돌아오면 정답 갱신
		if(x == startX && y == startY && len > 3) {
			answer = Math.max(answer, len);
			return;
		}
		
		//탐색을 dir방향부터 시작하게 하여 사각형을 만듬
		for (int d = dir; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			//내부가 아니면 다음 방향
			if(!isIn(nx, ny)) continue;
			//이미 있는 디저트 종류면 다음 방향
			if(visited[map[nx][ny]]) continue;
			//방문 표시
			visited[map[nx][ny]] = true;
			//다음 탐색
			find(nx, ny, len+1,d);
			//원상복귀
			visited[map[nx][ny]] = false;
		}		
	}

	//내부인지 확인
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}