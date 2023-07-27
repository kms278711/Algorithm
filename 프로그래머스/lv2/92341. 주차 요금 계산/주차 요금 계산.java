import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        //fees 기본시간, 기본 요금, 단위 시간, 단위 요금
    	List<String> busList = new ArrayList<>();
    	//버스배열 생성
    	for (int i = 0; i < records.length; i++) {
    		String busNum = records[i].split(" ")[1];
    		if(!busList.contains(busNum)) busList.add(busNum);			
		} 
    	int[] answer = new int[busList.size()];
    	//버스 정렬
    	Collections.sort(busList);
    	// 정답 idx
    	int idx = 0;
    	for (String bus : busList) {
    		int cnt = 0;
    		int start = 0;
    		int end = 0;
    		double time = 0;
    		for (int i = 0; i < records.length; i++) {
    			String[] record = records[i].split(" ");
				if(record[1].equals(bus)) {
					if(cnt == 1) {
						end = Integer.parseInt(record[0].split(":")[0])*60 + Integer.parseInt(record[0].split(":")[1]);
						cnt++;
						time += end - start;
					//버스 입출차 1번
					} else if(cnt == 0){
						start = Integer.parseInt(record[0].split(":")[0])*60 + Integer.parseInt(record[0].split(":")[1]);
						cnt++;
					}
				 	if(cnt == 2) {
				 		cnt = 0;
				 	}
				}
			}
    		//출차 없을경우
    		if(cnt == 1) time += 1439-start;
    		if(time<=fees[0]) answer[idx] = fees[1];
    		else answer[idx] = fees[1] + (int)Math.ceil(((time-(double)fees[0])/(double)fees[2])) * fees[3];
    		idx++;
    	}
        return answer;
    }
}