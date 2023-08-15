import java.util.*;
class Solution {

    public int solution(int[][] board, int[] moves) {
    	//정답개수
        int answer = 0;
        //선입후출구조기떄문에 스택선언
        Stack<Integer> stack = new Stack<>();
        //움직인 횟수 만큼
        for (int i = 0; i < moves.length; i++) {
			//board 길이만큼
        	for (int j = 0; j < board.length; j++) {	
        		//해당 위치에 젱리 위에있는 비어있지않는 공간을 찾음
				if(board[j][moves[i]-1] != 0) {
					//peek을 위해 예외처리
					try {
						if(stack.peek() == board[j][moves[i]-1]) {
							stack.pop();
							answer+=2;
						} else {
							stack.push(board[j][moves[i]-1]);						
						}						
					} catch (EmptyStackException e) {
						stack.push(board[j][moves[i]-1]);
					}
					board[j][moves[i]-1] = 0;
					break;
				}
			}
		}
        return answer;
    }
}