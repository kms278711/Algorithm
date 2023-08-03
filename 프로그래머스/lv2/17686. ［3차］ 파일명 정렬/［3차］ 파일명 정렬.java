class Solution {
    public String[] solution(String[] files) {
        String[] head = new String[files.length];
        int[] number = new int[files.length];
        for (int i = 0; i < files.length; i++) {
			//head 구하기
        	String tmp = files[i];
			char[] c = tmp.toCharArray();
			int idx = 0;
			for (int j = 0; j < c.length; j++) {
				if(Character.isDigit(c[j])) {
					idx = j;
					break;
				}
				else head[i] += c[j];
			}
			head[i] = head[i].toUpperCase();
			//숫자 구하기
			String numberTmp = "";
			for (int j = idx; j < c.length; j++) {
				if(!Character.isDigit(c[j])) {
					idx = j;
					break;
				}
				else numberTmp += c[j];
			}
			number[i] = Integer.parseInt(numberTmp);
		}
        
        for (int i = 0; i < files.length-1; i++) {
			for (int j = 0; j < files.length-i-1; j++) {
				if(head[j].equals(head[j+1])) {
					if(number[j] > number[j+1]) {
						//파일 교환
						String tmp = files[j];
						files[j] = files[j+1];
						files[j+1] = tmp;
						
						//number 교환
						int numberTmp = number[j];
						number[j] = number[j+1];
						number[j+1] = numberTmp;
					}
				} else {
					char[] c1 = head[j].toCharArray();
					char[] c2 = head[j+1].toCharArray();
					int idx = 0;
					while(true) {
						if(head[j].length() > head[j+1].length() && idx == Math.min(head[j].length(), head[j+1].length())) {
							String tmp = files[j];
							files[j] = files[j+1];
							files[j+1] = tmp;
							
							//head 교환						
							tmp = head[j];
							head[j] = head[j+1];
							head[j+1] = tmp;
							
							//number 교환
							int numberTmp = number[j];
							number[j] = number[j+1];
							number[j+1] = numberTmp;
							break;
						}
						if(idx==Math.min(head[j].length(), head[j+1].length())) break;
						if(c1[idx] == c2[idx]) {
							idx++;
						} else {
							if((int)c1[idx]>(int)c2[idx]) {
								String tmp = files[j];
								files[j] = files[j+1];
								files[j+1] = tmp;
								
								//head 교환						
								tmp = head[j];
								head[j] = head[j+1];
								head[j+1] = tmp;
								
								//number 교환
								int numberTmp = number[j];
								number[j] = number[j+1];
								number[j+1] = numberTmp;
							}
							break;
						}
					}
				}
			}
		}
        return files;
    }
}