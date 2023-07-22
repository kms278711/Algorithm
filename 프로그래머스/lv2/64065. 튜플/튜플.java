import java.util.ArrayList;
import java.util.Arrays;

class Solution {
	public boolean contain() {
		return true;
	}
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(1,s.length()-1);
        boolean flag = false;
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        ArrayList<Integer> arrTmp = new ArrayList<>();
        String str = "";
        for (char c : s.toCharArray()) {
        	if(c=='{') {
        		arrTmp = new ArrayList<>();
        		flag = false;
			} else if(c==',') {
				if(!flag) {
					arrTmp.add(Integer.parseInt(str));
					str = "";
				}
			} else if(c=='}') {
				arrTmp.add(Integer.parseInt(str));
				str = "";
				arr.add(arrTmp);
				flag = true;
			} else {
				str += c;
			}
		}
        
        int size = arr.size(); 
        ArrayList<Integer> answerArr = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
        	for (int j = 0; j < size; j++) {
        		if(arr.get(j).size() == i) {
        			for (int k = 0; k < i; k++) {
						if(!answerArr.contains(arr.get(j).get(k))) {
							answerArr.add(arr.get(j).get(k));
							break;
						}
					}
        			break;
        		}
			}
			
		}
        answer = new int[answerArr.size()];
        for (int i = 0; i < answerArr.size(); i++) {
			answer[i] = answerArr.get(i);
		}
        
        return answer;
    }
}