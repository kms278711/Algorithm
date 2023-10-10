import java.util.*;
import java.io.*;

public class Solution {
	//톱니바퀴 상태
	static int[][] arr;
	//톱니바퀴 방문배열
	static boolean[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		arr = new int[4][8];
		for(int i=1; i <= T; i++) {
			int answer = 0;
			sb.append("#" + i + " ");
			int K = Integer.parseInt(br.readLine());
			for (int j = 0; j < 4; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 8; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int j = 0; j < K; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				//방문배열 초기화
				visited = new boolean[4];
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				//1번 -> 0번 톱니바퀴
				//주어진 톱니바퀴로 시작
				rotate(num-1, dir);
			}
			
			//점수계산
			if(arr[0][0] == 1) answer += 1;
			if(arr[1][0] == 1) answer += 2;
			if(arr[2][0] == 1) answer += 4;
			if(arr[3][0] == 1) answer += 8;
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	//회전
	private static void rotate(int i, int dir) {
		//해당 톱니 돌렸다고 표시
		visited[i] = true;
		//왼쪽 끝이 아니라면
		if(i!=0) {
			//현재 톱니에서 왼쪽 방문안했고 극이 다르다면
			if(!visited[i-1] && arr[i-1][2] != arr[i][6]) {
				//해당 톱니 현재방향의 반대방향으로 회전
				rotate(i-1, dir*-1);
			}
		}
		
		//오른쪽 끝이 아니라면
		if(i!=3) {
			//현재 톱니에서 왼쪽 방문안했고 극이 다르다면
			if(!visited[i+1] && arr[i+1][6] != arr[i][2]) {
				//해당 톱니 현재방향의 반대방향으로 회전
				rotate(i+1, dir*-1);
			}
		}
		
		//시계방향
		if(dir == 1) {
			int[] tmp = Arrays.copyOf(arr[i], 8);
			arr[i][0] = tmp[7]; 
			for (int j = 0; j < 7; j++) {
				arr[i][j+1] = tmp[j];
			}
		//반시계방향
		} else if(dir == -1) {
			int[] tmp = Arrays.copyOf(arr[i], 8);
			arr[i][7] = tmp[0]; 
			for (int j = 0; j < 7; j++) {
				arr[i][j] = tmp[j+1];
			}
		}
	}
}
