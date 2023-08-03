import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		int[] check = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			check[i] = Integer.parseInt(st.nextToken());
		}

		//카운팅할 배열
		int[] check2 = new int[4];
		int cnt = 0;
		
		//초기 카운팅 세팅
		for (int i = 0; i < P; i++) {
			if(str.charAt(i) == 'A') {
				check2[0]++;
			} else if(str.charAt(i) == 'C') {
				check2[1]++;
			} else if(str.charAt(i) == 'G') {
				check2[2]++;
			} else if(str.charAt(i) == 'T') {
				check2[3]++;
			}
		}
		
		//첫 값 비교
		if(check[0] <= check2[0] && check[1] <= check2[1] && check[2] <= check2[2] && check[3] <= check2[3]) {
			cnt++;
		}
		
		for (int i = 1; i < S-P+1; i++) {
			int lt = i;
			int rt = i+P-1;
			//현재 lt값 전에 있던 것 빼주기
			if(str.charAt(lt-1) == 'A') {
				check2[0]--;
			} else if(str.charAt(lt-1) == 'C') {
				check2[1]--;
			} else if(str.charAt(lt-1) == 'G') {
				check2[2]--;
			} else if(str.charAt(lt-1) == 'T') {
				check2[3]--;
			}
			
			//현재 rt값 넣기
			if(str.charAt(rt) == 'A') {
				check2[0]++;
			} else if(str.charAt(rt) == 'C') {
				check2[1]++;
			} else if(str.charAt(rt) == 'G') {
				check2[2]++;
			} else if(str.charAt(rt) == 'T') {
				check2[3]++;
			}
			if(check[0] <= check2[0] && check[1] <= check2[1] && check[2] <= check2[2] && check[3] <= check2[3]) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}