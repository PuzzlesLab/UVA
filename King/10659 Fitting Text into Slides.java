import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int M=Integer.parseInt(br.readLine());
			ArrayList<Integer> [] wordLength=new ArrayList[M];
			for (int m=0;m<M;m++) {
				wordLength[m]=new ArrayList<>();
				StringTokenizer st=new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) wordLength[m].add(st.nextToken().length());
			}
			StringTokenizer st=new StringTokenizer(br.readLine());
			int X=Integer.parseInt(st.nextToken()), Y=Integer.parseInt(st.nextToken());
			
			int ans=-1;
			for (int size=28;size>=8;size--) {
				int currX=0, currLine=0, maxLine=Y/size;
				boolean fail=false;
				for (int i=0;i<wordLength.length && !fail;i++) {
					for (int i2=0;i2<wordLength[i].size() && !fail;i2++) {
						int requiredX=wordLength[i].get(i2)*size;
						if (currX+requiredX<=X) currX=Math.min(currX+requiredX+size, X);
						else if (currLine+1<maxLine && requiredX<=X){
							currX=requiredX+size;
							currLine++;
						} else fail=true;
					}
					currX=0;
					currLine++;
				}
				if (!fail) {
					ans=size;
					break;
				}
			}
			
			System.out.println(ans==-1? "No solution" : ans);
		}
	}

}