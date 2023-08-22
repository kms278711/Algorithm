class Solution { 
    public int solution(String s) {
    	//길이 비교하고 갱신할 정답
        int answer = Integer.MAX_VALUE;
        //들어온 문자열 길이
        int len = s.length();
        //탐색범위
        int size = 1;
        //탐색범위가 문자열 길이 전까지 반복
        while(size <= len) {
        	//초기화
        	int start = 0;
        	int end = 0;
        	String result = "";
        	String cur = "";
        	int cnt = 1;
        	//끝점이 문자열 길이만큼 오면 종료
        	while(true) {
        		//시작점 갱신
        		start = end;
        		//끝점(시작점부터 탐색범위까지)이 문자열 길이보다 길어지면 문자열 길이로 넣어줌
        		end = start+size > len ? len : start+size;
        		//비어있다면 현재값으로 바꿔줌
        		if(cur.equals("")){
        			cur = s.substring(start, end);
        		//현재값이 다음문자열과 같다면 카운트
        		}else if(cur.equals(s.substring(start, end))) {
        			cnt ++;
        		//다르다면 결과값에 카운트가 1일때는 현재값만, 1이 아닐때는 카운트숫자와 현재값을 추가한다.	
        		} else {
        			result += cnt==1 ? cur : cnt+cur;
        			cnt = 1;
        			cur = s.substring(start,end);
        		} 
        		//끝점이 들어온 문자열 끝까지 왔다면 종료
        		if(end==len) {
        			//마지막 문자열 추가
        			result += cnt==1 ? cur : cnt+cur;
        			break;
    			}
        	}
        	//정답 갱신
        	answer = Math.min(answer, result.length());
        	//탐색범위 늘리기
        	size++;
        }
        return answer;
    }
}