import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	public static class Expr {
		int multi, pow;

		public Expr(String s) {
			StringBuilder sb=new StringBuilder();
			boolean hasPow=false;
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				
				if (c=='n') {
					if (sb.length()==0) multi=1;
					else if (sb.length()==1 && sb.charAt(0)=='-') multi=-1;
					else if (sb.length()==1 && sb.charAt(0)=='+') multi=1;
					else multi=Integer.parseInt(sb.toString());

					sb.setLength(0);
					pow=1;
				} else if (c=='^') hasPow=true;
				else sb.append(c);
			}
			if (sb.length()>0) {
				if (hasPow) pow=Integer.parseInt(sb.toString());
				else multi=Integer.parseInt(sb.toString());
			}
		}
		
		public long mod(int n, int div) {
			long temp=multi;
			if (multi%div==0) multi%=div;

			for (int p=0;p<pow;p++) temp=(temp*n)%div;
			return temp;
		}
	}

	private static boolean eval(ArrayList<Expr> exprList, int n, int div) {
		long temp=0;
		for (int i=0;i<exprList.size();i++) {
			temp=(temp+exprList.get(i).mod(n,div))%div;
		}
		return temp==0;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		String s;
		while (!(s=br.readLine()).equals(".")) {
			String [] p1=s.split("\\)/");
			String num=p1[0].substring(1);
			int den=Integer.parseInt(p1[1]);
			
			ArrayList<Expr> parts=new ArrayList<>();
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<num.length();i++) {
				char c=num.charAt(i);
				if ((c=='+' || c=='-') && sb.length()>0) {
					parts.add(new Expr(sb.toString()));
					sb.setLength(0);
				}
				sb.append(c);
			}
			if (sb.length()>0) parts.add(new Expr(sb.toString())); 

			boolean flag=true;
			for (int e=0;e<=100 && flag;e++) flag&=eval(parts,e,den);

			sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			sb.append(flag?"Always":"Not always");
			sb.append(" an integer");
			System.out.println(sb);
		}
	}

}
