import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static int [] Parent;

	private static boolean canChange(String s1, String s2) {
		if (s1.length()!=s2.length()) return false;
		int diff=0;
		for (int i=0;i<s1.length();i++) {
			if (s1.charAt(i)!=s2.charAt(i)) diff++;
			if (diff>1) return false;
		}
		return diff==1;
	}

	private static int getParent(int i) {
		if (Parent[i]==i) return i;
		return Parent[i]=getParent(Parent[i]);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<String> words=new ArrayList<>();
		HashMap<String,Integer> idxMap=new HashMap<>();
		while (!(s=br.readLine()).equals("--")) {
			words.add(s);
			idxMap.put(s, words.size()-1);
		}

		Parent=new int [words.size()];
		for (int i=0;i<Parent.length;i++) Parent[i]=i;
		
		for (int i=0;i<words.size();i++) {
			for (int i2=i+1;i2<words.size();i2++) {
				int p1=getParent(i);
				int p2=getParent(i2);
				if (p1!=p2 && canChange(words.get(i),words.get(i2))) Parent[p2]=p1;
			}
		}

		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			String from=st.nextToken();
			String to=st.nextToken();
			int p1=getParent(idxMap.get(from));
			int p2=getParent(idxMap.get(to));
			System.out.println(p1==p2?"Yes":"No");
		}
	}

}