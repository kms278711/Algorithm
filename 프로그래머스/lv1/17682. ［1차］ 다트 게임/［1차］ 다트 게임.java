class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int idx = 0;
        int prior = 0;
        int cur = 0;
        String score = "";
        char[] c = dartResult.toCharArray();
        while(idx != dartResult.length()) {
        	if(Character.isDigit(c[idx])) {
        		score += c[idx];
        	} else if(c[idx]=='S') {
        		cur = Integer.parseInt(score);
        	} else if(c[idx]=='D') {
        		cur = (int) Math.pow(Integer.parseInt(score), 2);
        	} else if(c[idx]=='T') {
        		cur = (int) Math.pow(Integer.parseInt(score), 3);
        	}
        	if(Character.isAlphabetic(c[idx])) {
        		if(idx+1 != dartResult.length()) {
	        		if(c[idx+1]=='*') {
	            		idx++;
	            		if(answer == 0) answer += cur;
	            		else answer += cur + prior;
	            		prior = cur*2;
	            	} else if(c[idx+1]=='#') {
	        			idx++;
	        			cur *= -1;
	        			prior = cur;
	        		} else {
	        			prior = cur;
	        		}
        		} else {
        			prior = cur;        			
        		}
        		answer += cur;
        		score = "";
        	}
        	idx++;
        }
        return answer;
    }
}