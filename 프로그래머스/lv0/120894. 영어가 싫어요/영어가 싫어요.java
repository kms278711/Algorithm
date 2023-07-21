class Solution {
    public long solution(String numbers) {
        long answer = 0;
        String tmp="";
        int idx = 0;
        char[] c = numbers.toCharArray();
        while(idx<numbers.length()) {
        	switch(c[idx]) {
        		case 'z' : 
        			idx += 4;
        			tmp += 0;
        			break;
        		case 'o' :
        			idx += 3;
        			tmp += "1";
        			break;
        		case 't' :
        			if(c[idx+1] == 'w') {
        				idx += 3;
        				tmp += "2";
        			} else if(c[idx+1] == 'h') {
        				idx += 5;
        				tmp += "3";
        			}
        			break;
        		case 'f' :
        			if(c[idx+1] == 'o') {
        				idx += 4;
        				tmp += "4";
        			} else if(c[idx+1] == 'i') {
        				idx += 4;
        				tmp += "5";
        			}
        			break;
        		case 's' :
        			if(c[idx+1] == 'i') {
        				idx += 3;
        				tmp += "6";
        			} else if(c[idx+1] == 'e') {
        				idx += 5;
        				tmp += "7";
        			}
        			break;
        		case 'e' :
        			idx += 5;
        			tmp += "8";
        			break;
        		case 'n' :
        			idx += 4;
        			tmp += "9";
        			break;
        	}
        }
        answer = Long.parseLong(tmp);
        return answer;
    }
}