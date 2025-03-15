import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine().trim()); // Wtf? Judge input contains <number><space>!
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long [] lengths={Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken())};
			Arrays.sort(lengths);

			if (lengths[0]==lengths[1] && lengths[1]==lengths[2] && lengths[2]==lengths[3]) {
				System.out.println("square");
			} else if (lengths[0]==lengths[1] && lengths[2]==lengths[3]) {
				System.out.println("rectangle");
			} else if (lengths[0]+lengths[1]+lengths[2]>lengths[3]) {
				System.out.println("quadrangle");
			} else {
				System.out.println("banana");
			}
		}
 	}

}
