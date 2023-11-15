import java.util.*;
import java.io.*;

public class Solution {
	//두께, 가로 크기, 최소 합격기준, 정답(약 투여 횟수)
	private static int D, W, K, answer;
	//보호 필름 정보
	private static int[][] board;
	//막 별 방문여부
	private static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			answer = K;
			board = new int[D][W];
			visited = new boolean[D];
			
			for (int x = 0; x < D; x++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int y = 0; y < W; y++) {
					board[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			
			//탐색시작
			find(0, 0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	//약 투여 횟수 찾기
	private static void find(int cnt, int cur) {
		if(K==1) {
			answer = 0;
			return;
		}
		
		//이미 나온 투여횟수보다 현재 분기의 횟수가 많다면 리턴
		if(cnt >= answer) return;
		
		//합격 기준 충족하는지 체크
		if(check()) {
			//충족하면 정답 갱신
			answer = cnt;
			return;
		}
		
		//두께만큼 진행
		for (int x = cur; x < D; x++) {
			//방문하지 않은 막이라면
			if(!visited[x]) {
				//방문표시
				visited[x] = true;
				//해당 막 임시로 복사해놓기
				int[] arr = Arrays.copyOf(board[x], W);
				//A,B 2가지 경우로 돌려보기
				for (int value = 0; value < 2; value++) {
					for (int y = 0; y < W; y++) {
						board[x][y] = value; 
					}
					//다음 분기 진행
					find(cnt+1, x+1);
				}
				
				//원상복구
				board[x] = Arrays.copyOf(arr, W);
				visited[x] = false;
			}
		}
	}
	
	//합격인지 체크하는 함수
	private static boolean check() {
		for (int y = 0; y < W; y++) {
			//카운팅 변수
			int cnt = 0;
			//현재 특성
			int cur = board[0][y]; 
			for (int x = 0; x < D; x++) {
				//이어져 오던 특성과 다르다면
				if(board[x][y] != cur) {
					//카운팅 초기화, 현재 특성 변경
					cnt = 1;
					cur = board[x][y];
					if(x == D-K+1) break;
				} else {
					cnt++;
					//합격 기준 통과하면 다음 세로방향 탐색
					if(cnt == K) break;
				}
			}
			//세로칸 중 기준에 통과못하는게 하나라도 있으면 false 리턴
			if(cnt < K) return false;
		}
		return true;
	}
	
	
}
