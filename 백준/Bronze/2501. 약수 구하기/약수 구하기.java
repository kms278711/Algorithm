import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt = 0;
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if(N%i==0) {
				cnt ++;
				answer = i;
			}
			if(cnt == K) break;
			
		}
		if(cnt<K) answer = 0;
		System.out.println(answer);
	}

}
