class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] possible = {"aya", "ye", "woo", "ma"};
        for (String s : babbling) {
        	// replace로 공백으로 바꾸기
			for (int i = 0; i < 4; i++) {
				s = s.replace(possible[i], " ");
			}
			//공백을 없애고 확인 -> 처음부터 ""로 할경우 wyeoo -> woo -> "" 되서 불가능
			s = s.replace(" ", "");
			if(s.equals("")) answer++;
		}
        return answer;
    }
}