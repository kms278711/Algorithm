import java.io.*;
import java.util.*;

public class Main {	
	static class Node{
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	private static int V,E;
	private static int K;
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		Node[] adjList = new Node[V];
		int[] distance = new int[V];
		boolean[] visited = new boolean[V];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to= Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
		distance[K-1] = 0;
		
		int min = 0;
		int stopOver = 0;
		for (int i = 0; i < V; i++) {
			stopOver = -1;
			min = INF;
			for (int j = 0; j < V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					stopOver = j;
				}
			}
			if(stopOver == -1) break;
			visited[stopOver] = true;
			for (Node temp = adjList[stopOver]; temp != null; temp=temp.next) {
				// 해당 정점이 방문 정점이 아니고 현재 정점에서 갈 수 있는 정점의 경우
				// 최소거리 정점을 거쳐서 해당 정점을 갈경우의 토탈 가중치와 기존까지 계산된 해당정점까지의 토탈가중치를 비교하여 최소값을 만족하는지
				if(!visited[temp.vertex]&&
						distance[temp.vertex] > min+temp.weight) {
					distance[temp.vertex] = min+temp.weight;
				}
			}
		}
		for (int i = 0; i < V; i++) {
			System.out.println(distance[i]==INF ? "INF":distance[i]);
		}
	}
}
