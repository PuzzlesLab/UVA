import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static class Line implements Comparable<Line> {
		String date;
		int id;
		BigDecimal amount;
		boolean bad;
		
		public Line(String d, int i, BigDecimal a) {
			this.date=d;
			this.id=i;
			this.amount=a;
		}

		public int compareTo(Line l) {
			return this.id-l.id;
		}
	}

	public static void main (String [] args) throws Exception {
		final BigDecimal MAX=new BigDecimal("1000000");

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		br.readLine(); // Empty
		for (int n=0;n<N;n++) {
			ArrayList<Line> entries=new ArrayList<>();
			String s;
			while ((s=br.readLine())!=null) {
				if (s.isEmpty()) break;
				StringTokenizer st=new StringTokenizer(s);
				String date=st.nextToken();
				int id=Integer.parseInt(st.nextToken());
				BigDecimal amount=st.hasMoreTokens() ? new BigDecimal(st.nextToken()) : BigDecimal.ZERO;
				if (amount.compareTo(MAX)>=0) continue;
				amount=amount.setScale(2,RoundingMode.DOWN);
				entries.add(new Line(date,id,amount));
			}
			
			Collections.sort(entries);
			for (int i=1;i<entries.size();i++) {
				Line entry=entries.get(i);
				if (entry.id!=entries.get(i-1).id+1) entry.bad=true;
			}
			int h=entries.size()/3+(entries.size()%3==0?0:1);
			StringBuilder [] printLines=new StringBuilder [h];
			for (int i=0;i<h;i++) {
				int i2=i+h;
				int i3=i2+h;
				if (printLines[i]==null) printLines[i]=new StringBuilder();

				Line l1=entries.get(i);
				Line l2=i2<entries.size()?entries.get(i2):null;
				Line l3=i3<entries.size()?entries.get(i3):null;
				printLines[i].append(String.format("%4d",l1.id));
				printLines[i].append(l1.bad?'*':' ');
				printLines[i].append(String.format("%10s",l1.amount));
				printLines[i].append(' ');
				printLines[i].append(l1.date);
				
				if (l2!=null) {
					printLines[i].append("   ");
					printLines[i].append(String.format("%4d",l2.id));
					printLines[i].append(l2.bad?'*':' ');
					printLines[i].append(String.format("%10s",l2.amount));
					printLines[i].append(' ');
					printLines[i].append(l2.date);
				}
				
				if (l3!=null) {
					printLines[i].append("   ");
					printLines[i].append(String.format("%4d",l3.id));
					printLines[i].append(l3.bad?'*':' ');
					printLines[i].append(String.format("%10s",l3.amount));
					printLines[i].append(' ');
					printLines[i].append(l3.date);
				}
			}
			
			StringBuilder ans=new StringBuilder();
			if (n>0) ans.append('\n');
			for (int i=0;i<printLines.length;i++) {
				ans.append(printLines[i]);
				ans.append('\n');
			}
			System.out.print(ans);
		}
	}

}