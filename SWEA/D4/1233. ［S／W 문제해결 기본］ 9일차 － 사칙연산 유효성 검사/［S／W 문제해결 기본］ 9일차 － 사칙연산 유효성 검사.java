import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	public static BufferedReader br;
    public static int solution(int num) throws IOException {
    	int answer = 1;
    	StringTokenizer st = null;
    	for (int i = 1; i <= num; i++) {
    		st = new StringTokenizer(br.readLine());
    		st.nextToken();
    		String tmp = st.nextToken();
    		if(st.hasMoreTokens()) {
    			if(!(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("/"))) {
    				answer = 0;
    			}   
    		} else {
    			if(tmp.equals("+") || tmp.equals("-") || tmp.equals("*") || tmp.equals("/")) {
    				answer = 0;
    			}    			
    		}
		}
    	return answer;
    }

    
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int T = 1; T <= 10; T++) {
			sb.append("#").append(T).append(" ").append(solution(Integer.parseInt(br.readLine()))).append("\n");
		}
		System.out.println(sb);
	}
}