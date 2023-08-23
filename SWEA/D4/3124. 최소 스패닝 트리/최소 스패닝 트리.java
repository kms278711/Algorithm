import java.io.*;
import java.util.*;

/**
 * 간선클래스
 * from : 시작, to : 끝, weight : 가중치
 */
class Edge implements Comparable<Edge>{
	public int from, to, weight;

	public Edge(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	//가중치 오름차순으로
	@Override
	public int compareTo(Edge o) {
		return this.weight-o.weight;
	}
	
	
}
public class Solution {
	private static int[] parents;
	private static int V,E;
	
	//부모트리 생성
	private static void make() {
		parents = new int[V];
		//자기자신을 부모로 초기화
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	//부모찾는함수
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	//합집합
	private static boolean union(int a, int b) {
		//부모 찾아서 넣어주기
		int aRoot = find(a);
		int bRoot = find(b);
		
		//부모 비교해서 같으면 이미 연결된 정점
		if(aRoot == bRoot) return false;
		//아니라면 연결하고 부모 바꾸기
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			//입력받은 정점 개수로 부모 배열 생성
			make();
			//간선리스트
			Edge[] list = new Edge[E];
			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				list[j] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			//간선리스트 가중치 내림차순으로 정렬
			Arrays.sort(list);
			
			//정점 개수 카운팅 변수
			int count = 0;
			//가중치합
			long result = 0;
			for (Edge edge : list) {
				//합쳐진다면
				if(union(edge.from-1, edge.to-1)) {
					//해당 간선의 가중치 더하고 
					result += edge.weight;
					//정점개수 카운팅
					count ++;
					if(count == V-1) break;
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
