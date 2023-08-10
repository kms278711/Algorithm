import java.util.*;
import java.io.*;
class Point {
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}


public class Main {
	public static void DFS(int L, String cur) {
		if(L == K) {
			int tmp = 0;
			for (int i = 0; i < questCheck.length; i++) {
				if(questCheck[i] == 1) tmp++;
			}
			if(tmp==K) 
				quest.add(cur);
			return;
		} else {
			for (int i = 0; i < K; i++) {
				if(questCheck[i] == 0) {
					questCheck[i] = 1;
					DFS(L+1, cur+i);
					questCheck[i] = 0;
					DFS(L+1, cur);
				}
			}
		}
	}
	public static int[][] arr, arrTmp;
	public static int[] check, startX, startY, endX, endY;
	public static int[] questCheck;
	public static List<String> quest = new ArrayList<String>();
	public static int N,M,K, answer = Integer.MAX_VALUE;	
	public static void move(Point start, Point end) {
		//임시적으로 들어온 크기만큼의 작은 배열 생성
		int[][] tmp = new int[end.x-start.x+1][end.y-start.y+1];
		for (int i = 0; i < end.x-start.x+1; i++) {
			for (int j = 0; j < end.y-start.y+1; j++) {
				tmp[i][j] = arrTmp[start.x+i][start.y+j];
			}
		}
		
		//쓰이는 x, y범위 배열
		int[] dx = {0, end.x-start.x};
		int[] dy = {0, end.y-start.y};
		//돌릴수있을때까지만
		while(dx[1]-dx[0] >= 1 && dy[1]-dy[0] >= 1) {
			//회전
			for (int i = dy[1]; i > dy[0]; i--) {
				tmp[dx[1]][i-1] = arrTmp[start.x + dx[1]][start.y + i];
			}
			for (int i = dx[1]; i > dx[0]; i--) {
				tmp[i-1][dy[0]] = arrTmp[start.x + i][start.y + dy[0]];
			}
			for (int i = dy[0]; i < dy[1]; i++) {
				tmp[dx[0]][i+1] = arrTmp[start.x + dx[0]][start.y + i];
			}
			for (int i = dx[0]; i < dx[1]; i++) {
				tmp[i+1][dy[1]] = arrTmp[start.x + i][start.y + dy[1]];
			}
			//범위 좁히기
			dx[0] += 1;
			dx[1] -= 1;
			dy[0] += 1;
			dy[1] -= 1;
		}
	
		//원본배열에 덮어쓰기
		for (int i = 0; i < end.x-start.x+1; i++) {
			for (int j = 0; j < end.y-start.y+1; j++) {
				arrTmp[start.x+i][start.y+j] = tmp[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		arrTmp = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arrTmp[i][j] = arr[i][j];
			}
		}
		startX = new int[K];
		startY = new int[K];
		endX = new int[K];
		endY = new int[K];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			startX[i] = r-s;
			startY[i] = c-s;
			endX[i] = r+s;
			endY[i] = c+s;
		}
		questCheck = new int[K];
		DFS(0, "");
		for (String string : quest) {
			String[] tmp = string.split("");
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					arrTmp[j][k] = arr[j][k];
				}
			}
			for (int i = 0; i < K; i++) {
				move(new Point(startX[Integer.parseInt(tmp[i])], startY[Integer.parseInt(tmp[i])]), new Point(endX[Integer.parseInt(tmp[i])], endY[Integer.parseInt(tmp[i])]));
			}
			int answerTmp = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int k = 0; k < M; k++) {
					sum += arrTmp[j][k];
				}
				answerTmp = Math.min(sum, answerTmp);
			}
			answer = Math.min(answer, answerTmp);
		}
		System.out.println(answer);
	}
}