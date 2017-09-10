
public class PrimeNumber {

	public static void main(String[] args) {
		int i,j;
		int num=0;
		for(i=3; i<100; i=i+2) {
			for(j=3; j<i; j=j+2){
				if (i % j == 0) break;
			}
			if(i==j) {
				System.out.print(i + "\t"); 
				num++;
				if(num%5 == 0) System.out.print("\n");
				}
		}
		System.out.println("\n" + num);
	}
}
