import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {
	
	public static void main (String [] args) throws Exception {
		BigInteger [] bi=new BigInteger [401];
		for (int i=0;i<bi.length;i++) bi[i]=BigInteger.valueOf(i);
		String [] names= {
				"Ghost of Tanveer Ahsan!!!",
				"Ghost of Shahriar Manzoor!!!",
				"Ghost of Adrian Kugel!!!",
				"Ghost of Anton Maydell!!!",
				"Ghost of Derek Kisman!!!",
				"Ghost of Rezaul Alam Chowdhury!!!",
				"Ghost of Jimmy Mardell!!!",
				"Ghost of Monirul Hasan!!!",
				"Ghost of K. M. Iftekhar!!!"
		};
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int T=0;
		while (!(s=br.readLine()).equals("0")) {
			BigInteger initial=new BigInteger(s);
			BigInteger year=initial.subtract(BigInteger.valueOf(2148));
			
			boolean [] flag=new boolean [names.length];
			if (year.compareTo(BigInteger.ZERO)>=0) {
				if (year.mod(bi[2]).equals(BigInteger.ZERO)) flag[0]=true;
				if (year.mod(bi[5]).equals(BigInteger.ZERO)) flag[1]=true;
				if (year.mod(bi[7]).equals(BigInteger.ZERO)) flag[2]=true;
				if (year.mod(bi[11]).equals(BigInteger.ZERO)) flag[3]=true;
				if (year.mod(bi[15]).equals(BigInteger.ZERO)) flag[4]=true;
				if (year.mod(bi[20]).equals(BigInteger.ZERO)) flag[5]=true;
				if (year.mod(bi[28]).equals(BigInteger.ZERO)) flag[6]=true;
				if (year.mod(bi[36]).equals(BigInteger.ZERO)) flag[7]=true;
				if ((initial.mod(bi[4]).equals(BigInteger.ZERO) && !initial.mod(bi[100]).equals(BigInteger.ZERO)) || initial.mod(bi[400]).equals(BigInteger.ZERO)) flag[8]=true;
			}
	
			StringBuilder sb=new StringBuilder();
			if (T++>0) sb.append('\n');
			sb.append(initial.toString());
			sb.append('\n');
			
			int count=0;
			for (int i=0;i<flag.length;i++) if (flag[i]) {
				count++;
				sb.append(names[i]);
				sb.append('\n');
			}
			if (count==0) sb.append("No ghost will come in this year\n");
			
			System.out.print(sb.toString());
		}
	}

}