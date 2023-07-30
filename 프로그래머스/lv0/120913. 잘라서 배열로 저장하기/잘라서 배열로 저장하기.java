class Solution {
    public String[] solution(String my_str, int n) {
    	String[] answer = null;
    	//배열 크기 정하기. 딱 나눠지면 몫으로 그렇지 않으면 +1
    	if(my_str.length()%n == 0) answer = new String[my_str.length()/n];
    	else answer = new String[my_str.length()/n+1];
        //인덱스
    	int idx = 0;
    	//answer에 substring으로 잘라서 문자열 넣기
        for (int i = 0; i < my_str.length(); i=i+n) {
        	if(i+n < my_str.length()) answer[idx++] = my_str.substring(i, i+n);
        	else answer[idx++] = my_str.substring(i, my_str.length());
		}
        return answer;
    }
}