import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		//input 배열
		int[] arr = new int[n];
		//스택생성
		Stack<Integer> stack = new Stack<>();
		//현재 값
		int cur = 1;
		//비교할 정답 인덱스
		int idx = 0;
		//입력받기
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		while(true) {
			//비어있다면 값 넣기
			if(stack.isEmpty()) {
				stack.push(cur++);
				sb.append("+\n");
			} else {
				//제일 위에있는 값 비교
				if(stack.peek() == arr[idx]) {
					idx++;
					stack.pop();
					sb.append("-\n");
				} else {
					//peek값이 아니면서 현재값을 넘어간경우
					if(cur == n+1) break;
					stack.push(cur++);
					sb.append("+\n");
				}
			}
			//마지막 input 인덱스까지 탐색한경
			if(idx==n) break;
		}
		if(stack.isEmpty() && cur==n+1) System.out.println(sb);
		else System.out.println("NO");	
	}
}