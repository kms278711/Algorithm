import java.util.*;
import java.io.*;

class Point{
	public int x,y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
public class Solution {
	//회사와 집 좌표
	private static Point com, home;
	//손님들 좌표
	private static Point[] arr;
	//손님들 체크 배열
	private static boolean checked[];
	//최소로 계속 갱신할 answer
	private static int answer;
	public void solution(int cnt, int x, int y, int dis) {
		//정답보다 이미 크다면 리턴
		if(dis>answer) return;
		//손님들 다 보고 왔으면
		if(cnt == arr.length) {
			//마지막 집까지 거리 합해서 최소값으로 갱신
			int tmp2 = Math.abs(x-home.x) + Math.abs(y-home.y);
			answer = Math.min(answer, dis+tmp2);
		} else {
			for (int i = 0; i < arr.length; i++) {
				//아직 방문안한 손님이면
				if(!checked[i]) {
					//방문
					checked[i] = true;
					int tmp = Math.abs(x-arr[i].x) + Math.abs(y-arr[i].y);
					solution(cnt+1, arr[i].x, arr[i].y, dis+tmp);
					//방문안함
					checked[i] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Solution s = new Solution();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			//정답 최대값으로 초기화
			answer=Integer.MAX_VALUE;
			arr = new Point[N];
			checked = new boolean[N];
			//입력받기
			com = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int j = 0; j < N; j++) {
				arr[j] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			//회사좌표를 현재위치로 재귀시작
			s.solution(0, com.x, com.y, 0);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
}