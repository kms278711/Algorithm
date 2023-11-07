import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int answer1 = 1;
		int answer2 = 0;
		int min = Math.min(N, M);
		int max = Math.max(N, M);
		
		for (int i = 2; i <= min; i++) {
			if(N % i == 0 && M % i == 0) {
				answer1 = i;
			}
		}
		
		for (int i = 1; i <= min; i++) {
			if(max * i % min == 0) {
				answer2 = max*i;
				break;
			}
		}
		System.out.println(answer1);
		System.out.println(answer2);
	}
}
