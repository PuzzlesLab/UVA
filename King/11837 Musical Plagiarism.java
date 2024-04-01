import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static final String [][] Notes= {
		{"A"},
		{"A#","Bb"},
		{"B","Cb"},
		{"C","B#"},
		{"Db","C#"},
		{"D"},
		{"Eb","D#"},
		{"E","Fb"},
		{"F","E#"},
		{"F#","Gb"},
		{"G"},
		{"Ab","G#"},
	};
	private static final HashMap<String,Integer> NotePos=new HashMap<>();

	private static boolean check(String [] mM, String [] mT) {
		if (mM.length==1 && mT.length==1) return false;
		if (mT.length==1) {
			for (int m=0;m<mM.length;m++) if (mM[m].equals(mT[0])) return true;
			return false;
		}

		int [] dM=new int [mM.length-1];
		for (int m=0;m<dM.length;m++) dM[m]=(NotePos.get(mM[m])-NotePos.get(mM[m+1])+Notes.length)%Notes.length;

		int [] dT=new int [mT.length-1];
		for (int t=0;t<dT.length;t++) dT[t]=(NotePos.get(mT[t])-NotePos.get(mT[t+1])+Notes.length)%Notes.length;

		int t1=0;
		int t2=-1;
		int [] back=new int [dT.length+1];
		back[0]=-1;
		while (t1<dT.length) {
			while (t2>=0 && dT[t1]!=dT[t2]) t2=back[t2];
			back[++t1]=++t2;
		}

		int m=0;
		int t=0;
		while (m<dM.length && t<dT.length) {
			while (t>=0 && dT[t]!=dM[m]) t=back[t];
			t++;
			m++;
			if (t==dT.length) return true;
		}

		return false;
	}

	public static void main (String [] args) throws Exception {
		for (int i=0;i<Notes.length;i++) for (int i2=0;i2<Notes[i].length;i2++) NotePos.put(Notes[i][i2],i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			String [] mM=new String [M];
			st=new StringTokenizer(br.readLine());
			for (int m=0;m<M;m++) mM[m]=st.nextToken();

			String [] mT=new String [T];
			st=new StringTokenizer(br.readLine());
			for (int t=0;t<T;t++) mT[t]=st.nextToken();

			System.out.println(check(mM,mT)?'S':'N');
		}
	}
}
