import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	private static final String SENTENCE="!sentence";
	private static final String TRANS_SENTENCE="!trans-sentence";
	private static final String INTRANS_SENTENCE="!intrans-sentence";
	private static final String SUBJECT="!subject";
	private static final String OBJECT="!object";
	private static final String NOUN_PHRASE="!noun-phrase";
	private static final String MODIFIED_NOUN = "!modified-noun";
	private static final String MODIFIER = "!modifier";
	private static final String VERB_PHRASE = "!verb-phrase";
	private static final String INTRANS_VERB_PHRASE = "!intrans-verb-phrase";
	private static final String PREP_PHRASE = "!prep-phrase";
	private static final String NOUN = "!noun";
	private static final String TRANS_VERB = "!trans-verb";
	private static final String INTRANS_VERB = "!intrans-verb";
	private static final String ARTICLE = "!article";
	private static final String ADJECTIVE = "!adjective";
	private static final String ADVERB = "!adverb";
	private static final String PREPOSITION = "!preposition";
	private static final String EMPTY = "!empty";
	private static final HashMap<String, String [][]> MAP=new HashMap<>();
	private static int K=1;
	
	private static void build(StringBuilder sb, String key) {
		if (key.length()==0 || key.charAt(0)!='!') {
			if (key.length()>0 && sb.length()>0) sb.append(' ');
			sb.append(key);
			return;
		}

		String [][] options=MAP.get(key);
		String [] list=options[K%options.length];
		if (options.length>1) K++;
		for (int i=0;i<list.length;i++) {
			build(sb,list[i]);
		}
	}

	public static void main (String [] args) throws Exception {
		MAP.put(SENTENCE, new String[][] {{TRANS_SENTENCE},{INTRANS_SENTENCE}});
		MAP.put(TRANS_SENTENCE, new String[][] {{SUBJECT,VERB_PHRASE,OBJECT,PREP_PHRASE}});
		MAP.put(INTRANS_SENTENCE, new String[][] {{SUBJECT,INTRANS_VERB_PHRASE,PREP_PHRASE}});
		MAP.put(SUBJECT, new String[][] {{NOUN_PHRASE}});
		MAP.put(OBJECT, new String[][] {{NOUN_PHRASE}});
		MAP.put(NOUN_PHRASE, new String[][] {{ARTICLE,MODIFIED_NOUN}});
		MAP.put(MODIFIED_NOUN, new String[][] {{NOUN},{MODIFIER,NOUN}});
		MAP.put(MODIFIER, new String[][] {{ADJECTIVE},{ADVERB,ADJECTIVE}});
		MAP.put(VERB_PHRASE, new String[][] {{TRANS_VERB},{ADVERB,TRANS_VERB}});
		MAP.put(INTRANS_VERB_PHRASE, new String[][] {{INTRANS_VERB},{ADVERB,INTRANS_VERB}});
		MAP.put(PREP_PHRASE, new String[][] {{PREPOSITION,NOUN_PHRASE},{EMPTY}});
		MAP.put(NOUN, new String[][] {{"man"},{"dog"},{"fish"},{"computer"},{"waves"}});
		MAP.put(TRANS_VERB, new String[][] {{"struck"},{"saw"},{"bit"},{"took"}});
		MAP.put(INTRANS_VERB, new String[][] {{"slept"},{"jumped"},{"walked"},{"swam"}});
		MAP.put(ARTICLE, new String[][] {{"the"},{"a"}});
		MAP.put(ADJECTIVE, new String[][] {{"green"},{"small"},{"rabid"},{"quick"}});
		MAP.put(ADVERB, new String[][] {{"nearly"},{"suddenly"},{"restlessly"}});
		MAP.put(PREPOSITION, new String[][] {{"on"},{"over"},{"through"}});
		MAP.put(EMPTY, new String [][] {{""}});

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringBuilder sb=new StringBuilder();
			build(sb,"!"+s);
			System.out.println(sb.toString());
		}
	}

}
