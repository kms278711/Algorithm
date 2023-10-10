import java.util.*;
import java.io.*;

public class Main {
	static int R,C,T;
	static int[][] map;
	//공기청정기 좌표
	static int[][] cleaner;
	//시계방향(우 하 좌 상)
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	//x범위에 맞고 map을 벗어나지 않는 지 체크
	private static boolean isIn(int x, int y, int[] xRange) {
		return x>=xRange[0] && y>=0 && x<=xRange[1] && y<C;
	}
	
	//확산
	private static void spread() {
		//추가되는 먼지 배열
		int[][] add = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				//먼지가 있으면
				if(map[i][j] != 0) {
					//확산 개수
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						//다음좌표가 map을 안나가면서 공기청정기 위치가 아니라면 확산
						if(isIn(nx,ny, new int[] {0, R-1}) && !(nx==cleaner[0][0] && ny==cleaner[0][1]) && !(nx==cleaner[1][0] && ny==cleaner[1][1])) {
							//카운팅
							cnt++;
							add[nx][ny] += map[i][j]/5;
						}
					}
					//없어지는 먼지 양 반영
					map[i][j] -= map[i][j]/5 * cnt;
				}
			}
		}
		
		//추가된 먼지 원본 배열에 추가
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] += add[i][j];
			}
		}
	}
	
	//공기청정기 작동(공기청정기 순서, 시계/반시계, x범위)
	private static void wind(int seq, int dir, int[] xRange) {
		//복사 임시 배열
		int[][] tmp = new int[R][C];
		//복사
		for (int i = 0; i < R; i++) {
			tmp[i] = Arrays.copyOf(map[i], C);
		}
		
		
		int idx = 0;
		//작동하는 공기청정기 좌표
		int curX = cleaner[seq][0];
		int curY = cleaner[seq][1];
		while(true) {
			int nx = curX + dx[idx];
			int ny = curY + dy[idx];
			//x범위에 맞는 내부라면
			if(!isIn(nx, ny, xRange)) {
				idx += dir;
				//index나가는거 방지
				if(idx == -1) idx = 3;
				if(idx == 4) idx = 0;
				nx = curX + dx[idx];
				ny = curY + dy[idx];
			}
			//공기청정기 좌표로 돌아오면 종료
			if(nx == cleaner[seq][0] && ny == cleaner[seq][1]) break;
			//전 좌표의 먼지값을 다음 좌표값으로 이동
			tmp[nx][ny] = map[curX][curY];
			//다음좌표로 이동
			curX = nx;
			curY = ny;
		}
		
		//원본배열에 다시 복사
		for (int i = 0; i < R; i++) {
			map[i] = Arrays.copyOf(tmp[i], C);
		}
	}

	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		//[0:위 1:아래] [0:x좌표 1:y좌표]
		cleaner = new int[2][2];
		//두번째 공기청정기인지 확인
		boolean isSecond = false;
		int answer = 0;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//공기청정기 위치 저장
				//해당 위치는 map에 0으로 표시
				if(map[i][j] == -1) {
					map[i][j]=0;
					if(isSecond) {
						cleaner[1][0] = i;
						cleaner[1][1] = j;
					} else {
						isSecond = true;
						cleaner[0][0] = i;
						cleaner[0][1] = j;
					}
				}
			}
		}
		
		for (int i = 0; i < T; i++) {
			//확산
			spread();
			//공기청정기 작동(위)
			wind(0, -1, new int[] {0, cleaner[0][0]});
			//공기청정기 작동(아래)
			wind(1, 1, new int[] {cleaner[1][0], R-1});
		}
		
		//먼지개수 카운트
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}

	
}