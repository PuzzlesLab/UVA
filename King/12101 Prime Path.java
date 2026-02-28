import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static boolean [] NotPrime=new boolean [10000];
	
	private static class Tuple {
		int n;
		int step;
		
		public Tuple(int n, int s) {
			this.n=n;
			this.step=s;
		}
	}

	private static void eSieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
	}

	public static void main(String [] args) throws Exception {
		eSieve();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			int cost=-1;
			boolean [] visited=new boolean [10000];
			LinkedList<Tuple> q=new LinkedList<>();
			visited[from]=true;
			q.add(new Tuple(from,0));
			while (!q.isEmpty()) {
				Tuple curr=q.removeFirst();
				if (curr.n==to) {
					cost=curr.step;
					break;
				}
				
				for (int pos=0;pos<4;pos++) {
					int [] digits=new int [] {curr.n/1000,(curr.n/100)%10,(curr.n/10)%10,curr.n%10};
					for (int n=0;n<10;n++) {
						digits[pos]=n;
						int next=digits[0]*1000+digits[1]*100+digits[2]*10+digits[3];
						if (next<1000) continue;

						if (NotPrime[next]) continue;
						if (visited[next]) continue;
						
						if (!NotPrime[next]) {
							visited[next]=true;
							q.addLast(new Tuple(next,curr.step+1));
						}
					}
				}
			}
			
			System.out.println(cost==-1?"Impossible":cost);
		}
	}

}
