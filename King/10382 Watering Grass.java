import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			double wHalf=W/2.0;

			ArrayList<Double []> list=new ArrayList<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				double l=Double.parseDouble(st.nextToken());
				double r=Double.parseDouble(st.nextToken());
				if (r>wHalf) {
					double c=Math.sqrt(r*r-wHalf*wHalf);
					list.add(new Double [] {Math.max(0, l-c), l+c});
				}
			}
			
			list.add(new Double [] {L+W+2.0, L+W+2.0});
			Collections.sort(list, (a,b) -> {
				int cmp=Double.compare(a[0],b[0]);
				return cmp==0 ? Double.compare(a[1], b[1]) : cmp;
			});

			double end=0;
			int index=0, sol=0;
			while (index<list.size() && end<L) {
				boolean found=false;
				double tempEnd=end;
				for (;index<list.size();index++) {
					if (list.get(index)[0] <= tempEnd) {
						end=Math.max(list.get(index)[1], end);
						found=true;
					} else break;
				}
				if (found) sol++;
				else {
					end=-1;
					break;
				}
			}

			if (end<L) System.out.println(-1);
			else System.out.println(sol);
		}
	}

}