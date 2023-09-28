import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

class Main {

	private static BigInteger [] Perm=new BigInteger [32];

        // Cantor expansion
	private static BigInteger count(char [] ch, int curr) {
		BigInteger sum=BigInteger.ZERO;
		BigInteger num=Perm[ch.length-curr-1];
		
		int [] dups=new int [26];
		for (int i=curr;i<ch.length;i++) dups[ch[i]-'a']++;
		int max=ch[curr]-'a';
		for (int i=0;i<max;i++) if (dups[i]>0) {
			BigInteger temp=num;
			dups[i]--;
			for (int i2=0;i2<dups.length;i2++) if (dups[i2]>1) temp=temp.divide(Perm[dups[i2]]);
			dups[i]++;
			sum=sum.add(temp);
		}
		return sum;
	}

	public static void main(String[] args) throws Exception {
		Perm[0]=BigInteger.ONE;
		for (int i=1;i<Perm.length;i++) Perm[i]=Perm[i-1].multiply(BigInteger.valueOf(i));

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("#")) {
			char [] ch=s.toCharArray();
			BigInteger ans=BigInteger.ONE;
			for (int i=0;i<ch.length-1;i++) ans=ans.add(count(ch,i));
			System.out.printf("%10s\n", ans);
		}
	}

}
