import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet; // Tree Set will give TLE
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int person=3;
			HashSet<Integer> [] owns=new HashSet [person];
			int [] size=new int [person];
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<owns.length;i++) {
				owns[i]=new HashSet<>();
				size[i]=Integer.parseInt(st.nextToken());
			}

			for (int p=0;p<person;p++) {
				st=new StringTokenizer(br.readLine());
				for (int i=0;i<size[p];i++) owns[p].add(Integer.parseInt(st.nextToken()));
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase);
			sb.append(":\n");
			for (int p=0;p<owns.length;p++) {
				int pNext=(p+1)%person;
				int pNextNext=(p+2)%person;

				if (owns[pNext].size()>owns[pNextNext].size()) { //Lazy condition evaluation optimization
					int temp=pNext;
					pNext=pNextNext;
					pNextNext=temp;
				}

				int onlyMe=0;
				for (int choco : owns[p]) if (!owns[pNext].contains(choco) && !owns[pNextNext].contains(choco)) onlyMe++;
				
				int onlyOthers=0;
				for (int choco : owns[pNext]) if (owns[pNextNext].contains(choco) && !owns[p].contains(choco)) onlyOthers++;
				
				sb.append(onlyMe);
				sb.append(' ');
				sb.append(onlyOthers);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}
}
