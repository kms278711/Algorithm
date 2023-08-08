class Solution {
    public String solution(String s) {
        String answer = "";
        char[] c = s.toCharArray();
        int idx = 0;
        int cnt = 0;
        char tmp = ' ';
        while(idx<s.length()) {
        	if(c[idx] == ' ') {
        		answer += " ";
        		cnt = 0;
        		idx++;
        		tmp = ' ';
        		continue;
        	} else {
        		tmp = (cnt%2==0 ? Character.toUpperCase(c[idx]) : Character.toLowerCase(c[idx]));
            	idx++;
            	cnt++;
        		answer += tmp;
        	}
        }
        return answer.substring(0, s.length());
    }
}