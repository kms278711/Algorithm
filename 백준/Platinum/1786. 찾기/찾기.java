import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		String str2 = br.readLine();
		int len = str.length();
		int len2 = str2.length();
		int[] P = new int[len2];
		P[0] = 0;
		int j = 0;
		for (int i = 1; i < len2; i++) {
			while(j>0 && str2.charAt(i) != str2.charAt(j)) {
				j = P[j-1];
			}
			if(str2.charAt(i) == str2.charAt(j)) {
				P[i] = ++j;
			}
		}
		
		int cnt = 0;
		j = 0;
		for (int i = 0; i < len; i++) {
			while(j>0 && str.charAt(i) != str2.charAt(j)) {
				j = P[j-1];
			}
			
			if(str.charAt(i) == str2.charAt(j)) {
				if(j == len2-1) {
					cnt++;
					sb.append(i-len2+2).append(" ");
					j = P[j];
				} else {
					j++;
				}
			}
		}	
		System.out.println(cnt);
		System.out.println(sb);
	}
}
