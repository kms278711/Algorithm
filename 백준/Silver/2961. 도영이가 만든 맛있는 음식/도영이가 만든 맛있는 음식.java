import java.util.*;
import java.io.*;

//재료 클래스
class ingredient {
	public int sour;
	public int bitter;
}

public class Main {
	//재료 개수
	public static int N;
	//정답
	public static long answer=Long.MAX_VALUE;
	//재료배열
	public static List<ingredient> ingredients;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ingredients = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ingredient tmp = new ingredient();
			tmp.sour = Integer.parseInt(st.nextToken());
			tmp.bitter = Integer.parseInt(st.nextToken());
			ingredients.add(tmp);
		}
		//초기값으로 재귀
		find(0, 1, 0, 0);
		System.out.println(answer);
	}
	
	public static void find(int cnt, int sour, int bitter, int start) {
		if(cnt == N) {
			//재료를 하나도 사용하지 않은 경우 제외
			if(sour != 1 &&  bitter != 0) answer = Math.min(answer, Math.abs(bitter-sour));
			return;
		} else {
			for (int i = start; i < N; i++) {
				ingredient cur = ingredients.get(i); 
				//해당 재료 사용시
				find(cnt+1, sour*cur.sour, bitter+cur.bitter, i+1);
				//해당 재료 사용안할시
				find(cnt+1, sour, bitter, i+1);
			}
		}
	}
}