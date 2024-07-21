import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static final long MAX=30000;
	private static final int MAX_DEPTH=10;
	private static long [] Target;
	private static String Sol;

	private static void search(LinkedList<Long>[] stacks, StringBuilder curr, int max) {
		if (Sol!=null) return;
		int currSize=stacks[0].size();
		if (currSize==1 && curr.length()==max) {
			boolean match=true;
			for (int i=0;i<stacks.length;i++) {
				if (stacks[i].getLast()!=Target[i]) {
					match=false;
					break;
				}
			}
			if (match) {
				Sol=curr.toString();
				return;
			}
		}
		if (curr.length()==max) return;
		for (int i=0;i<stacks.length;i++) if (Math.abs(stacks[i].getLast())>MAX) return;

		long [] tempA=new long [stacks.length];
		long [] tempB=new long [stacks.length];
		long [] nextValues=new long[stacks.length];
		boolean ok=true;

		// Add
		if (currSize>1) {
			ok=true;
			for (int i=0;i<stacks.length;i++) {
				tempA[i]=stacks[i].removeLast();
				tempB[i]=stacks[i].removeLast();
				nextValues[i]=tempA[i]+tempB[i];
			}
			if (ok) {
				for (int i=0;i<stacks.length;i++) stacks[i].addLast(nextValues[i]);
				curr.append('A');
				search(stacks,curr,max);
				for (int i=0;i<stacks.length;i++) stacks[i].removeLast();
				curr.setLength(curr.length()-1);
			}
			for (int i=0;i<stacks.length;i++) {
				stacks[i].addLast(tempB[i]);
				stacks[i].addLast(tempA[i]);
			}
		}

		// Div
		if (currSize>1) {
			ok=true;
			for (int i=0;i<stacks.length;i++) {
				tempA[i]=stacks[i].removeLast();
				tempB[i]=stacks[i].removeLast();
				if (tempA[i]!=0) nextValues[i]=tempB[i]/tempA[i];
				else ok=false;
			}
			if (ok) {
				for (int i=0;i<stacks.length;i++) stacks[i].addLast(nextValues[i]);
				curr.append('D');
				search(stacks,curr,max);
				for (int i=0;i<stacks.length;i++) stacks[i].removeLast();
				curr.setLength(curr.length()-1);
			}
			for (int i=0;i<stacks.length;i++) {
				stacks[i].addLast(tempB[i]);
				stacks[i].addLast(tempA[i]);
			}
		}

		// Dup
		for (int i=0;i<stacks.length;i++) stacks[i].addLast(stacks[i].getLast());
		curr.append('C');
		search(stacks,curr,max);
		for (int i=0;i<stacks.length;i++) stacks[i].removeLast();
		curr.setLength(curr.length()-1);

		// Mul
		if (currSize>1) {
			ok=true;
			for (int i=0;i<stacks.length;i++) {
				tempA[i]=stacks[i].removeLast();
				tempB[i]=stacks[i].removeLast();
				nextValues[i]=tempA[i]*tempB[i];
			}
			if (ok) {
				for (int i=0;i<stacks.length;i++) stacks[i].addLast(nextValues[i]);
				curr.append('M');
				search(stacks,curr,max);
				for (int i=0;i<stacks.length;i++) stacks[i].removeLast();
				curr.setLength(curr.length()-1);
			}
			for (int i=0;i<stacks.length;i++) {
				stacks[i].addLast(tempB[i]);
				stacks[i].addLast(tempA[i]);

			}
		}

		// Sub
		if (currSize>1) {
			ok=true;
			for (int i=0;i<stacks.length;i++) {
				tempA[i]=stacks[i].removeLast();
				tempB[i]=stacks[i].removeLast();
				nextValues[i]=tempB[i]-tempA[i];
			}
			if (ok) {
				for (int i=0;i<stacks.length;i++) stacks[i].addLast(nextValues[i]);
				curr.append('S');
				search(stacks,curr,max);
				for (int i=0;i<stacks.length;i++) stacks[i].removeLast();
				curr.setLength(curr.length()-1);
			}
			for (int i=0;i<stacks.length;i++) {
				stacks[i].addLast(tempB[i]);
				stacks[i].addLast(tempA[i]);
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			long [] x=new long [N];
			Target=new long [N];
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) x[i]=Long.parseLong(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<N;i++) Target[i]=Long.parseLong(st.nextToken());

			LinkedList<Long> [] stacks=new LinkedList [N];
			for (int i=0;i<N;i++) {
				stacks[i]=new LinkedList<>();
				stacks[i].add(x[i]);
			}
			Sol=null;
			for (int i=0;i<=MAX_DEPTH && Sol==null;i++) {
				search(stacks,new StringBuilder(),i);
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Program ");
			sb.append(tc++);
			sb.append('\n');
			if (Sol==null) sb.append("Impossible");
			else if (Sol.length()==0) sb.append("Empty sequence");
			else {
				for (int i=0;i<Sol.length();i++) {
					char c=Sol.charAt(i);
					if (c=='A') sb.append("ADD");
					else if (c=='S') sb.append("SUB");
					else if (c=='M') sb.append("MUL");
					else if (c=='D') sb.append("DIV");
					else if (c=='C') sb.append("DUP");
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
			}
			sb.append('\n');
			System.out.println(sb);
		}
	}

}
