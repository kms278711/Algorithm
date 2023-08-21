import java.io.*;
import java.util.*;

class Point{
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	//그리드
	private static String[][] arr;
	//배열길이
	private static int N;
	private static boolean[][] visited1, visited2;
	//사방탐색
	private static int[] dx = {-1, 0, 1, 0};
	private static int[] dy = {0, 1, 0, -1};
	//일반인
	private static void BFS1(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		//현재 좌표 넣기
		queue.offer(new Point(x, y));
		//들어온 현재 좌표 방문 표시
		visited1[x][y] = true;
		//현재 색깔 저장
		String color = arr[x][y];
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//사방탐색
			for (int i = 0; i < 4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				//다음 좌표가 밖으로 나가지 않으면서 색이 같고 방문하지 않았다면 큐에 추가 
				if(nx>=0 && ny>=0 && nx<N && ny<N && arr[nx][ny].equals(color) && !visited1[nx][ny]) {
					queue.offer(new Point(nx, ny));
					//방문표시
					visited1[nx][ny] = true;
				}
			}
		}
	}
	//적록색약
	private static void BFS2(int x, int y) {
		Queue<Point> queue = new ArrayDeque<>();
		//현재 좌표 넣기
		queue.offer(new Point(x, y));
		//들어온 현재 좌표 방문 표시
		visited2[x][y] = true;
		//현재 색깔 저장
		String color = arr[x][y];
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			//사방탐색
			for (int i = 0; i < 4; i++) {
				int nx = cur.x+dx[i];
				int ny = cur.y+dy[i];
				//다음 좌표가 밖으로 나가지 않으면서, 적색녹색일 경우 적색녹색인지 파랑색이면 파랑색인지 확인하고 방문하지 않았다면 큐에 추가 
				if(nx>=0 && ny>=0 && nx<N && ny<N && !visited2[nx][ny]) {
					if((color.equals("R") || color.equals("G")) ? (arr[nx][ny].equals("R") || arr[nx][ny].equals("G")):arr[nx][ny].equals(color)) {
						queue.offer(new Point(nx, ny));
						//방문표시
						visited2[nx][ny] = true;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//입력 및 초기화
		N = Integer.parseInt(br.readLine());
		arr = new String[N][N];
		visited1 = new boolean[N][N];
		visited2 = new boolean[N][N];
		int answer1 = 0;
		int answer2 = 0;
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				arr[i][j] = tmp[j];
			}
		}
		
		//일반인 BFS
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited1[i][j]) {
					answer1++;
					BFS1(i,j);
				}
			}
		}
		//적록색맹 BFS
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited2[i][j]) {
					answer2++;
					BFS2(i,j);
				}
			}
		}
		//출력
		System.out.println(answer1 + " " + answer2);
	}
}