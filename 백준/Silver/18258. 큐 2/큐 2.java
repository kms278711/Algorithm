import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Queue<String> queue = new LinkedList<>();
		//마지막 들어간 값
		String last = "";
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if(order.equals("push")) {
				String tmp = st.nextToken(); 
				//큐에 값 넣기
				queue.offer(tmp);
				//마지막 값 갱신
				last = tmp;
			} else if(order.equals("pop")) {
				if(queue.isEmpty()) sb.append("-1\n"); 
				else sb.append(queue.poll()).append("\n");
			} else if(order.equals("size")) {
				sb.append(queue.size()).append("\n");
			} else if(order.equals("empty")) {
				sb.append(queue.isEmpty() ? "1":"0").append("\n");
			} else if(order.equals("front")) {
				//peek 하면 다음 나올 값 나옴
				if(queue.isEmpty()) sb.append("-1\n"); 
				else sb.append(queue.peek()).append("\n");
			} else if(order.equals("back")) {
				//마지막으로 offer한것 출력 
				if(queue.isEmpty()) sb.append("-1\n"); 
				else sb.append(last).append("\n");
			}
		}
		System.out.println(sb);
	}
}