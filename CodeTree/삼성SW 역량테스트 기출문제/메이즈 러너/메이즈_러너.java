import java.util.*;
import java.io.*;

public class 메이즈_러너 {
	//배열 길이, 제한시간, 움직인 횟수
	private static int N, M, K, moveSum;
	//출구 좌표
	private static int[] exit;
	//맵
	private static int[][] map;
	//남은 사람 리스트
	private static List<int[]> people;
	//사방탐색(상하좌우)
	private static int[] dx = {0,-1,1,0,0};
	private static int[] dy = {0,0,0,-1,1};

	//내부인지 체크
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
		
	//1초 움직이기
	private static void move() {
		//remove를 위해 역순으로 진행
		for (int i = people.size()-1; i >= 0; i--) {
			//현재 i번째 사람의 x,y좌표
			int curX = people.get(i)[0];
			int curY = people.get(i)[1];
			int minVal = Integer.MAX_VALUE;
			//값이 최소인 방향 리스트
			List<Integer> idxList = new ArrayList<>();
			for (int j = 0; j < 5; j++) {
				//다음 좌표
				int nx = curX + dx[j];
				int ny = curY + dy[j];
				//내부인지 검사
				if(isIn(nx, ny)) {
					//거리
					int dis = Math.abs(exit[0] - nx) + Math.abs(exit[1] - ny); 
					if(minVal > dis) {
						//거리갱신되면 인덱스 리스트 초기화
						idxList.clear();
						idxList.add(j);
						minVal = dis;
					//같은 경우 인덱스 넣어주기
					} else if(minVal == dis) {
						idxList.add(j);
					}
				}
			}
			
			//인덱스 리스트 값들 중 우선순위가 높고 갈 수 있는 인덱스 찾기
			for (int j = 0; j < idxList.size(); j++) {
				int nx = curX + dx[idxList.get(j)];
				int ny = curY + dy[idxList.get(j)];
				if(map[nx][ny] == 0) {
					//출구면 해당사람 remove
					if(nx == exit[0] && ny == exit[1]) {
						people.remove(i);
						moveSum++;
					} else {
						//위치 갱신
						people.set(i, new int[] {nx, ny});
						moveSum++;
					}
					break;
				}
			}
		}
	}
	
	//영역 회전
	private static void rotate() {
		//정사각형 크기
		int size = Integer.MAX_VALUE;
		//최소 크기 구하기
		for (int[] point : people) {
			//x좌표 차이와 y좌표 차이중 큰 값을 size로
			int sizeTmp = Math.max(Math.abs(point[0]-exit[0]), Math.abs(point[1]-exit[1]));
			//더 작은 사이즈가 가능하다면 갱신
			size = Math.min(sizeTmp+1, size);
		}
		int xStart = 0;
		int yStart = 0;
		while(true) {
			//y범위가 벗어나는 경우
			if(!isIn(0, yStart+size-1)) {
				xStart += 1;
				yStart = 0;
			}
			//내부에 출구와 참가자가 있으면 사각형 범위 탐색 종료
			if(isInclude(xStart, yStart, size)) {
				break;
			}
			yStart++;
		}
	}

	//내부에 출구와 참가자가 있는지 확인
	private static boolean isInclude(int xStart, int yStart, int size) {
		boolean flag = false;
		//출구가 있다면
		if(exit[0] >= xStart && exit[0] < xStart+size && exit[1] >= yStart && exit[1] < yStart+size) {
			for (int i = 0; i < people.size(); i++) {
				int[] person = people.get(i);
				//참가자가 있다면
				if(person[0] >= xStart && person[0] < xStart+size && person[1] >= yStart && person[1] < yStart+size) {
					//있다고 표시
					flag = true;
					break;
				}
			}
		}
		
		//출구와 참가자 모두 있다면
		if(flag) {
			//복사된 맵
			int[][] mapTmp = new int[N][N];
			//방문배열
			boolean[] visited = new boolean[people.size()];
			//출구 움직였는지 여부
			boolean exitMv = false;
			//맵 복사
			for (int i = 0; i < N; i++) {
				mapTmp[i] = Arrays.copyOf(map[i], N);
			}
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					//90도 회전
					//왼쪽 아래부터 오른쪽 위까지 탐색해서 값 바꿔주기, 0보다 크면 -1해서 넣기
					map[xStart + i][yStart + j] = (mapTmp[xStart+size-j-1][yStart+i]==0 ? mapTmp[xStart+size-j-1][yStart+i] : mapTmp[xStart+size-j-1][yStart+i]-1);
					for (int k = 0; k < people.size(); k++) {
						int[] person = people.get(k);
						//아직 움직이지 않은 사람이면서 해당 좌표에 있는 사람이면 회전
						if(!visited[k] && person[0] == xStart+size-j-1 && person[1] == yStart+i) {
							//해당 사람 회전했다고 표시
							visited[k] = true;
							people.set(k, new int[] {xStart+i, yStart+j});
						}
					}
					//출구가 움직이지 않았고 해당 좌표에 출구가 있다면 회전
					if(!exitMv && exit[0] == xStart+size-j-1 && exit[1] == yStart+i) {
						//회전했다고 표시
						exitMv = true;
						exit[0] = xStart+i;
						exit[1] = yStart+j;
					}
				}
			}
		}
		return flag;
	}
	
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		//출구좌표 
		exit = new int[2];
		people = new ArrayList<>();
		
		//맵 초기화
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//사람 좌표 초기화
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			people.add(new int[] {x, y});
		} 
		
		//출구 좌표 초기화
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken())-1;
		int y = Integer.parseInt(st.nextToken())-1;
		exit[0] = x;
		exit[1] = y;
		
		//총 소요시간
		int time = 0;
		
		//K초 전까지만
		while(time<K) {
			//시간 진행
			time ++;
			//사람들 움직이기
			move();
			//남은 사람없으면 종료
			if(people.size() == 0) {
				break;
			}
			//회전
			rotate();
		}
		System.out.println(moveSum);
		System.out.println((exit[0]+1) + " " + (exit[1]+1));
	}
	
}