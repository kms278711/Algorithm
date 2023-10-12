import java.util.*;
import java.io.*;

public class Main {
	//경로 클래스
	//시작섬, 끝섬, 다리 길이
	static class Path {
		int start, end, len;
		public Path(int start, int end, int len) {
			super();
			this.start = start;
			this.end = end;
			this.len = len;
		}

		@Override
		public String toString() {
			return "path [start=" + start + ", end=" + end + ", len=" + len + "]";
		}
	}
	
	//가로,세로,섬번호
	static int N,M,areaNum;
	//사방탐색
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	//root배열
	static int[] parents; 
	//맵
	static int[][] map;
	//다리길이 오름차순으로 정렬을 위해 pq사용
	static Queue<Path> pq = new PriorityQueue<>((o1,o2)-> o1.len-o2.len);
	
	//map을 벗어나지 않는 지 체크
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x< N && y< M;
	}
	
	//섬 번호 세팅
	private static void setArea(int x, int y) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x,y});
		//탐색시작 좌표 번호 붙이기
		map[x][y] = areaNum;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			//4방 탐색
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				//내부면서 섬이라면
				if(isIn(nx,ny) && map[nx][ny] == -2) {
					//섬 번호 부여
					map[nx][ny] = areaNum;
					//다음 탐색을 위해 큐에 추가
					queue.offer(new int[] {nx,ny});
				}
			}
		}
		//섬 하나가 번호 부여 끝나면 번호증가
		areaNum++;
	}
	
	//경로 세팅
	private static void setRoutes() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//섬이라면
				if(map[i][j] != 0) {
					//우, 하만 이용
					for (int k = 1; k < 3; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						//다리길이
						int len = 1;
						//다음 좌표가 내부면서 바다라면
						if(isIn(nx,ny) && map[nx][ny] == 0) {
							while(true) {
								//다리 놓기
								len ++;
								nx += dx[k];
								ny += dy[k];
								//밖으로 나가면 큐에 추가하지 않고 break
								if(!isIn(nx,ny)) break;
								if(map[nx][ny] == map[i][j]) break;
								//섬을 만나면 같은 섬인지 확인하고 아니면 큐에 추가
								if(map[nx][ny] != 0 && map[i][j]!= map[nx][ny]) {
									//다리길이 2이상인지 체크
									if(len - 1 > 1) {
										//pq에 해경로 추가
										pq.offer(new Path(map[i][j], map[nx][ny], len-1));
									}
									break;
								}
							}
						}
 					}
				}
			}
		}
	}
	
	//union-find 관련
	//부모배열 생성 
	private static void make() {
		parents = new int[areaNum];
		for (int i = 0; i < areaNum; i++) {
			parents[i] = i;
		}
	}
	
	//find
	//root 찾기
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	//union
	//루트 비교 후 합치기
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	//모든 섬의 root가 같은지 확인
	private static boolean endCheck() {
		int root = find(parents[1]);
		for (int i = 2; i < areaNum; i++) {
			if(find(i) != root) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int answer = 0;
		map = new int[N][M];
		//섬 시작 번호
		areaNum = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				//섬 번호를 1번부터 시작하기 위해 1 -> -2로 넣기
				map[i][j] = Integer.parseInt(st.nextToken()) * -2;
			}
		}
		
		//섬 번호 세팅
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == -2) setArea(i,j);
			}
		}
		
		//될 수 있는 다리 다 구하기
		setRoutes();
		
		//union-find root배열 생성
		make();
		while(!pq.isEmpty()) {
			//pq에서 가장 작은 길이의 다리 가져옴
			Path cur = pq.poll();
			//합칠 수 있다면 합치고 최종 길이에 추가
			if(union(cur.start, cur.end)) {
				answer += cur.len;
			}
			//모든 섬 연결 체크
			if(endCheck()) break;
		}
		if(!endCheck()) answer = 0;
		System.out.println(answer == 0 ? -1:answer);
	}
}