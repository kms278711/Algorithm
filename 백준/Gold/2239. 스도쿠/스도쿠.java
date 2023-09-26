import java.io.*;

public class Main {
	private static int[][] board, box;
	private static boolean answer;
	private static boolean[][][] check;
	private static void sudoku(int cnt) {
		if(answer) return;
		if(cnt == 0) {
			answer = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			return;
		} else {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if(board[i][j] == 0) {
						for (int k = 1; k <= 9; k++) {
							if(!check[0][i][k] && !check[1][j][k] && !check[2][box[i][j]][k]) {
								check[0][i][k] = true;
								check[1][j][k] = true;
								check[2][box[i][j]][k] = true;
								board[i][j] = k;
								sudoku(cnt-1);
								check[0][i][k] = false;
								check[1][j][k] = false;
								check[2][box[i][j]][k] = false;
								board[i][j] = 0;
							}
						}
						if(board[i][j] == 0) return;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		board = new int[9][9];
		box = new int[][] {
				{0,0,0,1,1,1,2,2,2},
				{0,0,0,1,1,1,2,2,2},
				{0,0,0,1,1,1,2,2,2},
				{3,3,3,4,4,4,5,5,5},
				{3,3,3,4,4,4,5,5,5},
				{3,3,3,4,4,4,5,5,5},
				{6,6,6,7,7,7,8,8,8},
				{6,6,6,7,7,7,8,8,8},
				{6,6,6,7,7,7,8,8,8}
		};
		
		
		//0:가로, 1:세로, 2:사각형
		//방향, 몇번째, 사용숫자
		check = new boolean[3][9][10];
		
		for (int i = 0; i < 9; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(tmp[j]);
				if(board[i][j] != 0) {
					check[0][i][board[i][j]] = true;
					check[1][j][board[i][j]] = true;
					check[2][box[i][j]][board[i][j]] = true;
				} else {
					cnt++;
				}
			}
		}
		sudoku(cnt);
	}
}
