//Harry Goodwin
//02/10/2020
public  boolean checkBarcode(String input) {
		//initialise  variables	
		int checkSum = 0;
		int  evenSum = 0;
		int oddSum   = 0;

		//Input validation solution "Adam  Liss", Stack overflow
		if(input.matches("[0-9]+") && input.length() > 7) {

			//validate barcode length
			if(input.length() == 13){

				//iterate over barcode values,  ignoring last  value
				for(int i=0;i<input.length()-1;i++) {

					//even index  rule
					if(i==0 || i%2==0) {
						evenSum +=  Character.getNumericValue(input.charAt(i));
					}

					//odd index rule
					else {
						oddSum += Character.getNumericValue(input.charAt(i))*3;
					}				
				}
			}
			//validate barcode length
			else if (input.length() == 8) {

				for(int i=0;i<input.length()-1;i++) {
					//even index  rule
					if(i==0 || i%2==0) {
						evenSum +=  Character.getNumericValue(input.charAt(i))*3;
					}

					//odd index rule
					else {
						oddSum += Character.getNumericValue(input.charAt(i));
					}	
				}
			}
			checkSum = oddSum+evenSum;

			//check digit compared to 10-modulo  10
			if(10-(checkSum%10) == Character.getNumericValue(input.charAt(input.length()-1))){					
				return true;
			}
			else {					
				return false;
			}
		}

		else {
			return false;		
		}
	}