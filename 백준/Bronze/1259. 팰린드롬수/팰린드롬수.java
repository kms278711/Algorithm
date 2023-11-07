import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		loop:
		while(true) {
			String num = br.readLine();
			if(num.equals("0")) break;
			int len = num.length();
			int cnt = 0;
			while(cnt < len/2) {
				if(num.charAt(cnt) != num.charAt(len-cnt-1)) {
					sb.append("no").append("\n");
					continue loop;
				}
				cnt++;
			}
			sb.append("yes").append("\n");
		}
		System.out.println(sb);
	}
}
