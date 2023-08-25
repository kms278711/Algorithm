import java.io.*;
import java.util.*;

public class Main {	
	/**
	 *	Node 클래스 
	 *	정점, 가중치, 다음 노드주소
	 */
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
	
	//정점수, 간선수
	private static int V,E;
	//시작인덱스
	private static int K;
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		//간선연결정보를 담을 인접리스트
		Node[] adjList = new Node[V];
		//시작점에서 각 정점까지의 최단거리 배열
		int[] distance = new int[V];
		//정점 방문 배열
		boolean[] visited = new boolean[V];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to= Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			//to라는 정점으로 weight라는 가중치를 가지고 기존에 있던 adjList[from]을 가리키게
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		//최소로 비교할것이기 때문에 최대값으로 배열 초기화
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
		//시작점 거리 0
		distance[K-1] = 0;
		
		//최단거리
		int min = 0;
		//최단 정점 인덱스
		int stopOver = 0;
		for (int i = 0; i < V; i++) {
			//-1로 초기화
			stopOver = -1;
			//최소로 비교위해서 최대값으로 초기화
			min = INF;
			for (int j = 0; j < V; j++) {
				//방문하지 않았고 현재 최소거리보다 정점에서의 거리가 더 작다면
				if(!visited[j] && min > distance[j]) {
					//최소값 갱신
					min = distance[j];
					//인덱스도 갱신
					stopOver = j;
				}
			}
			//갱신할게 없다면 break
			if(stopOver == -1) break;
			//해당 정점 방문 체크
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
		//출력
		for (int i = 0; i < V; i++) {
			//거리가 여전히 최대값이면 INF출력
			System.out.println(distance[i]==INF ? "INF":distance[i]);
		}
	}
}
