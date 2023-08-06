import java.util.*;

class Solution { 
    public int solution(String str1, String str2) {
    	int answer = 0;
        //소문자로 통일
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        //2개씩 넣을 list
        List<String> strList1 = new ArrayList<>();
        List<String> strList2 = new ArrayList<>();
        for (int i = 0; i < arr1.length-1; i++) {
            //문자아닌경우 continue
        	if(!Character.isAlphabetic(arr1[i]) || arr1[i+1] == ' ' || !Character.isAlphabetic(arr1[i+1])) continue;
            //두자리 뽑기
        	String tmp = Character.toString(arr1[i]) + Character.toString(arr1[i+1]);
        	strList1.add(tmp);
        }
        //마찬가지
        for (int i = 0; i < arr2.length-1; i++) {
        	if(!Character.isAlphabetic(arr2[i]) || arr2[i+1] == ' ' || !Character.isAlphabetic(arr2[i+1])) continue;
        	String tmp = Character.toString(arr2[i]) + Character.toString(arr2[i+1]);
        	strList2.add(tmp);
        }

        //뽑은게 둘다 없는 경우
        if(strList1.size()+strList2.size() == 0) return 65536;
        //소수 계산을 위해 double
        // 합집합 계산
        double B = strList1.size()+strList2.size();
        
        //교집합 개수 
        double A = 0;
        for (String s: strList1) {
			if(strList2.contains(s)) {
				A++;
				strList2.remove(strList2.indexOf(s));
			}
		}
        //합집합
        B -= A;
        //유사도 계산
        double tmp = A/B;
        answer = (int)(Math.floor(tmp * 65536));
        return answer;
    }
}