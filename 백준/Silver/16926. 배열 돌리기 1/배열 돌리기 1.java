import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); 
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < R; i++) {
			int[][] tmp = new int[N][M];
			int[] dx = new int[2];
			int[] dy = new int[2];
			dx[1] = N-1;
			dy[1] = M-1;
			while(dx[1]-dx[0] >= 1 && dy[1]-dy[0] >= 1) {
				for (int j = dy[1]-1; j >= dy[0]; j--) {
					tmp[dx[0]][j] = arr[dx[0]][j+1];
				}
				for (int j = dx[0]+1; j <= dx[1]; j++) {
					tmp[j][dy[0]] = arr[j-1][dy[0]];
				}
				for (int j = dy[0]+1; j <= dy[1]; j++) {
					tmp[dx[1]][j] = arr[dx[1]][j-1];
				}
				for (int j = dx[1]-1; j >= dx[0]; j--) {
					tmp[j][dy[1]] = arr[j+1][dy[1]];
				}
				dx[0] += 1;
				dx[1] -= 1;
				dy[0] += 1;
				dy[1] -= 1;
			}	
			
			//복사
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					arr[j][k] = tmp[j][k];
				}
			}
		}
		
		//출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}