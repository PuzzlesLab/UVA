import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		long m;
		long mi;
		long r;
		long xi;
	}

	private static ArrayList<Tuple> [] Equations;
	private static ArrayList<Long> Answer;
	private static BigInteger Bound=new BigInteger("10000");
	private static long M;
	private static long eeX;
	private static long eeY;

	private static long mod(long a, long m) {
		return ((a%m)+m)%m;
	}

	private static long extEuclid(long a, long b) {
		long xx=eeX=0;
		long yy=eeY=1;
		while (b!=0) {
			long q=a/b;
			long t=b; b=a%b; a=t;
			t=xx; xx=eeX-q*xx; eeX=t;
			t=yy; yy=eeY-q*yy; eeY=t;
		}
		return a;
	}

	private static long calcXi(long mi, long m) {
		eeX=0;
		eeY=0;
		long d=extEuclid(mi,m);
		if (d!=1) return -1;
		return mod(eeX,m);
	}

	private static void calcX(Tuple [] sel) {
		long ans=0;
		for (int i=0;i<sel.length;i++) {
			ans+=((((sel[i].r%M)*(sel[i].mi%M))%M)*(sel[i].xi%M))%M;
			ans=ans%M;
		}
		Answer.add(ans);
	}

	private static void find(int c, Tuple [] sel) {
		if (c==sel.length) {
			calcX(sel);
			return;
		}
		for (int i=0;i<Equations[c].size();i++) {
			sel[c]=Equations[c].get(i);
			find(c+1,sel);
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int C=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			
			BigInteger totalCombi=BigInteger.ONE;
			Equations=new ArrayList [C];
			long [] mods=new long [C];
			HashSet<Long> [] existsRem=new HashSet [C];
			for (int c=0;c<C;c++) {
				Equations[c]=new ArrayList<>();
				st=new StringTokenizer(br.readLine());
				mods[c]=Long.parseLong(st.nextToken());
				int K=Integer.parseInt(st.nextToken());
				existsRem[c]=new HashSet<>();

				for (int k=0;k<K;k++) {
					Tuple equation=new Tuple();
					equation.m=mods[c];
					equation.r=Long.parseLong(st.nextToken());
					Equations[c].add(equation);
					existsRem[c].add(equation.r);
				}
				Collections.sort(Equations[c], (a, b) -> Long.compare(a.r, b.r));

				totalCombi=totalCombi.multiply(BigInteger.valueOf(K));
			}

			M=1;
			for (int c=0;c<C;c++) M*=Equations[c].get(0).m;
			for (int c=0;c<C;c++) for (int k=0;k<Equations[c].size();k++) {
				Tuple eq=Equations[c].get(k);
				eq.mi=M/eq.m;
			}

			StringBuilder sb=new StringBuilder();
			if (totalCombi.compareTo(Bound)<=0) {
				for (int c=0;c<C;c++) for (int k=0;k<Equations[c].size();k++) {
					Tuple eq=Equations[c].get(k);
					eq.xi=calcXi(eq.mi,eq.m);
				}

				Answer=new ArrayList<>();
				find(0, new Tuple [C]);
				Collections.sort(Answer);
				for (int i=0;S>0;i++) {
					long multi=i*M;
					for (int i2=0;i2<Answer.size() && S>0;i2++) {
						long ans=Answer.get(i2)+multi;
						if (ans==0) continue;

						sb.append(ans);
						sb.append('\n');
						S--;
					}
				}
			} else {
				int lowest=0;
				for (int c=0;c<C;c++) {
					if (mods[lowest]*Equations[c].size()<mods[c]*Equations[lowest].size()) {
						lowest=c;
					}
				}

				for (long i=0;S>0;i++) {
					long multi=i*mods[lowest];
					for (int i2=0;i2<Equations[lowest].size() && S>0;i2++) {
						Tuple eq=Equations[lowest].get(i2);
						long ans=multi+eq.r;
						if (ans==0) continue;

						boolean flag=true;
						for (int c=0;c<C;c++) {
							flag &= existsRem[c].contains(ans%mods[c]);
							if (!flag) break;
						}
						
						if (flag) {
							sb.append(ans);
							sb.append('\n');
							S--;
						}
					}
				}
			}
			System.out.println(sb.toString());
		}
	}

}
