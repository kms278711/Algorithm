import java.util.*;
import java.io.*;

public class Solution {
	//맵 길이, 정답, 시작 위치
	private static int N, M, R, C, L, answer;
	//맵
	private static int[][] map;
	//방문 배열(최소 갈 수 있는 시간 넣기)
	private static int[][] visited;
	//파이프 종류 별 갈 수 있는 방향(0은 없음)
	private static int[][] dir = {
			{},
			{0,1,2,3},
			{0,2},
			{1,3},
			{0,1},
			{1,2},
			{2,3},
			{0,3}
	};
	//상우하좌
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			answer = 0;
			map = new int[N][M];
			visited = new int[N][M];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			//해당 시간 전까지 파이프 시간 구하기
			findTime();
			//숨을 수 있는 파이프 개수 세기
			count();
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	//방문할 수 있는 파이프 구하기
	//방문시간이 찍혀 있는 것들 카운팅
	private static void count() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j] != 0) answer ++;
			}
		}
	}

	private static void findTime() {
		//큐 초기위치 넣어서 초기화
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {R,C});
		visited[R][C] = 1;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			//현재 파이프 숫자
			int curPipe = map[cur[0]][cur[1]];
			//갈 수 있는 방향 길이만큼만 탐색
			for (int i = 0; i < dir[curPipe].length; i++) {
				//다음좌표
				int nx = cur[0] + dx[dir[curPipe][i]];
				int ny = cur[1] + dy[dir[curPipe][i]];
				
				//내부인지 체크
				if(!isIn(nx,ny)) continue;
				//방문한 곳인지 체크
				if(visited[nx][ny] != 0) continue;
				//큐에서 탐색하고 있는 현재 좌표가 문제에서 제시한 시간인지 체크(더 이상 탐색할 필요 없음) 
				if(visited[cur[0]][cur[1]] == L) continue; 
				
				//다음 파이프 숫자
				int nextPipe = map[nx][ny];
				//다음 좌표의 파이프 없는지 체크
				if(nextPipe == 0) continue;
				
				//다음 좌표의 파이프가 현재 파이프와 연결될 수있는 파이프인지 체크
				if(dir[curPipe][i] == 0) {
					if(nextPipe == 3 || nextPipe == 4 || nextPipe == 7) continue;
				} else if(dir[curPipe][i] == 1) {
					if(nextPipe == 2 || nextPipe == 4 || nextPipe == 5) continue;
				} else if(dir[curPipe][i] == 2) {
					if(nextPipe == 3 || nextPipe == 5 || nextPipe == 6) continue;
				} else if(dir[curPipe][i] == 3) {
					if(nextPipe == 2 || nextPipe == 6 || nextPipe == 7) continue;
				}
				
				//다음 좌표 방문시간 = 현재 좌표 방문시간 +1
				visited[nx][ny] = visited[cur[0]][cur[1]] + 1;
				queue.offer(new int[] {nx, ny});
			}
		}
	}
	
	//내부인지 체크
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}
}
