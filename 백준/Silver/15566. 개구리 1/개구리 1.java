import java.util.*;
import java.io.*;

public class Main {
	private static String answer="NO";
	private static int N,M;
	//주제별 흥미
	private static int[][] talk;
	//좋아하는 연꽃
	private static int[][] favor;
	//연꽃에 게구리 번호 세팅정보
	private static int[] result;
	//통나무 배열
	private static Route[] routeArr;
	
	//통나무 클래스
	//시작, 끝, 흥미주제번호
	static class Route {
		int start, end, favor;

		public Route(int start, int end, int favor) {
			super();
			this.start = start;
			this.end = end;
			this.favor = favor;
		}

		@Override
		public String toString() {
			return "Route [start=" + start + ", end=" + end + ", favor=" + favor + "]";
		}
		
	}
	
	private static void find(int cnt) {
		if(answer.equals("YES")) return;
		if(cnt == N+1) {
			for (int i = 0; i < M; i++) {
				Route cur = routeArr[i];
				if(talk[result[cur.start]][cur.favor] != talk[result[cur.end]][cur.favor]) return;
			}
			answer = "YES";
			System.out.println(answer);
			if(answer.equals("YES")) {
				for (int i = 1; i <= N; i++) {
					System.out.print(result[i] + " ");
				}
			}
			return;
		} else {
			for (int i = 0; i < 2; i++) {
				if(result[favor[cnt][i]] == 0) {
					result[favor[cnt][i]] = cnt;
					find(cnt+1);
					result[favor[cnt][i]] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		talk = new int[N+1][5];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 5; j++) {
				talk[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		favor = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				favor[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = new int[N+1];
		routeArr = new Route[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int favor = Integer.parseInt(st.nextToken());
			routeArr[i] = new Route(start, end, favor);
		}
		find(1);
		if(answer.equals("NO")) System.out.println(answer);
	}
	
}
