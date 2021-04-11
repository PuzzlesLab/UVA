import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class Main {

	public static class Weight {
		int reading;
		LinkedList<String> minimized;
		int initialMode=-2, finalMode=-1;
		
		public Weight(ArrayList<String> slist) {
			for (int i=0;i<slist.size();i++) for (char c: slist.get(i).toCharArray()) {
				if (c>='A' && c<='Z') {
					while (c>0) {
						reading+=(c%2==1) ? 50 : 25;
						c>>=1;
					}
				}
			}

			this.minimized=new LinkedList<>();
			for (int i=0;i<slist.size();i++) {
				for (char c: slist.get(i).toCharArray()) if (c!='.') {
					if (this.initialMode==-2) this.initialMode=i;
					this.minimized.addLast(slist.get(i));
					break;
				}
			}
		}
		
		public void setMode(int mode) {
			String line="........";
			if (mode==0) { //higher
				this.minimized.addLast(line);
				this.minimized.addLast(line);
			} else if (mode==1) { //balance
				this.minimized.addFirst(line);
				this.minimized.addLast(line);
			} else if (mode==2) { //lower
				this.minimized.addFirst(line);
				this.minimized.addFirst(line);
			}
			this.finalMode=mode;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		
		StringBuilder ans=new StringBuilder();
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			ArrayList<String> weight1Str=new ArrayList<>();
			ArrayList<String> weight2Str=new ArrayList<>();
			for (int i=0;i<7;i++) {
				String s=br.readLine();
				weight1Str.add(s.substring(0,8));
				weight2Str.add(s.substring(10,18));
			}
			Weight weight1=new Weight(weight1Str);
			Weight weight2=new Weight(weight2Str);
			if (weight1.reading>weight2.reading) {
				weight1.setMode(2);
				weight2.setMode(0);
			} else if (weight1.reading==weight2.reading) {
				weight1.setMode(1);
				weight2.setMode(1);
			} else {
				weight1.setMode(0);
				weight2.setMode(2);
			}
			

			ans.append("Case ");
			ans.append(testCase);
			ans.append(":\n");
			if (weight1.initialMode==weight1.finalMode && weight2.initialMode==weight2.finalMode)
				ans.append("The figure is correct.\n");
			else {
				Iterator<String> weight1It=weight1.minimized.iterator();
				Iterator<String> weight2It=weight2.minimized.iterator();
				for (int i=0;i<7;i++) {
					ans.append(weight1It.next());
					ans.append("||");
					ans.append(weight2It.next());
					ans.append('\n');
				}
			}
			
			br.readLine(); //useless line
		}
		System.out.print(ans.toString());
	}
}
