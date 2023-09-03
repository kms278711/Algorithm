import java.util.*;
import java.io.*;

public class Main {
	/**
	 * 고슴도치 좌표클래스 
	 * 현재 좌표와 현재이동횟수 받음
	 */
	static class Position{
		int x,y,time;
		public Position(int x, int y, int move) {
			super();
			this.x = x;
			this.y = y;
			this.time = move;
		}
	}
	
	//행, 열, 정답
	static int R,C,answer;
	//4방향
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	//고슴도치 방문배열
	static boolean[][] visited;
	//지도
	static String[][] map;
	
	/**
	 * 내부인지 확인
	 * @param x 
	 * @param y
	 * @return 내부여부
	 */
	static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<R && y<C) return true; 
		return false;
	}
	
	/**
	 * 1분 지났을때로 물채우기
	 */
	static void fillWater() {
		//임시배열
		String[][] tmp = new String[R][C];
		
		//임시배열에 원본배열 복사
		for (int i = 0; i < R; i++) {
			tmp[i] = Arrays.copyOf(map[i], C);
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				//원본배열에서 물을 만나면 인접한 방향 물로 채우기
				if(map[i][j].equals("*")) {
					for (int k = 0; k < 4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						//내부인지 확인하고 빈공간인지 확인
						if(isIn(nx,ny) && tmp[nx][ny].equals(".")) {
							tmp[nx][ny] = "*";
						}
					}
				}
			}
		}
		
		//원본배열에 임시배열 복사
		for (int i = 0; i < R; i++) {
			map[i] = Arrays.copyOf(tmp[i], C);
		}
	}
	
	/**
	 * 고슴도치의 최소 이동 횟수 구하기
	 * @param x
	 * @param y
	 * @return
	 */
	static int BFS(int x, int y) {
		Queue<Position> queue = new ArrayDeque<>();
		//처음 고슴도치 좌표와 이동횟수 넣기
		queue.offer(new Position(x, y, 0));
		//현재 시간
		int curTime = 0;
		//1분 채우기
		fillWater();
		while(!queue.isEmpty()) {
			Position cur = queue.poll();
			//현재 시간과 이동횟수가 다르면 물을 채우고(1분) 현재시간 갱신
			if(cur.time!=curTime) {
				fillWater();
				curTime = cur.time;
			}
			//4방향 확인
			for (int i = 0; i < 4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				//내부면서 고슴도치가 방문안했고
				//다음 좌표가 빈공간이거나 비버의 굴이면 이동
				if(isIn(nx,ny) && !visited[nx][ny] && (map[nx][ny].equals(".") || map[nx][ny].equals("D"))) {
					//비버굴 도착하면 현재 횟수에 1추가해서 리턴
					if(map[nx][ny].equals("D")) {
						return cur.time+1;
					}
					//큐에 다음 좌표와 이동횟수 추가해서 넣기
					queue.offer(new Position(nx, ny, cur.time+1));
					//해당 좌표 방문 표시
					visited[nx][ny] = true;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//입력 및 초기화
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new String[R][C];
		visited = new boolean[R][C];
		//현재 고슴도치 좌표
		int curX = 0;
		int curY = 0;
		for (int i = 0; i < R; i++) {
			String[] arrTmp = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				//고슴도치 좌표 넣어주고, 해당 좌표는 빈공간(.)으로 넣기
				if(arrTmp[j].equals("S")) {
					curX = i;
					curY = j;
					map[i][j] = ".";
				} else {
					map[i][j] = arrTmp[j];
				}
			}
		}
		//최소시간 담기
		int cnt = BFS(curX,curY);
		
		//0이면 KAKTUS 출력
		System.out.println(cnt==0 ? "KAKTUS":cnt);
	}
}
