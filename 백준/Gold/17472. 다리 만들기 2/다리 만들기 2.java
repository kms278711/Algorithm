import java.util.*;
import java.io.*;

public class Main {
	private static int N,M, areaNum=1, answer;
	private static int[][] map;
	private static int[] dx = {-1,0,1,0};
	private static int[] dy = {0,1,0,-1};
	private static boolean[][] visited, visited2;
	
	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	//내부인지 체크
	private static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<N && y<M) return true;
		return false;
	}
	
	private static void setArea(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x,y));
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			map[cur.x][cur.y] = areaNum;
			for (int i = 0; i < 4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				if(isIn(nx,ny) && !visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					queue.offer(new Point(nx,ny));
				}
			}
		}
		areaNum++;
	}
	
	private static boolean union(int a, int b, int[] parents) {
		int aRoot = find(a, parents);
		int bRoot = find(b, parents);
		if(aRoot==bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	private static int find(int a, int[] parents) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a], parents);
	}

	private static void DFS(int[][] cur, int[] parents) {
		int check = 0;
		for (int i = 1; i < parents.length; i++) {
			if(parents[i] == i) {
				check = i;
				break;
			}
		}
		int cnt = 0;
		for (int i = 1; i < parents.length; i++) {
			if(find(i,parents) == check) cnt ++;
		}
		
		if(cnt == parents.length-1) {
			int answerTmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(cur[i][j] < 0) {
						answerTmp += Math.abs(cur[i][j]);
					}
				}
			}
			answer = Math.min(answer, answerTmp);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(cur[i][j] > 0) {
					int[] parentsTmp = Arrays.copyOf(parents, parents.length);
					int curTmp[][] = new int[N][M];
					
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(isIn(nx,ny) && isIn(nx+dx[k], ny+dy[k])&& cur[nx][ny]<=0 && cur[nx+dx[k]][ny+dy[k]] <= 0) {
							parentsTmp = Arrays.copyOf(parents, parents.length);;
							for (int l = 0; l < N; l++) {
								curTmp[l] = Arrays.copyOf(cur[l], M);
							}
							curTmp[nx][ny] -= 1;
							
							int minusCnt = 0;
							while(true) {
								nx += dx[k];
								ny += dy[k];
								if(isIn(nx,ny)) {
									if(curTmp[nx][ny] <= 0) {
										curTmp[nx][ny] -= 1;
										minusCnt++;
									} else {
										if(union(curTmp[i][j], curTmp[nx][ny], parentsTmp)) {
											DFS(curTmp, parentsTmp);
										}
										break;
									}
								} else {
									break;
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		visited2 = new boolean[N][M];
		answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					setArea(i,j);
				}
			}
		}
		
		int[] parents = new int[areaNum];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		
		DFS(map, parents);
		System.out.println(answer==Integer.MAX_VALUE ? -1:answer);
	}
}
