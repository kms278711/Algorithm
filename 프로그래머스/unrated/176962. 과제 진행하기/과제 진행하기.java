import java.util.*;

class Subject{
	public String name;
	public int start, playtime;
	
	public Subject(String name, String start, String playtime) {
		super();
		this.name = name;
		this.start = Integer.parseInt(start.split(":")[0]) * 60 + Integer.parseInt(start.split(":")[1]);
		this.playtime = Integer.parseInt(playtime);
	}
	
}
class Solution {
    public String[] solution(String[][] plans) {
    	String[] answer = new String[plans.length]; 
        List<String> answerTmp = new ArrayList<>();
        List<Subject> subjectList = new ArrayList<>();
        for (int i = 0; i < plans.length; i++) {
			subjectList.add(new Subject(plans[i][0], plans[i][1], plans[i][2]));
		}
        Collections.sort(subjectList, new Comparator<Subject>() {
			@Override
			public int compare(Subject o1, Subject o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});
        Stack<Subject> stack = new Stack<>();
        Subject cur = subjectList.get(0);
        subjectList.remove(0);
        int curTime = cur.start;
        while(true) {
        	curTime ++;
        	if(cur != null) {
        		cur.playtime--;
        		if(cur.playtime == 0) {
        			answerTmp.add(cur.name);
        			if(!stack.isEmpty()) {
        				cur = stack.pop();
        			} else {
        				cur = null;
        			}
        		}
        	}
        	
        	if(subjectList.size() != 0) {
        		if(subjectList.get(0).start == curTime) {
        			stack.add(cur);
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