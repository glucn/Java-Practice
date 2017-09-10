
public class LogicalOpTable {

	public static void main(String[] args) {
		int p, q;
		
		System.out.println("P\tQ\tAND\tOR\tXOR");
		
		for(p=0; p<=1; p++) {
			for(q=0; q<=1; q++) {
				System.out.print(p + "\t" + q + "\t");
				System.out.print(((p==1)&(q==1))?1 + "\t":0 + "\t");
				System.out.print(((p==1)|(q==1))?1 + "\t":0 + "\t");
				System.out.println(((p==1)^(q==1))?1:0);	
			}
		}
	}

}
