import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		//큐 생성
		Queue<Integer> q = new LinkedList<>();
		//1부터 N까지 넣기
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		//카운팅
		int cnt = 0;
		while(q.size() != 1) {
			if(cnt == K-1) {
				sb.append(q.poll()).append(", ");
				cnt = 0;
			//뒤에다 붙이기
			} else {				
				int tmp = q.poll();
				q.offer(tmp);
				cnt ++;
			}
		}
		sb.append(q.poll()).append(">");
		System.out.println(sb);
	}
}