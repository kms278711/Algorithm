import java.util.*;
import java.io.*;

public class 포탑_부수기 {
	// 세로, 가로, 턴수
	private static int N,M,K;
	// 공격자, 피해자
	private static int[] attacker, victim;
	// 공격력 배열
	private static int[][] map;
	// 마지막 공격턴 저장
	private static int[][] lastAttack;
	// 전투와 관련있었는지 여부
	private static boolean[][] isRelated;
	
	// 8방향 탐색
	private static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	private static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
	
	// 상태 클래스
	static class Status {
		// 현재 좌표
		int[] cur;
		// 지금까지 온 경로
		List<int[]> path;
		public Status(int[] cur, List<int[]> path) {
			super();
			this.cur = cur;
			this.path = path;
		}
	}
	
	// 단순구현
	// 공격자 찾기
	private static int[] findAttack() {
		int[] answer = new int[2];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0 && map[i][j] < min) {
					answer[0] = i;
					answer[1] = j;
					min = map[answer[0]][answer[1]];
				} else if(map[i][j] == min) {
					if(lastAttack[answer[0]][answer[1]] < lastAttack[i][j]) {
						answer[0] = i;
						answer[1] = j;
					} else if(lastAttack[answer[0]][answer[1]] == lastAttack[i][j]) {
						if(answer[0] + answer[1] < i + j) {
							answer[0] = i;
							answer[1] = j;
						} else if(answer[0] + answer[1] == i + j) {
							if(answer[1] < j) {
								answer[0] = i;
								answer[1] = j;
							}
						}
					}
				}
			}
		}
		return answer;
	}
	
	// 단순구현
	// 피해자 찾기
	private static int[] findVictim() {
		int[] answer = new int[2];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0 && map[i][j] > max) {
					answer[0] = i;
					answer[1] = j;
					max = map[answer[0]][answer[1]];
				} else if(map[i][j] == max) {
					if(lastAttack[answer[0]][answer[1]] > lastAttack[i][j]) {
						answer[0] = i;
						answer[1] = j;
					} else if(lastAttack[answer[0]][answer[1]] == lastAttack[i][j]) {
						if(answer[0] + answer[1] > i + j) {
							answer[0] = i;
							answer[1] = j;
						} else if(answer[0] + answer[1] == i + j) {
							if(answer[1] > j) {
								answer[0] = i;
								answer[1] = j;
							}
						}
					}
				}
			}
		}
		return answer;
	}
	
	// 공격시작
	private static void attack() {
		Queue<Status> queue = new ArrayDeque<Status>();
		Status answer = null;
		// 방문배열
		boolean[][] visited = new boolean[N][M];
		// 공격자를 시작으로 큐에 넣기
		queue.offer(new Status(attacker, new ArrayList<>()));
		// 방문표시
		visited[attacker[0]][attacker[1]] = true;
		
		// 레이저 발사
		loop :
		while(!queue.isEmpty()) {
			Status status = queue.poll();
			// 4방향 탐색 우 하 좌 상
			for (int i = 0; i < 8; i = i+2) {
				// 0보다 작아지면 끝으로 보내버리기
				// 그렇지 않다면 나머지연산으로 처리
				int nx = (status.cur[0] + dx[i] < 0) ? N-1 : (status.cur[0] + dx[i])%N;
				int ny = (status.cur[1] + dy[i] < 0) ? M-1 : (status.cur[1] + dy[i])%M;
				// 방문안했고 0이 아니라면
				if(!visited[nx][ny] && map[nx][ny] != 0) {
					// 피해자 좌표에 도착하면 while 루프 종료
					if(nx==victim[0] && ny==victim[1]) {
						// null인 answer에 값 넣기
						answer = status;
						break loop;
					}
					// 방문표시
					visited[nx][ny] = true;
					// 경로 복사
					List<int[]> path = new ArrayList<>(status.path); 
					// 경로에 좌표 추가
					path.add(new int[] {nx, ny});
					// 큐에 해당 상태 저장
					queue.offer(new Status(new int[] {nx, ny}, path));
				}
			}
		}
		
		// 레이저 발사 할 수 없는 경우
		// 포탄공격
		if(answer == null) {
			for (int i = 0; i < 8; i++) {
				// 0보다 작아지면 끝으로 보내버리기
				// 그렇지 않다면 나머지연산으로 처리
				int nx = (victim[0] + dx[i] < 0) ? N-1 : (victim[0] + dx[i])%N;
				int ny = (victim[1] + dy[i] < 0) ? M-1 : (victim[1] + dy[i])%M;
				// 공격자 좌표가 아니면서 부서진 포탑이 아니라면
				if(!(nx == attacker[0] && ny == attacker[1]) && map[nx][ny] != 0) {
					// 연관있다고 표시
					isRelated[nx][ny] = true;
					// 2로 나눈 몫만큼 피해 입히기
					map[nx][ny] -= map[attacker[0]][attacker[1]]/2;
					// 0보다 작아졌을 경우 0으로 넣기
					if(map[nx][ny]<0) map[nx][ny] = 0;
				}
			}
		// 레이저 발사한 경우
		} else {
			// 최단경로를 따라서 진행
			for (int[] pathPoint : answer.path) {
				// 연관있다고 표시
				isRelated[pathPoint[0]][pathPoint[1]] = true;
				// 2로 나눈 몫만큼 피해 입히기
				map[pathPoint[0]][pathPoint[1]] -= map[attacker[0]][attacker[1]]/2;
				// 0보다 작아졌을 경우 0으로 넣기
				if(map[pathPoint[0]][pathPoint[1]]<0) map[pathPoint[0]][pathPoint[1]] = 0;
			}
		}
		// 공격자의 공격력만큼 피해자 공격력 깎기
		map[victim[0]][victim[1]] = (map[victim[0]][victim[1]] - map[attacker[0]][attacker[1]] <= 0 ? 0:map[victim[0]][victim[1]] - map[attacker[0]][attacker[1]]);
	}
	
	// 포탑 정비
	private static void maintenance() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 관련있다고 표시된게 아니면서 부서진 포탑이 아니라면 1 회복
				if(map[i][j] != 0 && !isRelated[i][j]) {
					map[i][j] ++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// 입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		lastAttack = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1턴부터 진행
		// K초까지 진행
		for (int i = 1; i <= K; i++) {
			// 연관배열 초기화
			isRelated = new boolean[N][M];
			// 1. 공격자 선정
			attacker = findAttack();
			// 해당 공격자가 현재 턴에 공격했다고 표시
			lastAttack[attacker[0]][attacker[1]] = i;
			
			// 2. 공격자의 공격 
			victim = findVictim();
			// 공격자와 피해자 전투와 연관있다고 표시
			isRelated[attacker[0]][attacker[1]] = true;
			isRelated[victim[0]][victim[1]] = true;
			// 공격자 공격력 N+M만큼 증가
			map[attacker[0]][attacker[1]] += N+M;
			attack();
			
			// 3. 포탑 부서짐.
			// 남은 개수 카운팅
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(map[j][k] != 0) cnt++; 
				}
			}
			// 1개 남았으면 종료
			if(cnt == 1) break;
			
			// 4. 포탑 정비
			maintenance();
		}
		
		// 가장 강한 포탑 찾기
		int[] answer = findVictim();
		System.out.println(map[answer[0]][answer[1]]);
	}
}
