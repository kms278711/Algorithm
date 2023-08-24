import java.io.*;
import java.util.*;

//좌표 클래스
class Point{
	public int x,y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

/**
 * 정점 클래스
 * 가중치 낮은게 앞으로 오게
 */
class Vertex implements Comparable<Vertex>{
	int no;
	double weight; 
	public Vertex(int no, double weight) {
		super();
		this.no = no;
		this.weight = weight;
	}
	@Override
	public int compareTo(Vertex o) {
		return Double.compare(this.weight, o.weight);
	}
}
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int i = 1; i <= T; i++) {
			//입력 및 초기화
			sb.append("#").append(i).append(" ");
			int N = Integer.parseInt(br.readLine());
			double result = 0;
			//정점들 리스트
			List<Point> points = new ArrayList<>();
			//정점들 사이 가중치 배열
			double[][] dis = new double[N][N];
			//현재 들어온 정점에서 갈 수 있는 최소거리
			double[] minEdge = new double[N];
			//최대값으로 초기화
			Arrays.fill(minEdge, Double.MAX_VALUE);
			//방문배열
			boolean[] visited = new boolean[N];
			
			int[] x = new int[N];
			int[] y = new int[N];
			
			//x, y 좌표 받아서 point 클래스로 묶고 리스트에 넣기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				x[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				y[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < N; j++) {
				points.add(new Point(x[j], y[j]));
			}
			
			//환경 부담 세율
			double E = Double.parseDouble(br.readLine());
			
			//E * L^2로 dis 채우기
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					long a = Math.abs(points.get(j).x-points.get(k).x);
					long b = Math.abs(points.get(j).y-points.get(k).y);
					dis[j][k] = ((a * a + b * b) * E);
				}
			}
			
			//우선순위 큐 선언
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			//자기자신 거리 0
			minEdge[0] = 0;
			//초기값으로 우선순위 큐에 넣기
			pq.offer(new Vertex(0, minEdge[0]));
			
			//연결된 정점 개수 카운팅 변수
			int cnt = 0;
			while(!pq.isEmpty()) {
				Vertex cur = pq.poll();
				//이미 방문되어서 필요없는 값 패스
				if(visited[cur.no]) continue;
				//방문표시(연결)
				visited[cur.no] = true;
				result += cur.weight;
				//정점개수만큼 나왔으면 break
				if(++cnt == N) break;
				for (int j = 0; j < N; j++) {
					//방문하지 않았고 자기자신아니고 최단거리로 넣어놓은 거리보다 짧으면 거리 갱신
					if(!visited[j] && dis[cur.no][j] != 0 && dis[cur.no][j] < minEdge[j]) {
						minEdge[j] = dis[cur.no][j];
						//거리 갱신후 최소 거리로 다시 큐에 넣어주기.
						//큐에 기존에 같은 정점으로 들어가 있는 애가 있지만 가중치에 따라 정렬되기 때문에 걸러낼 수 있음. 
						pq.offer(new Vertex(j, minEdge[j]));
					}
				}
			}
			sb.append(Math.round(result)).append("\n");
		}
		System.out.println(sb);
	}
}
