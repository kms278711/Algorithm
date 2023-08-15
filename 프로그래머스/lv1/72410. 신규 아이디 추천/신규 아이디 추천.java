class Solution {
    public String solution(String new_id) {
        String answer = new_id;
        //소문자로 바꾸기
        answer = answer.toLowerCase();
        //a-z 0-9 - _ .을 뺴고 다 공백으로 바꾸기
        answer = answer.replaceAll("[^a-z0-9-_.]", "");
        //.이 2번 이상 반복 되었따면 .으로 바꾸기
        answer = answer.replaceAll("[.]{2,}", ".");
        //처음에 위치한 . 제거
        answer = answer.charAt(0) == '.' ? (answer.length()==1)? "":answer.substring(1):answer;
        //빈문자열인 경우 a추가
        answer = answer.equals("") ? "a":answer;
        //길이가 16이상이면 15자리로 자르고 끝문자가 .이면 제거
        answer = answer.length() >= 16 ? answer.substring(0, 15):answer;
        answer = answer.charAt(answer.length()-1)=='.' ? answer.substring(0,answer.length()-1):answer;
        //아이디의 길이가 2자 이하면 3자가 될떄까지 마지막문자 추가
        if(answer.length()<=2) {        	
        	while(answer.length()!=3) {
        		answer = answer + answer.charAt(answer.length()-1);
        	}
        }
        return answer;
    }
}