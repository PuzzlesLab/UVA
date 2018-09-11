import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    
	private static class Data implements Comparable<Data> {
		private String [] d;
		
		public Data(StringTokenizer st, String dn) {
			this.d=new String[8];
			for (int i=0;i<7;i++) this.d[i]=st.nextToken();
			this.d[7]=dn;
		}

		public String toString() {
			return String.format("%s %s %s\n%s\nDepartment: %s\nHome Phone: %s\nWork Phone: %s\nCampus Box: %s",
								 this.d[0], this.d[1], this.d[2], this.d[3], this.d[7],
								 this.d[4], this.d[5], this.d[6]);
		}
		
		@Override
		public int compareTo(Data n) {
			if (this.d[2].equals(n.d[2])) return this.d[7].compareTo(n.d[7]);
			return this.d[2].compareTo(n.d[2]);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int dc=Integer.parseInt(br.readLine());
		String dn;
		PriorityQueue<Data> list=new PriorityQueue<>();
		for (int i=0;i<dc;i++) {
			dn=br.readLine();
			String s;
			while ((s=br.readLine())!=null) {
				if (s.length()==0) break;
				list.offer(new Data(new StringTokenizer(s, ","), dn));
			}
		}
		StringBuilder sb=new StringBuilder();
		while (list.size()>0) {
			sb.append("----------------------------------------\n");
			sb.append(list.poll().toString());
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}

}