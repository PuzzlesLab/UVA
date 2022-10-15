import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

class Main {

	private static char [] Nodes;
	private static ArrayList<Integer> [] AdjList;
	private static ArrayList<Integer> Trace;
	private static boolean [] Visited;
	private static HashSet<String> Solutions;

	private static ArrayList<Integer> rearrange(ArrayList<Integer> nodes) {
		int min=Integer.MAX_VALUE;
		int minIdx=-1;
		for (int i=0;i<nodes.size();i++) {
			if (nodes.get(i)<min) {
				min=nodes.get(i);
				minIdx=i;
			}
		}

		ArrayList<Integer> ret=new ArrayList<>();
		int idx=minIdx;
		while (ret.size()!=nodes.size()+1) {
			ret.add(nodes.get(idx));
			idx--;
			if (idx<0) idx+=nodes.size();
		}
		return ret;
	}

	private static void find(int curr, int length) {
		for (int next: AdjList[curr]) {
			if (!Visited[next]) {
				if (length==Trace.size()) Trace.add(next);
				else Trace.set(length,next);
				Visited[next]=true;
				find(next,length+1);
				Visited[next]=false;
			} else {
				ArrayList<Integer> nodes=new ArrayList<>();
				nodes.add(next);
				for (int i=length-1;Trace.get(i)!=next;i--) nodes.add(Trace.get(i));
				nodes=rearrange(nodes);

				StringBuilder sb=new StringBuilder();
				for (int i=0;i<nodes.size();i++) {
					sb.append(Nodes[nodes.get(i)]);
					sb.append('-');
				}
				sb.setLength(sb.length()-1);
				Solutions.add(sb.toString());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int TC=Integer.parseInt(sc.next());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(sc.next());
			int M=Integer.parseInt(sc.next());
			int E=Integer.parseInt(sc.next());
			int T=N+M;
			
			AdjList=new ArrayList[T];
			for (int t=0;t<T;t++) AdjList[t]=new ArrayList<>();

			/*
			 * The output must start with lowest character first (A-Z, a-z)!
			 * Wrong : b-X-b
			 * Correct: X-b-X
			 */
			TreeSet<Character> orderedChar=new TreeSet<>();
			ArrayList<String> edges=new ArrayList<>();
			for (int e=0;e<E;e++) {
				String s=sc.next();
				edges.add(s);
				char n1=s.charAt(0);
				char n2=s.charAt(2);
				orderedChar.add(n1);
				orderedChar.add(n2);
			}
			int [] chToIdx=new int [128];
			Nodes=new char [T];
			int nodeIdx=0;
			for (Character ch: orderedChar) {
				chToIdx[ch]=nodeIdx;
				Nodes[nodeIdx++]=ch;
			}
			
			for (int e=0;e<E;e++) {
				String s=edges.get(e);
				AdjList[chToIdx[s.charAt(0)]].add(chToIdx[s.charAt(2)]);
			}

			Solutions=new HashSet<>();
			for (int t=0;t<T;t++) {
				Visited=new boolean [T];
				Trace=new ArrayList<>();
				Trace.add(t);
				Visited[t]=true;
				find(t,1);
			}
			
			ArrayList<String> ordered=new ArrayList<>(Solutions);
			Collections.sort(ordered, (a, b) -> {
				if (a.length()!=b.length()) return a.length()-b.length();
				return a.compareTo(b);
			});
			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			sb.append(ordered.isEmpty()?"NO\n":"YES\n");
			for (String ans: ordered) {
				sb.append(ans);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
