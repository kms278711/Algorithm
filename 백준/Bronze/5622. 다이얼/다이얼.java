import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public void findTime(String s) {
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String alpha = "ABC,DEF,GHI,JKL,MNO,PQRS,TUV,WXYZ";
		String[] section = alpha.split(",");
		int time = 0 ;
		for (String problem : st.nextToken().split("")) {
			for (int i = 0; i < section.length; i++) {
				if(section[i].contains(problem)) {
					// time += 현재 주소값 + 1까지 걸리는 시간
					time += (i + 1) + 2;
					break;
				}
			}
		}
		System.out.println(time);
	}
}
