import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class match {
	//첫번째팀, 두번째팀
	int first, second;
	match(int first, int second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public String toString() {
		return this.first + " " + this.second;
	}
}
public class Main {
	//경기 경우의수 리스트
	private static List<match> matches;
	// [팀][승 무 패]
	private static int[][] score;
	// 유효한 경기맞나? / 5경기 맞나?
	private static boolean isAvail, isCorrect;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		matches = new ArrayList<match>();
		//경기 경우의 수 채우기
		for (int i = 0; i < 5; i++) {
			for (int j = i+1; j < 6; j++) {
				matches.add(new match(i,j));
			}
		}
		
		for (int i = 0; i < 4; i++) {
			isAvail = false;
			isCorrect = true;
			score = new int[6][3];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				int gameCnt = 0;
				for (int k = 0; k < 3; k++) {
					int tmp = Integer.parseInt(st.nextToken());
					//경기 수 카운트
					gameCnt += tmp;
					score[j][k] = tmp;
				}
				//경기 수 5개 아니면 false
				if(gameCnt != 5) {
					isCorrect = false;
					break;
				}
			}
			
			if(!isCorrect) {
				sb.append(0).append(" ");
				continue;
			}
			check(0);
			if(isAvail) sb.append(1);
			else sb.append(0);
			sb.append(" ");
		}
		System.out.println(sb);
		
	}
	
	private static void check(int cnt) {
		//경기 수 15개면 true로 변경
		if(cnt == 15) {
			isAvail = true;
			return;
		} else {
			//first 이긴횟수 > 0 && second 진횟수 > 0
			if(score[matches.get(cnt).first][0] > 0 && score[matches.get(cnt).second][2] > 0) {
				score[matches.get(cnt).first][0]--;
				score[matches.get(cnt).second][2]--;
				//경기수 추가
				check(cnt+1);
				//원상복귀
				score[matches.get(cnt).first][0]++;
				score[matches.get(cnt).second][2]++;
			}
			//first 비긴횟수 > 0 && second 비긴횟수 > 0
			if(score[matches.get(cnt).first][1] > 0 && score[matches.get(cnt).second][1] > 0) {
				score[matches.get(cnt).first][1]--;
				score[matches.get(cnt).second][1]--;
				check(cnt+1);
				score[matches.get(cnt).first][1]++;
				score[matches.get(cnt).second][1]++;
			}
			//first 진횟수 > 0 && second 이긴횟수 > 0
			if(score[matches.get(cnt).first][2] > 0 && score[matches.get(cnt).second][0] > 0) {
				score[matches.get(cnt).first][2]--;
				score[matches.get(cnt).second][0]--;
				check(cnt+1);
				score[matches.get(cnt).first][2]++;
				score[matches.get(cnt).second][0]++;
			}
		}
	}
}