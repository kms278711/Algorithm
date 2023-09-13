import java.util.*;
import java.io.*;

public class Main {
	static int answer = Integer.MAX_VALUE;
	
	private static void dfs(int use, int cnt, int[][] arrTmp, int[] used) {
		//사용횟수가 이미 나온 정답보다 크면 리턴
		if(use>answer) return;
		//1이 모두 사라졌다면
		if(cnt == 0) {
			//정답 갱신
			answer = Math.min(answer, use);
		} else {
			//파라미터의 배열을 복사할 임시배열
			int[][] tmp = new int[10][10];
			int[] usedTmp = new int[6];
			//제일 큰 색종이의 사이즈
			int size = 5;
			int x = -1;
			int y = -1;
			//(0,0)부터 처음나오는 1의 위치 찾기
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if(arrTmp[i][j] == 1) {
						x = i;
						y = j;
						break;
					}
				}
				if(x!=-1 && y!=-1) break;
			}
			
			//색종이의 사이즈가 0이 될때까지 -> 5부터 1까지
			while(size != 0) {
				//해당 사이즈의 색종이가 남아있다면
				if(used[size] != 5 ) {
					//해당 위치에 해당 색종이를 사용할 수 있는지에 대한 여부
					boolean flag = true;
					//파라미터 배열 복사
					for (int i = 0; i < 10; i++) {
						tmp[i] = Arrays.copyOf(arrTmp[i], 10);
					}
					usedTmp = Arrays.copyOf(used, 6);
					//색종이가 들어갈 위치에 0이 있는지 확인
					for (int i = 0; i < size; i++) {
						for (int j = 0; j < size; j++) {
							//다음 좌표
							int nx = x+i;
							int ny = y+j;
							//내부인지 확인
							if(!isIn(nx, ny)) {
								flag = false;
								break;
							}
							//해당 좌표가 0이라면 색종이가 들어가는 것이 불가능하다고 표시하고 break
							if(arrTmp[nx][ny] == 0) {
								flag = false;
								break;
							//해당 좌표가 1이라면 색종이가 들어갔다고 표시 -> 0으로 바꿈
							} else {
								tmp[nx][ny] = 0;
							}
						}
						if(!flag) break;
					}
					
					//색종이가 들어갈 수 있다면
					if(flag) {
						//해당 색종이 카운트 증가
						usedTmp[size]++;
						//현재 상태로 사용횟수 추가하고, 없어진 1의 개수만큼 빼준뒤 재귀 진행
						dfs(use+1, cnt-size*size, tmp, usedTmp);
					}
				}
				//색종이 사이즈 1줄이기
				size--;
			}
		}
	}
	
	//내부인지 확인
	private static boolean isIn(int nx, int ny) {
		if(nx>=0 && ny>=0 && nx<10 && ny<10) return true;
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[10][10];
		int cnt = 0;
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); 
				//1 개수 카운팅
				if(arr[i][j] == 1) {
					cnt++;
				}
			}
		}
		int[] used = new int[6];
		dfs(0, cnt, arr,used);
		//답이 갱신이 안되었다면 -1 리턴
		System.out.println(answer == Integer.MAX_VALUE ? -1:answer);
	} 
}
