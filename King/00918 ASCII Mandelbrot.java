import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static int calcFactor(BigDecimal start, BigDecimal end, BigDecimal step) {
		int count=0;
		while (start.compareTo(end)<=0) {
			start=start.add(step);
			count++;
		}
		return count;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String sSet=st.nextToken();
			sSet=sSet.substring(1,sSet.length()-1);
			sSet=sSet+" ";
			char [] chSet=sSet.toCharArray();
			
			String minIS=st.nextToken();
			String maxIS=st.nextToken();
			String pIS=st.nextToken();
			String minRS=st.nextToken();
			String maxRS=st.nextToken();
			String pRS=st.nextToken();

			double minI=Double.parseDouble(minIS);
			double pI=Double.parseDouble(pIS);
			double minR=Double.parseDouble(minRS);
			double pR=Double.parseDouble(pRS);

			int H=calcFactor(new BigDecimal(minIS),new BigDecimal(maxIS), new BigDecimal(pIS));
			int W=calcFactor(new BigDecimal(minRS),new BigDecimal(maxRS), new BigDecimal(pRS));
			ArrayList<StringBuilder> result=new ArrayList<>();

			double currI=minI;
			for (int x=0;x<H;x++) {
				result.add(new StringBuilder());
				double currR=minR;
				for (int y=0;y<W;y++) {
					int itCount=0;

					// (a+bi)^N, and N = index of char list. Expected output doesn't match with double. :/
					float za=0;
					float zb=0;
					while (itCount<chSet.length-1) {
						float na=za*za-zb*zb;
						float nb=2*za*zb;
						za=(float)(na+currR);
						zb=(float)(nb+currI);
						if (za*za+zb*zb>4) break;
						itCount++;
					}
					currR+=pR;
					result.get(result.size()-1).append(chSet[itCount]);
				}
				currI+=pI;
			}
			
			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			for (int i=0;i<result.size();i++) {
				sb.append(result.get(i));
				sb.append('\n');
			}
			System.out.print(sb);
		}
	}
}
