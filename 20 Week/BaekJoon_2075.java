import java.io.*;
import java.util.*;

public class BaekJoon_2075 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		int[] arr=new int[n*n]; 
		
		int index=0;
		// 2차원 배열을 1차원으로 입력 받기
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[index++]=Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.sort(arr); // 오름차순으로 정렬 
		
		// n*n개의 숫자 중 뒤에서 n번째 숫자 출력(=n번째로 큰 수), 오름차순 정렬했기 때문
		System.out.println(arr[n*n-n]);
	}

}
