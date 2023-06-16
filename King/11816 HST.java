import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Item {
		String name;
		long pst,gst,hst;
		
		private static long parseRate(String s) {
			s=s.substring(0,s.length()-1);
			return (long)(Double.parseDouble(s)*100.0+0.5);
		}

		public Item(String line) {
			StringTokenizer st=new StringTokenizer(line);
			this.name=st.nextToken();
			this.pst=parseRate(st.nextToken());
			this.gst=parseRate(st.nextToken());
			this.hst=parseRate(st.nextToken());
		}
	}

	private static long parseValue(String s) {
		s=s.substring(1);
		return (long)(Double.parseDouble(s)*100.0+0.5);
	}

	private static long calc(long v, long multi) {
		return (v*multi+5000)/10000;
	}

	public static void main(String[] args) throws Exception {
		// Problem description is misleading. WA if you do every item in long + round.
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			HashMap<String,Item> items=new HashMap<>();
			for (int n=0;n<N;n++) {
				Item item=new Item(br.readLine());
				items.put(item.name,item);
			}

			long v1=0;
			long v2=0;
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				Item item=items.get(st.nextToken());
				long v=parseValue(st.nextToken());

				long pv=calc(v,item.pst);
				long gv=calc(v,item.gst);
				long hv=calc(v,item.hst);

				v1+=pv+gv;
				v2+=hv;
			}
			System.out.printf("%.2f\n",(v2-v1)/100.0);
		}
	}

}
