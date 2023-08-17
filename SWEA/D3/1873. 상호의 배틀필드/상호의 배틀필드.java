import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//테스트케이스개수
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			//기본적인 출력폼 StringBuilder에 추가
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			//문제 기본조건 입력받기
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			String[][] board = new String[H][W];
			//현재 x,y좌표
			int curX = 0;
			int curY = 0;
			//전장 입력. 현재탱크위치 입력
			for (int j = 0; j < H; j++) {
				String[] tmp = br.readLine().split("");
				for (int k = 0; k < W; k++) {
					if(tmp[k].equals("<") || tmp[k].equals("^") || tmp[k].equals("v") || tmp[k].equals(">")) {
						curX = j;
						curY = k;
					}
					board[j][k] = tmp[k];
				}
			}
			int N = Integer.parseInt(br.readLine());
			//작동명령
			String[] orders = new String[N];
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < orders.length; j++) {
				orders[j] = tmp[j];
			}
			//명령길이만큼 반복
			for (String order : orders) {
				switch(order) {
					case "U":
						//내부면서 다음갈곳이 평지라면
						if(curX-1>=0 && board[curX-1][curY].equals(".")) {
							//있던위치 평지로 변경
							board[curX][curY] = ".";
							//탱크 이동
							curX -= 1;
						}
						//탱크 방향 변경
						board[curX][curY] = "^";
						break;
					case "D":
						if(curX+1<H && board[curX+1][curY].equals(".")) {
							board[curX][curY] = ".";
							curX += 1;
						}
						board[curX][curY] = "v";
						break;
					case "L":
						if(curY-1>=0 && board[curX][curY-1].equals(".")) {
							board[curX][curY] = ".";
							curY -= 1;
						}
						board[curX][curY] = "<";
						break;
					case "R":
						if(curY+1<W && board[curX][curY+1].equals(".")) {
							board[curX][curY] = ".";
							curY += 1;
						}
						board[curX][curY] = ">";
						break;
					case "S":
						//탱크머리방향 확인
						switch(board[curX][curY]) {
							case "^":
								for (int j = curX-1; j >= 0; j--) {
									//바라보는 방향 가장먼저 나오는 벽이 벽돌벽이면 부수고 break, 철벽이면 그냥 break;
									if(board[j][curY].equals("*")) {
										board[j][curY] = ".";
										break;
									}
									if(board[j][curY].equals("#")) {
										break;
									}
								}
								break;
							case "v":
								for (int j = curX+1; j < H; j++) {
									if(board[j][curY].equals("*")) {
										board[j][curY] = ".";
										break;
									}
									if(board[j][curY].equals("#")) {
										break;
									}
								}
								break;
							case "<":
								for (int j = curY-1; j >= 0 ; j--) {
									if(board[curX][j].equals("*")) {
										board[curX][j] = ".";
										break;
									}
									if(board[curX][j].equals("#")) {
										break;
									}
								}
								break;
							case ">":
								for (int j = curY+1; j < W; j++) {
									if(board[curX][j].equals("*")) {
										board[curX][j] = ".";
										break;
									}
									if(board[curX][j].equals("#")) {
										break;
									}
								}
								break;
						}
						break;
				}
			}
			//출력 추가
			for (int j = 0; j < H; j++) {
				for (int k = 0; k < W; k++) {
					sb.append(board[j][k]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
    }
}