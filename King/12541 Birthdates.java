import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static class Person implements Comparable<Person> {
		String name;
		int [] date;
		
		public Person(StringTokenizer st) {
			this.name=st.nextToken();
			this.date=new int [3];
			for (int i=0;i<3;i++) this.date[i]=Integer.parseInt(st.nextToken());
		}

		@Override
		public int compareTo(Person arg0) {
			for (int i=2;i>=0;i--) if (this.date[i]!=arg0.date[i]) return this.date[i]-arg0.date[i];
			return 0;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		Person [] p=new Person [N];
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			p[n]=new Person(st);
		}
		Arrays.sort(p);
		System.out.println(p[N-1].name);
		System.out.println(p[0].name);
	}

}