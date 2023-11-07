import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new ArrayDeque<>();
		Queue<Integer> answer = new ArrayDeque<>();		
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			cnt ++;
			if(cnt==K) {
				answer.offer(queue.poll());
				cnt = 0;
			} else {
				queue.offer(queue.poll());
			}
		}
		System.out.println(answer.toString().replace("[", "<").replace("]", ">"));
	}
}
