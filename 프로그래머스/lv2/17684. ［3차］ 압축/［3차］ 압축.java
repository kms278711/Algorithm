import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public Integer[] solution(String msg) {
        Integer[] answer = {};
        
        //사전리스트
        List<String> arr = new ArrayList<>();
        
        //정답리스트
        List<Integer> answerArr = new ArrayList<>();
        
        //첫인덱스 채우기
        arr.add(" ");
        
        //A~Z 사전에 넣기
        for (int i = 0; i < 26; i++) {
			arr.add(Character.toString((char)(65+i)));
		}
        String[] msgArr = msg.split("");
        int idx = 0;
        while(true) {
        	if(idx==msgArr.length)break;
        	
        	//현재입력 index
            int tmp = 0;
            
            String str = msgArr[idx];
			while(true) {
				if(arr.contains(str)) {
					tmp = arr.indexOf(str);
					idx++;
					
					//마지막인지 체크
					if(idx==msgArr.length) {
						answerArr.add(tmp);
						break;
					}
					
					//문자 더하기
					str += msgArr[idx];
				} else {
					arr.add(str);
					answerArr.add(tmp);
					break;
				}
			}
			if(idx==msgArr.length) break;
		}
        
        answer = answerArr.toArray(answer);
        return answer;
    }
}