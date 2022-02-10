import java.io.*;
import java.util.*;

public class BaekJoon_1744 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		int[] num=new int[n];
		
		int minus=0;
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(br.readLine());
			
			// 음수의 개수 count
			if(num[i]<=0)
				minus++;
		}
			
		
		Arrays.sort(num);
		
		int result=0;
		// 두 개의 음수는 곱하는 것이 최댓값
		for(int i=1;i<minus;i+=2)
			result+=num[i-1]*num[i];
		
		// 음수의 개수가 홀수개일 경우 양수와 곱해주면 X -> 음수를 그냥 더해주는 것이 최댓값
		if(minus%2==1)
			result+=num[minus-1];
		
		// 양수의 개수가 홀수개일 경우 가장 작은 양수를 더해준다. -> 큰 양수들은 곱해서 더해주는 것이 최댓값
		if((n-minus)%2==1)
			result+=num[minus];
		
		// 나머지 양수를 그냥 더하는 것과 두 수를 묶어 곱한 값 중 큰 값을 누적
		for(int i=n-1;i>minus;i-=2) {
			int sum=num[i]+num[i-1];
			int mul=num[i]*num[i-1];
			
			if(sum>mul)
				result+=sum;
			else
				result+=mul;
		}
		
		System.out.println(result);
	}

}
