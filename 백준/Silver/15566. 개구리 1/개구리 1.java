import java.util.*;
import java.io.*;

public class Main {
	//정답비교를 위해 NO로 초기화
	private static String answer="NO";
	//개구리마리수, 통나무개수
	private static int N,M;
	//주제별 흥미
	private static int[][] talk;
	//좋아하는 연꽃정보 [개구리번호][선호연꽃 2개]
	private static int[][] favor;
	//연꽃에 개구리 번호 세팅정보
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
	
	//선호 연꽃을 기준으로 개구리 세팅하기
	private static void find(int cnt) {
		//이미 정답이 나왔다면 탐색 종료
		if(answer.equals("YES")) return;
		//1번부터 탐색 시작하기때문에 N+1이 종료 조건
		if(cnt == N+1) {
			//통나무 목록 돌면서 대화가능한지 체크
			for (int i = 0; i < M; i++) {
				//현재 통나무
				Route cur = routeArr[i];
				//주제의 관심도가 같은지 체크
				//다르면 리턴
				if(talk[result[cur.start]][cur.favor] != talk[result[cur.end]][cur.favor]) return;
			}
			//for문을 나왔다면 YES로 갱신해주고 출력
			answer = "YES";
			System.out.println(answer);
			if(answer.equals("YES")) {
				for (int i = 1; i <= N; i++) {
					System.out.print(result[i] + " ");
				}
			}
			return;
		} else {
			//연꽃개수만큼 탐색
			for (int i = 0; i < 2; i++) {
				//cnt번쨰 개구리의 선호 연꽃이 비어있다면
				if(result[favor[cnt][i]] == 0) {
					//cnt번쨰 개구리의 선호 연꽃이 비어있다면 해당 개구리 넣기
					result[favor[cnt][i]] = cnt;
					//다음 개구리 세팅
					find(cnt+1);
					//세팅 복구
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
		//개구리1번부터 시작
		talk = new int[N+1][5];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 5; j++) {
				talk[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//개구리1번부터 시작
		favor = new int[N+1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 2; j++) {
				favor[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		routeArr = new Route[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int favor = Integer.parseInt(st.nextToken());
			routeArr[i] = new Route(start, end, favor);
		}
		result = new int[N+1];
		find(1);
		//정답이 여전히 NO라면 NO출력
		if(answer.equals("NO")) System.out.println(answer);
	}
	
}
