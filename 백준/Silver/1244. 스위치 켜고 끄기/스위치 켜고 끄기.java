import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//스위치 개수
		int cnt = Integer.parseInt(br.readLine());
		//스위치
		int[] arr = new int[cnt];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cnt; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//인원
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			//남,여학생 비교
			if(st.nextToken().equals("1")) {
				//할당받은 스위치 번호
				int num = Integer.parseInt(st.nextToken());
				for (int j = num-1; j < cnt; j = j + num) {
					arr[j] = (arr[j] == 1) ? 0:1; 
				}
			} else {
				int num = Integer.parseInt(st.nextToken());
				//시작과 끝 인덱스
				int start = num-1;
				int end = num-1;
				//시작과 끝 인덱스 확장
				while(true) {
					// 끝에 오면 break
					if(start-1 < 0 || end+1 >= cnt) break;
					// 다를경우 break
					if(arr[start-1] != arr[end+1]) break;
					start = start-1;
					end = end+1;
				}
				//스위치 전환
				for (int j = start; j <= end; j++) {
					arr[j] = (arr[j] == 1) ? 0:1; 
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if(i!=0 && i%20 == 0) System.out.println();
			System.out.print(arr[i] + " ");
		}
	}
}