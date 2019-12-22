import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals(".")) {
			char [] ch=s.toCharArray();
			int best=1;
			for (int size=1;size<=ch.length/2;size++) if (ch.length%size==0) {
				String match=s.substring(0, size);
				boolean flag=true;
				for (int i=1;i<ch.length/size;i++) flag &= match.equals(s.substring(i*size,(i+1)*size));
				if (flag) {
					best=Math.max(best, ch.length/size);
					break;
				}
			}
			System.out.println(best);
		}
	}

}