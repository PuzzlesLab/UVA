import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static final int NODE_MAX=45;
	private static ArrayList<Street> Streets;
	private static Street FirstStreet;
	private static int [] Degree;
	private static int [] Parent;
	private static boolean [] Exists;
	
	private static class Street implements Comparable<Street> {
		int n1, n2, id;
		boolean visited;
		
		public Street(int n1, int n2, int id) {
			this.n1=n1;
			this.n2=n2;
			this.id=id;
		}
		
		public int compareTo(Street s) {
			return this.id-s.id;
		}
	}

	private static int getParent(int n) {
		if (Parent[n]!=n) Parent[n]=getParent(Parent[n]);
		return Parent[n];
	}

	private static void init() {
		Streets=new ArrayList<>();
		FirstStreet=null;
		Degree=new int [NODE_MAX];
		Parent=new int [NODE_MAX];
		for (int n=0;n<Parent.length;n++) Parent[n]=n;
		Exists=new boolean [NODE_MAX];
	}

	private static void traverse(int startFrom, LinkedList<Integer> result) {
		for (int i=0;i<Streets.size();i++) {
			Street next=Streets.get(i);
			if (next.visited) continue;
			if (next.n1!=startFrom && next.n2!=startFrom) continue;
			
			next.visited=true;
			traverse(next.n1==startFrom?next.n2:next.n1,result);
			result.addFirst(next.id);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		init();

		while (true) {
			String s=br.readLine().trim();
			if (s.equals("0 0")) {
				if (Streets.size()==0) break;
				Collections.sort(Streets);
				
				HashSet<Integer> parents=new HashSet<>();
				for (int i=0;i<NODE_MAX;i++) if (Exists[i]) parents.add(getParent(i));
				if (parents.size()!=1) {
					System.out.println("Round trip does not exist.\n");
					init();
					continue;
				}

				boolean allEven=true;
				for (int i=0;i<NODE_MAX;i++) if (Exists[i]) allEven&=Degree[i]%2==0;
				if (!allEven) {
					System.out.println("Round trip does not exist.\n");
					init();
					continue;
				}

				LinkedList<Integer> list=new LinkedList<>();
				traverse(Math.min(FirstStreet.n1,FirstStreet.n2),list);
				
				StringBuilder sb=new StringBuilder();
				while (!list.isEmpty()) {
					sb.append(list.removeFirst());
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
				System.out.println(sb.toString());

				init();
				continue;
			}

			StringTokenizer st=new StringTokenizer(s);
			Street street=new Street(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			Streets.add(street);
			if (FirstStreet==null) FirstStreet=street;
			Degree[street.n1]++;
			Degree[street.n2]++;
			Exists[street.n1]=true;
			Exists[street.n2]=true;

			int p1=getParent(street.n1);
			int p2=getParent(street.n2);
			if (p1<p2) Parent[p2]=p1;
			else Parent[p1]=p2;
		}
	}
}
