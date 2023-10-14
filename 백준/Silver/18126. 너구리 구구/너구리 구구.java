import java.util.*;
import java.io.*;

public class Main {
	static long[][] dis;
	static long[] minDis;
	static int N;
	
	static class Status{
		int point;
		long len;

		public Status(int point, long len) {
			super();
			this.point = point;
			this.len = len;
		}
	}
	private static void find() {
		Queue<Status> queue = new ArrayDeque<>();
		queue.offer(new Status(0, 0));
		while(!queue.isEmpty()) {
			Status cur = queue.poll();
			for (int i = 1; i < N; i++) {
				if(dis[cur.point][i] != 0 && i!= cur.point) {
					long tmp = cur.len + dis[cur.point][i];
					if(minDis[i]>tmp) {
						minDis[i] = tmp;
						queue.offer(new Status(i, tmp));
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dis = new long[N][N];
		minDis = new long[N];
		Arrays.fill(minDis, Long.MAX_VALUE);
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			long len = Integer.parseInt(st.nextToken());
			dis[a][b] = len;
			dis[b][a] = len;
		}
		find();
		long answer = Long.MIN_VALUE;
		for (int i = 1; i < N; i++) {
			if(minDis[i] != Long.MAX_VALUE) answer = Math.max(answer, minDis[i]);
		}
		System.out.println((answer == Long.MIN_VALUE)? 0 : answer);
	} 
}
