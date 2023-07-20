class Solution {
    public int solution(String[] strArr) {
        int answer = 0;
        int[] length  = new int[31];
        for (String s : strArr) {
			length[s.length()] += 1;
		}
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length.length; i++) {
			max = Math.max(length[i], max);
		}
        answer = max;
        return answer;
    }
}