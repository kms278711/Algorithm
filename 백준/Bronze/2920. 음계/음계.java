import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		switchPt:
		switch(n) {
			case 1: 
				n = 2;
				while(n<=8) {
					if(n++ != Integer.parseInt(st.nextToken())) {
						System.out.println("mixed");
						break switchPt;
					}
				}
				System.out.println("ascending");
				break;
			case 8 :
				n = 7;
				while(n>=1) {
					if(n-- != Integer.parseInt(st.nextToken())) {
						System.out.println("mixed");
						break switchPt;
					}
				}
				System.out.println("descending");
				break;
			default:
				System.out.println("mixed");
				break;
		}
	}
}
