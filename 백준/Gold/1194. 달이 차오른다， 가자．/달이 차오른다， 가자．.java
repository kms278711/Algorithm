import java.io.*;
import java.util.*;

public class Main {
	//가로,세로
	private static int N,M;
	//미로
	private static char[][] miro;
	//방문배열. 첫번째부터 열쇠보유현황(비트를 숫자로 변환), x좌표, y좌표
	private static boolean[][][] visited;
	
	/**
	 * 상태 클래스
	 * 이동횟수, x좌표, y좌표
	 * key : 비트마스킹 ex)111111 키 다있음
	 * 				 ex)100000 F키만 있음
	 * 				 ex)000001 A키만 있음
	 */
	static class Status{
		int cnt,x,y,key;
		Status(int cnt, int x, int y, int key) {
			this.cnt = cnt;
			this.x = x;
			this.y = y;
			this.key = key;
		}
		@Override
		public String toString() {
			return "Status [cnt=" + cnt + ", x=" + x + ", y=" + y + ", key=" + key + "]";
		}
	}
	
	//맵 내부인지 체크
	private static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<N && y<M) return true;
		return false;
	}
	
	//BFS
	private static void find(int x, int y) {
		//사방탐색
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		Queue<Status> queue = new ArrayDeque<>();
		queue.offer(new Status(0,x,y,0));
		while(!queue.isEmpty()) {
			Status cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				//다음갈 좌표들
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				//다음 좌표가 내부인지, 벽이 아닌지, 방문하지 않은 점인지 확인
				if(isIn(nx,ny) && miro[nx][ny] != '#' && !visited[cur.key][nx][ny]) {
					//현재 키값 임시저장
					int key = cur.key;
					if(miro[nx][ny] == '.' || miro[nx][ny] == '0') {
						queue.offer(new Status(cur.cnt+1, nx, ny, key));
					} else if(miro[nx][ny] >= 97 && miro[nx][ny] <= 122) {
						//열쇠 보유여부 갱신. 소문자 a를 빼서 0~5까지 shift 연산 후, 비트 or 연산으로 열쇠 갱신 
						key = cur.key | (1<<miro[nx][ny] - 'a');
						queue.offer(new Status(cur.cnt+1, nx, ny, key));
					} else if(miro[nx][ny] >= 65 && miro[nx][ny] <= 90) {
						//키가 있는지 확인. 대문자 A를 빼서 0~5까지 shift 연산 후, 비트 and 연산으로 열쇠 여부 확인
						if((cur.key & (1<<miro[nx][ny] - 'A')) != 0) {
							queue.offer(new Status(cur.cnt+1, nx, ny, key));
						}
					//1이라면 현재까지 이동횟수에 1더해서 출력하고 리턴
					} else if(miro[nx][ny] == '1') {
						System.out.println(cur.cnt + 1);
						return;
					}
					//방문표시
					visited[key][nx][ny] = true;
				}
			}
		}
		//못 찾고 while문 빠져나올시 -1 출력
		System.out.println(-1);
	}

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//초기화 및 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[(int)Math.pow(2, 6)][N][M];
		miro = new char[N][M];
		//시작좌표
		int startX = 0;
		int startY = 0;
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				miro[i][j] = tmp.charAt(j);
				if(miro[i][j]=='0') {
					startX = i;
					startY = j;
				}
			}
		}
		find(startX, startY);
	}
}
