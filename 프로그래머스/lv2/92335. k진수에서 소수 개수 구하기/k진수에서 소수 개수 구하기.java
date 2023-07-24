class Solution {
    public int solution(long n, int k) {
        int answer = 0;
        String num = Long.toString(n, k);
        int idx = 0;
        char[] c = num.toCharArray();
        while(idx<num.length()+1) {
        	String tmp = "";
        	boolean flag = false;
        	while(true) {
        		if(idx==num.length()) break;
        		if(c[idx] == '0') {
        			break;
        		} else {
        			tmp += c[idx];
        			idx++;
        		}
        	}
        	if(tmp != "") {
	        	long prime = Long.parseLong(tmp);
	        	if(prime != 1) {
		        	for (int i = 2; i <= Math.sqrt(prime); i++) {
						if(prime%i==0) {
							flag = true;
							break;
						}
					}
	
		        	if(!flag) answer++;
	        	}
        	}
        	idx++;
        }
        return answer;
    }
}