import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = -1;
		while(true) {
			//5보다 클경우
			if(N>=5) {
				//최대개수부터 3으로 나눌 수 있는지 검사
				for (int i = N/5; i >= 0 ; i--) {
					if((N - (5*i)) % 3 == 0) {
						answer = i;
						answer += (N - (5*i)) / 3;
						break;
					}
				}
				break;
			//5보다 작다면 3으로 나누어지는지 검사
			} else {
				if(N % 3 == 0) {
					answer = N/3;
				}
				break;
			}
		}
		System.out.println(answer);
	}
}