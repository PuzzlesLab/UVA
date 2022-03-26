import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	private static int Min;
	private static int Max;
	private static StringBuilder [] Ans;
	
	private static void find(String [] selections, int currSize, List<String> newspapers, boolean [] selected, int currStart) {
		if (currSize>=Min && currSize<=Max) {
			StringBuilder currAns=new StringBuilder();
			for (int i=0;i<currSize;i++) {
				currAns.append(selections[i]);
				currAns.append(", ");
			}
			currAns.setLength(currAns.length()-2);
			currAns.append('\n');
			Ans[currSize].append(currAns);
		}
		if (currSize<Max) {
			for (int i=currStart;i<newspapers.size();i++) if (!selected[i]) {
				selected[i]=true;
				selections[currSize]=newspapers.get(i);
				find(selections,currSize+1,newspapers,selected,i);
				selected[i]=false;
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); //empty
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			Min=1;
			Max=999;
			if (st.countTokens()==1) {
				String temp=st.nextToken();
				if (!temp.equals("*")) {
					Min=Integer.parseInt(temp);
					Max=Min;
				}
			} else if (st.countTokens()==2) {
				Min=Integer.parseInt(st.nextToken());
				Max=Integer.parseInt(st.nextToken());
			}
			
			ArrayList<String> newspapers=new ArrayList<>();
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				newspapers.add(s);
			}
			if (Max==999) Max=newspapers.size();
			
			String [] selections=new String[Max];
			boolean [] selected = new boolean[newspapers.size()];
			Ans=new StringBuilder[Max+1];
			for (int i=0;i<Ans.length;i++) {
				Ans[i]=new StringBuilder();
				Ans[i].append("Size ");
				Ans[i].append(i);
				Ans[i].append('\n');
			}
			find(selections,0,newspapers,selected,0);
			
			StringBuilder sb=new StringBuilder();
			if (testCase>0) sb.append('\n');
			for (int i=Min;i<=Max;i++) {
				sb.append(Ans[i]);
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}

}