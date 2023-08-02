import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Stack<Integer> stack = new Stack<>();
		//현재 값
		int cur = 1;
		//비교할 정답 인덱스
		int idx = 0;
		stack.push(cur++);
		sb.append("+\n");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		while(true) {
			if(stack.isEmpty()) {
				stack.push(cur++);
				sb.append("+\n");
			} else {
				if(stack.peek() == arr[idx]) {
					idx++;
					stack.pop();
					sb.append("-\n");
				} else {
					if(cur == n+1) break;
					stack.push(cur++);
					sb.append("+\n");
				}
			}
			if(idx==n) break;
			if(stack.isEmpty() && cur==n+1) break;
		}
		if(stack.isEmpty() && cur==n+1) System.out.println(sb);
		else System.out.println("NO");	
	}
}