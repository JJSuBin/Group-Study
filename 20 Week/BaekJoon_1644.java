import java.io.*;
import java.util.*;

public class BaekJoon_1644 {
	static boolean[] isPrime;
	static ArrayList<Integer> prime=new ArrayList<>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		isPrime=new boolean[n+1];
		
		// 소수 구하기 -> 에라토스테네스의 채 
		isPrime[0]=isPrime[1]=true;
		for(int i=2;i*i<=n;i++) {
			if(!isPrime[i]) {
				for(int j=i*i;j<=n;j+=i)
					isPrime[j]=true; // 소수가 아니면 true로 변경
			}
		}
		
		// 1~n 사이의 소수를 연결리스트에 삽입
		for(int i=1;i<=n;i++)
			if(!isPrime[i])
				prime.add(i);
		
		// 슬라이딩 윈도우
		int left=0, right=0, sum=0, count=0;
		while(true) {
			if(sum>=n) // 구간 합이 n보다 크다면 구간 합에서 왼쪽의 값 하나 뺴기
				sum-=prime.get(left++);
			else if(right==prime.size()) // right가 1~n 사이의 마지막 소수에 도달했다면 종료
				break;
			else // 구간 합이 n보다 작다면 오른쪽 값 추가
				sum+=prime.get(right++);
			
			if(sum==n) // 구간 합이 n인 경우를 찾은 경우
				count++;
		}
		
		System.out.println(count);
	}

}
