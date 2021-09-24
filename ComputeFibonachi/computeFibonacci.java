//Harry Goodwin
//25/09/2020

public int computeFibonacci (int n) {

	int result =1;
	int lastResult = 1;
	int temp = 0;

	for(int i=0;i<n-2;i++) {
		temp=result;
		result+=lastResult;
		lastResult=temp;
	}
	return result;
}