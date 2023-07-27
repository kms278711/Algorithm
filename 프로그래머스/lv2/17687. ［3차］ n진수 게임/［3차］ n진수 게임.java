public class Solution {
    public String solution(int n, int t, int m, int p) {
    	String answer = "";
    	int cur = 0;
    	int idx = 1;
    	while(true) {
    		String num = Integer.toString(cur, n);
    		for (String s : num.split("")) {
				if(idx == p) {
					answer += s.toUpperCase();
					if(answer.length()==t) break;
				}
				idx++;
				if(idx == m+1) idx = 1;
			}
    		if(idx == m+1) idx = 1;
    		if(answer.length()==t) break;
    		cur++; 
    	}
    	return answer;
    }
}