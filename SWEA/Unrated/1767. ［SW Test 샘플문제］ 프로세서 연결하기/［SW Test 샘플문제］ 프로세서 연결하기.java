import java.util.*;
import java.io.*;

public class Solution {
	//그래프 길이, 외곽을 제외한 총 코어 수, 최대 코어 수, 정답(최대 코어 수일 때의 최소 전선길이)
	private static int N, total, maxCon, answer;
	//코어 좌표 리스트
	private static List<int[]> coreList;
	//프로세서 판
	private static int[][] map;
	//4방탐색(상우하좌)
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			N = Integer.parseInt(br.readLine());
			total = 0;
			maxCon = 0;
			answer = Integer.MAX_VALUE;
			coreList = new ArrayList<>();
			map = new int[N][N];
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					//외곽에 있지 않은 코어라면 카운팅 후 좌표로 추가
					if(map[j][k] == 1) {
						if(j>0 && k>0 && j<N-1 && k<N-1) {
							coreList.add(new int[] {j, k});
							total++;
						}
					}
				}
			}
			//탐색시작
			find(0, 0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void find(int cnt, int con) {
		//현재까지 연결된 코어수 + 남은 코어 수 < 최대 연결 코어 수
		//라면 탐색 필요없음(가지치기)
		if(con + (total-cnt) < maxCon) return;
		
		//모든 코어를 탐색했다면
		if(cnt == total) {
			//최대 코어 수와 코어 수가 같다면 길이만 갱신
			if(maxCon == con) {
				//정답갱신
				answer = Math.min(count(), answer);
			//최대 코어수보다 현재분기의 코어 수가 크다면 갱신
			} else if(maxCon < con) {
				maxCon = con;
				answer = count();
			}
			return;
		}
		
		//현재 탐색 코어
		int[] cur = coreList.get(cnt);
		for (int dir = 0; dir < 4; dir++) {
			//현재 방향 연결 가능한지 체크
			if(isPossible(cur, dir)) {
				//현재 코어 위치부터 해당 방향 끝까지 -1(전선)으로 표시
				setMap(cur, dir, -1);
				//개수 증가시키고 다음 코어 탐색
				find(cnt+1, con+1);
				//전선 원상복귀
				setMap(cur, dir, 0);
			} else {
				//다음 코어 탐색
				find(cnt+1 , con);
			}
		}
	}

	//cur 좌표부터 dir 방향으로 value로 채우는 함수
	private static void setMap(int[] cur, int dir, int value) {
		int nx = cur[0];
		int ny = cur[1];
		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			//외곽에 닿았다면 종료
			if(!isIn(nx, ny)) break;
			//value로 채우기
			map[nx][ny] = value;
		}	
	}

	//cur 좌표부터 dir방향으로 연결이 가능한지 확인하는 함수
	private static boolean isPossible(int[] cur, int dir) {
		int nx = cur[0];
		int ny = cur[1];
		while(true) {
			nx += dx[dir];
			ny += dy[dir];
			//외곽에 닿았다면 true리턴
			if(!isIn(nx, ny)) return true;
			//다음 진행한 좌표가 빈칸이 아니라면 종료
			if(map[nx][ny]!=0) break;
		}
		return false;
	}

	//전선 개수 카운팅 함수
	private static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == -1) cnt++; 
			}
		}
		return cnt;
	}
	
	//내부인지 확인하는 함수
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
}
