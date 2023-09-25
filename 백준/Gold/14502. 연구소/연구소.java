import java.util.*;
import java.io.*;

class Main {
	private static int[][] map;
	private static int N, M;
	//사방탐색
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	//방문배열
	private static boolean[][] visited;
	private static int answer = Integer.MIN_VALUE;
	
	//좌표 클래스
	static class Point {
		int x,y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	//내부인지 검사
	private static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<N && y<M) return true;
		return false;
	}
	
	//벽 3개 설치 로직
	private static void DFS(int cnt) {
		//3개일 때만 BFS돌려서 전염시키기
		if(cnt==3) {
			int num = BFS(map);
			answer = Math.max(answer, num);
		} else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					//방문하지 않은 점이면서 해당 좌표가 빈칸이라면 진행
					if(!visited[i][j]&&map[i][j] == 0) {
						//사용하는 경우
						visited[i][j] = true;
						map[i][j] = 1;
						//벽 개수 1개 증가
						DFS(cnt+1);
						//사용하지 않았을 경우
						map[i][j] = 0;
						visited[i][j] = false;
					}
				}
			}
		}
	}
	
	//탐색하면서 해당 배열로 전염진행
	private static int BFS(int[][] map2) {
		int[][] arrTmp = new int[N][M];
		//빈칸개수 카운팅변수
		int num = 0;
		//들어온 배열 복사
		for (int i = 0; i < N; i++) {
			arrTmp[i] = Arrays.copyOf(map2[i], M);
		}
		//방문배열
		boolean[][] visitedTmp = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//방문하지 않은 점이면서 바이러스가 있는 칸이라면
				if(!visitedTmp[i][j] && arrTmp[i][j] == 2) {
					Queue<Point> queue = new ArrayDeque<>();
					queue.offer(new Point(i,j));
					while(!queue.isEmpty()) {
						//하나 뽑기
						Point cur = queue.poll();				
						for(int k=0; k<4; k++) {
							//다음 x 좌표
							int nx = cur.x + dx[k];
							//다음 y 좌표
							int ny = cur.y + dy[k];
							//다음 좌표가 안에 있으면서 다음 좌표의 자리가 빈칸이라면 진행
							if(isIn(nx,ny) && arrTmp[nx][ny] == 0) {
								//전염되었다고 표시
								arrTmp[nx][ny] = 2;
								//해당 칸 또 방문하지 않기 위해 방문처리
								visitedTmp[cur.x][cur.y] = true;
								//새로 전염된 칸 넣어주기
								queue.offer(new Point(nx, ny));
							}
						}
					}
				}
			}
		}
		
		//돌면서 빈칸 개수 세기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(arrTmp[i][j] == 0) num++;
			}
		}
		return num;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//초기화 및 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//3개 중 설치된 벽개수 0개로 시작
		DFS(0);
		System.out.println(answer);
	}
}
