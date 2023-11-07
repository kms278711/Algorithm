import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		loop:
		for (int i = 0; i < N; i++) {
			Stack<String> stack = new Stack<>();
			String[] str = br.readLine().split("");
			for (int j = 0; j < str.length; j++) {
				if(str[j].equals("(")) {
					stack.push("(");
				} else {
					if(stack.isEmpty()) {
						sb.append("NO").append("\n");
						continue loop;
					}
					stack.pop();
				}
			}
			if(stack.isEmpty()) sb.append("YES").append("\n");
			else sb.append("NO").append("\n");
		}
		System.out.println(sb);
	}
}
