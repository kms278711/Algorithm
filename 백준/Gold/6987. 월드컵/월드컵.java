import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class match {
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
	private static List<match> matches;
	private static int[][] score;
	private static boolean isAvail, isCorrect;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringBuilder sb = new StringBuilder();
		matches = new ArrayList<match>();
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
					gameCnt += tmp;
					score[j][k] = tmp;
				}
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
		if(cnt == 15) {
			isAvail = true;
			return;
		} else {
			if(score[matches.get(cnt).first][0] > 0 && score[matches.get(cnt).second][2] > 0) {
				score[matches.get(cnt).first][0]--;
				score[matches.get(cnt).second][2]--;
				check(cnt+1);
				score[matches.get(cnt).first][0]++;
				score[matches.get(cnt).second][2]++;
			}
			
			if(score[matches.get(cnt).first][1] > 0 && score[matches.get(cnt).second][1] > 0) {
				score[matches.get(cnt).first][1]--;
				score[matches.get(cnt).second][1]--;
				check(cnt+1);
				score[matches.get(cnt).first][1]++;
				score[matches.get(cnt).second][1]++;
			}
			
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