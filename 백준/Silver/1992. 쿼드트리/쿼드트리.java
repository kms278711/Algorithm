import java.io.*;
import java.util.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	private static int[][] arr;
	//분할정복
	private static void slice(int len, int x, int y) {
		//합 초기화
		int sum = 0;
		//해당구역 합구하기
		for (int i = x; i < x+len; i++) {
			for (int j = y; j < y+len; j++) {				
				sum += arr[i][j];
			}
		}
		//다 1인지
		if(sum == len*len) {
			sb.append(1);
		//다 0인지
		} else if(sum == 0) {
			sb.append(0);
		} else {
			//괄호추가
			sb.append("(");
			//1구역
			slice(len/2, x, y);
			//2구역
			slice(len/2, x, y+len/2);
			//3구역
			slice(len/2, x+len/2, y);
			//4구역
			slice(len/2, x+len/2, y+len/2);
			//괄호닫기
			sb.append(")");
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		int len = N;
		slice(len, 0, 0);
		System.out.println(sb);
	}
}