import java.io.*;
import java.util.*;

public class BaekJoon_9024 {
	static int n,k,min;
	static int[] num;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t=Integer.parseInt(br.readLine());
		
		while(t-->0) {
			st=new StringTokenizer(br.readLine());
			
			n=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			min=Integer.MAX_VALUE;
			num=new int[n];
			
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) 
				num[i]=Integer.parseInt(st.nextToken());
			
			Arrays.sort(num);
			
			System.out.println(Two_Pointer());
		}
	}
	
	static int Two_Pointer() {
		int left=0;
		int right=n-1;
		
		int count=0;
		while(left<right) {
			int sum=num[left]+num[right]; // 두 수의 합
			
			if(sum>=k) // 합이 k보다 크다면 큰 수 빼기 -> right--
				right--;
			
			else // 합이 k보다 작으면 더 큰 수 더하기 -> left++
				left++;
			
			/*
			 * min은 k와 k와 가까운 수의 차가 저장된 변수
			 * min이 갱신될 때 마다 k와 가까운 수의 개수를 구하는 count 초기화 
			 */
			if(Math.abs(k-sum)<min) {
				min=Math.abs(k-sum);
				count=1;
			}
			// k와 가까운 정수를 발견하면 count 증가
			else if(Math.abs(k-sum)==min)
				count++;
		}
		
		return count;
	}
}
