import java.util.*;
import java.io.*;

public class Main {	
	private static int N, M, answer;
	private static int[][] map;
	private static List<CCTV> cctvList;
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	static class CCTV {
		int num;
		int[] point;
		
		public CCTV(int num, int[] point) {
			super();
			this.num = num;
			this.point = point;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = N*M;
		map = new int[N][M];
		cctvList = new ArrayList<>();
		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < M; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				if(map[x][y] >= 1 && map[x][y] <= 5) cctvList.add(new CCTV(map[x][y], new int[] {x, y})); 
			}
		}
		setCCTV(0);
		System.out.println(answer);
	}
	
	private static void setCCTV(int cnt) {
		if(cnt == cctvList.size()) {
			answer = Math.min(answer, findBlindSpot());
			return;
		}
		
		CCTV cur = cctvList.get(cnt);
		
		int[][] mapTmp = new int[N][M];
		for (int x = 0; x < N; x++) {
			mapTmp[x] = Arrays.copyOf(map[x], M);
		}
		
		if(cur.num == 1) {
			for (int i = 0; i < 4; i++) {
				watch(cur.point, i);
				setCCTV(cnt+1);
				reset(mapTmp);
			}
		} else if(cur.num == 2) {
			for (int dir = 0; dir < 2; dir++) {
				watch(cur.point, dir);
				watch(cur.point, dir+2);
				setCCTV(cnt+1);
				reset(mapTmp);
			}
		} else if(cur.num == 3) {
			for (int dir = 0; dir < 4; dir++) {
				watch(cur.point, dir);
				watch(cur.point, (dir+1)%4);
				setCCTV(cnt+1);
				reset(mapTmp);
			}
		} else if(cur.num == 4) {
			for (int dir = 0; dir < 4; dir++) {
				watch(cur.point, dir);
				watch(cur.point, (dir+1)%4);
				watch(cur.point, (dir+2)%4);
				setCCTV(cnt+1);
				reset(mapTmp);
			}
		} else if(cur.num == 5) {
			for (int dir = 0; dir < 4; dir++) {
				watch(cur.point, dir);
			}
			setCCTV(cnt+1);
			reset(mapTmp);
		} 
		
	}

	private static int findBlindSpot() {
		int cnt = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if(map[x][y]==0) cnt++;
			}
		}
		return cnt;
	}

	private static void watch(int[] point, int dir) {
		int nx = point[0];
		int ny = point[1];
		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			if(!isIn(nx,ny)) break;
			if(map[nx][ny] == 6) break;
			if(map[nx][ny] >= 1 && map[nx][ny] <= 5) continue;
			map[nx][ny] = -1;
		}
	}
	
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}

	private static void reset(int[][] mapTmp) {
		for (int x = 0; x < N; x++) {
			map[x] = Arrays.copyOf(mapTmp[x], M);
		}
	}

}
