import java.util.*;
import java.io.*;

public class Main {
	/**
	 * 좌표클래스 
	 * x,y,z좌표와 현재 일 수
	 */
	static class Point{
		int x,y,z, day;

		public Point(int z, int x, int y, int day) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.day = day;
		}
		
	}
	
	//가로, 세로, 높이
	static int M,N,H;
	//6방향(위, 아래, 왼쪽, 오른쪽, 앞, 뒤)
	static int[] dz = {1, -1, 0, 0, 0, 0};
	static int[] dx = {0, 0, 0, 0, 1, -1};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	//지도(높이, 세로, 가로)
	static int[][][] tomato;
	static Queue<Point> queue = new ArrayDeque<>();
	
	/**
	 * 내부인지 확인
	 * @param x 
	 * @param y
	 * @return 내부여부
	 */
	static boolean isIn(int z, int x, int y) {
		if(x>=0 && y>=0 && z>= 0 && z<H && x<N && y<M) return true; 
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//입력 및 초기화
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		tomato = new int[H][N][M];
		//안익은 개수
		int cnt = 0;
		
		//z
		for (int i = 0; i < H; i++) {
			//x
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				//y
				for (int k = 0; k < M; k++) {
					tomato[i][j][k] = Integer.parseInt(st.nextToken());
					if(tomato[i][j][k] == 1) {
						queue.offer(new Point(i, j, k, 0));
					}
					//안익은 개수 카운팅
					if(tomato[i][j][k] == 0) cnt++;
				}
			}
		}
		
		//안익은게 없다면 0출력
		if(cnt == 0) System.out.println(0);
		else {
			//익을 수 있는 토마토를 다 돌거나, 안익은 개수가 0이 될때까지
			while(!queue.isEmpty()) {
				//현재 익은 토마토 좌표
				Point cur = queue.poll();
				for (int i = 0; i < 6; i++) {
					int nx = cur.x+dx[i];
					int ny = cur.y+dy[i];
					int nz = cur.z+dz[i];
					//칸 안에 있으면서, 다음 좌표에 안익은 토마토가 있다면
					if(isIn(nz, nx, ny) && tomato[nz][nx][ny] == 0) {
						//안익은 토마토 개수 1개 빼주고
						cnt--;
						//해당 좌표 토마토 익은 걸로 표시
						tomato[nz][nx][ny] = 1;
						//일수 더해서 큐에 넣어주기
						queue.offer(new Point(nz, nx, ny, cur.day+1));
						//안익은게 없다면 해당 일수에 다음 일수 출력하고 break
						if(cnt==0) {
							System.out.println(cur.day+1);
							break;
						}
					}
					if(cnt==0) {
						break;
					}
				}
			}
			//(큐를 다비우고 cnt가 남아있을 경우)불가능하기 때문에 -1출력
			if(cnt != 0) System.out.println(-1);
		}
		
		
	}
}
