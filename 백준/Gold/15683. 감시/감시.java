import java.io.*;
import java.util.*;

public class Main {
	private static int N,M,cctvCnt, answer=Integer.MAX_VALUE;
	private static int[][] office;
	private static int[][] officeCCTVcnt;
	
	//내부인지 체크
	private static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<N && y<M) return true;
		else return false;
	}
	
	private static void DFS(int cnt, int x, int y) {
		//cctv 개수 같다면
		if(cnt == cctvCnt+1) {
			//0개수 카운팅
			int tmp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(office[i][j] == 0) tmp++; 
				}
			}
			//더 작은값으로 갱신
			answer = Math.min(answer, tmp);
		} else {
			//현재 cctv값 확인
			switch(office[x][y]) {
				//cctv1
				case 1:
					int[] dx1 = {-1, 0, 1, 0};
					int[] dy1 = {0, 1, 0, -1};
					for (int i = 0; i < 4; i++) {
						int nx = x;
						int ny = y;
						while(true) {
							nx += dx1[i];
							ny += dy1[i];
							//안에 있으면서 빈공간이거나 이미 다른 cctv가 탐색한 구간이라면 
							if(isIn(nx, ny) && (office[nx][ny] == 0 || office[nx][ny] == -1)) {
								//cctv의 탐색영역으로 설정
								office[nx][ny] = -1;
								//해당 구역을 바라보고 있는 cctv개수 추가
								officeCCTVcnt[nx][ny] ++;
							}
							//내부가 아니거나 벽을 만나면 break
							if(!isIn(nx,ny) || office[nx][ny] == 6) break;
						}
						
						//cctv가 더 있는지 체크
						boolean flag = false;
						for (int j = x; j < N; j++) {
							for (int h = 0; h < M; h++) {
								if(office[j][h] != 0 && office[j][h] != 6 && office[j][h] != -1) {
									//현재 cctv보다 다음영역이라면
									if(j<=x && h<=y) continue;
									DFS(cnt+1,j,h);
									flag = true;
									break;
								}
							}
							if(flag) break;
						}
						//더 없다면 마지막분기로 보냄
						if(!flag) DFS(cctvCnt+1,x,y);
						//cctv 회전을 위해 구역 다시 채우기
						while(true) {
							nx -= dx1[i];
							ny -= dy1[i];
							//실내면서 탐색한 구역이라면 
							if(isIn(nx, ny) && office[nx][ny] == -1) {
								//cctv개수 줄이고
								officeCCTVcnt[nx][ny] --;
								//해당 구역 가르키는 cctv가 없다면 빈구역으로 바꾸기
								if(officeCCTVcnt[nx][ny] == 0) office[nx][ny] = 0;
							}
							//현재분기에 cctv위치까지 오면 break
							if(nx == x && ny == y) break;
						}
					}
					break;
				//cctv2
				case 2:
					//양쪽으로 가기 때문에 두개만 설정
					int[] dx2 = {0, 1};
					int[] dy2 = {1, 0};
					for (int i = 0; i < 2; i++) {
						//양방향
						int nx1 = x;
						int ny1 = y;
						int nx2 = x;
						int ny2 = y;
						//한쪽방향
						while(true) {
							nx1 += dx2[i];
							ny1 += dy2[i];
							if(isIn(nx1, ny1) && (office[nx1][ny1] == 0 || office[nx1][ny1] == -1)) {
								office[nx1][ny1] = -1;
								officeCCTVcnt[nx1][ny1] ++;
							}
							if(!isIn(nx1,ny1) || office[nx1][ny1] == 6) break;
						}
						//반대쪽 방향
						while(true) {
							nx2 -= dx2[i];
							ny2 -= dy2[i];
							if(isIn(nx2, ny2) && (office[nx2][ny2] == 0 || office[nx2][ny2] == -1)) {
								office[nx2][ny2] = -1;
								officeCCTVcnt[nx2][ny2] ++;
							}
							if(!isIn(nx2,ny2) || office[nx2][ny2] == 6) break;
						}
						
						boolean flag = false;
						for (int j = x; j < N; j++) {
							for (int h = 0; h < M; h++) {
								if(office[j][h] != 0 && office[j][h] != 6 && office[j][h] != -1) {
									if(j<=x && h<=y) continue;
									DFS(cnt+1,j,h);
									flag = true;
									break;
								}
							}
							if(flag) break;
						}
						if(!flag) DFS(cctvCnt+1,x,y);
						//한쪽방향 다시 채우기
						while(true) {
							nx1 -= dx2[i];
							ny1 -= dy2[i];
							if(isIn(nx1, ny1) && office[nx1][ny1] == -1) {
								officeCCTVcnt[nx1][ny1] --;
								if(officeCCTVcnt[nx1][ny1] == 0) office[nx1][ny1] = 0;
							}
							if(nx1 == x && ny1 == y) break;
						}
						//다른방향 다시 채우기
						while(true) {
							nx2 += dx2[i];
							ny2 += dy2[i];
							if(isIn(nx2, ny2) && office[nx2][ny2] == -1) {
								officeCCTVcnt[nx2][ny2] --;
								if(officeCCTVcnt[nx2][ny2] == 0) office[nx2][ny2] = 0;
							}
							if(nx2 == x && ny2 == y) break;
						}
					}
					break;
				//cctv3
				case 3:
					int[] dx3 = {-1, 0, 1, 0};
					int[] dy3 = {0, 1, 0, -1};
					for (int i = 0; i < 4; i++) {
						int nx1 = x;
						int ny1 = y;
						int nx2 = x;
						int ny2 = y;
						while(true) {
							nx1 += dx3[i];
							ny1 += dy3[i];
							if(isIn(nx1, ny1) && (office[nx1][ny1] == 0 || office[nx1][ny1] == -1)) {
								office[nx1][ny1] = -1;
								officeCCTVcnt[nx1][ny1] ++;
							}
							if(!isIn(nx1,ny1) || office[nx1][ny1] == 6) break;
						}
						while(true) {
							//i+1이 4라면 0으로 바꾸기
							nx2 += dx3[(i+1)==4? 0:i+1];
							ny2 += dy3[(i+1)==4? 0:i+1];
							if(isIn(nx2, ny2) && (office[nx2][ny2] == 0 || office[nx2][ny2] == -1)) {
								office[nx2][ny2] = -1;
								officeCCTVcnt[nx2][ny2] ++;
							}
							if(!isIn(nx2,ny2) || office[nx2][ny2] == 6) break;
						}
						boolean flag = false;
						for (int j = x; j < N; j++) {
							for (int h = 0; h < M; h++) {
								if(office[j][h] != 0 && office[j][h] != 6 && office[j][h] != -1) {
									if(j<=x && h<=y) continue;
									DFS(cnt+1,j,h);
									flag = true;
									break;
								}
							}
							if(flag) break;
						}
						if(!flag) DFS(cctvCnt+1,x,y);
						while(true) {
							nx1 -= dx3[i];
							ny1 -= dy3[i];
							if(isIn(nx1, ny1) && office[nx1][ny1] == -1) {
								officeCCTVcnt[nx1][ny1] --;
								if(officeCCTVcnt[nx1][ny1] == 0) office[nx1][ny1] = 0;
							}
							if(nx1 == x && ny1 == y) break;
						}
						
						while(true) {
							nx2 -= dx3[(i+1)==4? 0:i+1];
							ny2 -= dy3[(i+1)==4? 0:i+1];
							if(isIn(nx2, ny2) && office[nx2][ny2] == -1) {
								officeCCTVcnt[nx2][ny2] --;
								if(officeCCTVcnt[nx2][ny2] == 0) office[nx2][ny2] = 0;
							}
							if(nx2 == x && ny2 == y) break;
						}
					}
					break;
				//cctv4
				case 4:
					int[] dx4 = {-1, 0, 1, 0};
					int[] dy4 = {0, 1, 0, -1};
					for (int i = 0; i < 4; i++) {
						int nx1 = x;
						int ny1 = y;
						int nx2 = x;
						int ny2 = y;
						int nx3 = x;
						int ny3 = y;
						while(true) {
							nx1 += dx4[i];
							ny1 += dy4[i];
							if(isIn(nx1, ny1) && (office[nx1][ny1] == 0 || office[nx1][ny1] == -1)) {
								office[nx1][ny1] = -1;
								officeCCTVcnt[nx1][ny1] ++;
							}
							if(!isIn(nx1,ny1) || office[nx1][ny1] == 6) break;
						}
						while(true) {
							nx2 += dx4[(i+1)==4? 0:i+1];
							ny2 += dy4[(i+1)==4? 0:i+1];
							if(isIn(nx2, ny2) && (office[nx2][ny2] == 0 || office[nx2][ny2] == -1)) {
								office[nx2][ny2] = -1;
								officeCCTVcnt[nx2][ny2] ++;
							}
							if(!isIn(nx2,ny2) || office[nx2][ny2] == 6) break;
						}
						
						while(true) {
							//i+2가 4보다 커지면 나머지 값으로 인덱스 바꾸기
							nx3 += dx4[(i+2)>=4? (i+2)%4:i+2];
							ny3 += dy4[(i+2)>=4? (i+2)%4:i+2];
							if(isIn(nx3, ny3) && (office[nx3][ny3] == 0 || office[nx3][ny3] == -1)) {
								office[nx3][ny3] = -1;
								officeCCTVcnt[nx3][ny3] ++;
							}
							if(!isIn(nx3,ny3) || office[nx3][ny3] == 6) break;
						}
						boolean flag = false;
						
						for (int j = x; j < N; j++) {
							for (int h = 0; h < M; h++) {
								if(office[j][h] != 0 && office[j][h] != 6 && office[j][h] != -1) {
									if(j<=x && h<=y) continue;
									DFS(cnt+1,j,h);
									flag = true;
									break;
								}
							}
							if(flag) break;
						}
						if(!flag) DFS(cctvCnt+1,x,y);
						
						while(true) {
							nx1 -= dx4[i];
							ny1 -= dy4[i];
							if(isIn(nx1, ny1) && office[nx1][ny1] == -1) {
								officeCCTVcnt[nx1][ny1] --;
								if(officeCCTVcnt[nx1][ny1] == 0) office[nx1][ny1] = 0;
							}
							if(nx1 == x && ny1 == y) break;
						}
						
						while(true) {
							nx2 -= dx4[(i+1)==4? 0:i+1];
							ny2 -= dy4[(i+1)==4? 0:i+1];
							if(isIn(nx2, ny2) && office[nx2][ny2] == -1) {
								officeCCTVcnt[nx2][ny2] --;
								if(officeCCTVcnt[nx2][ny2] == 0) office[nx2][ny2] = 0;
							}
							if(nx2 == x && ny2 == y) break;
						}
						
						while(true) {
							nx3 -= dx4[(i+2)>=4? (i+2)%4:i+2];
							ny3 -= dy4[(i+2)>=4? (i+2)%4:i+2];
							if(isIn(nx3, ny3) && office[nx3][ny3] == -1) {
								officeCCTVcnt[nx3][ny3] --;
								if(officeCCTVcnt[nx3][ny3] == 0) office[nx3][ny3] = 0;
							}
							if(nx3 == x && ny3 == y) break;
						}
					}
					break;
				//cctv5(반복필요없이 십자로 체크)
				case 5:
					int[] dx5 = {-1, 0, 1, 0};
					int[] dy5 = {0, 1, 0, -1};
					int nx1 = x;
					int ny1 = y;
					int nx2 = x;
					int ny2 = y;
					int nx3 = x;
					int ny3 = y;
					int nx4 = x;
					int ny4 = y;
					while(true) {
						nx1 += dx5[0];
						ny1 += dy5[0];
						if(isIn(nx1, ny1) && (office[nx1][ny1] == 0 || office[nx1][ny1] == -1)) {
							office[nx1][ny1] = -1;
							officeCCTVcnt[nx1][ny1] ++;
						}
						if(!isIn(nx1,ny1) || office[nx1][ny1] == 6) break;
					}
					while(true) {
						nx2 += dx5[1];
						ny2 += dy5[1];
						if(isIn(nx2, ny2) && (office[nx2][ny2] == 0 || office[nx2][ny2] == -1)) {
							office[nx2][ny2] = -1;
							officeCCTVcnt[nx2][ny2] ++;
						}
						if(!isIn(nx2,ny2) || office[nx2][ny2] == 6) break;
					}
					while(true) {
						nx3 += dx5[2];
						ny3 += dy5[2];
						if(isIn(nx3, ny3) && (office[nx3][ny3] == 0 || office[nx3][ny3] == -1)) {
							office[nx3][ny3] = -1;
							officeCCTVcnt[nx3][ny3] ++;
						}
						if(!isIn(nx3,ny3) || office[nx3][ny3] == 6) break;
					}
					while(true) {
						nx4 += dx5[3];
						ny4 += dy5[3];
						if(isIn(nx4, ny4) && (office[nx4][ny4] == 0 || office[nx4][ny4] == -1)) {
							office[nx4][ny4] = -1;
							officeCCTVcnt[nx4][ny4] ++;
						}
						if(!isIn(nx4,ny4) || office[nx4][ny4] == 6) break;
					}
					boolean flag = false;
					for (int j = x; j < N; j++) {
						for (int h = 0; h < M; h++) {
							if(office[j][h] != 0 && office[j][h] != 6 && office[j][h] != -1) {
								if(j<=x && h<=y) continue;
								DFS(cnt+1,j,h);
								flag = true;
								break;
							}
						}
						if(flag) break;
					}
					if(!flag) DFS(cctvCnt+1,x,y);
					
					while(true) {
						nx1 -= dx5[0];
						ny1 -= dy5[0];
						if(isIn(nx1, ny1) && office[nx1][ny1] == -1) {
							officeCCTVcnt[nx1][ny1] --;
							if(officeCCTVcnt[nx1][ny1] == 0) office[nx1][ny1] = 0;
						}
						if(nx1 == x && ny1 == y) break;
					}
					
					while(true) {
						nx2 -= dx5[1];
						ny2 -= dy5[1];
						if(isIn(nx2, ny2) && office[nx2][ny2] == -1) {
							officeCCTVcnt[nx2][ny2] --;
							if(officeCCTVcnt[nx2][ny2] == 0) office[nx2][ny2] = 0;
						}
						if(nx2 == x && ny2 == y) break;
					}
					
					while(true) {
						nx3 -= dx5[2];
						ny3 -= dy5[2];
						if(isIn(nx3, ny3) && office[nx3][ny3] == -1) {
							officeCCTVcnt[nx3][ny3] --;
							if(officeCCTVcnt[nx3][ny3] == 0) office[nx3][ny3] = 0;
						}
						if(nx3 == x && ny3 == y) break;
					}
					
					while(true) {
						nx4 -= dx5[3];
						ny4 -= dy5[3];
						if(isIn(nx4, ny4) && office[nx4][ny4] == -1) {
							officeCCTVcnt[nx4][ny4] --;
							if(officeCCTVcnt[nx4][ny4] == 0) office[nx4][ny4] = 0;
						}
						if(nx4 == x && ny4 == y) break;
					}
					break;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		office = new int[N][M];
		officeCCTVcnt = new int[N][M];
		cctvCnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				//cctv 개수 카운팅
				if(office[i][j] != 0 && office[i][j] != 6) cctvCnt++; 
			}
		}
		//처음 cctv 찾아서 넣어주고 시작
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(office[i][j] != 0 && office[i][j] != 6) {
					DFS(1,i,j);
					flag = true;
					break;
				}
				if(flag) break;
			}
		}
		//cctv없는 경우를 위해 0개수 카운팅
		int tmp = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(office[i][j] == 0) tmp++; 
			}
		}
		//answer값이 갱신되지 않았다면 0개수로 출력. 갱신됐다면 answer값 출력
		System.out.println(answer == Integer.MAX_VALUE ? tmp:answer);
	}
}
