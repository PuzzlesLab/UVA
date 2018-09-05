import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			ArrayList<Integer> list=new ArrayList<>();
			while (st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
			
			char [] currentState=new char [list.size()];
			int [] stateCooldown=new int [list.size()];
			boolean flag=false;
			for (int i=0;i<currentState.length;i++) currentState[i]='G';
			for (int i=0;i<list.size();i++) stateCooldown[i]=list.get(i)-5;
			
			int ans=-1;
			for (int currTime=1; currTime<=3600; currTime++) {
				for (int i=0;i<list.size();i++) {
					stateCooldown[i]--;
					if (stateCooldown[i]==0) {
						if (currentState[i]=='R') {
							currentState[i]='G';
							stateCooldown[i]=list.get(i)-5;
						} else if (currentState[i]=='G') {
							currentState[i]='Y';
							stateCooldown[i]=5;
						} else if (currentState[i]=='Y') {
							currentState[i]='R';
							stateCooldown[i]=list.get(i);
							flag=true;
						}
					}
				}
				
				int greenCount=0;
				for (int i=0;i<list.size();i++) if (currentState[i]=='G' && flag) greenCount++;
				if (greenCount==list.size()) {
					ans=currTime;
					break;
				}
			}
			if (ans==-1) System.out.println("Set "+testCase+" is unable to synch after one hour.");
			else {
				int min=ans/60;
				int sec=ans%60;
				System.out.println("Set "+testCase+" synchs again at "+min+" minute(s) and "+sec+" second(s) after all turning green.");
			}
			testCase++;
		}
	}

}