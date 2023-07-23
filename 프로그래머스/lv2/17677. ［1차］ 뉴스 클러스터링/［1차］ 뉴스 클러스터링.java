import java.util.ArrayList;

class Solution {
    public int solution(String str1, String str2) {
    	int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        ArrayList<String> strList1 = new ArrayList<>();
        ArrayList<String> strList2 = new ArrayList<>();
        for (int i = 0; i < arr1.length-1; i++) {
        	if(!Character.isAlphabetic(arr1[i]) || arr1[i+1] == ' ' || !Character.isAlphabetic(arr1[i+1])) continue;
        	String tmp = Character.toString(arr1[i]) + Character.toString(arr1[i+1]);
        	strList1.add(tmp);
        }
        for (int i = 0; i < arr2.length-1; i++) {
        	if(!Character.isAlphabetic(arr2[i]) || arr2[i+1] == ' ' || !Character.isAlphabetic(arr2[i+1])) continue;
        	String tmp = Character.toString(arr2[i]) + Character.toString(arr2[i+1]);
        	strList2.add(tmp);
        }

        if(strList1.size()+strList2.size() == 0) return 65536;
        double B = strList1.size()+strList2.size();
        
        double A = 0;
        for (String s: strList1) {
			if(strList2.contains(s)) {
				A++;
				strList2.remove(strList2.indexOf(s));
			}
		}
        B -= A;
        double tmp = A/B;
        answer = (int)(Math.floor(tmp * 65536));
        return answer;
    }
}