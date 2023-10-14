import java.util.*;
import java.io.*;

public class Main {
	private static int N,M;
	private static Set<Integer> set;
	private static List<Integer> cowList;
	
	private static boolean isPrime(int num) {
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if(num%i==0) return false;
		}
		return true;
	}
	
	private static void setCow(int idx, int cnt, int sum) {
		if(cnt>M) return;
		if(idx == N) {
			if(cnt == M) {
				if(isPrime(sum)) set.add(sum);
			}
		} else {
			setCow(idx+1, cnt, sum);
			setCow(idx+1, cnt+1, sum+cowList.get(idx));
		}
	} 
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cowList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cowList.add(Integer.parseInt(st.nextToken()));
		}
		
		set = new HashSet<>();
		setCow(0, 0, 0);
		
		if(set.size() == 0) System.out.println(-1);
		else {
			List<Integer> answer = new ArrayList<>(set);
			Collections.sort(answer);
			for (Integer num : answer) {
				System.out.print(num + " ");
			}
		}
	}
	
}
