import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	private static final ArrayList<int []> permutations=new ArrayList<>();
	 // 0 to 4, 5 = It
	private static final int IT_IDX=5;
	// Roles //
	private static final int DIVINE_IDX=1;
	private static final int HUMAN_IDX=2;
	private static final int EVIL_IDX=3;
	private static final int LYING_IDX=4;
	// Day/Night //
	private static final int DAY_IDX=5;
	private static final int NIGHT_IDX=6;

	
	public static class Statement {
		private static HashMap<String,Integer> targetIdxMap;
		int firstIdx;
		int secondIdx; // 0 to 4, 5 = It
		int role;
		boolean not;
		
		public static int targetToIdx(String s) {
			if (targetIdxMap==null) {
				targetIdxMap=new HashMap<>();
				targetIdxMap.put("divine.",DIVINE_IDX);
				targetIdxMap.put("human.",HUMAN_IDX);
				targetIdxMap.put("evil.",EVIL_IDX);
				targetIdxMap.put("lying.",LYING_IDX);
				targetIdxMap.put("day.",DAY_IDX);
				targetIdxMap.put("night.",NIGHT_IDX);
			}
			return targetIdxMap.get(s);
		}

		public Statement(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.firstIdx=st.nextToken().charAt(0)-'A';
			
			String s2=st.nextToken();
			if (s2.equals("I")) this.secondIdx=this.firstIdx;
			else if (s2.equals("It")) this.secondIdx=IT_IDX;
			else this.secondIdx=s2.charAt(0)-'A';
			
			st.nextToken(); // useless
			
			String s3=st.nextToken();
			if (s3.equals("not")) {
				this.not=true;
				s3=st.nextToken();
			}
			this.role=Statement.targetToIdx(s3);
		}
	}

	private static void permutate(int [] curr, int currIdx) {
		if (currIdx<curr.length-1) {
			for (int role=1;role<=3;role++) {
				curr[currIdx]=role;
				permutate(curr,currIdx+1);
			}
		} else if (currIdx==curr.length-1) {
			for (int day=1;day<=2;day++) {
				curr[currIdx]=day;
				permutate(curr,currIdx+1);
			}
		} else permutations.add(Arrays.copyOf(curr,curr.length));
	}

	private static boolean validateUnit(int [] role, Statement st, boolean day) {
		boolean currFlag=false;
		
		int firstRole=role[st.firstIdx];
		if (st.secondIdx==IT_IDX) { // It is day/night
			if (firstRole==DIVINE_IDX) currFlag=st.role == (day ? DAY_IDX : NIGHT_IDX); // Match with day.
			else if (firstRole==HUMAN_IDX) currFlag=st.role == DAY_IDX; // Says day during day, says night during day.
			else if (firstRole==EVIL_IDX) currFlag=st.role == (day ? NIGHT_IDX : DAY_IDX); // Says day during night, says night during day.
		} else {
			boolean firstLegit=firstRole==DIVINE_IDX || (firstRole==HUMAN_IDX && day);
			boolean secondLegit=role[st.secondIdx]==DIVINE_IDX || (role[st.secondIdx]==HUMAN_IDX && day);
			
			if (st.role!=LYING_IDX) currFlag=role[st.secondIdx]==st.role; // X is xxx.
			else currFlag=!secondLegit; // X is lying.
			if (!(st.not ^ firstLegit)) currFlag=!currFlag; // Reverse if liar or NOT.
		}
		return currFlag;
	}

	public static void main (String [] args) throws Exception {
		permutate(new int [6],0); // Generate permutations (3*3*3*3*3*2)
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			ArrayList<Statement> stats=new ArrayList<>();
			for (int n=0;n<N;n++) stats.add(new Statement(br.readLine()));

			// Search for feasible solutions;
			HashSet<int []> solutions=new HashSet<>();
			for (int [] perm : permutations) {
				boolean totalFlag=true;
				for (Statement stat: stats) {
					totalFlag &= validateUnit(perm,stat,perm[perm.length-1]==1);
					if (!totalFlag) break;
				}
				if (totalFlag) solutions.add(perm);
	 		}

			StringBuilder sb=new StringBuilder();
			sb.append("Conversation #");
			sb.append(testCase);
			sb.append('\n');

			if (solutions.size()==0) sb.append("This is impossible.\n");
			else {
				ArrayList<int []> solutionList=new ArrayList<>();
				solutionList.addAll(solutions);
				int [] firstSolution=solutionList.get(0);
				
				boolean foundAny=false;
				for (int i=0;i<5;i++) {
					int role=firstSolution[i];
					boolean found=true;
					for (int [] sol: solutionList) {
						found &= sol[i] == role;
						if (!found) break;
					}
					if (found) {
						foundAny=true;
						sb.append((char)(i+'A'));
						sb.append(" is ");
						if (role==DIVINE_IDX) sb.append("divine");
						else if (role==HUMAN_IDX) sb.append("human");
						else if (role==EVIL_IDX) sb.append("evil");
						sb.append(".\n");
					}
				}
				
				boolean sameDay=true;
				int day=firstSolution[firstSolution.length-1];
				for (int [] sol: solutionList) {
					sameDay &= (sol[sol.length-1]==day);
					if (!sameDay) break;
				}
				if (sameDay) {
					if (day==1) sb.append("It is day.\n");
					else if (day==2) sb.append("It is night.\n");
					foundAny=true;
				}
				
				if (!foundAny) sb.append("No facts are deducible.\n");
			}
			
			System.out.println(sb.toString());
			testCase++;
		}
	}

}
