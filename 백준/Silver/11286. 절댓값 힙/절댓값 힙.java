import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		//우선수위 큐 선언
		PriorityQueue<Integer> prQueue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				//절댓값 같으면 작은수 앞으로
				if(Math.abs(o1) == Math.abs(o2)) {
					return o1-o2;
				}
				return Math.abs(o1)-Math.abs(o2);
			}
		});
		for (int i = 0; i < N; i++) {
			int tmp = Integer.parseInt(br.readLine());
			if(tmp == 0) {
				if(prQueue.isEmpty()) sb.append(0).append("\n");
				else sb.append(prQueue.poll()).append("\n");
			} else {
				prQueue.offer(tmp);
			}
		}
		
		System.out.println(sb);
	}
}