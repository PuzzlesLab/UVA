import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] vessels=new int [N];
			int sum=0;
			int maxV=0;
			for (int n=0;n<N;n++) {
				vessels[n]=Integer.parseInt(st.nextToken());
				sum+=vessels[n];
				maxV=Math.max(vessels[n], maxV);
			}
			
			int ans=0;
			int min=maxV;
			int max=sum+1;
			while (min<max) {
				int mid=(min+max)/2;
				if (canFill(mid, M, vessels)) {
					ans=mid;
					max=mid;
				} else min=mid+1;
			}
			
			System.out.println(ans);
		}
	}
	
	public static boolean canFill(int c, int containerCount, int [] vessels) {
		int remContainerLevel=0;
		int cc=0;
		for (int i=0;i<vessels.length;i++) {
			int req=vessels[i];
			if (req>remContainerLevel) {
				remContainerLevel=c;
				cc++;
				if (cc>containerCount) return false;
			}
			remContainerLevel-=req;
		}
		return true;
	}

}