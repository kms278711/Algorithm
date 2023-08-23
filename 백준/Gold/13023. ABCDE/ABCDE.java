import java.io.*;
import java.util.*;

public class Main {
	private static int N,M,answer;
	private static List<Integer>[] list;
	private static boolean[] checked;
	private static void DFS(int cnt, int cur) {
		//이미 답이 나왔으면 리턴
		if(answer == 1) return;
		//깊이가 N까지 오면 정답갱신하고 리턴
		if(cnt == 5) {
			answer = 1;
		} else {
			//현재번호와 관계가 있는 애들 테스트
			for (Integer num : list[cur]) {
				//아직 방문안한 친구면
				if(!checked[num]) {
					checked[num] = true;
					//방문하고 다음 깊이로
					DFS(cnt+1, num);
					//원상복귀
					checked[num] = false;
				}
			}
			
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//초기화
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new List[N];
		//list 안에 있는 리스트 초기화
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		//입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//a와 관계있는 애들 묶기
			list[a].add(b);
			//b와 관계있는 애들 묶기
			list[b].add(a);
		}
		
		for (int i = 0; i < N; i++) {
			checked = new boolean[N];
			checked[i] = true;
			if(answer == 1) break;
			DFS(1,i);
		}
		
		System.out.println(answer);
	}
}
