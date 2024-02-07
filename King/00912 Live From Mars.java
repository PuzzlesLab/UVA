import java.util.ArrayList;
import java.util.Scanner;

class Main {

	private static class Tuple {
		char left, right;
		
		public Tuple(char l, char r) {
			this.left=l;
			this.right=r;
		}
	}

	private static char getParent(char c, char [] parent) {
		if (parent[c]!=c) parent[c]=getParent(parent[c], parent);
		return parent[c];
	}

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int TC=0;
		while (sc.hasNext()) {
			int M=Integer.parseInt(sc.next());
			
			char [] dna1=new char [M];
			for (int m=0;m<M;m++) dna1[m]=sc.next().charAt(0);
			char [] dna2=new char [M];
			for (int m=0;m<M;m++) dna2[m]=sc.next().charAt(0);

			ArrayList<Tuple> pairs=new ArrayList<>();
			for (int m=0;m<M;m++) pairs.add(new Tuple(dna1[m],dna2[m]));
	
			// UFDS
			char [] parent=new char [128];
			for (int i=0;i<parent.length;i++) parent[i]=(char)i;
			for (int i=0;i<pairs.size();i++) {
				Tuple p=pairs.get(i);
				char leftC=getParent(p.left, parent);
				char rightC=getParent(p.right, parent);
				
				if (leftC<rightC) parent[leftC]=getParent(rightC,parent);
				else if (leftC>rightC) parent[rightC]=getParent(leftC,parent);
			}
			// Make sure not more than 1 alphabet per set.
			boolean fail=false;
			for (int i=0;i<pairs.size();i++) {
				Tuple p=pairs.get(i);
				fail |= Character.isAlphabetic(p.left) && getParent(p.left,parent)!=p.left;
				fail |= Character.isAlphabetic(p.right) && getParent(p.right,parent)!=p.right;
				if (fail) break;
			}

			StringBuilder sb=new StringBuilder();
			if (TC++>0) sb.append('\n');
			if (fail) sb.append("NO\n");
			else {
				// Find numbers with alphabet as parent
				boolean [] existsNum=new boolean [10];
				for (int i=0;i<pairs.size();i++) {
					Tuple p=pairs.get(i);
					if (Character.isDigit(p.left) && Character.isAlphabetic(getParent(p.left,parent))) {
						existsNum[p.left-'0']=true;
					}
					if (Character.isDigit(p.right) && Character.isAlphabetic(getParent(p.right,parent))) {
						existsNum[p.right-'0']=true;
					}
				}

				sb.append("YES\n");
				for (int i=1;i<existsNum.length;i++) if (existsNum[i]) {
					sb.append(i);
					sb.append(' ');
					sb.append(getParent((char)(i+'0'),parent));
					sb.append('\n');
				}
			}
			System.out.print(sb);
		}
	}

}
