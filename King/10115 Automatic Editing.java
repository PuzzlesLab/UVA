import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static class Tuple {
		String from;
		String to;
		
		public Tuple(String f, String t) {
			this.from=f;
			this.to=t;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			ArrayList<Tuple> list=new ArrayList<>();
			for (int n=0;n<N;n++) list.add(new Tuple(br.readLine(),br.readLine()));
			
			s=br.readLine();
			for (int n=0;n<list.size();n++) {
				while (true) {
					String s2=s.replaceFirst(list.get(n).from, list.get(n).to);
					if (s.equals(s2)) break;
					s=s2;
				}
			}
			System.out.println(s);
		}
	}
}