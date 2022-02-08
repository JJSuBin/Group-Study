import java.util.*;

public class BaekJoon_18870 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] arr=new int[n];
		int[] copy=new int[n];
		
		Map<Integer,Integer> map=new HashMap<>();
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<n;i++)
			arr[i]=sc.nextInt();
		
		for(int i=0;i<n;i++)
			copy[i]=arr[i];
		
		Arrays.sort(copy); // 오름차순 정렬
		
		// 좌표 정렬 : 입력 받은 좌쵸 값을 오름차순으로 정렬했을 때의 순서 표시
		int count=0;
		for(int i=0;i<n;i++)
			if(!map.containsKey(copy[i]))
				// map에 없는 값이라면 좌표 값과 인덱스 삽입
				map.put(copy[i],count++);
		
		for(int i=0;i<n;i++)
			sb.append(map.get(arr[i])+" ");
		
		System.out.println(sb);
	}

}
