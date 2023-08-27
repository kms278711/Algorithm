import java.util.*;

/**
 * 과제 클래스
 * 과제명, 시작시간(분), 수행시간
 */
class Subject{
	public String name;
	public int start, playtime;
	
	public Subject(String name, String start, String playtime) {
		super();
		this.name = name;
		//몇시몇분을 몇분으로 고쳐서 넣기
		this.start = Integer.parseInt(start.split(":")[0]) * 60 + Integer.parseInt(start.split(":")[1]);
		this.playtime = Integer.parseInt(playtime);
	}
	
}
class Solution {
    public String[] solution(String[][] plans) {
    	//실제리턴될 정답
    	String[] answer = new String[plans.length]; 
    	//임시정답
        List<String> answerTmp = new ArrayList<>();
        //남은 과제 리스트
        List<Subject> subjectList = new ArrayList<>();
        for (int i = 0; i < plans.length; i++) {
			subjectList.add(new Subject(plans[i][0], plans[i][1], plans[i][2]));
		}
        
        //과제시작시간기준으로 정렬
        Collections.sort(subjectList, new Comparator<Subject>() {
			@Override
			public int compare(Subject o1, Subject o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});
        //스택사용해서 가장 최근 넣은 과제를 꺼낼 수 있게
        Stack<Subject> stack = new Stack<>();
        //현재 수행 과제
        Subject cur = subjectList.get(0);
        //남은 과제 목록에서는 제거
        subjectList.remove(0);
        //현재시간(최소 과제 시작시간으로 초기화)
        int curTime = cur.start;
        while(true) {
        	//시간증가
        	curTime ++;
        	//현재 과제가 비어있지 않다면
        	if(cur != null) {
        		//과제 1분 수행
        		cur.playtime--;
        		//과제가 끝났다면
        		if(cur.playtime == 0) {
        			//임시정답 리스트에 넣기
        			answerTmp.add(cur.name);
        			//하던 과제가 있다면 현재 과제로 설정
        			if(!stack.isEmpty()) {
        				cur = stack.pop();
    				//하던 과제가 없다면 할 과제 목록에서 가져오기
        			} else {
        				cur = subjectList.get(0);
        				//현재시간 시작시간으로 갱신
        				curTime = cur.start;
        				subjectList.remove(0);
        			}
        		}
        	}
        	
        	//남은과제가 있다면
        	if(subjectList.size() != 0) {
        		//현재시간이랑 다음 과제의 시작시간이 일치한다면
        		if(subjectList.get(0).start == curTime) {
        			//하던과제를 넣고
        			stack.add(cur);
        			//현재 과제 갱신
        			cur = subjectList.get(0);
        			subjectList.remove(0);
        		}
        	}
        	
        	if(stack.isEmpty() && subjectList.size() == 0) {
        		if(cur!= null)
        			answerTmp.add(cur.name);
        		break;
        	}
		}
    	return answerTmp.toArray(answer);
    }
}