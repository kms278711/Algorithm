import java.io.*;
import java.util.*;

//재료 클래스
class Ingredient{
	public int score, cal;
	public Ingredient(int score, int cal) {
		this.score = score;
		this.cal = cal;
	}
}
public class Solution {
	public static int N,L,answer;
	public static Ingredient[] arr;
    public static void DFS(int cnt, int score, int cal) {
    	//칼로리 넘치면 return
        if(cal>L) return;
    	if(cnt==N) {
    		//더 높은 점수로 갱신
        	answer = Math.max(answer, score);
        	return;
        } else {
    		//재료 사용하는 경우
    		DFS(cnt+1, score+arr[cnt].score, cal + arr[cnt].cal);
    		//재료 사용하지 않는 경우
    		DFS(cnt+1, score, cal);
		}
    }

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			answer = 0;
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			arr = new Ingredient[N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				int scoreTmp = Integer.parseInt(st.nextToken());
				int calTmp = Integer.parseInt(st.nextToken());
				arr[j] = new Ingredient(scoreTmp, calTmp);
			}
			DFS(0,0,0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}