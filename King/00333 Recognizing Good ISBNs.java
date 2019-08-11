import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			s=s.trim();
			StringBuilder newS=new StringBuilder();
			//Sanity check;
			boolean valid=true;
			for (char c : s.toCharArray()) {
				if (Character.isDigit(c) || c=='X') newS.append(c);
				else if (c!='-' && c!=' ') valid=false;
			}
			for (int i=0;i<newS.length()-1;i++) valid&=newS.charAt(i)!='X';
			valid &= newS.length()==10;

			if (valid) {
				String sCal=newS.toString();
				int [] partialSum=new int[10];
				char [] ch=sCal.toCharArray();
				for (int i=0;i<ch.length;i++) {
					int v=ch[i]=='X' ? 10 : ch[i]-'0';
					if (i>0) partialSum[i]=partialSum[i-1];
					partialSum[i]+=v;
				}
				int total=0;
				for (int i=0;i<partialSum.length;i++) total+=partialSum[i];
				valid=total%11==0;
			}

			System.out.printf("%s is %s.\n",s,valid ? "correct" : "incorrect");
		}
	}

}