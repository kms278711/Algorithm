class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        int len = s.length();
        int size = 1;
        while(size <= len) {
        	int start = 0;
        	int end = 0;
        	String result = "";
        	String cur = "";
        	int cnt = 1;
        	while(true) {
        		start = end;
        		end = start+size > len ? len : start+size;
        		if(cur.equals(s.substring(start, end))) {
        			cnt ++;
        		} else if(cur.equals("")){
        			cur = s.substring(start, end);
        		} else {
        			result += cnt==1 ? cur : cnt+cur;
        			cnt = 1;
        			cur = s.substring(start,end);
        		} 
        		if(end==len) {
        			result += cnt==1 ? cur : cnt+cur;
    			}
        		if(end==len) break;
        	}
        	answer = Math.min(answer, result.length());
        	size++;
        }
        return answer;
    }
}