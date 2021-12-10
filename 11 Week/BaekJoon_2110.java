import java.util.*;

public class BaekJoon_2110 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int c=sc.nextInt();
		
		int[] house=new int[n];
		for(int i=0;i<n;i++)
			house[i]=sc.nextInt();
		
		Arrays.sort(house);
		
		int start=1; // 최소거리
		int end=house[n-1]-house[0]; // 최대거리
		
		int result=0;
		while(start<=end) {
			int mid=(start+end)/2; // 공유기 사이의 거리
			int count=1; // 설치해야 하는 공유기의 개수(첫 번째 집은 설치해야 하므로 1로 시작)
			int prev=house[0]; // 첫 번째 집 공유기 설치하고 시작
			
			for(int i=0;i<n;i++) {
				int dis=house[i]-prev; // 거리 계산
				
				if(dis>=mid) { // 공유기를 설치해야 한다면
					count++; // 개수 증가
					prev=house[i]; // 이전 공유기 설치한 곳 갱신
				}
			}
			
			if(count<c)
				end=mid-1;
			else {
				start=mid+1;
				result=mid;
			}
		}
		System.out.println(result);
	}

}
