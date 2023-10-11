import java.util.*;
import java.io.*;

public class Solution {
	//cell들의 좌표 리스트
	static List<int[]> cells;
	//맵
	static int[][] map;
	//4방향
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	//맵길이, 최대 셀 개수일때 전선길이, 최대 셀 개수
	static int N, answer, maxCell;
	
	//내부인지 확인
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
	
	private static void find(int cnt, int[][] cur, int conCell) {
		//더이상 진행할 필요없는 조건
		//아직 탐색 안한 셀 개수 + 연결된 개수가 최대 셀 개수보다 작으면 진행 x
		if(cells.size()-cnt+conCell < maxCell) return;
		//모두 탐색했으면
		if(cnt == cells.size()) {
			//전선 길이 체크
			int len = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(cur[i][j] == -1) len++;
				}
			}
			//같은 경우 더 작은 값으로 갱신
			if(maxCell == conCell) {
				answer = Integer.min(len, answer);
			//더 큰 셀 개수라면 셀 개수와 길이 갱신
			} else {
				maxCell = conCell;
				answer = len;
			}
			return;
		} else {
			//현재 탐색 셀
			int[] curCell = cells.get(cnt);
			//현재상태 복사할 임시 맵
			int[][] mapTmp = new int[N][N];
			//복사
			for (int j = 0; j < N; j++) {
				mapTmp[j] = Arrays.copyOf(cur[j], N);
			}
			//1. 연결 안할 경우
			find(cnt+1, mapTmp, conCell);
			
			//2. 4방향 체크 후 갈 수 있는 곳으로 연결하는 경우
			for (int i = 0; i < 4; i++) {
				//현재 상태로 초기화
				for (int j = 0; j < N; j++) {
					mapTmp[j] = Arrays.copyOf(cur[j], N);
				}
				
				//다음좌표 계산을 위해 현재좌표로 초기화
				int nx = curCell[0];
				int ny = curCell[1];
				//해당 방향 가능 여부
				boolean isPossible = false;
				while(true) {
					//다음 좌표 값 계산
					nx += dx[i];
					ny += dy[i];
					//밖으로 나갔다면 연결 가능
					if(!isIn(nx, ny)) {
						isPossible = true;
						break;
					//장애물이 있을 경우 탐색 종료
					} else if(mapTmp[nx][ny] != 0){
						break;
					}
				}
				
				//연결이 가능하다면
				if(isPossible) {
					//다음좌표 계산을 위해 현재좌표로 초기화
					nx = curCell[0];
					ny = curCell[1];
					
					while(true) {
						nx += dx[i];
						ny += dy[i];
						//해당방향 끝까지 배열에 전설을 -1로 표시하여 연결
						if(!isIn(nx,ny)) break;
						mapTmp[nx][ny] = -1;
					}
					//현재 맵 상태와 셀 하나 연결되었다고 표시 후, 다음 셀로 이동
					find(cnt+1, mapTmp, conCell+1);
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			answer = Integer.MAX_VALUE;
			maxCell = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			cells = new ArrayList<>();
			map = new int[N][N];
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					if(map[j][k] == 1) {
						//가장자리에 있는 것들 탐색대상에서 제외
						if(!(j==0 || j==N-1 || k==0 || k==N-1)) cells.add(new int[] {j, k});
					}
				}
			}
			//탐색 진행
			find(0, map, 0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
}
