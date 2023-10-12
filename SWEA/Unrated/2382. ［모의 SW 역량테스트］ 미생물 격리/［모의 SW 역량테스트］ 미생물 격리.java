import java.util.*;
import java.io.*;

public class Solution {
	//배열길이, 시간, 미생물 수
	static int N,M,K;
	//상 하 좌 우;
	static int[] dx = {0,-1,1,0,0};
	static int[] dy = {0,0,0,-1,1};
	//미생물 배열(맵)
	static Microbe[][] map;
	
	//미생물 클래스
	//미생물 수, 이동방향
	static class Microbe {
		int size, dirIdx;

		public Microbe(int size, int dirIdx) {
			super();
			this.size = size;
			this.dirIdx = dirIdx;
		}

		@Override
		public String toString() {
			return size + "";
		}
	}
	
	//약품
	private static void heal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				//위치가 가장자리에 있다면
				if(i==0 || j==0 || i==N-1 || j==N-1) {
					if(map[i][j] != null) {
						Microbe cur = map[i][j];
						//사이즈 1이면 제거
						if(cur.size == 1) {
							map[i][j] = null;
						} else {
							//방향 전환
							//상하 변환
							if(cur.dirIdx == 1 || cur.dirIdx == 2) cur.dirIdx = 3-cur.dirIdx;
							//좌우 변환
							else cur.dirIdx = 7-cur.dirIdx;
							//절반죽이기
							cur.size = cur.size/2;
						}
					}
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
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new Microbe[N][N];
			
			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dirIdx = Integer.parseInt(st.nextToken());
				map[x][y] = new Microbe(size, dirIdx); 
			}
			
			for (int j = 0; j < M; j++) {
				//다음 해당위치에 오게될 미생물 리스트
				List<Microbe>[][] listMap = new ArrayList[N][N];
				
				//초기화
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < N; l++) {
						listMap[k][l] = new ArrayList<>();
					}
				}
				
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < N; l++) {
						if(map[k][l]!=null) {
							Microbe cur = map[k][l];
							//해당자리 배열에 추가
							listMap[k+dx[cur.dirIdx]][l+dy[cur.dirIdx]].add(cur);
						}
					}
				}
				
					
				for (int k = 0; k < N; k++) {
					for (int l = 0; l < N; l++) {
						//0이면 null로 넣기
						if(listMap[k][l].size() == 0) {
							map[k][l] = null;
						//1이면 list에 0번째 값을 넣기
						} else if(listMap[k][l].size() == 1) {
							map[k][l] = listMap[k][l].get(0);
						//2개 이상이면
						} else {
							//개수 내림차순으로 정렬
							Collections.sort(listMap[k][l], (o1,o2)-> o2.size-o1.size);
							int size = 0;
							//미생물 합 구하기
							for (Microbe mic : listMap[k][l]) {
								size += mic.size;
							}
							//제일 미생물 많은 애의 방향 가져오기
							int dir = listMap[k][l].get(0).dirIdx;
							Microbe tmp = new Microbe(size, dir);
							//맵에 반영
							map[k][l] = tmp;
						}
					}
				}
				
				//약품 처리
				heal();
			}
			
			int answer = 0;
			for (int k = 0; k < N; k++) {
				for (int l = 0; l < N; l++) {
					if(map[k][l] != null) answer += map[k][l].size; 
				}
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
