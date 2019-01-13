import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int single=Integer.parseInt(st.nextToken());
			int repeat=Integer.parseInt(st.nextToken());
			if (single==0 && repeat==0) break;
			
			int [][] singleTable=new int [single][];
			for (int s=0;s<single;s++) {
				st=new StringTokenizer(br.readLine());
				singleTable[s]=new int [] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			int [][] repeatTable=new int [repeat][];
			for (int r=0;r<repeat;r++) {
				st=new StringTokenizer(br.readLine());
				repeatTable[r]=new int [] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			
			boolean hasConflict=false;
			BitSet occupied=new BitSet(1000000);
			for (int s=0;s<single && !hasConflict;s++) {
				int min=singleTable[s][0];
				int max=Math.min(occupied.size(), singleTable[s][1]);
				hasConflict=occupied.get(min, max).cardinality()>0;
				if (!hasConflict) occupied.set(min, max);
			}
			
			for (int r=0;r<repeat && !hasConflict; r++) {
				int currStart=0;
				while (repeatTable[r][0]+currStart<occupied.size() && !hasConflict) {
					int min=repeatTable[r][0]+currStart;
					int max=Math.min(occupied.size(), repeatTable[r][1]+currStart);
					hasConflict=occupied.get(min, max).cardinality()>0;
					if (!hasConflict) occupied.set(min, max);
					currStart+=repeatTable[r][2];
				}

			}
			
			if (hasConflict) System.out.println("CONFLICT");
			else System.out.println("NO CONFLICT");
		}
	}

}