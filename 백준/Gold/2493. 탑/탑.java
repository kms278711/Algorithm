import java.util.*;
import java.io.*;

class towel {
	public int index;
	public int height;
	public towel(int index, int height) {
		this.index = index;
		this.height = height;
	}
}
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		//타워 스택
		Stack<towel> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			//현재 탐색되어지는 높이
			int cur = Integer.parseInt(st.nextToken());
			while(true) {				
				if(!stack.isEmpty()) {
					//현재값보다 가장 위에있는값이 클 경우
					if(stack.peek().height >= cur) {
						//그곳을 바라볼테니까
						sb.append(stack.peek().index).append(" ");
						//현재값 추가
						stack.push(new towel(i+1, cur));
						break;
					//작을 경우 가장 위에 값 없애기
					} else {
						stack.pop();
					}
				//비어있다면
				} else {
					sb.append("0 ");
					stack.push(new towel(i+1, cur));
					break;
				}
			}
		}
		System.out.println(sb);
	}
}