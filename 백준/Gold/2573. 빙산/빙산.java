import java.io.*;
import java.util.*;

public class Main {
	static int N, M, answer;
	static int[][] map;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static boolean isEnd;
	static boolean[][] visited;
	
	/**
	 * 내부인지 검사
	 * @param x
	 * @param y
	 */
	static boolean isIn(int x, int y) {
		if(x>=0 && y>= 0 && x<N && y<M) return true;
		return false;
	}
	
	/**
	 * 사방 탐색하여 녹는 얼음 수 구하기
	 * @param x
	 * @param y
	 * @return
	 */
	static int melt(int x, int y) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(isIn(nx,ny) && map[nx][ny] == 0) cnt++;
		}
		return cnt;
	}
	
	/**
	 * 좌표클래스
	 */
	static class Point{
		int x,y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * 사방탐색으로 섬 연결되있는 곳 방문표시 함수
	 * @param x
	 * @param y
	 */
	static void BFS(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x,y));
		//현재 섬 방문 표시
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				if(!visited[nx][ny] && map[nx][ny] != 0) {
					visited[nx][ny] = true;
					queue.offer(new Point(nx,ny));
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//입력 및 초기화 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			} 
		}
		boolean isExist = false;
		
		//map 상태 갱신을 위해 복사할 이차원 배열
		int[][] tmp = new int[N][M];
		//섬이 2개이상으로 나누어질때까지
		while(true) {
			//섬 개수 확인
			int cnt = 0;
			//방문 배열 초기화
			visited = new boolean[N][M];
			isExist = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(!visited[i][j] && map[i][j] != 0) {
						//cnt가 이미 1인데 if문을 통과했다면 2개 이상이기 때문에 isEnd를 true로 바꾸고 break
						isExist = true;
						if(cnt == 1) {
							isEnd = true;
							break;
						}
						//해당 좌표부터 쭉 섬 방문 표시
						BFS(i,j);
						cnt++;
					}
				}
				if(isEnd) break;
			}
			//2개이상으로 나누어질 수 있거나 더이상 빙산이 존재하지 않으면 while문 빠져나가기
			if(isEnd || !isExist) break;
			
			//1년 지남
			answer++;
			
			//1년 지난 상태 반영
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					//빙산이 있다면
					if(map[i][j]!=0) {
						//현재 상태의 빙산높이에서 사방탐색하여 녹은 빙산 개수만큼 뺴서 임시 복사 배열에 넣기 
						tmp[i][j] = (map[i][j] - melt(i,j) <= 0 ? 0:map[i][j] - melt(i,j));
					}
				} 
			}
			
			//임시 복사배열 원본 배열에 반영
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.copyOf(tmp[i], M);
			}
		}
		if(!isExist) System.out.println(0);
		else System.out.println(answer);
	} 
}
