import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	private static class Chamber{
		private ArrayList<Integer> masses;
		private int massesSum=0;
		
		public Chamber () {
			this.masses=new ArrayList<>();
			this.massesSum=0;
		}
		
		public void addMass(int m) {
			this.masses.add(m);
			this.massesSum+=m;
		}
	}
	
	private static class Mass implements Comparable<Mass> {
		private int value;
		private Mass pair;
		
		public Mass (int value) {
			this.value=value;
		}
		
		@Override
		public int compareTo(Mass m) {
			return this.value-m.value;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int set=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int C=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			
			Mass [] mass=new Mass [2*C];
			int massCount=0;
			double average=0.0;
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<S;i++) {
				mass[massCount]=new Mass(Integer.parseInt(st.nextToken()));
				average+=mass[massCount++].value;
			}
			average/=C;
			for (;massCount<mass.length;massCount++) mass[massCount]=new Mass(0);

			Mass [] massSorted=new Mass [2*C];
			for (int i=0;i<mass.length;i++) massSorted[i]=mass[i];
			Arrays.sort(massSorted);
			for (int i=0;i<massSorted.length/2;i++) {
				massSorted[i].pair=massSorted[massSorted.length-i-1];
				massSorted[massSorted.length-i-1].pair=massSorted[i];
			}
			ArrayList<Chamber> fullList=new ArrayList<>();
			for (int i=0;i<C;i++) fullList.add(new Chamber());
			HashSet<Mass> added=new HashSet<>();
			int currC=0;
			for (int i=0;i<mass.length;i++) if (!added.contains(mass[i])) {
				fullList.get(currC).addMass(mass[i].value);
				added.add(mass[i]);

				fullList.get(currC).addMass(mass[i].pair.value);
				added.add(mass[i].pair);

				currC++;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Set #");
			sb.append(set++);
			sb.append('\n');
			double imbalance=0;
			for (int i=0;i<fullList.size();i++) {
				Chamber c=fullList.get(i);
				sb.append(String.format("%2d:", i));
				for (int m : c.masses) if (m!=0) {
					sb.append(" ");
					sb.append(m);
				}
				sb.append('\n');
				imbalance+=Math.abs(c.massesSum-average);
			}
			sb.append(String.format("IMBALANCE = %.5f\n", imbalance));
			System.out.println(sb.toString());
		}
		
	}

}