import java.io.*;
import java.util.*;

public class Main {
	//해당 정점에 연결된 정점들 리스트를 담은 리스트
	private static List<Integer>[] list;
	//구역1, 구역2
	private static List<Integer> area1, area2;
	//총 구역수, 정답, 연결되어있는지 체크할 정점 개수
	private static int N, answer=Integer.MAX_VALUE, cnt;
	//인구수 배열
	private static int[] peopleCnt;
	//사용정점 체크배열
	private static boolean[] checked;
	//연결되어있는지여부
	private static boolean isCorrect;
	/**
	 * 연결되어있는지 체크
	 * @param area
	 * @param cur
	 */
	private static void DFS(int num, int cur) {
		//현재 정점 체크
		checked[cur] = true;
		//이미 확인했다면 return
		if(isCorrect) return;
		//연결되어 있다고 판단된 정점개수가 파라미터로 들어온 구역 길이와 같다면 잘 연결되어있다고 표시
		if(cnt == (num == 1? area1.size():area2.size())) {
			isCorrect = true;
		} else {
			//해당 정점에서 갈 수 있는 정점 길이만큼
			for (int i = 0; i < list[cur].size(); i++) {
				int tmp = list[cur].get(i);
				switch(num) {
					case 1:
						//탐색안한 곳이면서 파라미터로 들어온 구역에 있는 정점이라면
						if(!checked[tmp] && area1.contains(tmp)) {
							//카운팅올리고
							cnt++;
							//현재정점 갱신해서 반복
							DFS(num, tmp);
						}
						break;
					case 2:
						if(!checked[tmp] && area2.contains(tmp)) {
							//카운팅올리고
							cnt++;
							//현재정점 갱신해서 반복
							DFS(num, tmp);
						}
						break;
				}
			}
		}
	}
	
	private static boolean check() {
		//초기화
		cnt = 1;
		checked = new boolean[N];
		isCorrect = false;
		//area1 체크
		DFS(1, area1.get(0));
		//연결되어있지않으면 리턴
		if(!isCorrect) return false;
		//초기화
		isCorrect = false;
		cnt = 1;
		checked = new boolean[N];
		//area2 체크
		DFS(2, area2.get(0));
		//연결되어 있지 않으면 리턴
		if(!isCorrect) return false;
		return true;
	}
	/**
	 * 조합 구하기
	 * @param cnt
	 * @param size
	 */
	private static void getComb(int cnt, int size) {
		//cnt가 길이보다 커지면 리턴
		if(cnt>N) return;
		//area1이 원하는 길이만큼 만들어지면
		if(area1.size() == size) {
			//area2 정의
			area2 = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if(!area1.contains(i)) area2.add(i);
			}
			//각각 서로 연결되어 있는지 체크
			if(check()) {
				
				int aSum = 0;
				int bSum = 0;
				for (Integer num : area1) {
					aSum += peopleCnt[num];
				}
				for (Integer num : area2) {
					bSum += peopleCnt[num];
				}
				//정답갱신
				answer = Math.min(answer, Math.abs(aSum-bSum));
			}
		} else {
			//현재 cnt 값을 포함했을경우
			area1.add(cnt);
			getComb(cnt+1, size);
			//현재 cnt값을 포함하지 않았을 경우
			area1.remove((Object)cnt);
			getComb(cnt+1, size);
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		peopleCnt = new int[N];
		for (int i = 0; i < N; i++) {
			peopleCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		list = new List[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				list[i].add(Integer.parseInt(st.nextToken())-1);
			}
		}
		
		//area1 기준으로 들어온 정점수의 절반까지 탐색. 
		for (int i = 1; i <= N/2; i++) {
			area1 = new ArrayList<>();
			getComb(0,i);
		}
		
		//정답이 갱신되지 않았다면 나눌 수 없는 것이기때문에 -1 출력
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
}
