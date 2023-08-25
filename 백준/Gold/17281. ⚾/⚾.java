import java.io.*;
import java.util.*;

public class Main {	
	//이닝수, 아웃카운트
	private static int N, score, answer;
	private static boolean[] used;
	private static int[] seq;
	private static int[][] inning;
	private static boolean[] base;
	private static int gameStart() {
		//초기화
		score = 0;
		int outCnt = 0;
		int idx = 0;
		//타자석, 1루, 2루, 3루 사람 여부
		base = new boolean[4];
		
		//이닝수만큼 반복
		for (int i = 0; i < N; i++) {
			//아웃카운트 3될때까지
			while(outCnt != 3) {
				//해당 이닝에 idx번째 선수의 행동
				switch(inning[i][seq[idx]]) {
					//아웃
					case 0:
						outCnt++;
						break;
					//안타
					case 1:
						base[0] = true;
						move(1);
						break;
					//2루타
					case 2:
						base[0] = true;	
						move(2);
						break;
					//3루타
					case 3:
						base[0] = true;
						move(3);
						break;
					//4루타
					case 4:
						base[0] = true;
						move(4);
						break;
				}
				//끝까지 왔으면 다시 0번 타자부터
				if(++idx == 9) idx = 0;
			}
			//새이닝을 위해 초기화
			outCnt = 0;
			base = new boolean[4];
		}
		return score;
	}
	
	//cnt만큼 진루
	private static void move(int cnt) {
		for (int i = 0; i < cnt; i++) {
			//3루에 사람 있으면 score증가
			if(base[3]) score++;
			base[3] = base[2];
			base[2] = base[1];
			base[1] = base[0];
			base[0] = false;
		}
	}
	
	private static void setting(int cnt) {
		//9명 다 뽑았으면
		if(cnt == 9) {
			//해당 순번으로 게임시작
			gameStart();
			//정답 갱신
			answer = Math.max(answer, score);
		} else {
			//4번타자(cnt=3) 스킵
			if(cnt == 3) setting(cnt+1);
			else {
				for (int i = 1; i < 9; i++) {
					if(!used[i]) {
						//이번 순서에 해당 선수 사용
						used[i] = true;
						seq[cnt] = i;
						setting(cnt+1);
						//이번 순서에 해당 선수 사용x
						used[i] = false;
					}
				}	
			}
		}
	}
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		//이닝당 선수들 행동
		inning = new int[N][9];
		//선수 사용여부
		used  = new boolean[9];
		//타자순서
		seq = new int[9];
		//0번째사용, 1번타자를 0으로 보기때문에 seq에 따로 넣지않음.
		used[0] = true;
		//이닝당 선수들 행동 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		setting(0);
		System.out.println(answer);
	}
}
