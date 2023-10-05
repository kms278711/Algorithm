import java.util.*;
import java.io.*;

public class Solution {
	//구슬횟수, 가로, 세로, 정답
	static int N,W,H, answer;
	//맵
	static int[][] board;
	//사방탐색
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	//좌표 클래스
	//x, y, 폭발력
	static class Point {
		int x,y, size;
		Point(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
	
	//내부인지 체크
	private static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<H && y<W) return true;
		return false;
	}
	
	//구슬 N번 떨어트리기
	private static void DFS(int cnt, int[][] cur) {
		if(cnt == N) {
			//-1로 초기화
			int blockCnt = 0;
			//남은 블록 개수 세기
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(cur[i][j] != 0) blockCnt++;
				}
			}
			answer = Math.min(blockCnt, answer);
		} else {
			//가로로 진행
			for (int i = 0; i < W; i++) {
				//파라미터로 들어온 원본 배열 복사
				//임시배열
				int[][] boardTmp = new int[H][W];
				for (int j = 0; j < H; j++) {
					boardTmp[j] = Arrays.copyOf(cur[j], W);
				}
				
				//y좌표 i위치에서 떨어트려서 가장 가까운 블록 찾기
				for (int j = 0; j < H; j++) {
					if(boardTmp[j][i] != 0) {
						//해당 블록으로 제거 진행
						BFS(j, i, boardTmp);
						break;
					}
				}
				//다음분기
				DFS(cnt+1, boardTmp);
			}
		}
	}
	
	//블록 제거
	private static void BFS(int x, int y, int[][] board) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(x,y, board[x][y]));
		//현재 블록 없어진것으로 표시
		board[x][y] = 0;
		boolean[][] visited = new boolean[H][W];
		visited[x][y] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				//한방향을 제거범위만큼 진행
				for (int j = 1; j <= cur.size-1; j++) {
					//다음 좌표
					int nx = cur.x+(dx[i]*j);
					int ny = cur.y+(dy[i]*j);
					//내부인지 체크
					if(isIn(nx,ny)&&!visited[nx][ny]) {
						//폭발력이 1보다 크면 큐에 넣기
						if(board[nx][ny] > 1) {
							queue.offer(new Point(nx,ny,board[nx][ny]));
							visited[nx][ny] = true;
						}
						//해당 블록 없애기
						board[nx][ny] = 0;
					}
				}
			}
		}
		
		//빈공간없게 땡기기
		for (int i = 0; i < W; i++) {
			//아래부터 진행
			for (int j = H-1; j > 0; j--) {
				//0인 곳을 찾으면
				if(board[j][i] == 0) {
					//해당 장소에서 위로 탐색하여 가장 가까운 블록을 찾고 해당 값 복사
					for (int k = j-1; k >= 0 ; k--) {
						if(board[k][i] != 0) {
							board[j][i] = board[k][i];
							//블록 받아온 곳 블록 없애기
							board[k][i] = 0;
							break;
						}
					}
				}
			}
		}
	}


	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i<=T; i++) {
			//출력설정, 입력 및 초기화
			sb.append("#").append(i).append(" ");
			answer = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			board = new int[H][W];
			
			for (int j = 0; j < H; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < W; k++) {
					board[j][k] = Integer.parseInt(st.nextToken()); 
				}
			}
			DFS(0, board);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
