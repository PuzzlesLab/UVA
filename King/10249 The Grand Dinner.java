import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			int [] teamMemCount=new int [M];
			ArrayList<Integer> [] teamMemAssignment=new ArrayList [M];
			for (int m=0;m<M;m++) {
				teamMemCount[m]=Integer.parseInt(st.nextToken());
				teamMemAssignment[m]=new ArrayList<>();
			}
			
			st=new StringTokenizer(br.readLine());
			int [] tableSize=new int [N];
			for (int n=0;n<N;n++) tableSize[n]=Integer.parseInt(st.nextToken());
			
			boolean [][] teamExistsInTable=new boolean [M][N];
			boolean fail=false;
			for (int m=0;m<M && !fail;m++) {
				while (teamMemCount[m]>0) {
					int assignTable=-1;
					for (int n=0;n<N;n++) if (!teamExistsInTable[m][n] && (assignTable==-1 || tableSize[n]>tableSize[assignTable]) && tableSize[n]>0) assignTable=n;
					if (assignTable!=-1) {
						teamExistsInTable[m][assignTable]=true;
						tableSize[assignTable]--;
						teamMemAssignment[m].add(assignTable+1);
						teamMemCount[m]--;
					} else {
						fail=true;
						break;
					}
				}
			}
			if (!fail) {
				StringBuilder sb=new StringBuilder();
				sb.append(1);
				sb.append('\n');
				for (ArrayList<Integer> assign : teamMemAssignment ) {
					for (int table : assign) {
						sb.append(table);
						sb.append(' ');
					}
					sb.setLength(sb.length()-1);
					sb.append('\n');
				}
				System.out.print(sb.toString());
			} else System.out.println(0);
		}

	}

}