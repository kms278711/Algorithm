import java.io.*;
import java.util.*;

public class Main {
	public static String answerDFS="", answerBFS="";
	public static int[][] arr;
	//체크배열
	public static int[] check;
	public static int N,M,V;
	//DFS
	public static void DFS(int L, int cur) {
		if(L==N) {
			//끝까지 왔으면 return
			answerDFS += cur;
			return;
		} else {
			//현재값 사용 체크
			check[cur] = 1;
			answerDFS += cur;
			answerDFS += " ";
			//1부터 N까지 오름차순으로 간선찾기
			for (int i = 1; i <= N; i++) {
				//사용 안된 정점만
				if(check[i] == 0 && arr[cur][i] == 1) {
					//길이 증가해서 재귀
					DFS(L+1, i);				
				}
			}
		}
	}
	
	//BFS
	public static void BFS() {
		Queue<Integer> queue = new ArrayDeque<>();
		//처음 정점 넣어주기
		queue.offer(V);
		while(!queue.isEmpty()) {
			//큐 최상단 poll
			int cur = queue.poll();
			answerBFS += cur;
			answerBFS += " ";
			//사용했으니 체크
			check[cur] = 1;
			for (int i = 1; i <= N; i++) {
				if(arr[cur][i] == 1 && check[i] == 0 && !queue.contains(i)) {
					queue.offer(i);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		check = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			arr[p1][p2] = 1;
			arr[p2][p1] = 1;		
		}
		DFS(0, V);
		//체크배열 초기화
		check = new int[N+1];
		BFS();
		System.out.println(answerDFS.substring(0, answerDFS.length()-1));
		System.out.println(answerBFS.substring(0, answerBFS.length()-1));
	}
}