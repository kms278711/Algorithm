import java.io.*;
import java.util.*;

/**
 * 간선클래스
 * round : 현재라운드, num : 숫자
 */
class Edge{
	public int round, num;

	public Edge(int round, int num) {
		super();
		this.round = round;
		this.num = num;
	}
}
public class Solution {
	private static int N, answer;
	//<시작정점, 해당 점에서 진출해서 만나는 정점들 목록>
	private static Map<Integer, List<Integer>> map;
	//<정점, 해당정점 사용여부>
	private static Map<Integer, Boolean> visited;
	/**
	 * 더 파고들게 없을 때까지 반복하는 BFS
	 * @param start
	 * @return 마지막 라운드 최대 숫자
	 */
	private static int BFS(int start) {
		//해당 라운드 정점 배열
		List<Integer> answerList = new ArrayList<>();
		Queue<Edge> queue = new ArrayDeque<>();
		// 0라운드 시작정점 넣어주기
		queue.offer(new Edge(0, start));
		//방문표시
		visited.put(start, true);
		//현재 라운드
		int round = 0;
		while(!queue.isEmpty()) {
			Edge cur = queue.poll();
			//해당정점에서 연결된 정점들 리스트
			List<Integer> next = map.get(cur.num);
			//뽑은게 다음라운드꺼라면
			if(round != cur.round) {
				//라운드 갱신
				round = cur.round;
				Collections.sort(answerList);
				//정답갱신
				answer = answerList.get(answerList.size()-1);
				//라운드 리스트 초기화
				answerList = new ArrayList<>();
			}
			//null이 아닐떄만
			if(next != null) {
				if(next.size() != 0) {
					int len = next.size();
					for (int i = 0; i < len; i++) {
						int tmp = next.get(i);
						//다음정점이 방문한곳인지 체크
						if(!visited.get(tmp)) {
							//라운드 리스트에 추가
							answerList.add(tmp);
							//한라운드 증가해서 큐에 넣어줌
							queue.offer(new Edge(cur.round+1, tmp));
							//방문체크
							visited.put(tmp, true);
						}
					}
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 10; i++) {
			//입력 및 초기화
			sb.append("#").append(i).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			map = new HashMap<>();
			visited = new HashMap<>();
			st = new StringTokenizer(br.readLine());
			//from to 한쌍이기때문에 N/2만큼 반복
			for (int j = 0; j < N/2; j++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				//방문체크초기화
				visited.put(from, false);
				visited.put(to, false);
				//없으면 새로 리스트 만들어서 넣기
				if(map.get(from) == null) {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(to);
					map.put(from, tmp);
				//이미 있으면 추가
				} else {
					map.get(from).add(to);
				}
			}
			sb.append(BFS(start)).append("\n");
		}
		System.out.println(sb);
	}
}
