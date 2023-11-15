import java.util.*;
import java.io.*;

public class Solution {
	//게임판 크기, 정답
	private static int N, answer;
	//게임판 정보
	private static int[][] map;
	//웜홀 리스트 배열(좌표 2개씩 가지고 있음)
	private static List<int[]>[] hole;
	//사방탐색(상우하좌)
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	//블록 정보(index방향으로 들어오면 값으로 방향 전환)
	private static int[][] block = {
		{},
		{2, 3, 1, 0},
		{1, 3, 0, 2},
		{3, 2, 0, 1},
		{2, 0, 3, 1},
		{2, 3, 0, 1}
	};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			N = Integer.parseInt(br.readLine().trim());
			answer = 0;
			map = new int[N][N];
			hole = new ArrayList[5];
			for (int j = 0; j < 5; j++) {
				hole[j] = new ArrayList<>();
			}
			
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					//웜홀인경우 웜홀배열에 추가
					if(map[j][k] >= 6) {
						hole[map[j][k]-6].add(new int[] {j , k});
					}
				}
			}
			

			//핀볼 놓을 수 있는 지점마다 4방향으로 게임 진행
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(map[j][k] == 0) {
						for (int dir = 0; dir < 4; dir++) {
							int score = game(j, k, dir); 
							answer = Math.max(answer, score);
						}
					}
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}


	//시작좌표, 진행방향
	private static int game(int startX, int startY, int dir) {
		//현재 좌표
		int x = startX;
		int y = startY;
		//제자리에 돌아왔을 때 체크를 위해 시간재기
		int time = 0;
		//점수 
		int score = 0;
		while(true) {
			//시작지점으로 돌아오면 점수 최대값으로 갱신
			if(x == startX && y == startY && time != 0) break;
			
			//시간진행
			time ++;
			
			//다음 좌표
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			//벽에 부딫히면
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
				//방향 전환
				if(dir == 0 || dir == 2) {
					dir = (dir == 0) ? 2:0;
				} else {
					dir = (dir == 1) ? 3:1;
				}
				//점수 증가
				score++;
				//원위치
				nx = x;
				ny = y;
			}
			
			//다음 좌표 반영
			x = nx;
			y = ny;
			//블랙홀이면 게임 종료
			if(map[nx][ny] == -1) break;
			//빈공간이 아니라면
			if(map[nx][ny] != 0) {
				//다음 좌표가 블록일 경우
				if(map[nx][ny] <= 5) {
					//정해놓은 것 참고해서 방향 바꾸기
					dir = block[map[nx][ny]][dir];
					//점수 증가
					score++;
				//웜홀 만날경우
				} else if(map[nx][ny] > 5) {
					//첫번째 웜홀
					int[] firstHole = hole[map[nx][ny]-6].get(0);
					//두번째 웜홀
					int[] secondHole = hole[map[nx][ny]-6].get(1);
					//현재 위치와 같은 숫자에 해당하는 다른 웜홀로 현재위치 변경
					if(firstHole[0] == x && firstHole[1] == y) {
						x = secondHole[0];
						y = secondHole[1];
					} else {
						x = firstHole[0];
						y = firstHole[1];
					}
				}
			}
		}
		return score;
	}
}
