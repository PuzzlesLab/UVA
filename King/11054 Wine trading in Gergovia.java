import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static class House {
		int index;
		long value;
		public House(int i, long v) {
			this.index=i;
			this.value=v;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			LinkedList<House> sell=new LinkedList<>();
			LinkedList<House> buy=new LinkedList<>();
			for (int n=0;n<N;n++) {
				House h=new House(n,Integer.parseInt(st.nextToken()));
				if (h.value<0) sell.add(h);
				else if (h.value>0) buy.add(h);
			}
			
			long ans=0;
			while (!sell.isEmpty() && !buy.isEmpty()) {
				House firstSell=sell.getFirst();
				House firstBuy=buy.getFirst();
				long v=Math.min(-firstSell.value,firstBuy.value);
				firstSell.value+=v;
				firstBuy.value-=v;
				ans+=(v*Math.abs(firstSell.index-firstBuy.index));
				if (firstSell.value==0) sell.removeFirst();
				if (firstBuy.value==0) buy.removeFirst();
			}
			
			System.out.println(ans);
		}
	}

}