import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int T=1;
		while (!(s=br.readLine()).equals("end")) {
			ArrayList<Stack<Character>> list=new ArrayList<>();
			for (char c : s.toCharArray()) {
				boolean added=false;
				for (int i=0;i<list.size() && !added;i++) if (c<=list.get(i).peek()) { //Tries to put on larger letter
					list.get(i).push(c);
					added=true;
				}
				if (!added) {
					list.add(new Stack<>());
					list.get(list.size()-1).push(c);
				}
			}
			System.out.printf("Case %d: %d\n", T++, list.size());
		}
	}

}