import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
	
	private static class Alignment implements Comparable<Alignment> {
		int priority;
		int time;
		
		public Alignment (int p, int t) {
			this.priority=p;
			this.time=t;
		}
		
		public int compareTo(Alignment a) {
			return this.priority-a.priority;
		}
	}
	
	private static String Solution="";
	private static TreeSet<Integer> PriorityList;
	private static HashMap<Integer,Integer> SolutionMissedByPriority;
	private static int SolutionTotalMissed=-1;
	
	private static void permutate(int [] lengths, Alignment [] aligns, boolean [] added, int [] currLengths, int [] currLengthsCul, int currCount) {
		if (currCount==currLengths.length) {
			int totalMissed=0;
			HashMap<Integer,Integer> currMissedByPriority=new HashMap<>();
			for (Alignment a : aligns) {
				int minMissed=Integer.MAX_VALUE;
				for (int time : currLengthsCul) minMissed=Math.min(minMissed,Math.abs(a.time-time));
				totalMissed+=minMissed;
				currMissedByPriority.put(a.priority,currMissedByPriority.getOrDefault(a.priority,0)+minMissed);
			}
			
			boolean flag=SolutionTotalMissed==-1;
			if (!flag) {
				for (int p : PriorityList) {
					int curr=currMissedByPriority.get(p);
					int sol=SolutionMissedByPriority.get(p);
					if (curr!=sol) {
						flag=curr<sol;
						break;
					}
				}
			}

			if (flag) {
				SolutionMissedByPriority=currMissedByPriority;
				SolutionTotalMissed=totalMissed;
				StringBuilder sb=new StringBuilder();
				for (int i : currLengths) {
					sb.append(' ');
					sb.append(i);
				}
				Solution=sb.toString();
			}

		} else {
			for (int i=0;i<lengths.length;i++) if (!added[i]) {
				added[i]=true;
				currLengths[currCount]=lengths[i];
				currLengthsCul[currCount+1]=currLengthsCul[currCount]+lengths[i];
				permutate(lengths,aligns,added,currLengths,currLengthsCul,currCount+1);
				added[i]=false;
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int P=Integer.parseInt(st.nextToken());
			int [] lengths=new int [P];
			for (int p=0;p<P;p++) lengths[p]=Integer.parseInt(st.nextToken());
			Arrays.sort(lengths);
			
			st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			Alignment [] aligns=new Alignment[A];
			for (int a=0;a<A;a++) aligns[a]=new Alignment(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			Arrays.sort(aligns);
			PriorityList=new TreeSet<>();
			for (int a=0;a<A;a++) PriorityList.add(aligns[a].priority);
			
			Solution="";
			SolutionTotalMissed=-1;
			SolutionMissedByPriority=new HashMap<>();
			permutate(lengths,aligns,new boolean[lengths.length],new int[lengths.length],new int [lengths.length+1],0);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Data set ");
			sb.append(testCase++);
			sb.append('\n');
			sb.append("Order:");
			sb.append(Solution);
			sb.append('\n');
			sb.append("Error: ");
			sb.append(SolutionTotalMissed);
			System.out.println(sb.toString());
		}
	}

}