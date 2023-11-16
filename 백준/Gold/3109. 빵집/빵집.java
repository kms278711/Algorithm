import java.util.*;
import java.io.*;

public class Main {	
	//가로, 세로, 정답(최대 연결된 파이프라인 수)
	private static int R,C, answer;
	//맵
	private static String[][] map;
	//방문배열
	private static boolean[][] visited;
	//x이동방향(우상, 우, 우하)
	//y는 1씩 증가 고정
	private static int[] dx = {-1,0,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		answer = 0;
		map = new String[R][C];
		visited = new boolean[R][C];
		for (int x = 0; x < R; x++) {
			String[] tmp = br.readLine().split("");
			for (int y = 0; y < C; y++) {
				map[x][y] = tmp[y];
			}
		}
		
		for (int x = 0; x < R; x++) {
			//방문 표시
			visited[x][0] = true;
			find(x, 0);
		}
		
		System.out.println(answer);
	}
	
	//파이프를 세방향으로 붙여보면서 끝까지 가는지 확인
	//이미 갔던 곳은 갈필요가 없음. 어짜피 안되는 곳임. 방문배열 변경 필요없음
	private static boolean find(int x, int y) {
		//끝까지 연결되면 정답 개수 추가하고 true 리턴
		if(y == C-1) {
			answer ++;
			return true;
		}
		
		for (int dir = 0; dir < 3; dir++) {
			int nx = x + dx[dir];
			int ny = y + 1;
			//밖으로 나갈경우 다음 방향탐색
			if(!isIn(nx, ny)) continue;
			//방문한 곳이라면 다음 방향탐색
			if(visited[nx][ny]) continue;
			//벽일 경우 다음 방향탐색
			if(map[nx][ny].equals("x")) continue;
			//방문표시
			visited[nx][ny] = true;
			//끝까지 연결되면 true 리턴
			if(find(nx,ny)) {
				return true;
			}
		}
		return false;
	}

	//내부인지 확인
	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<R && y<C;
	}
}
