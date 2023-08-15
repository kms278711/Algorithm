//좌표클래스 선언
class Point{
	public int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";      
        //왼손좌표
        Point left = new Point(3, 0);
        //오른손좌표
        Point right = new Point(3, 2);
        for (int i = 0; i < numbers.length; i++) {
        	//3으로 나누어지는 경우 몫-1 , 아니면 몫
        	int x = numbers[i]%3 == 0 ? numbers[i]/3-1:numbers[i]/3;
        	//3으로 나누어지는 경우 2 , 아니면 나머지-1
        	int y = numbers[i]%3 == 0 ? 2:numbers[i]%3-1;
			//1 4 7인경우
        	if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				answer += "L";
				//왼손좌표갱신
				left.x = x;
				left.y = y;
			//3 6 9 인경우
			} else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				answer += "R";
				//오른손좌표 갱신
				right.x = x;
				right.y = y;
			} else {
				//0인경우 재정의
				if(numbers[i] == 0) {
					x = 3;
					y = 1;
				}
				//왼손과의 거리
				int leftDis = Math.abs(x-left.x)+Math.abs(y-left.y);
				//오른손과의 거리
				int rightDis = Math.abs(x-right.x)+Math.abs(y-right.y);
				//왼손거리가 더 멀경우
				if(leftDis > rightDis) {
					answer += "R";
					right.x = x;
					right.y = y;
				//같은경우
				} else if(leftDis == rightDis) {
					//왼손잡이인지 오른손잡이인지 체크
					if(hand.equals("right")) {
						answer += "R";
						right.x = x;
						right.y = y;
					} else {
						answer += "L";
						left.x = x;
						left.y = y;
					}
				//오른손거리가 더 멀경우
				} else {
					answer += "L";
					left.x = x;
					left.y = y;
				}
			}	
		
        }
        return answer;
    }
}