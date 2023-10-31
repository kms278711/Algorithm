import java.util.*;
import java.io.*;

public class Main {
	//구역수, 정답
	private static int N, answer=Integer.MAX_VALUE;
	//인구수 배열(1부터 시작)
	private static int[] population;
	//구역 간 연결 인접행렬 (1,1부터 시작)
	private static boolean[][] conInfo;
	//구역
	private static List<Integer> area1, area2;
	
	//두 선거구의 인구수 차이 구하기
	private static int getDiff() {
		int aSum = 0;
		int bSum = 0;
		for (int i = 0; i < area1.size(); i++) {
			aSum += population[area1.get(i)];
		}
		
		for (int i = 0; i < area2.size(); i++) {
			bSum += population[area2.get(i)];
		}
		return Math.abs(aSum-bSum);
	}
	
	//해당 리스트의 구역들이 연결되어 있는지 확인(BFS)
	private static boolean checkCon(List<Integer> list) {
		Queue<Integer> queue = new ArrayDeque<>();
		//초기값 세팅
		queue.offer(list.get(0));
		//방문배열
		boolean[] visited = new boolean[N+1];
		visited[list.get(0)] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				//큐에서 뽑아온 구역과 연결이 되어있고
				//아직 방문안한 구역이면서
				//list에 포함되어있는 구역이라면
				if(conInfo[cur][i] && !visited[i] && list.contains(i)) {
					//방문처리 후 해당 구역 큐에 넣기
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		
		//리스트에 있는 값들을 모두 방문했는지 체크
		for (int n : list) {
			if(!visited[n]) return false;
		}
		return true;
	}
	
	//PowerSet 이용
	//경우의 수 구하기
	private static void findCase(int cnt) {
		if(cnt == N) {
			//두개로 리스트가 나눠질수 없으면 리턴
			if(area1.size()==0 || area1.size() == N) return;
			
			area2 = new ArrayList<>();
			//리스트1에 없는 값들 리스트2에 넣기
			for (int i = 1; i <= N; i++) {
				if(!area1.contains(i)) area2.add(i);
			}
			
			//각 리스트가 각각 연결되어질 수 있다면 정답 갱신
			if(checkCon(area1) && checkCon(area2)) {
				answer = Math.min(getDiff(), answer);
			} 
		} else {
			//cnt+1번째 구역 리스트1에 넣고 분기 진행
			area1.add(cnt+1);
			findCase(cnt+1);
			
			//cnt+1번째 구역 리스트1에서 빼고 분기 진행
			area1.remove((Object)(cnt+1));
			findCase(cnt+1);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		conInfo = new boolean[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				int pt = Integer.parseInt(st.nextToken());
				conInfo[i][pt] = true;
				conInfo[pt][i] = true;
			}
		}
		area1 = new ArrayList<>();
		findCase(0);
		
		System.out.println((answer == Integer.MAX_VALUE) ? -1:answer);
	}
	
}
