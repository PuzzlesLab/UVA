import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Mobile {
		int wL,dL,wR,dR;
		
		public Mobile(int i1, int i2, int i3, int i4) {
			this.wL=i1;
			this.dL=i2;
			this.wR=i3;
			this.dR=i4;
		}
	}

	private static ArrayList<Mobile> Mobiles;
	private static int MobileIdx;
	
	private static int updateWeights() {
		MobileIdx++;
		if (MobileIdx==Mobiles.size()) return 0;
		
		Mobile curr=Mobiles.get(MobileIdx);
		if (curr.wL==0) curr.wL=updateWeights();
		if (curr.wR==0) curr.wR=updateWeights();
		return curr.wL+curr.wR;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		br.readLine(); // Empty
		for (int tc=0;tc<TC;tc++) {
			Mobiles=new ArrayList<>();

			while (true) {
				String s=br.readLine();
				if (s==null || s.length()==0) break;
				StringTokenizer st=new StringTokenizer(s);
				Mobiles.add(new Mobile(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			MobileIdx=-1;
			updateWeights();
			
			boolean flag=true;
			for (int i=0;i<Mobiles.size();i++) {
				Mobile m=Mobiles.get(i);
				flag&=m.dL*m.wL==m.dR*m.wR;
			}
			if (tc>0) System.out.println();
			System.out.println(flag?"YES":"NO");
		}
	}

}
