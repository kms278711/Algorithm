import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<Character, Integer> map = new LinkedHashMap<>();
        
        //카운팅하기 위한 맵 초기화
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
        
        for (int i = 0; i < survey.length; i++) {
        	//설문응답결고를 char배열로 쪼개고
			char[] c = survey[i].toCharArray();
			switch(choices[i]) {
				//답변결과에 따라 카운팅 c[0]는 문제기준 앞유형, c[1]는 문제기준 뒤유형
				case 1:
					map.put(c[0], map.get(c[0]) + 3);
					break;
				case 2:
					map.put(c[0], map.get(c[0]) + 2);
					break;
				case 3:
					map.put(c[0], map.get(c[0]) + 1);
					break;
				case 5:
					map.put(c[1], map.get(c[1]) + 1);
					break;
				case 6:
					map.put(c[1], map.get(c[1]) + 2);
					break;
				case 7:
					map.put(c[1], map.get(c[1]) + 3);
					break;
			}
		}
        
        //카운팅 개수 비교해서 성격 유형 리턴
		if(map.get('R')>=map.get('T')) answer += "R";
		else answer += "T";
		if(map.get('C')>=map.get('F')) answer += "C";
		else answer += "F";
		if(map.get('J')>=map.get('M')) answer += "J";
		else answer += "M";
		if(map.get('A')>=map.get('N')) answer += "A";
		else answer += "N";
        
		return answer;
    }
}