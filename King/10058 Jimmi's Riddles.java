import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		final String VERB="(hate|love|know|like)s*";
		final String NOUN="(tom|jerry|goofy|mickey|jimmy|dog|cat|mouse)";
		final String ARTICLE="(a|the)";
		final String ACTOR=String.format("(%s|%s %s)",NOUN, ARTICLE, NOUN);
		final String ACTIVE_LIST=String.format("((%s and )*%s)", ACTOR, ACTOR);
		final String ACTION=String.format("%s %s %s",ACTIVE_LIST, VERB, ACTIVE_LIST);
		final String STATEMENT=String.format("^%s( , %s)*$",ACTION, ACTION);

		final String YES="YES I WILL";
		final String NO="NO I WON'T";
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			System.out.println(s.matches(STATEMENT)?YES:NO);
		}
	}

}
