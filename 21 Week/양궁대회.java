import java.util.*;

public class 양궁대회 {
	static int[] ryan, apeach;
	static int N,max=Integer.MIN_VALUE;
	static ArrayList<int[]> answer=new ArrayList<>();
	public int[] solution(int n, int[] info) {
        ryan=new int[11];
        apeach=new int[11];
        N=n;
       
        for(int i=0;i<11;i++)
        	apeach[i]=info[i];
        
        dfs(0,0);
        
        if(answer.isEmpty()) // 가능한 경우의 수가 없는 경우
        	return new int[] {-1};
        
        // 낮은 점수가 많은 순으로 정렬
        Collections.sort(answer, (o1, o2)->{
        	// 인덱스 10이 과녁의 1점 부분이기 때문에 내림차순으로 정렬 해주어야 함
        	for(int i=10;i>=0;i--)
        		if(o1[i]!=o2[i])
        			return o2[i]-o1[i];
			return 0;
        });
        return answer.get(0);
    }
	
	public static void dfs(int depth, int start) {
		if(depth==N) { // n발을 다 쏜 경우
			int a=0, r=0; // 어피치, 라이언의 점수
			
			for(int i=0;i<11;i++) {
				// 두 선수 모두 맞추지 못한 점수는 넘어간다
				if(apeach[i]==0&&ryan[i]==0)
					continue;
				// 라이언이 더 많이 맞춘 점수일 경우
				if(apeach[i]<ryan[i])
					r+=10-i; 
				// 어피치가 더 많이 맞추거나, 같게 맞춘 경우
				else
					a+=10-i;
			}
			
			// 라이언의 점수가 어피치의 점수보다 높은 경우
			if(r>a) {
				int diff=r-a; // 점수 차 구하기
				if(diff>max) { // max 값 보다 점수 차가 더 많이 난다면
					max=diff; // max 값 갱신
					answer.clear(); // 여태까지의 점수차가 max인 경우의 수 초기화
					answer.add(ryan.clone()); // 현재 경우의 수를 answer에 삽입
				}
				/*
				 * 주의! 
				 * 가장 큰 점수차이가 나는 경우가 여러가지일 경우에는 이후 정렬을 통해
				 * 가장 낮은 점수를 더 많이 맞힌 경우를 return 해야 하므로 answer를 비워주면 안됌, 해당 경우를 추가만 해주어야 한다.
				 * 따라서 위의 조건문에 diff>=max를 적어주먼 8, 18번 케이스에서 실패
				 */
				else if(diff==max)
					answer.add(ryan.clone());
			}
		}
		
		// 백트래킹을 사용하여 라이언의 양궁 점수가 될 수 있는 모든 경우의 수 탐색
		else {
			for(int i=start;i<11;i++) {
				ryan[i]++;
				dfs(depth+1,i);
				ryan[i]--;
			}
		}
		
	}
}
