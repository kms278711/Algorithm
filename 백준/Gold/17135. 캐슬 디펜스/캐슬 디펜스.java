import java.io.*;
import java.util.*;

class Point{
	public int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	//행, 열, 공격거리, 임시카운팅변수
	private static int N,M,D, answer, cnt;
	//게임판
	private static int[][] board;
	//궁수자리 check배열
	private static boolean[] checked;
	//없어진 적 check배열
	private static boolean[][] boardChecked;
	/**
	 * boardTmp의 궁수 세팅으로 게임 진행 
	 * @param boardTmp
	 */
	private static void shotCnt(int[][] boardTmp) {
		//없어진 적 개수 초기화
		cnt = 0;
		for (int turn = 0; turn < N; turn++) {
			//턴마다 없어진 적 배열 초기화
			boardChecked = new boolean[N][M];
			for (int i = 0; i < M; i++) {
				if(boardTmp[N][i] == 2) {
					//거리, 없어질 적의좌표, 적 수 초기화(종료위함)
					int dis = Integer.MAX_VALUE;
					int curX = -1;
					int curY = -1;
					int enemyCnt = 0;
					//턴수에 따라 아래부터 탐색
					for (int j = N-1; j >= turn ; j--) {
						for (int k = M-1; k >= 0; k--) {
							if(boardTmp[j][k] == 1) {
								//적이 있다면 적 카운팅
								enemyCnt++;
								//현재 궁수와의 거리 계산
								int tmp = Math.abs(N-j)+Math.abs(i-k);
								if(tmp<=D) {
									//더 작다면 갱신
									if(dis > tmp) {
										dis = tmp;
										curX = j;
										curY = k;
									//거리가 같으면 더 왼쪽에 있는 적으로 좌표 갱신
									} else if(dis == tmp) {
										if(k<curY) {
											curX = j;
											curY = k;
										}
									}
								}
							}
						}
					}
					//적이 한명도 없다면 탐색종료
					if(enemyCnt == 0) return;
					//없어질 적 좌표값 true로 바꾸기
					if(curX != -1) {
						boardChecked[curX][curY] = true;
					}
				}
			}
			
			//없어질 적 좌표값 0으로 바꾸고 없앤적 수 카운팅
			for (int i = turn; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(boardChecked[i][j]) {
						boardTmp[i][j] = 0;
						cnt++;
					}
				}
			}
			
			//아래부터 바로 위줄을 다음줄로 복사, 아래부터 하는이유 : 위부터 하면 덮어씌워짐
			for (int i = N-2; i >= turn; i--) {
				for (int j = 0; j < M; j++) {
					boardTmp[i+1][j] = boardTmp[i][j];
				}
			}
		}
	}
	
	/**
	 * 궁수세팀 함수
	 * @param L
	 */
	private static void setArcher(int L) {
		//3명 뽑히면
		if(L == 3) {
			//임시배열 생성
			int[][] boardTmp = new int[N+1][M];
			//배열 복사
			for (int i = 0; i < N+1; i++) {
				for (int j = 0; j < M; j++) {
					boardTmp[i][j] = board[i][j];
				}
			}
			//게임진행
			shotCnt(boardTmp);
			//최대값으로 갱신
			answer = Math.max(answer, cnt);
		} else {
			for (int i = 0; i < M; i++) {
				//사용 안한 자리라면
				if(!checked[i]) {
					//해당 자리를 궁수자리로 세팅
					checked[i] = true;
					board[N][i] = 2;
					//다음 궁수 세팅
					setArcher(L+1);
					//해당 자리를 궁수자리로 세팅안함
					checked[i] = false;
					board[N][i] = 0;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		board = new int[N+1][M];
		checked = new boolean[M];
		answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		setArcher(0);
		System.out.println(answer);
	}
}