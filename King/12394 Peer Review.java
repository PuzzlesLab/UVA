import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
	
	public static class Author {
		int id;
		String institution;
	}
	
	public static class Paper {
		Author author;
		ArrayList<Author> reviewers;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int K=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			Author [] authors=new Author[N+1];
			Paper [] papers=new Paper[N+1];
			for (int n=1;n<=N;n++) {
				authors[n]=new Author();
				authors[n].id=n;
				papers[n]=new Paper();
				papers[n].author=authors[n];
				papers[n].reviewers=new ArrayList<>();
			}
			
			for (int n=1;n<=N;n++) {
				st=new StringTokenizer(br.readLine());
				Author a=authors[n];
				a.institution=st.nextToken();
				for (int k=0;k<K;k++) papers[Integer.parseInt(st.nextToken())].reviewers.add(a);
			}
			
			int problems=0;
			for (int n=1;n<=N;n++) {
				boolean foundProblem=false;
				for (Author reviewer : papers[n].reviewers) if (reviewer.institution.equals(papers[n].author.institution)) {
					problems++;
					foundProblem=true;
					break;
				}
				if (!foundProblem) {
					HashSet<Author> uniqueReviewers=new HashSet<>();
					uniqueReviewers.addAll(papers[n].reviewers);
					if (uniqueReviewers.size()!=papers[n].reviewers.size() || uniqueReviewers.size() != K) {
						problems++;
						foundProblem=true;
					}
				}
			}
			if (problems==0) System.out.println("NO PROBLEMS FOUND");
			else if (problems==1) System.out.println("1 PROBLEM FOUND");
			else System.out.printf("%d PROBLEMS FOUND\n", problems);
		}
	}

}