import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int [] members=new int[P];
			for (int p=0;p<P;p++) members[p]=Integer.parseInt(st.nextToken());

			int max=(int)Math.pow(2, P);
			int [] powerIndex=new int [P];
			for (int i=0;i<max/2;i++) {
				int side0Count=0;
				int side1Count=0;
				for (int bit=0;bit<P;bit++) {
					if ((i & (1 << bit))==0) side0Count+=members[bit]; 
					else side1Count+=members[bit];
				}
				for (int p=0;p<P;p++) {
					if ((i & (1 << p))==0) {
						if (side0Count>side1Count && side1Count+members[p]>=side0Count-members[p]) powerIndex[p]++;
					} else {
						if (side1Count>side0Count && side0Count+members[p]>=side1Count-members[p]) powerIndex[p]++;
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<P;i++) {
				sb.append("party ");
				sb.append(i+1);
				sb.append(" has power index ");
				sb.append(powerIndex[i]);
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}

}