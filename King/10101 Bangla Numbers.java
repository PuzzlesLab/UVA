import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static class Unit {
		int len;
		String name;
		public Unit(int l, String n) {
			this.len=l;
			this.name=n;
		}
	}

	private static Unit [] Units = new Unit [] {
		new Unit(7,"kuti"),
		new Unit(5,"lakh"),
		new Unit(3,"hajar"),
		new Unit(2,"shata")
	};

	private static String name(String s) {
		// Remove leading 0.
		boolean found=false;
		for (int i=0;i<s.length();i++) {
			char c=s.charAt(i);
			if (c!='0') {
				found=true;
				s=s.substring(i,s.length());
				break;
			}
		}
		if (!found) return "";
		
		int length=s.length();
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<Units.length;i++) {
			if (length>Units[i].len) {
				String before=name(s.substring(0,length-Units[i].len));
				String after=name(s.substring(length-Units[i].len,length));
				if (before.length()>0) {
					sb.append(before);
					sb.append(' ');
				}
				sb.append(Units[i].name);
				if (after.length()>0) {
					sb.append(' ');
					sb.append(after);
				}

				break;
			}
		}
		if (sb.length()==0) sb.append(s);

		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			String sol=name(s);
			if (sol.length()==0) sol="0";
			System.out.printf("%4d. %s\n",tc++,sol);
		}
	}
}
