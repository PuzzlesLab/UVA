import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			int [] credits=new int [N];

			for (int n=0;n<N;n++) credits[n]=Integer.parseInt(br.readLine());
			Arrays.sort(credits);

			ArrayList<Integer> uniqCredits=new ArrayList<>();
			int [] endIdx=new int [161];
			for (int n=0;n<N;n++) {
				if (n==0 || credits[n-1]!=credits[n]) uniqCredits.add(credits[n]);
				endIdx[credits[n]]=n;
			}

			int ansA=0;
			int ansB=1;
			int ansC=2;
			int UN=uniqCredits.size();
			if (UN>=4) {
				int minDiff=100000000;

				for (int a=0;a<uniqCredits.size();a++) {
					int sizeA=Math.abs(((endIdx[uniqCredits.get(a)]+1)<<2)-N);  // Size*4 instead of avg/4.
					for (int b=a+1;b<uniqCredits.size();b++) {
						int sizeB=Math.abs((endIdx[uniqCredits.get(b)]-endIdx[uniqCredits.get(a)]<<2)-N);
						for (int c=b+1;c<uniqCredits.size();c++) {
							int sizeC=Math.abs((endIdx[uniqCredits.get(c)]-endIdx[uniqCredits.get(b)]<<2)-N);
							int sizeD=Math.abs((endIdx[uniqCredits.get(uniqCredits.size()-1)]-endIdx[uniqCredits.get(c)]<<2)-N);
							int diff=sizeA+sizeB+sizeC+sizeD;
							//System.out.println(uniqCredits.get(a)+", "+uniqCredits.get(b)+", "+uniqCredits.get(c)+" >> "+diff+", "+Arrays.toString(sizes)+", AVG="+avg);
							if (diff<minDiff) {
								minDiff=diff;
								ansA=uniqCredits.get(a);
								ansB=uniqCredits.get(b);
								ansC=uniqCredits.get(c);
							}
						}
					}
				}
			} else if (UN==3) {
				ansB=Math.max(uniqCredits.get(0),ansB);
				ansC=Math.max(uniqCredits.get(1),ansC);
			}
			else if (UN==2) ansC=Math.max(ansC,uniqCredits.get(0));

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ansA);
			sb.append(' ');
			sb.append(ansB);
			sb.append(' ');
			sb.append(ansC);
			System.out.println(sb.toString());
		}
	}

}