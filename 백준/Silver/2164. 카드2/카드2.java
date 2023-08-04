import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> deq = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			deq.offer(i);
		}
		while(deq.size()!=1) {
			deq.pollFirst();
			if(deq.size()==1) break;
			deq.offer(deq.pollFirst());
		}
		if(N == 1) System.out.println(1);
		else System.out.println(deq.poll());
	}
}