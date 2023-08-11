import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static String[][] board = new String[5][5];
	
	public static boolean check() {
		int cnt = 0;
		int tmp = 0;
		
		//대각선체크1
		for (int i = 0; i < 5; i++) {
			if(board[i][i].equals("0")) tmp++;
		}
		if(tmp == 5) cnt++;

		//대각선체크2
		tmp = 0;
		for (int i = 0; i < 5; i++) {
			if(board[i][4-i].equals("0")) tmp++;
		}
		if(tmp == 5) cnt++;

		//가로 체크
		for (int i = 0; i < 5; i++) {
			tmp = 0;
			for (int j = 0; j < 5; j++) {
				if(board[i][j].equals("0")) tmp++;
			}
			if(tmp==5) cnt++;
		}
		if(cnt >= 3) return true;
		
		//세로 체크
		for (int i = 0; i < 5; i++) {
			tmp = 0;
			for (int j = 0; j < 5; j++) {
				if(board[j][i].equals("0")) tmp++;
			}
			if(tmp==5) cnt++;
		}
		if(cnt >= 3) return true;
		return false;
	}
	
	//숫자 찾기
	public static void find(String num) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(board[i][j].equals(num)) {
					board[i][j] = "0";
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int answer = 0;
		boolean flag = false;
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				board[i][j] = st.nextToken();
			}
		}
		
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				//사회자가 부른것 0으로 바꾸기
				find(st.nextToken());
				//횟수 증가
				answer++;
				//최소횟수 12보다 크고 빙고가 3개인지 체크
				if(answer >= 12 && check()) {
					flag = true;
					break;
				}
			}
			//찾았으면 break
			if(flag) break;
		}
		System.out.println(answer);
	}
}