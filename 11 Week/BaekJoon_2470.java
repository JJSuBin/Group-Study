import java.io.*;
import java.util.*;

public class BaekJoon_2470 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] liquid=new int[n];
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++)
			liquid[i]=Integer.parseInt(st.nextToken());
		
		Arrays.sort(liquid);
		
		int start=0;
		int end=n-1;
		int min=Integer.MAX_VALUE;
		
		int liquid1=0;
		int liquid2=0;
		
		while(start<end) {
			int mid=liquid[start]+liquid[end]; // 두 용액의 합
			int temp=Math.abs(mid); // 두 값 사이의 길이를 구하기 위해 절댓값 계산
			
			if(temp<min) { // 최솟값을 갱신해야 하는 경우
				liquid1=liquid[start];
				liquid2=liquid[end];
				min=temp;
			}
			
			if(mid>0) // 두 용액의 합이 0보다 큰 경우 큰 값 빼기
				end--;
			else  // 두 용액의 합이 0보자 작은 경우 작은 값 빼기
				start++;
		}
		
		System.out.println(liquid1+" "+liquid2);
	}

}
