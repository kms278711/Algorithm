import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		//스택 생성
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//명령어 저장
			String order = st.nextToken();
			//탐색
			if(order.equals("push")) {
				stack.push(st.nextToken());
			} else if(order.equals("pop")) {
				//비었을경우 -1
				if(stack.isEmpty()) sb.append("-1\n");
				//있으면 출력
				else sb.append(stack.pop()).append("\n");
			} else if(order.equals("size")) {
				sb.append(stack.size()).append("\n");
			} else if(order.equals("empty")) {
				//비었다면 1, 아니면 0
				sb.append(stack.isEmpty() ? "1":"0").append("\n");
			} else if(order.equals("top")) {
				//비었을경우 -1
				if(stack.isEmpty()) sb.append("-1\n");
				//가장 위에있는 정수 호출. 스택상태는 변하지 않음.
				else sb.append(stack.peek()).append("\n");
			}
		}
		System.out.println(sb);
	}
}