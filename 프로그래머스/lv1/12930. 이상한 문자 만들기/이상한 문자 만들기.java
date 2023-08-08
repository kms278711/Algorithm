class Solution {
    public String solution(String s) {
        String answer = "";
        //쪼개기
        char[] c = s.toCharArray();
        //c배열의 비교할 인덱스
        int idx = 0;
        //단어의 몇번째 글자인지
        int cnt = 0;
        //answer에 들어가기 위해 작업할 임시 char
        char tmp = ' ';
        //문자열 길이만큼
        while(idx<s.length()) {
        	//공백이면
        	if(c[idx] == ' ') {
        		//초기화, 공백추가
        		answer += " ";
        		cnt = 0;
        		//인덱스 증가
        		idx++;
        		tmp = ' ';
        		continue;
        	} else {
        		//짝수면 대문자, 홀수면 소문자
        		tmp = (cnt%2==0 ? Character.toUpperCase(c[idx]) : Character.toLowerCase(c[idx]));
            	idx++;
            	cnt++;
        		answer += tmp;
        	}
        }
        //마지막 공백 제거
        return answer;
    }
}