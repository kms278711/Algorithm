import java.util.*;
import java.io.*;

public class Main {	
	//동굴의 크기
	private static int N;
	//맵
	private static int[][] map;
	//맵의 해당위치까지 최단거리
	private static int[][] dis;
	//4방탐색
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	//상태 클래스
	//좌표와 해당 좌표까지의 거리
	static class Status{
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
		//문제번호
		int pNum = 0;
		while(true) {
			pNum ++;
			N = Integer.parseInt(br.readLine().trim());			
			//0이면 종료
			if(N == 0) break;
			sb.append("Problem " + pNum + ": ");
			map = new int[N][N];
			dis = new int[N][N];
			
			//최단거리 계산을 위해 나올수 있는 최대값으로 초기화
			// 9 * 125 = 1125
			for (int i = 0; i < N; i++) {
				Arrays.fill(dis[i], 1126);
			}
			
			for (int x = 0; x < N; x++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int y = 0; y < N; y++) {
					map[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			//최단거리 찾기
			findDis();
			//[N-1][N-1]까지의 최단거리 출력
			sb.append(dis[N-1][N-1]).append("\n");
		}
		System.out.println(sb);
	}

	private static void findDis() {
		//우선순위 큐 사용
		//길이 오름차순으로
		PriorityQueue<Status> queue = new PriorityQueue<>(new Comparator<Status>() {
			@Override
			public int compare(Status o1, Status o2) {
				return o1.len-o2.len;
			}
		});
		
		//시작점과 시작금액 넣기
		queue.offer(new Status(new int[] {0, 0}, map[0][0]));
		dis[0][0] = map[0][0];
		while(!queue.isEmpty()) {
			Status cur = queue.poll();
			//도착했으면 종료
			if(cur.point[0] == N-1 && cur.point[1] == N-1) {
				dis[N-1][N-1] = cur.len;
				return;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.point[0] + dx[dir];
				int ny = cur.point[1] + dy[dir];
				//내부인지 확인
				if(!isIn(nx, ny)) continue;
				//해당 좌표까지 오는 자원량보다 적으면 갱신하고 해당 상태 큐에 넣기
				if(dis[nx][ny] > cur.len + map[nx][ny]) {
					dis[nx][ny] = cur.len + map[nx][ny];
					queue.offer(new Status(new int[] {nx, ny}, dis[nx][ny]));
				}
			}
		}
	}

	//내부인지 확인
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
