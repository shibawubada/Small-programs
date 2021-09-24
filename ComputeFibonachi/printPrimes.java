//Harry Goodwin
//25/09/2020

public void printPrimes(int range) {

	for(int n=2;n<=range;n++) {

		Boolean prime = true;
		for(int f=2;f<=Math.sqrt(n);f++) {

			if(n%f==0) {
				prime = false;
			}
		}
		if(prime) {
			System.out.println(n+" is prime");
		}
	}
}