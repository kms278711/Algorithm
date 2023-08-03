import java.io.*;

public class Main {
	public static int N;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 첫자리 2357만 계산
		DFS(1,2);
		DFS(1,3);
		DFS(1,5);
		DFS(1,7);
		System.out.println(sb);
	}
	
	public static void DFS(int cnt, int num) {
		if(cnt==N) {
			if(isPrime(num))
				sb.append(num).append("\n");
			return;
		} else {
			for (int i = 1; i < 10; i++) {
				//들어온 수가 소수라면 넘김
				if(isPrime(num)) {
					DFS(cnt+1, num*10+i);
				}
			}
		}
	}
	
	public static boolean isPrime(int num) {
		//2는 소수
		if(num < 2) return false;
		
		//소수검사
		for (int j = 2; j <= Math.sqrt(num); j++) {
			if(num % j == 0) {
				return false;
			}
		}
		return true;
	}
}