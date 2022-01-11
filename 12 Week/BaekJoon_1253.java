import java.util.*;

public class BaekJoon_1253 {
	static int[] num;
	static int count=0,n;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		num=new int[n];
		
		for(int i=0;i<n;i++)
			num[i]=sc.nextInt();
		
		Arrays.sort(num);
		
		for(int i=0;i<n;i++) {
			int start=0, end=n-1;
			int target=num[i]; // 두 수를 더한 값의 결과가 되야하는 값
			
			while(start<end) {
				int sum=num[start]+num[end]; // 두 수를 더한 값이
				
				// target 보다 작다면 시작 포인트 증가
				if(sum<target)
					start++;
				// target 보다 크다면 끝 포인트 감소
				else if(sum>target)
					end--;
				else { // target과 같고
					// 두 포인트가 i번째 수가 아니라면 count 증가
					if(i!=start&&i!=end) {
						count++;
						break;
					}
					// 두 포인트중 i번째 수가 나오면 안되기 때문에 처리
					if(start==i)
						start++;
					if(end==i)
						end--;
				}
			}
		}
		System.out.println(count);
	}
}
