class Solution {
	public int solution(String s) {
        String answer = "";
        char[] arr = s.toCharArray();
        for (int i=0; i<arr.length; i++) {
        	if(Character.isDigit(arr[i])) {
        		answer += arr[i];
        	} else {
        		switch(arr[i]) {
                    case 'z' : 
                        answer += 0;
                        i += 3;
                        break;
        			case 'o' : 
        				answer += 1;
        				i += 2;
        				break;
        			case 'e' : 
        				answer += 8;
        				i += 4;
        				break;
        			case 'n' : 
        				answer += 9;
        				i += 3;
        				break;
        			case 't' : 
        				if(arr[i+1] == 'h') {
        					answer += 3;
            				i += 4;
        				} else if(arr[i+1] == 'w') {
        					answer += 2;
            				i += 2;
        				}
        				break;
        			case 'f' : 
        				if(arr[i+1] == 'o') {
        					answer += 4;
            				i += 3;
        				} else if(arr[i+1] == 'i') {
        					answer += 5;
            				i += 3;
        				}
        				break;
        			case 's' : 
        				if(arr[i+1] == 'i') {
        					answer += 6;
            				i += 2;
        				} else if(arr[i+1] == 'e') {
        					answer += 7;
            				i += 4;
        				}
        				break;
        		}
        	}
		}
        return Integer.parseInt(answer);
    }
}