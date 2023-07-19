class Solution {
    public String[] solution(String[] quiz) {
    	System.out.println(quiz.length);
        String[] answer = new String[quiz.length];
        for (int i = 0; i < quiz.length; i++) {
			String[] elements = quiz[i].split(" ");
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