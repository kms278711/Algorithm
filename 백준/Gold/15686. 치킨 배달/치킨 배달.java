import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Point {
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	//N, M, 정답
	public static int N,M, answer=Integer.MAX_VALUE;
	//집좌표리스트, 치킨집좌표리스트, 사용하기로한 치킨집좌표리스트
	public static List<Point> home, chicken, used;
	public static int[] check;
	public static void DFS(int cnt) {
		//깊이가 치킨집개수만큼왔으면
		if(cnt==chicken.size()) {
			int checkCnt = 0;
			for (int i = 0; i < check.length; i++) {
				if(check[i] == 1) checkCnt++;
				if(checkCnt > M) break;
			}
			//사용하기로한개 M개라면
			if(checkCnt == M) {
				//사용하기로한 치킨집좌표리스트 초기화
				used.clear();
				//사용하기로한 치킨집 넣어주기
				for (int i = 0; i < check.length; i++) {
					if(check[i] == 1) used.add(chicken.get(i));
				}
				int answerTmp = 0;
				for (Point h : home) {
					//최단거리 구하기
					int tmp = Integer.MAX_VALUE;
					for (Point c: used) {
						tmp = Math.min(tmp, Math.abs(h.x-c.x) + Math.abs(h.y-c.y));
					}
					//최단거리 더해주기
					answerTmp += tmp;
				}
				//최종정답 갱신
				answer = Math.min(answerTmp, answer);
			}
		} else {
			//해당 치킨집 사용
			check[cnt] = 1;
			DFS(cnt+1);					
			//해당 치킨집 사용x
			check[cnt] = 0;
			DFS(cnt+1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		used = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				String tmp = st.nextToken();
				//집 좌표 받기
				if(tmp.equals("1")) home.add(new Point(i, j));
				//치킨집 좌표 받기
				else if(tmp.equals("2")) chicken.add(new Point(i, j));
			}
		}
		check = new int[chicken.size()];
		DFS(0);
		System.out.println(answer);
	}
}