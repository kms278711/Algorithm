import java.util.*;
import java.io.*;

public class Main {
	//정해진 각 정점사이 거리
	static long[][] dis;
	//0번 부터의 최소거리
	static long[] minDis;
	//방 개수
	static int N;
	
	//상태클래스
	//현재 정점과 해당 정점까지의 거리를 가짐
	static class Status{
		int point;
		long len;

		public Status(int point, long len) {
			super();
			this.point = point;
			this.len = len;
		}
	}
	
	//탐색
	private static void find() {
		Queue<Status> queue = new ArrayDeque<>();
		//시작정점과 길이0으로 시작
		queue.offer(new Status(0, 0));
		while(!queue.isEmpty()) {
			Status cur = queue.poll();
			//0번은 탐색 필요x
			//1번부터 탐색시작
			for (int i = 1; i < N; i++) {
				//현재 정점에서 연결되어 있는 인접정점이라면
				if(dis[cur.point][i] != 0) {
					//현 상태에서 해당 정점까지의 거리 계산
					long tmp = cur.len + dis[cur.point][i];
					//해당 정점까지의 거리보다 계산된 값이 작으면 갱신하고 큐에 상태추가
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
		
		//최소거리 계산을 위해 최대값으로 넣기
		Arrays.fill(minDis, Long.MAX_VALUE);
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//1번 -> 0번
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			long len = Integer.parseInt(st.nextToken());
			//양방향
			dis[a][b] = len;
			dis[b][a] = len;
		}
		
		//찾기
		find();
		
		//비교를 위해 최소값으로 초기화
		long answer = Long.MIN_VALUE;
		for (int i = 1; i < N; i++) {
			//현재 정답 값보다 크면 갱신
			//가장 먼 방
			if(minDis[i] != Long.MAX_VALUE) answer = Math.max(answer, minDis[i]);
		}
		
		//갈 수 있는 방이 하나도 없으면 0출력
		System.out.println((answer == Long.MIN_VALUE)? 0 : answer);
	} 
}
