import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(":\n");
			sb.append("#include<string.h>\n#include<stdio.h>\nint main()\n{\n");
			for (int n=1;n<=N;n++) {
				String s=br.readLine();
				s=s.replace("\\", "\\\\");
				s=s.replace("\"","\\\"");
				sb.append("printf(\"");
				sb.append(s);
				sb.append("\\n\"");
				sb.append(");\n");
			}
			sb.append("printf(\"\\n\");\nreturn 0;\n}");
			System.out.println(sb);
		}
	}
}