class Solution {
	public String[] solution(int n, int[] arr1, int[] arr2) {
		//정답 배열
		String[] answer = new String[n];
		//계산해서 넣을 배열
		for (int i = 0; i < n; i++) {
			answer[i] = Integer.toBinaryString((arr1[i] | arr2[i]));
		}
		for (int i = 0; i < n; i++) {
			String tmp = answer[i].replace("0", " ").replace("1", "#");
			if(tmp.length()<n) {
				//앞자리 공백 채워주기
				int length = tmp.length();
				for (int j = 0; j < n-length; j++) {
					tmp = " " + tmp;
				}
			}
			answer[i] = tmp;
		}
		return answer;
	}
}