import java.util.*;
import java.io.*;

//페르마의 소정리 이용
//a^(p-1) = 1(mod p)
//(x/a) % p = (x * a^(p-2)) % p = ((x % p) * (a^(p-2) % p)) % p
public class Solution {
	//나눌 소수
	private static long mod = 1234567891;
	
	//제곱 구하기(나머지 연산 포함)
	private static long pow(long a, long b) {
		//power가 0이면 1리턴
		if(b == 0)
			return 1;
		//power가 1이면 밑 그대로 리턴
		else if(b == 1)
			return a;
		
		//짝수인 경우
		if(b % 2 == 0) {
			//제곱을 절반으로 하는 하여 나머지 연산 결과를 받아온다.
			long tmp = pow(a, b/2);
			//절반으로 나온 값을 다시 제곱하고 나머지 연산한 값 리턴
			return (tmp * tmp) % mod;
		//홀수인 경우
		} else {
			//1을 빼서 power를 짝수로 하여 재귀 후 나머지 연산을 한다.
			long tmp = pow(a, b-1) % mod;
			//power 1뺀만큼 곱하고 나머지연산 진행 후 리턴
			return (tmp * a) % mod;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력 및 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1; i <= T; i++) {
			sb.append("#" + i + " ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			//미리 구해놓기
			long[] facto = new long[N+1];
			facto[1] = 1;
			for (int j = 2; j <= N; j++) {
				//이전 계산된 값에 현재 값 곱하고 나머지 연산 후 집어넣기
				facto[j] = (facto[j-1] * j) % mod;
			}
			
			//밑
			long bottom = (facto[R]*facto[N-R]) % mod;
			//mod-2만큼 제곱
			bottom = pow(bottom, mod-2);
			//N의 팩토리얼(나머지연산)값에 계산된 밑 값을 곱하고 나머지 연산 진행한다.
			sb.append((facto[N] * bottom) % mod).append("\n");
		}
		System.out.println(sb);
	}
}