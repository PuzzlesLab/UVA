import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			
			ArrayList<Long> list=new ArrayList<>();
			list.add(0L);
			for (int p=1;p<=P;p++) {
				ArrayList<Long> listNext=new ArrayList<>();
				for (long i : list) {
					listNext.add(i*10+1);
					listNext.add(i*10+2);
				}
				list=listNext;
			}
			Collections.sort(list);
			
			long min=Long.MAX_VALUE;
			long max=Long.MIN_VALUE;
			int pow2=(int)Math.pow(2,Q);
			if (list.size()>0) {
				for (int i=0;i<list.size();i++) if (list.get(i)%pow2==0) {
					min=list.get(i);
					break;
				}
				for (int i=list.size()-1;i>=0;i--) if (list.get(i)%pow2==0) {
					max=list.get(i);
					break;
				}
			}
			
			System.out.print("Case "+testCase+": ");
			if (min==Long.MAX_VALUE) System.out.println("impossible");
			else if (min==max) System.out.println(min);
			else System.out.println(min+" "+max);
		}
	}

}