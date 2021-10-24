import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static boolean valid(int [] musicians, int muPerScore, int maxScores) {
		int scoresNeeded=0;
		for (int i=0;i<musicians.length;i++) {
			scoresNeeded+=(musicians[i]/muPerScore);
			if (musicians[i]%muPerScore!=0) scoresNeeded++;
		}
		return scoresNeeded<=maxScores;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int P=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int max=-1;
			int [] musicians=new int [N];
			for (int n=0;n<N;n++) {
				musicians[n]=Integer.parseInt(st.nextToken());
				max=Math.max(musicians[n],max);
			}
			
			int min=1;
			
			int ans=-1;
			while (min<=max) {
				int mid=(min+max)/2;
				if (valid(musicians,mid,P)) {
					ans=mid;
					max=mid-1;
				} else min=mid+1;
			}
			
			System.out.println(ans);
		}
	}

}
