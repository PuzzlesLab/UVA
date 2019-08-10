import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class Main {
	
	private static class Entry {
		public String dest, country, admd, prmd, on;
		
		public Entry (String d, String c, String a, String p, String o) {
			this.dest=d;
			this.country=c;
			this.admd=a;
			this.prmd=p;
			this.on=o;
		}
		
		public boolean possible(String c, String a, String p, String o) {
			boolean flag1=this.country.equals("*") || this.country.equals(c);
			boolean flag2=this.admd.equals("*") || this.admd.equals(a);
			boolean flag3=this.prmd.equals("*") || this.prmd.equals(p);
			boolean flag4=this.on.equals("*") || this.on.equals(o);
			
			return flag1 && flag2 && flag3 && flag4;
		}
	}
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCase=1;
		while (sc.hasNext()) {
			int M=Integer.parseInt(sc.next());
			HashMap<String, ArrayList<Entry>> map=new HashMap<>();
			for (int m=0;m<M;m++) {
				String name=sc.next();
				int I=Integer.parseInt(sc.next());
				map.put(name, new ArrayList<>());
				for (int i=0;i<I;i++) {
					Entry e=new Entry(sc.next(),sc.next(),sc.next(),sc.next(),sc.next());
					map.get(name).add(e);
				}
			}
			
			StringBuilder sb=new StringBuilder("Scenario # ");
			sb.append(testCase++);
			sb.append('\n');
			
			int N=Integer.parseInt(sc.next());
			for (int n=1;n<=N;n++) {
				String curr=sc.next();

				String country=sc.next();
				String admd=sc.next();
				String prmd=sc.next();
				String on=sc.next();
				
				HashSet<String> visited=new HashSet<>();
				visited.add(curr);
				sb.append(n);
				sb.append(" -- ");
				while (true) {
					String dest=null;
					
					if (map.containsKey(curr)) {
						for (Entry e : map.get(curr)) if (e.possible(country, admd, prmd, on)) {
							dest=e.dest;
							break;
						}
					}

					if (dest==null) {
						sb.append("unable to route at ");
						sb.append(curr);
						break;
					}
					
					if (dest.equals(curr) ) {
						sb.append("delivered to ");
						sb.append(curr);
						break;
					} else if (visited.contains(dest)) {
						sb.append("circular routing detected by ");
						sb.append(dest);
						break;
					} else {
						curr=dest;
						visited.add(curr);
					}
				}
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
		}
	}

}