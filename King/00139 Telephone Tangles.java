import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static class Entry {
		String prefix, dest;
		int unitCost;
		
		public Entry (String p, String d, int u) {
			this.prefix=p;
			this.dest=d;
			this.unitCost=u;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		ArrayList<Entry> entries=new ArrayList<>();
		while (!(s=br.readLine()).equals("000000")) {
			String prefix=s.substring(0, s.indexOf(' '));
			s=s.substring(s.indexOf(' ')+1);
			String dest=s.substring(0, s.indexOf('$'));
			int unitCost=Integer.parseInt(s.substring(s.indexOf('$')+1));
			
			boolean ok=false;
			if (prefix.startsWith("00") && prefix.length()<=5) ok=true;
			else if (prefix.startsWith("0") && prefix.length()<=6) ok=true;
			
			if (ok) entries.add(new Entry(prefix, dest, unitCost));
		}
		
		ArrayList<String []> ans=new ArrayList<>();
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s);
			String num=st.nextToken();
			int dur=Integer.parseInt(st.hasMoreTokens() ? st.nextToken() : br.readLine());
			String [] data=new String [6];
			data[0]=num;
			
			boolean isLocal=!num.startsWith("0");
			Entry destEntry=null;
			
			boolean validNum=false;
			if (num.startsWith("00")) validNum=num.length()>=7 && num.length()<=15;
			else if (num.startsWith("0")) validNum=num.length()>=6 && num.length()<=13;
			else validNum=true;
			
			if (validNum) {
				if (isLocal) {
					data[1]="Local";
					data[2]=num;
				} else {
					for (Entry e : entries) {
						boolean ok=false;
						if (num.startsWith("00") && e.prefix.startsWith("00")) ok=num.startsWith(e.prefix) && num.length()-e.prefix.length()>=4 && num.length()-e.prefix.length()<=10;
						else if (num.startsWith("0") && e.prefix.startsWith("0")) ok=num.startsWith(e.prefix) && num.length()-e.prefix.length()>=4 && num.length()-e.prefix.length()<=7;
						
						if (ok) {
							destEntry=e;
							break;
						}
					}
					data[1]=destEntry!=null ? destEntry.dest : "Unknown";
					data[2]=destEntry!=null ? num.substring(destEntry.prefix.length()) : "";
				}
			} else {
				data[1]="Unknown";
				data[2]="";
			}

			data[3]=String.valueOf(dur);
			if (isLocal) {
				data[4]="0.00";
				data[5]="0.00";
			}
			else if (destEntry!=null) {
				data[4]=String.format("%.2f", destEntry.unitCost/100.0);
				data[5]=String.format("%.2f", (dur * destEntry.unitCost)/100.0);
			} else {
				data[4]="";
				data[5]="-1.00";
			}
			
			ans.add(data);
		}
		
		StringBuilder ansSB=new StringBuilder();
		for (String [] d : ans) {
			StringBuilder sb=new StringBuilder();
			sb.append(d[0]);
			while (sb.length()<16) sb.append(' ');
			sb.append(d[1]);
			while (sb.length()+d[2].length()<51) sb.append(' ');
			sb.append(d[2]);
			while (sb.length()+d[3].length()<56) sb.append(' ');
			sb.append(d[3]);
			while (sb.length()+d[4].length()<62) sb.append(' ');
			sb.append(d[4]);
			while (sb.length()+d[5].length()<69) sb.append(' ');
			sb.append(d[5]);
			sb.append('\n');
			ansSB.append(sb);
		}
		System.out.print(ansSB.toString());
	}

}