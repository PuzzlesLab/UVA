import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int R=Integer.parseInt(br.readLine());
			int [][] occurence=new int [128][128];
			boolean [] hasReplacement=new boolean [128];
			for (int r=0;r<R;r++) {
				StringTokenizer st=new StringTokenizer(br.readLine(),"->");
				char src=st.nextToken().charAt(0);
				hasReplacement[src]=true;
				for (char c : st.nextToken().toCharArray()) occurence[src][c]++;
			}
			
			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				char [] text=st.nextToken().toCharArray();
				char qc=st.nextToken().charAt(0);
				int N=Integer.parseInt(st.nextToken());
				BigInteger [] cCount=new BigInteger [128];
				Arrays.fill(cCount, BigInteger.ZERO);
				for (char c: text) cCount[c]=cCount[c].add(BigInteger.ONE);
				
				for (int n=0;n<N;n++) {
					BigInteger [] nextCCount=new BigInteger [128];
					Arrays.fill(nextCCount, BigInteger.ZERO);
					for (int i=0;i<128;i++) {
						if (!hasReplacement[i]) nextCCount[i]=nextCCount[i].add(cCount[i]);
						else {
							for (int next=0;next<128;next++) nextCCount[next]=nextCCount[next].add(cCount[i].multiply(BigInteger.valueOf(occurence[i][next])));
						}
					}
					cCount=nextCCount;
				}
				
				System.out.println(cCount[qc]);
			}
		}
	}
}