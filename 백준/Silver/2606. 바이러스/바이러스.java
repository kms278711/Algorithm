import java.io.*;
import java.util.*;

public class Main {
	public static int[][] arr;
	//체크배열
	public static int[] check;
	public static int N,M;

	//BFS
	public static int BFS() {
		int cnt = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		//처음 정점 넣어주기
		queue.offer(1);
		while(!queue.isEmpty()) {
			//큐 최상단 poll
			int cur = queue.poll();
			cnt ++;
			//사용했으니 체크
			check[cur] = 1;
			for (int i = 1; i <= N; i++) {
				if(arr[cur][i] == 1 && check[i] == 0 && !queue.contains(i)) {
					queue.offer(i);
				}
			}
		}
		return cnt-1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		check = new int[N+1];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			arr[p1][p2] = 1;
			arr[p2][p1] = 1;		
		}
		//체크배열 초기화
		check = new int[N+1];
		System.out.println(BFS());
	}
}