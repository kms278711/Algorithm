import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int answer = 1;
		if(N/2 < K) K = N-K;
		
		for (int i = 0; i < K; i++) {
			answer *= N-i;
		}
		
		for (int i = K; i > 0; i--) {
			answer /= i;
		}
		System.out.println(answer);
	}
}
