import java.io.*;
import java.util.*;

/**
 * SCV상태클래스
 * cnt : 공격 횟수
 * scvList : 체력리스트
 */
class SCVstate{
	int cnt;
	List<Integer> scvList;
	public SCVstate(int cnt, List<Integer> scvList) {
		super();
		this.cnt = cnt;
		this.scvList = scvList;
	}
}
public class Main {
	//메모이제이션를 위한 memo배열
	static boolean[][][] memo;
	//가능한 조합
	static int[][] comb = {{9,3,1},{9,1,3}, {3,9,1}, {3,1,9},{1,3,9},{1,9,3}};
	static int BFS(List<Integer> list) {
		Queue<SCVstate> queue = new ArrayDeque<SCVstate>();
		//내림차순으로 정렬
		Collections.sort(list, Collections.reverseOrder());
		//처음 상태 집어넣기
		queue.offer(new SCVstate(0, list));
		while(!queue.isEmpty()) {
			//현재 상태 하나 뽑아오기
			SCVstate cur = queue.poll();
			//체력이 다 0이라면 현재 공격횟수 리턴. BFS이기 때문에 최단이 나옴
			if(cur.scvList.get(0)==0&&cur.scvList.get(1)==0&&cur.scvList.get(2)==0) {
				return cur.cnt;
			}
			int scv1 = cur.scvList.get(0);
			int scv2 = cur.scvList.get(1);
			int scv3 = cur.scvList.get(2);
			for (int i = 0; i < 6; i++) {
				//임시 리스트
				List<Integer> tmp = new ArrayList<>();
				//조합별로 빼서 임시 리스트에 넣기
				tmp.add(scv1-comb[i][0] <= 0 ? 0:scv1-comb[i][0]);
				tmp.add(scv2-comb[i][1] <= 0 ? 0:scv2-comb[i][1]);
				tmp.add(scv3-comb[i][2] <= 0 ? 0:scv3-comb[i][2]);
				//내림차순 정렬
				Collections.sort(tmp, Collections.reverseOrder());
				//이미 방문한 곳인지 체크
				if(!memo[tmp.get(0)][tmp.get(1)][tmp.get(2)]) {
					//방문 표시
					memo[tmp.get(0)][tmp.get(1)][tmp.get(2)] = true;
					queue.offer(new SCVstate(cur.cnt+1, tmp));
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new boolean[61][61][61];
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> list = new ArrayList<>();
		//입력받을 수 있는 체력만큼
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		//나머지 3-N개 체력 0으로 넣기
		for (int i = 0; i < 3-N; i++) {
			list.add(0);
		}
		System.out.println(BFS(list));
	} 
}
