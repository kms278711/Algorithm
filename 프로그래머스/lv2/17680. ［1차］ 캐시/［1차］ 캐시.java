import java.util.ArrayList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> arr = new ArrayList<>();
    	if(cities.length!=0) {
        	answer = 5;
            arr.add(cities[0].toLowerCase());
        }
    	if(cacheSize == 0) return cities.length*5;
        boolean flag = false;
        for (int i = 1; i < cities.length; i++) {
        	for (int j = 0; j < arr.size(); j++) {
    			if(arr.get(j).equals(cities[i].toLowerCase())) {
    				answer += 1;
    				flag = true;
    				String tmp = arr.get(j);
    				arr.remove(j);
    				arr.add(tmp);
    				break;
    			}
    		}
        	if(!flag) {
        		answer += 5;
        		if(arr.size()==cacheSize) {
        			arr.remove(0); 
        		}
        		arr.add(cities[i].toLowerCase());
        	}
        	flag = false;
		}
        return answer;
    }
}