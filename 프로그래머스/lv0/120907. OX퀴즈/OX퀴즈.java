class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        for (int i = 0; i < quiz.length; i++) {
        	// 뛰어쓰기 기준으로 나눠서 배열생성
			String[] elements = quiz[i].split(" ");
			//연산 확인
			if(elements[1].equals("-")) {
				if(Integer.parseInt(elements[4]) == Integer.parseInt(elements[0]) - Integer.parseInt(elements[2]))answer[i] = "O";
				else answer[i] = "X";
			} else {
				if(Integer.parseInt(elements[4]) == Integer.parseInt(elements[0]) + Integer.parseInt(elements[2]))answer[i] = "O";
				else answer[i] = "X";
			}
		}
        return answer;
    }
}