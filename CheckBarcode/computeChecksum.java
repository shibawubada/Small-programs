//Harry Goodwin
//02/10/2020
public int computeChecksum(String iban) {

	//initialise variables
	int checkSum = 0;
	String stringInt = "";
	String digits = "";

	//remove  any white space from string
	iban = iban.replaceAll(" ", "").toUpperCase();

	//substring first 2 characters for  country validation
	String country = iban.substring(0,2);

	//country to ISBN length validation
	if(country.equals("GB")  &&  iban.length()!=22) {
		System.out.println("Invalid IBAN length: "+iban.length());
		return -1;
	}
	else  if(country.equals("GR")  &&  iban.length()!=27) {
		System.out.println("Invalid IBAN length: "+iban.length());
		return -1;
	}
	else  if(country.equals("SA")&&  iban.length()!=24) {
		System.out.println("Invalid IBAN length: "+iban.length());
		return -1;
	}
	else  if(country.equals("CH")&&  iban.length()!=21) {
		System.out.println("Invalid IBAN length: "+iban.length());
		return -1;
	}
	else  if(country.equals("TR")&&  iban.length()!=26) {
		System.out.println("Invalid IBAN length: "+iban.length());
		return -1;
	}
	//if country not recognised error
	else if(!country.equals("GB") && !country.equals("GR") && !country.equals("SA")
			&& !country.equals("CH") && !country.equals("TR")) {
		System.out.println("Unknown country code: "+country);
		return -1;
	}

	//letters mapping solution on stack overflow ,Etaoin 
	String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	//take substring of country notifier for conversion
	String tempIban = iban.substring(0,2);

	//iterate through country notifier
	for(int i=0;i<tempIban.length();i++) {

		//concate the index of letter in letters +10
		stringInt += letters.indexOf(tempIban.charAt(i))+10;
	}	

	//build iban again with converted country notifier
	String ibanHold = iban.substring(4,iban.length())+stringInt+"00";

	//create substring of bank name for conversion
	tempIban = ibanHold.substring(0,4);

	for(int i=0;i<ibanHold.length();i++) {

		//validate whether character at index is a number
		if(Character.isDigit(ibanHold.charAt(i))) {
			//concate number to string
			digits += ibanHold.charAt(i);
		}
		//validate whether character is a letter at index
		else if(Character.isLetter(ibanHold.charAt(i))){
			//concate the index of letter in letters +10
			digits += letters.indexOf(ibanHold.charAt(i))+10;
		}
		//IBAN character validation if special char is detected
		else {
			System.out.println("Invalid character in IBAN: "+ ibanHold.charAt(i));
			return -1;
		}
	}
	//use bigInteger class  to mod digits with 97		
	int mod = new BigInteger(digits).mod(BigInteger.valueOf(97)).intValue();
	//compute checksum	
	checkSum=98-mod;

	return  checkSum;
}
