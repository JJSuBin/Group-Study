import java.util.*;

class Tree {
	int x,y,age;
	
	public Tree(int x, int y, int age) {
		this.x=x;
		this.y=y;
		this.age=age;
	}
}
public class BaekJoon_16235 {
	static int[][] map,food;
	static int n,m,k,year=0;
	static LinkedList<Tree> trees=new LinkedList<>();
	static Queue<Tree> dead=new LinkedList<>();
	static int[] dx ={-1,-1,-1,0,0,1,1,1};
    static int[] dy ={-1,0,1,-1,1,-1,0,1};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt(); // 맵 크기 nXn
		m=sc.nextInt(); // 초기 나무의 수
		k=sc.nextInt(); // k년 후에 남아있는 나무의 수 구하기
		
		map=new int[n+1][n+1]; // 각 칸에 존재하는 양분의 크기
		food=new int[n+1][n+1]; // 매년 겨울에 추가되는 양분 크기
		
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				food[i][j]=sc.nextInt();
				map[i][j]=5; // 초기 양분의 크기는 5
			}
		}
			
		for(int i=0;i<m;i++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			int a=sc.nextInt();
			
			trees.add(new Tree(x,y,a));
		}
		
		After_1year();
		System.out.println(trees.size());
	}
	
	// 봄-여름-가을-겨울 처리
	static void After_1year() {
		while(true) {
			if(year==k)
				return;
			
			// 봄 
			Iterator<Tree> iterator=trees.iterator(); // 시간초과 나지 않기 위해 Iterator 사용
			while(iterator.hasNext()) {
				Tree tree=iterator.next();
				
				// 칸에 나무의 나이만큼의 양분이 없다면 나무는 바로 죽음
				if(map[tree.x][tree.y]-tree.age<0) {
					dead.add(tree); 
					iterator.remove(); // trees 연결리스트에서 삭제해주기 
				}
				else {
					map[tree.x][tree.y]-=tree.age; // 나무의 나이만큼 양분 주기
					tree.age+=1; // 나무 나이 1살 증가
				}
			}
			
			// 여름 
			while(!dead.isEmpty()) {
				// 봄에 죽은 나무들의 나이/2만큼 양분으로 변환
				Tree tree=dead.poll();
				map[tree.x][tree.y]+=tree.age/2;
			}
			
			// 가을
			// 새로운 연결리스트에 새로 심게된 나무 따로 저장하지 않으면 concurrentmodification 예외 발생
			LinkedList<Tree> newTrees=new LinkedList<>(); 
            for(Tree tree:trees) {
                if (tree.age%5!=0) 
                	continue;
                
                // 나무의 나이이가 5의 배수라면 8방향에 나이가 1인 나무 심기
                for (int d=0;d<8;d++) {
                    int nx=tree.x+dx[d];
                    int ny=tree.y+dy[d];
                    if (nx<1||ny<1||nx>n||ny>n) continue;
                    newTrees.add(new Tree(nx,ny,1));
                }
            }
            // 기존 나무가 저장된 연결리스트에 새롭게 심어진 나무들 추가
            trees.addAll(0,newTrees);
			
			// 겨울
			for(int i=1;i<=n;i++) 
				for(int j=1;j<=n;j++) 
					// 각 칸에 양분 추가
					map[i][j]+=food[i][j];
			
			year++; // 1년 흐름
		}
	}

}
