import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> throwList=new ArrayList<>();
		StringTokenizer st=new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) throwList.add(Integer.parseInt(st.nextToken()));
		
		while (true) {
			int P=Integer.parseInt(br.readLine());
			if (P==0) break;
			
			int [] next=new int [101];
			int [] turn=new int [101];
			for (int i=0;i<101;i++) next[i]=i;
			
			while (true) {
				st=new StringTokenizer(br.readLine());
				int start=Integer.parseInt(st.nextToken());
				int end=Integer.parseInt(st.nextToken());
				if (start==0 && end==0) break;
				next[start]=end;
			}
			
			while (true) {
				int index=Integer.parseInt(br.readLine());
				if (index==0) break;
				turn[Math.abs(index)]=index;
			}
			
			int [] pPos=new int [P]; Arrays.fill(pPos, 0);
			boolean [] canRoll=new boolean [P]; Arrays.fill(canRoll,true);
			int currPlayer=0;
			int winner=-1;
			for (int roll=0;roll<throwList.size()-1;roll++) {
				if (canRoll[currPlayer] && pPos[currPlayer]+throwList.get(roll)<=100) {
					pPos[currPlayer]+=throwList.get(roll);
					if (pPos[currPlayer]==100) {
						winner=currPlayer;
						break;
					}
					
					while (next[pPos[currPlayer]]!=pPos[currPlayer]) pPos[currPlayer]=next[pPos[currPlayer]];

					while (turn[pPos[currPlayer]]>0 && roll<throwList.size()-1) {
						if (pPos[currPlayer]+throwList.get(roll)<=100) pPos[currPlayer]+=throwList.get(roll++);
						else break;
					}
					if (turn[pPos[currPlayer]]<0) canRoll[currPlayer]=false;
				} else canRoll[currPlayer]=true;
				currPlayer=(currPlayer+1)%P;
			}
			System.out.println(winner+1);
		}

	}

}