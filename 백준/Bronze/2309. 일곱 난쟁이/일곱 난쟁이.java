import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  null;
		int dwarf[] = new int[9];
		int sum = 0;
		int idx = 0;
		// 총합 구하기
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			sum += length;
			dwarf[i] = length;
		}
		// lt rt로 가짜 찾기
		for (int lt = 0; lt < dwarf.length-1; lt++) {
			for (int rt = lt+1; rt < dwarf.length; rt++) {
				if(sum-dwarf[lt]-dwarf[rt] == 100) {
					sum = 100;
					dwarf[lt] = 0;
					dwarf[rt] = 0;
					break;
				}
			}
			if(sum == 100) break;
		}
		Arrays.sort(dwarf);
		for (int i = 2; i < dwarf.length; i++) {
			System.out.println(dwarf[i]);
		}
	}
}