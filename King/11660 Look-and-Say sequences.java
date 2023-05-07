import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			String seq=st.nextToken();
			ArrayList<Integer> x=new ArrayList<>();
			for (int i=0;i<seq.length();i++) x.add(seq.charAt(i)-'0');
			int I=Integer.parseInt(st.nextToken());
			int j=Integer.parseInt(st.nextToken());

			for (int i=1;i<I;i++) {
				ArrayList<Integer> temp=new ArrayList<>();
				for (int idx=0;idx<x.size();idx++) {
					int idx2=idx;
					while (idx2<x.size() && x.get(idx)==x.get(idx2)) {
						idx2++;
					}
					int count=idx2-idx;
					temp.add(count);
					temp.add(x.get(idx));
					idx=idx2-1;
					if (temp.size()>j*4) break; // Max expand by 1000 times, covering 4 digits is sufficient.
				}
				x=temp;
			}

			System.out.println(x.get(j-1));
		}
	}

}
