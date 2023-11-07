import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new ArrayDeque<>();
		int last=0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			switch(order) {
				case "push" :
					int num = Integer.parseInt(st.nextToken());
					queue.offer(num);
					last = num;
					break;
				case "pop" :
					if(queue.isEmpty()) sb.append(-1 + "\n");
					else {
						sb.append(queue.poll() + "\n");
					}
					break;
				case "size" :
					sb.append(queue.size()+"\n");
					break;
				case "empty" :
					if(queue.isEmpty()) sb.append(1+"\n");
					else sb.append(0+"\n");
					break;
				case "front" :
					if(queue.isEmpty()) sb.append(-1 + "\n");
					else {
						sb.append(queue.peek() + "\n");
					}
					break;
				case "back" :
					if(queue.isEmpty()) sb.append(-1 + "\n");
					else {
						sb.append(last + "\n");
					}
					break;
			}
		}
		System.out.println(sb);
	}
}
