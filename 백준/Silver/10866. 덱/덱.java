import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			switch(order) {
				case "push_front" :
					queue.offerFirst(Integer.parseInt(st.nextToken()));
					break;
				case "push_back" :
					queue.offerLast(Integer.parseInt(st.nextToken()));
					break;
				case "pop_front" :
					if(queue.isEmpty()) sb.append(-1 + "\n");
					else {
						sb.append(queue.pollFirst() + "\n");
					}
					break;
				case "pop_back" :
					if(queue.isEmpty()) sb.append(-1 + "\n");
					else {
						sb.append(queue.pollLast() + "\n");
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
						sb.append(queue.peekFirst() + "\n");
					}
					break;
				case "back" :
					if(queue.isEmpty()) sb.append(-1 + "\n");
					else {
						sb.append(queue.peekLast() + "\n");
					}
					break;
			}
		}
		System.out.println(sb);
	}
}
