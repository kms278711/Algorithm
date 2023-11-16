import java.util.*;
import java.io.*;

public class Main {	
	//가로, 세로, 정답(최소 사각지대 수)
	private static int N, M, answer;
	//맵
	private static int[][] map;
	//cctv정보 리스트
	private static List<CCTV> cctvList;
	//4방탐색
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	//CCTV 클래스
	//CCTV 번호와 CCTV 좌표를 가짐
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
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//최대 사각지대 수 : N*M
		answer = N*M;
		map = new int[N][M];
		cctvList = new ArrayList<>();
		for (int x = 0; x < N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < M; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				//CCTV라면 리스트에 추가
				if(map[x][y] >= 1 && map[x][y] <= 5) cctvList.add(new CCTV(map[x][y], new int[] {x, y})); 
			}
		}
		setCCTV(0);
		System.out.println(answer);
	}
	
	//CCTV 방향 세팅
	private static void setCCTV(int cnt) {
		//모든 CCTV의 세팅이 끝나면 사각지대 찾기
		if(cnt == cctvList.size()) {
			answer = Math.min(answer, findBlindSpot());
			return;
		}
		
		//현재 CCTV
		CCTV cur = cctvList.get(cnt);
		
		//세팅 전 맵 상태 저장
		int[][] mapTmp = new int[N][M];
		for (int x = 0; x < N; x++) {
			mapTmp[x] = Arrays.copyOf(map[x], M);
		}
		
		//CCTV 번호 별 세팅
		if(cur.num == 1) {
			for (int dir = 0; dir < 4; dir++) {
				watch(cur.point, dir);
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

	//사각지대 찾기
	private static int findBlindSpot() {
		int cnt = 0;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if(map[x][y]==0) cnt++;
			}
		}
		return cnt;
	}

	//point 지점부터 dir방향으로 감시하기
	private static void watch(int[] point, int dir) {
		int nx = point[0];
		int ny = point[1];
		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			//다음좌표가 내부가 아닐 경우
			if(!isIn(nx,ny)) break;
			//다음좌표가 벽일 경우
			if(map[nx][ny] == 6) break;
			//다음좌표가 CCTV일 경우 다음 좌표로 이동
			if(map[nx][ny] >= 1 && map[nx][ny] <= 5) continue;
			//-1로 감시영역 표시
			map[nx][ny] = -1;
		}
	}
	
	//내부인지 확인
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}

	//이전 상태의 맵으로 초기화
	private static void reset(int[][] mapTmp) {
		for (int x = 0; x < N; x++) {
			map[x] = Arrays.copyOf(mapTmp[x], M);
		}
	}

}
