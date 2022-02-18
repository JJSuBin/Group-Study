public class k진수에서_소수_개수_구하기 {
	public int solution(int n, int k) {
        int answer=0;
        
        // n을 k진수로 바꾼 수를 0을 기준으로 쪼갠다.
        String[] k_num=trans(n,k).split("0");
        for(String num:k_num) {
        	System.out.println(num);
        	
        	if(num.equals(""))
        		continue;
        	if(isPrime(Long.valueOf(num)))
        		answer++;
        }
        
        return answer;
    }
	
	// n을 k진수로 변환한 숫자를 반환하는 함수
	static String trans(int n, int k) {
		String result="";
		
		while(n>0) {
			// 새롭게 도출되는 나머지 값이 맨 앞으로 와야하기 때문에 이전 결과를 저장한 result를 뒤에 붙인다.
			result=n%k+result; 
			n/=k;
		}
		
		return result;
	}

	// 소수인 경우 true, 아닌경우 false return하는 함수
	static boolean isPrime(long num) {
		if(num<=1) // 음수인 경우 false return
			return false;
		else if(num==2) // 소수 2인 경우 true return
			return true;
		
		for(int i=2;i<=Math.sqrt(num);i++)
			if(num%i==0) // 다른 수와 나눠 떨어지면 소수가 x
				return false;
		
		return true;
	}
}
