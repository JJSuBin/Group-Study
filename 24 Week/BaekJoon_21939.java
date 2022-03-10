import java.util.*;

class Hard implements Comparable<Hard> {
	int num, level;
	
	public Hard(int num, int level) {
		this.num=num;
		this.level=level;
	}
	
	@Override
	public int compareTo(Hard o) {
		// TODO Auto-generated method stub
		if(this.level==o.level) // 난이도가 같다면 문제 번호가 큰 것 출력
			return o.num-this.num;  
		
		return o.level-this.level; // 난이도가 높은 문제부터 출력
	}
}

class Easy implements Comparable<Easy> {
	int num, level;
	
	public Easy(int num, int level) {
		this.num=num;
		this.level=level;
	}
	
	@Override
	public int compareTo(Easy o) {
		// TODO Auto-generated method stub
		if(this.level==o.level) // 난이도가 같다면 문제 번호가 작은 것 출력
			return this.num-o.num;
		
		return this.level-o.level; // 난이도가 낮은 문제부터 출력
	}
	
}

public class BaekJoon_21939 {
	static PriorityQueue<Hard> hard=new PriorityQueue<>(); // 난이도 어려운 순으로 문제를 정렬하는 우선순위 큐
	static PriorityQueue<Easy> easy=new PriorityQueue<>(); // 난이도 쉬운 순으로 문제를 정렬하는 우선순위 큐
	static int n,m;
	static int[] level=new int[100001];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		
		for(int i=0;i<n;i++) {
			int p=sc.nextInt(); // 문제번호
			int l=sc.nextInt(); // 난이도
			
			hard.add(new Hard(p,l));
			easy.add(new Easy(p,l));
			
			level[p]=l; // 문제번호 p의 난이도 저장
		}
		
		m=sc.nextInt();
		for(int i=0;i<m;i++) {
			String op=sc.next();
			
			if(op.equals("add")) { // 문제번호 p의 난이도가 l인 문제 각 우선순위 큐에 삽입
				int p=sc.nextInt();
				int l=sc.nextInt();
				
				hard.add(new Hard(p,l));
				easy.add(new Easy(p,l));
				
				level[p]=l;
			}
			else if(op.equals("recommend")) {
				int x=sc.nextInt();
				
				if(x==1) { // 가장 어려운 번호의 문제 출력
					while(level[hard.peek().num]!=hard.peek().level)
						hard.poll();
					
					System.out.println(hard.peek().num);
				}
				else { // 가장 쉬운 문제의 번호 출력
					while(level[easy.peek().num]!=easy.peek().level)
						easy.poll();
					
					System.out.println(easy.peek().num);
				}
			}
			else if(op.equals("solved")) { // 문제번호 p 제거
				int p=sc.nextInt();
				level[p]=0;
			}
		}
	}
	
}
