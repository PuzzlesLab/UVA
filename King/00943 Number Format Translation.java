import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		HashMap<String,BigInteger> values=new HashMap<>();
		values.put("zero", BigInteger.valueOf(0));
		values.put("um", BigInteger.valueOf(1));
		values.put("dois", BigInteger.valueOf(2));
		values.put("tres", BigInteger.valueOf(3));
		values.put("quatro", BigInteger.valueOf(4));
		values.put("cinco", BigInteger.valueOf(5));
		values.put("seis", BigInteger.valueOf(6));
		values.put("sete", BigInteger.valueOf(7));
		values.put("oito", BigInteger.valueOf(8));
		values.put("nove", BigInteger.valueOf(9));
		
		values.put("dez", BigInteger.valueOf(10));
		values.put("onze", BigInteger.valueOf(11));
		values.put("doze", BigInteger.valueOf(12));
		values.put("treze", BigInteger.valueOf(13));
		values.put("catorze", BigInteger.valueOf(14));
		values.put("quatorze", BigInteger.valueOf(14));
		values.put("quinze", BigInteger.valueOf(15));
		values.put("dezasseis", BigInteger.valueOf(16));
		values.put("dezesseis", BigInteger.valueOf(16));
		values.put("dezassete", BigInteger.valueOf(17));
		values.put("dezessete", BigInteger.valueOf(17));
		values.put("dezoito", BigInteger.valueOf(18));
		values.put("dezanove", BigInteger.valueOf(19));
		values.put("dezenove", BigInteger.valueOf(19));

		values.put("vinte", BigInteger.valueOf(20));
		values.put("trinta", BigInteger.valueOf(30));
		values.put("quarenta", BigInteger.valueOf(40));
		values.put("cinquenta", BigInteger.valueOf(50));
		values.put("sessenta", BigInteger.valueOf(60));
		values.put("setenta", BigInteger.valueOf(70));
		values.put("oitenta", BigInteger.valueOf(80));
		values.put("noventa", BigInteger.valueOf(90));
		
		values.put("cem", BigInteger.valueOf(100));
		values.put("cento", BigInteger.valueOf(100));
		values.put("duzentos", BigInteger.valueOf(200));
		values.put("trezentos", BigInteger.valueOf(300));
		values.put("quatrocentos", BigInteger.valueOf(400));
		values.put("quinhentos", BigInteger.valueOf(500));
		values.put("seiscentos", BigInteger.valueOf(600));
		values.put("setecentos", BigInteger.valueOf(700));
		values.put("oitocentos", BigInteger.valueOf(800));
		values.put("novecentos", BigInteger.valueOf(900));

		values.put("mil", BigInteger.valueOf(1000));
		values.put("milhao", BigInteger.valueOf(1000000));
		values.put("milhoes", BigInteger.valueOf(1000000));
		values.put("biliao", BigInteger.valueOf(1000000000));
		values.put("bilioes", BigInteger.valueOf(1000000000));
		values.put("bilhao", BigInteger.valueOf(1000000000));
		values.put("bilhoes", BigInteger.valueOf(1000000000));
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		System.out.println(TC);
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());

			LinkedList<BigInteger> list=new LinkedList<>();
			LinkedList<BigInteger> curr=new LinkedList<>();
			while (st.hasMoreTokens()) {
				String s=st.nextToken();
				if (s.charAt(s.length()-1)==',') s=s.substring(0,s.length()-1);
				BigInteger v=values.getOrDefault(s, null);
				if (v==null) continue;

				if (!curr.isEmpty() && v.compareTo(curr.getFirst())>0) {
					BigInteger temp=BigInteger.ZERO;
					while (!curr.isEmpty()) temp=temp.add(curr.removeFirst());
					list.add(temp.multiply(v));
				} else curr.addLast(v);
			}

			BigInteger ans=BigInteger.ZERO;
			while (!list.isEmpty()) ans=ans.add(list.removeFirst());
			while (!curr.isEmpty()) ans=ans.add(curr.removeFirst());
			System.out.println(ans);
		}
	}
}