import java.io.*;
import java.util.*;

public class Solution {
	public static int[] arr = new int[9];
	public static int[] check;
	public static int score1, score2, answer1, answer2;
    public static void DFS(int L) {
    	//9번째 라운드인지체크
    	if(L==9) {
    		//더 점수가 높은쪽이 이김, 이긴횟수 카운팅
    		if(score1>score2) answer1++;
    		else if(score1<score2) answer2++;
        	return;
        } else {
        	for (int i = 1; i <= 18; i++) {
        		//안쓴 카드인지 검사
				if(check[i] == 0) {
					//더 크면 규영이 점수에 합산
					if(arr[L] > i) {
						score1 += arr[L]+i;
					//더 작으면 인영이 점수에 합산
					} else if(arr[L] < i) {
						score2 += arr[L]+i;
					}
					//사용했음을 체크
					check[i] = 1;
					
					DFS(L+1);
					//사용안했을경우
					if(arr[L] > i) {
						score1 -= arr[L]+i;
					} else if(arr[L] < i) {
						score2 -= arr[L]+i;
					}
					check[i] = 0;
				}
			}
		}
    }

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			//초기화
			answer1 = 0;
			answer2 = 0;
			score1 = 0;
			score2 = 0;
			check = new int[19];
			for (int j = 0; j < 9; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < 9; j++) {
				check[arr[j]] = 1;
			}
			DFS(0);
			sb.append(answer1).append(" ").append(answer2).append("\n");
		}
		System.out.println(sb);
	}
}