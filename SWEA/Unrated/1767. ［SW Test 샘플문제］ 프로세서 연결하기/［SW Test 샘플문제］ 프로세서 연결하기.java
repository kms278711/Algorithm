import java.io.*;
import java.util.*;

//좌표 클래스
class Point{
	public int x,y;
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Solution {
	//가로세로길이, 최대 멕시노스 개수, 최대개수일때  전선길이, 탐색해야할 총 개수
	private static int N, maxCnt, maxSize, total;
	private static int[][] map;
	//상우하좌
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	/**
	 * 탐색
	 * @param cur 해당분기 현재 맵 상태
	 * @param cnt 연결코어수
	 * @param size 연결선수
	 */
	private static void DFS(int[][] cur, int checkCnt, int cnt, int sX) {
		//총개수에서 현재탐색개수 뺴고 연결된 개수가 지금까지 계산된 최대개수보다 작으면 탐색 패스
		if(total-checkCnt+cnt < maxCnt) return;
		//더이상 코어가 없다면
		if(checkCnt == total) {
			if(cnt < maxCnt) return;

			int size = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(cur[i][j] == -1) {
						size++;
					}
				}
			}
			
			//최대로 나온 것보다 현재분기의 코어수가 더 많으면
			if(cnt > maxCnt) {
				maxCnt = cnt;
				maxSize = size;
			//코어수가 같다면
			} else if(cnt==maxCnt) {
				//더작은 전선수로 교체
				maxSize = Math.min(maxSize, size);
			}
		} else {
			//현재 상태 복사 배열
			int[][] tmp = new int[N][N];
			//배열 복사
			for (int h = 0; h < N; h++) {
				tmp[h] = Arrays.copyOf(cur[h], N);
			}
			for (int i = sX; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//해당 칸에 코어가 있을 경우
					if(cur[i][j] == 1) {
						//현재 좌표 입력
						int x = i, y = j;
						//탐색한 코어 2로 표시
						cur[i][j] = 2;
						tmp[i][j] = 2;
						//4방향 확인
						for (int k = 0; k < 4; k++) {
							//초기화
							x = i;
							y = j;
							int cntTmp = 0;
							//한방향으로 쭉 가보기
							while(true) {
								x += dx[k];
								y += dy[k];
								if(isOut(x, y)) {
									cntTmp ++;
									break;
								} else if(tmp[x][y] == 0) {
									tmp[x][y] = -1;
								} else {
									//원상복귀
									if(dx[k] == 0) {
										for (int l = 0; l < N; l++) {
											tmp[i][l] = cur[i][l];
										}
									} else {
										for (int l = 0; l < N; l++) {
											tmp[l][j] = cur[l][j];
										}	
									}
									break;
								}
							}
							//추가된 코어수만큼, 추가된 연결선수만큼 더해서 다음분기로
							DFS(tmp, checkCnt+1, cnt+cntTmp, i);
							if(dx[k] == 0) {
								for (int l = 0; l < N; l++) {
									tmp[i][l] = cur[i][l];
								}
							} else {
								for (int l = 0; l < N; l++) {
									tmp[l][j] = cur[l][j];
								}	
							}
						}
						//탐색 안했다고 표시
						cur[i][j] = 1;
					}
				}
			}
		}
	}
	
	//밖으로 나갔는지(연결됐는지)확인
	private static boolean isOut(int x, int y) {
		if(x>=N || y>=N || x<0 || y<0) return true;
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			//입력 및 초기화
			sb.append("#").append(i).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			maxCnt = 0;
			total = 0;
			
			maxSize=Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < N; k++) {
					map[j][k] = Integer.parseInt(st.nextToken());
					//외곽에 있는것 연결되어 있기떄문에 탐색영역 제외하기
					if(map[j][k] == 1 && (j==0 || k==0 || j==N-1 || k==N-1)) {
						map[j][k] = 2;
					}
					if(map[j][k] == 1) total++;
				}
			}
			DFS(map, 0, 0, 0);
			sb.append(maxSize).append("\n");
		}
		System.out.println(sb);
	}
}
