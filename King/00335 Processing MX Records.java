import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static class LookupEntry {
		public String source, destination;
		public int weight;
		public boolean regex, down;
		
		public LookupEntry(String src, String dest, int w, boolean rgx) {
			this.source=src;
			this.destination=dest;
			this.weight=w;
			this.regex=rgx;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		ArrayList<LookupEntry> list=new ArrayList<>();
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			boolean usePrevSrc = st.countTokens()==2;

			String src=usePrevSrc ? list.get(list.size()-1).source : st.nextToken();
			int weight=Integer.parseInt(st.nextToken());
			String dest=st.nextToken();
			boolean rgx=usePrevSrc ? list.get(list.size()-1).regex : src.charAt(0)=='*';
			if (rgx && !usePrevSrc) src=src.substring(1);
			list.add(new LookupEntry(src, dest, weight, rgx));

		}
		StringBuilder sb=new StringBuilder();
		String s;
		while (!(s=br.readLine()).equals("X")) {
			StringTokenizer st=new StringTokenizer(s);
			char op=st.nextToken().charAt(0);
			String target=st.nextToken();
			if (op == 'U') {
				for (LookupEntry le : list) if (le.destination.equals(target)) le.down=false;
			} else if (op == 'D') {
				for (LookupEntry le : list) if (le.destination.equals(target)) le.down=true;
			}
			else if (op == 'A') {
				String result="";
				int lowestWeight=Integer.MAX_VALUE;
				for (LookupEntry le : list) if (!le.down) 
					if (((!le.regex && le.source.equals(target)) || (le.regex && target.endsWith(le.source))) && le.weight<lowestWeight) {
						result=" "+le.destination;
						lowestWeight=le.weight;
					}
				sb.append(target);
				sb.append(" =>");
				sb.append(result);
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}

}
