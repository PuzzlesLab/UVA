import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	public static int cmp(char [] c1, char [] c2) {
		for (int i=0;i<c1.length;i++) if (c1[i]!=c2[i]) return c1[i]-c2[i];
		return 0;
	}

	public static String simplify(char [] ch) {
		char [] lowest=null;
		for (int delta=0;delta<ch.length;delta++) {
			char [] curr=new char [ch.length];
			for (int i=0;i<ch.length;i++) curr[i]=ch[(i+delta)%ch.length];
			if (lowest==null || cmp(curr,lowest)<0) lowest=curr;
		}
		return new String(lowest);
	}

	public static void main (String [] args) throws Exception {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			String initial=br.readLine();
			char [] ch=initial.toCharArray();
			int [] rules=new int [8];
			for (int i=0;i<rules.length;i++) {
				char [] r=br.readLine().toCharArray();
				int idx=((r[0]-'a')<<2)+((r[1]-'a')<<1)+(r[2]-'a');
				rules[idx]=r[3];
			}
			int rewrites=Integer.parseInt(br.readLine());
			
			ArrayList<char []> rewritten=new ArrayList<>();
			
			for (int i=0;i<rewrites;i++) {
				char [] newCh=new char[ch.length];
				char [] last=(i==0)?ch:rewritten.get(i-1);
				for (int i2=0;i2<last.length;i2++) {
					int c1=last[Math.floorMod(i2-2, ch.length)]-'a';
					int c2=last[i2]-'a';
					int c3=last[Math.floorMod(i2+1, ch.length)]-'a';
					newCh[i2]=(char)(rules[(c1<<2)+(c2<<1)+c3]);
				}

				rewritten.add(newCh);
				if (cmp(newCh,ch)==0) break;
			}

			char [] last=rewritten.get(Math.floorMod(rewrites-1,rewritten.size()));
			System.out.println(simplify(last));
		}
	}
}