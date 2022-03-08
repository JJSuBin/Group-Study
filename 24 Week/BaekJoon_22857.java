import java.util.*;

public class BaekJoon_22857 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		int n=sc.nextInt();
		int k=sc.nextInt();
		
		boolean flag=false;
		int[] arr=new int[50001];
		
		for(int i=0;i<n;i++) {
			arr[i]=sc.nextInt();
			
			if(arr[i]%2==0)
				flag=true;
		}
		
		int left=0,right=0;
		int count=arr[0]%2; // 홀수의 개수 count
		int result=0;
		
		while(left<=right&&right<n) {
			if(count<=k&&right+1<n) { // right 오른쪽으로 한 칸 옮기기
				right++;
				
				if(arr[right]%2==1) // 새롬게 추가되는 수가 홀수라면 count 값 조정
					count++;
			}
			else { // left 오른쪽으로 한 칸 옮기기
				if(arr[left]%2==1) // 범위에서 빠지게 되는 수가 홀수라면 count 값 조정
					count--;
				
				left++;
			}
			
			if(count<=k) {
				result=Math.max(result,(right-left+1)-count);
			}
		}
		
		if(flag)
			result=Math.max(1, result);
		
		System.out.println(result);
	}

}
