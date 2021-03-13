import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			char [] c=s.toCharArray();
			ArrayList<Integer> occupiedIndex=new ArrayList<>();
			for (int i=0;i<c.length;i++) if (c[i]=='X') occupiedIndex.add(i);
			int maxDiff=0;
			int leftOccupiedIndex=-1;
			for (int i=0;i<c.length;i++) {
				if (c[i]=='.') {
					int minDiff=Integer.MAX_VALUE;
					if (leftOccupiedIndex!=-1) minDiff=Math.min(minDiff, i-occupiedIndex.get(leftOccupiedIndex)-1);
					if (leftOccupiedIndex+1<occupiedIndex.size()) minDiff=Math.min(minDiff, occupiedIndex.get(leftOccupiedIndex+1)-i-1);
					if (minDiff!=Integer.MAX_VALUE) maxDiff=Math.max(minDiff, maxDiff);
				} else leftOccupiedIndex++;
			}
			System.out.println(maxDiff);
		}
	}
}