import java.util.*;
import java.io.*;

public class Solution {
	private static int N,K,answer;
	private static int[][] map;
	private static List<int[]> top;
	
	static class Status {
		int[] point;
		int len;
		
		public Status(int[] point, int len) {
			super();
			this.point = point;
			this.len = len;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = 0;
			map = new int[N][N];
			top = new ArrayList<>();
			for (int x = 0; x < N; x++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int y = 0; y < N; y++) {
					map[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			setTop();
			
			//모든 산을 K까지 깎아보면서 등산로 찾기
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					for (int h = 0; h <= K; h++) {
						if(map[x][y] < h) break;
						map[x][y] -= h;
						for (int[] point : top) {
							findRoute(point);
						}
						//원상복구
						map[x][y] += h;
					}
				}
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	//등산로 찾기
	private static void findRoute(int[] point) {
		Queue<Status> queue = new ArrayDeque<>();
		queue.offer(new Status(new int[] {point[0], point[1]}, 1));
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		while(!queue.isEmpty()) {
			Status cur = queue.poll();
			int size = queue.size();
			for (int i = 0; i < 4; i++) {
				int nx = cur.point[0] + dx[i];
				int ny = cur.point[1] + dy[i];
				//내부가 아니라면 continue
				if(!isIn(nx,ny)) continue;
				//현재 좌표보다 다음 좌표 산의 높이가 낮다면 큐에 추가
				if(map[cur.point[0]][cur.point[1]] > map[nx][ny]) {
					queue.offer(new Status(new int[] {nx, ny}, cur.len+1));
				}
			}
			if(size == queue.size()) answer = Math.max(answer, cur.len);
		}
	}

	//내부인지 확인
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}

	//가장 높은 높이의 봉우리들 좌표 세팅
	private static void setTop() {
		int max = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if(max < map[x][y]) {
					max = map[x][y];
					top.clear();
					top.add(new int[] {x, y});
				} else if(max == map[x][y]) {
					top.add(new int[] {x, y});
				}
			}
		}	
	}
}