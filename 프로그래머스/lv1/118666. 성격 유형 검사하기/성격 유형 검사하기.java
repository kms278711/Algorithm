import java.util.*;
import java.io.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<Character, Integer> map = new LinkedHashMap<>();
        
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
        
        for (int i = 0; i < survey.length; i++) {
			char[] c = survey[i].toCharArray();
			switch(choices[i]) {
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