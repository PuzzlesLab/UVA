import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static int secondDiff(int h1, int m1, int s1, int h2, int m2, int s2) {
		int t1=h1*3600+m1*60+s1;
		int t2=h2*3600+m2*60+s2;
		if (t1>t2) t2+=24*3600;
		return t2-t1;
	}
	
	private static int calcLit(int [] lit, int num) {
		if (num==0) return lit[0];
		else {
			int sum=0;
			while (num>0) {
				sum+=lit[num%10];
				num/=10;
			}
			return sum;
		}
	}
	public static void main (String [] args) throws Exception {
		int [] lit= {6,2,5,5,4,5,6,3,7,6};

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		String s;
		
		int lastH=0, lastM=0, lastS=0, home=0, guest=0, currCost=0;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			String op=st.nextToken();
			
			StringTokenizer st2=new StringTokenizer(st.nextToken(), ":");
			int currH=Integer.parseInt(st2.nextToken());
			int currM=Integer.parseInt(st2.nextToken());
			int currS=Integer.parseInt(st2.nextToken());
			
			if (op.equals("START")) {
				lastH=currH;
				lastM=currM;
				lastS=currS;
				home=0;
				guest=0;
				currCost=0;
			} else if (op.equals("SCORE")) {
				int duration=secondDiff(lastH,lastM,lastS,currH,currM,currS);
				currCost+=(calcLit(lit,home)+calcLit(lit,guest))*duration;
				lastH=currH;
				lastM=currM;
				lastS=currS;
				String team=st.nextToken();
				if (team.equals("home")) home+=Integer.parseInt(st.nextToken());
				else if (team.equals("guest")) guest+=Integer.parseInt(st.nextToken());
			} else if (op.equals("END")) {
				int duration=secondDiff(lastH,lastM,lastS,currH,currM,currS);
				currCost+=(calcLit(lit,home)+calcLit(lit,guest))*duration;
				System.out.printf("Case %d: %d\n",testCase++,currCost);
			}
		}
	}
}