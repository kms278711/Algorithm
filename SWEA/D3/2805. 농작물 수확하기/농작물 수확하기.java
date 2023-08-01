import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	public static int solution(int[][] board) {
		int answer = 0;
		for (int i = 0; i < board.length/2+1; i++) {
			for (int j = board.length/2-i; j <= board.length/2+i; j++) {
				answer += board[i][j];
			}
		}
		for (int i = board.length/2-1; i >= 0 ; i--) {
			for (int j = board.length/2-i; j <= board.length/2+i; j++) {
				answer += board[board.length-i-1][j];
			}
		}
		return answer;
	}
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			int n = Integer.parseInt(br.readLine());
			sb.append("#").append(i).append(" ");
			int[][] board = new int[n][n];
			for (int j = 0; j < n; j++) {
				String[] arr = br.readLine().split("");
				for (int k = 0; k < n; k++) {
					board[j][k] = Integer.parseInt(arr[k]);			
				}
			}
			sb.append(solution(board)).append("\n");
		}
		System.out.println(sb);
	}
}