import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static int M;
	private static int Step;
	private static ArrayList<Integer> [] Pegs;

	public static void print() {
		StringBuilder sb=new StringBuilder();
		sb.append('\n');
		for (int i=0;i<Pegs.length;i++) {
			sb.append((char)('A'+i));
			sb.append("=>");
			if (!Pegs[i].isEmpty()) sb.append("  ");
			for (int i2=0;i2<Pegs[i].size();i2++) {
				sb.append(' ');
				sb.append(Pegs[i].get(i2));
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}

	public static void play(int temp, int A, int B, int C) {
		if (Step==M) return;
		if (temp==1) {
			Step++;
			Pegs[B].add(Pegs[A].remove(Pegs[A].size()-1));
			print();
		} else {
			play(temp-1,A,C,B);
			play(1,A,B,C);
			play(temp-1,C,B,A);
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;

			ArrayList<Integer> pA=new ArrayList<>();
			for (int i=N;i>0;i--) pA.add(i);
			ArrayList<Integer> pB=new ArrayList<>();
			ArrayList<Integer> pC=new ArrayList<>();
			Step=0;
			Pegs=new ArrayList [] {pA,pB,pC};

			StringBuilder sb=new StringBuilder();
			sb.append("Problem #");
			sb.append(testCase++);
			System.out.println(sb);
			print();
			play(N,0,2,1);
			System.out.println();
		}
	}

}