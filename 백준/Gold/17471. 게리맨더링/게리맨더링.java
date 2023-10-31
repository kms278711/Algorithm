import java.util.*;
import java.io.*;

public class Main {
	private static int N, answer=Integer.MAX_VALUE;
	private static int[] population;
	private static boolean[][] conInfo;
	private static List<Integer> list1, list2;
	
	private static int getDiff() {
		int aSum = 0;
		int bSum = 0;
		for (int i = 0; i < list1.size(); i++) {
			aSum += population[list1.get(i)];
		}
		
		for (int i = 0; i < list2.size(); i++) {
			bSum += population[list2.get(i)];
		}
		return Math.abs(aSum-bSum);
	}
	
	private static boolean checkCon(List<Integer> list) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(list.get(0));
		boolean[] visited = new boolean[N+1];
		visited[list.get(0)] = true;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i <= N; i++) {
				if(conInfo[cur][i] && !visited[i] && list.contains(i)) {
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		for (int n : list) {
			if(!visited[n]) return false;
		}
		return true;
	}
	
	private static void findCase(int cnt) {
		if(cnt == N) {
			if(list1.size()==0 || list1.size() == N) return;
			
			list2 = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if(!list1.contains(i)) list2.add(i);
			}
			
			if(checkCon(list1) && checkCon(list2)) {
				answer = Math.min(getDiff(), answer);
			} 
		} else {
			list1.add(cnt+1);
			findCase(cnt+1);
			list1.remove((Object)(cnt+1));
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
		list1 = new ArrayList<>();
		findCase(0);
		
		System.out.println((answer == Integer.MAX_VALUE) ? -1:answer);
	}
	
}
