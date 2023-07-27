class Solution {
    public String solution(String code) {
        String answer = "";
        int mode = 0;
        char[] arr = code.toCharArray();
        //돌면서 mode 체크
        for (int idx = 0; idx < arr.length; idx++) {
        	if(mode==1) {
        		if(arr[idx] != '1') {
        			if(idx%2 == 1) answer+=arr[idx];
        		} else mode = 0;	
        	} else {
        		if(arr[idx] != '1') {
        			if(idx%2 == 0) answer+=arr[idx];
        		} else mode = 1;	
        	}
		}
        if(answer.equals("")) answer = "EMPTY";
        return answer;
    }
}