import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int wx=Integer.parseInt(st.nextToken())-1;
			int wy=Integer.parseInt(st.nextToken())-1;
			
			ArrayList<char []> list=new ArrayList<>();
			String s;
			while (true) {
				s=br.readLine();
				if (s==null || s.length()==0) break;
				list.add(s.toCharArray());
			}
			
			char [][] ch=new char[list.size()][list.get(0).length];
			for (int i=0;i<ch.length;i++) ch[i]=list.get(i);
			
			int count=0;
			Stack<Integer> stkX=new Stack<>();
			Stack<Integer> stkY=new Stack<>();
			stkX.add(wx);
			stkY.add(wy);
			ch[wx][wy]='1';
			while (!stkX.isEmpty()) {
				int x=stkX.pop(), y=stkY.pop();
				count++;
				for (int i=-1;i<2;i++) {
					if (x+i>=0 && x+i<ch.length && ch[x+i][y]=='0') {
						ch[x+i][y]='1';
						stkX.push(x+i);
						stkY.push(y);
					}
					if (y+i>=0 && y+i<ch[0].length && ch[x][y+i]=='0') {
						ch[x][y+i]='1';
						stkX.push(x);
						stkY.push(y+i);
					}
				}
			}
			
			if (testCase>0) System.out.println();
			System.out.println(count);
		}
	}

}