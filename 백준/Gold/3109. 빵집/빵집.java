import java.io.*;
import java.util.*;

public class Main {
	private static int R,C, answer;
	//가능한 파이프 방향
	private static int[] dx = {-1, 0, 1};
	private static String[][] board;
	//방문배열
	private static boolean [][] visited;
	//탐색 여부 
	private static boolean flag;
	private static void DFS(int x, int y) {
		//끝 좌표 까지 y가 왔다면
		if(y == C-1) {
			answer++;
			//탐색 여부 참으로 변경
			flag = true;
			visited[x][y] = true;
			return;
		} else {			
			for (int j = 0; j < 3; j++) {
				//내부면서 안간곳인지 체크
				if(x+dx[j] >= 0 && x+dx[j]<R && y+1 < C && board[x+dx[j]][y+1].equals(".") && !visited[x+dx[j]][y+1]) {
					DFS(x+dx[j], y+1);
					visited[x][y] = true;
					if(flag) return;
				}
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new String[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				board[i][j] = tmp[j]; 
			}
		}
		for (int i = 0; i < R; i++) {		
			flag = false;
			DFS(i, 0);
		}
		System.out.println(answer);
	}
}