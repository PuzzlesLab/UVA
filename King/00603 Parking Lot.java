import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	private static class Auto {
		int initial, curr, parked=-1;
		public Auto (int i) { this.initial=i; this.curr=i;}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();//empty.
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			ArrayList<Auto> autos=new ArrayList<>();
			while (true) {
				int pos=Integer.parseInt(br.readLine());
				if (pos==99) break;
				autos.add(new Auto(pos-1));
			}
			
			ArrayList<Integer> vacant=new ArrayList<>();
			while (true) {
				String s=br.readLine();
				if (s==null || s.equals("")) break;
				vacant.add(Math.floorMod(Integer.parseInt(s)-1, 20));
			}
			
			for (int vac : vacant) {
				Auto closestAuto=null;
				int closest=30;
				for (Auto a : autos) if (a.parked==-1) {
					int dist=0;
					if (a.curr>vac) dist=vac+20-a.curr;
					else dist=vac-a.curr;
					
					if (dist<closest) {
						closest=dist;
						closestAuto=a;
					}
				};
				if (closestAuto!=null) {
					closestAuto.parked=vac;
					for (Auto a : autos) if (a.parked==-1) a.curr=(a.curr+closest)%20;
				}
			}
			
			if (testCase>0) System.out.println();
			for (Auto a : autos) {
				if (a.parked!=-1) System.out.printf("Original position %d parked in %d\n", a.initial+1, a.parked+1);
				else System.out.printf("Original position %d did not park\n", a.initial+1);
			}
			
		}
	}

}
