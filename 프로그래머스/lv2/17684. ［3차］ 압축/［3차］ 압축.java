import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    public Integer[] solution(String msg) {
        Integer[] answer = {};
        List<String> arr = new ArrayList<>();
        List<Integer> answerArr = new ArrayList<>();
        arr.add(" ");
        for (int i = 0; i < 26; i++) {
			arr.add(Character.toString((char)(65+i)));
		}
        String[] msgArr = msg.split("");
        int idx = 0;
        while(true) {
        	if(idx==msgArr.length)break;
            int tmp = 0;
            String str = msgArr[idx];
			while(true) {
				if(arr.contains(str)) {
					System.out.println(str);
					tmp = arr.indexOf(str);
					idx++;
					if(idx==msgArr.length) {
						answerArr.add(tmp);
						break;
					}
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