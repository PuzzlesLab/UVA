import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static boolean evaluate(char [] ch, int chIndex, boolean p, boolean q, boolean r, boolean s, boolean t) {
		char c=ch[chIndex];
		if (c=='p') return p;
		else if (c=='q') return q;
		else if (c=='r') return r;
		else if (c=='s') return s;
		else if (c=='t') return t;
		else if (c=='N') return !evaluate(ch,chIndex+1,p,q,r,s,t);
		else {
			int needed=1;
			int split=-1;
			for (int i=chIndex+1;i<ch.length;i++) {
				if (ch[i] >= 'a' && ch[i] <= 'z') needed--;
				else if (ch[i]!='N') needed++;
				if (needed==0) {
					split=i+1;
					break;
				}
			}
			boolean w=evaluate(ch,chIndex+1,p,q,r,s,t);
			boolean k=evaluate(ch,split,p,q,r,s,t);
			
			if (c=='K') return w && k;
			else if (c=='A') return w || k;
			else if (c == 'C') return !(w && !k);
			else if (c == 'E') return (w == k);
		}
		return false;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String in;
		while (!(in=br.readLine()).equals("0")) {
			char [] ch=in.toCharArray();
			boolean flag=true;
			for (int p=0;p<=1 && flag;p++) for (int q=0;q<=1 && flag;q++) for (int r=0;r<=1 && flag;r++) for (int s=0;s<=1 && flag;s++) for (int t=0;t<=1 && flag;t++) {
				flag=evaluate(ch,0,p==1,q==1,r==1,s==1,t==1);
			}
			System.out.println(flag ? "tautology" : "not");
		}
	}

}