class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        //p길이
        int length = p.length();
        //인덱스
        int idx = 0;
        //끝에 length길이의 문자열까지 탐색 
        while(idx<=t.length()-length) {
        	// p의 길이가 18자리까지 나올수 있기때문에 double
        	if(Double.parseDouble(p)>=Double.parseDouble(t.substring(idx, idx+length))) answer++;
        	idx++;
        }
        return answer;
    }
}