import java.io.*;

public class Main {
	static int[] memo;
	static int find(int N) {
		if(N<=2) return memo[N];
		if(memo[N] != 0) return memo[N];
		else {
			if(N%6 == 0) {
				memo[N] = Math.min(find(N/3), find(N/2));
				return memo[N] = Math.min(memo[N], find(N-1))+1;
			}
			if(N%3 == 0) return memo[N] = Math.min(find(N/3), find(N-1))+1;
			if(N%2 == 0) return memo[N] = Math.min(find(N/2), find(N-1))+1;
			return memo[N]=find(N-1) + 1;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[(int)Math.pow(10, 6)+1];
		memo[2] = 1;
		System.out.println(find(N));
	} 
}
