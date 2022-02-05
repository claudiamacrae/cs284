package hw1;

public class BinaryNumber {
	
	private int[] data;
	private int length;
	
	public BinaryNumber(int length){
		this.length = length;
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = 0;
		}
		this.data = arr;
	}
		
	public BinaryNumber(String str){
		int[] arr = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			int val = Character.getNumericValue(c);
			arr[i] = val;
		}
		this.data = arr;
		this.length = str.length();
	}
	
	public int getLength() {
		return length;
	}
	
	public int[] getInnerArray() {
		return data;
	}
	
	public int getDigit(int index) {
		if (index >= length) {
			System.out.println("Given index is out of bounds.");
			return -1;
		}
		return data[index];
	}
	
	public int toDecimal() {
		int dec = 0;
		for(int i = 0; i < length; i++) {
			dec = dec + data[length - (i + 1) ] * (int)Math.pow(2, i);
		}
		return dec;
	}
	
	public void bitShift(int direction, int amount) { 		
		int[] r;
		if (direction == 1) {				// right shift 
			r = new int[length - amount];
			for (int i = 0; i < r.length; i++) {	//trims amount of digits off the end of number
				r[i] = data[i];
			}
		}else if (direction == -1) { 			//left shift
			r = new int[length + amount];
			for (int i = 0; i < r.length; i++) { 	//copies data and appends amount of zeros at the end
				if ( i < length) {
					r[i] = data[i];
				} else {
					r[i] = 0;
				}
			}
		} else {
			return;
		}
		data = r;
		length = data.length;
		return;
	}
	
	public static int[] bwor(BinaryNumber bn1, BinaryNumber bn2) {
		if( bn1.length != bn2.length) {				//if numbers are not the same length, they are not valid input
			System.out.println("Numbers must be of the same length");
			return new int[0];
		}
		int[] result = new int[bn1.length];
		for (int i = 0; i < bn1.length; i++) {
			result[i] = (bn1.getInnerArray()[i] + bn2.getInnerArray()[i] > 0) ? 1 : 0; // if sum of elements is greater than 0, result digit is 1, else it is 2
		}
		return result;
	}

	public static int[] bwand(BinaryNumber bn1, BinaryNumber bn2) {
		if( bn1.length != bn2.length) {						//if numbers are not the same length, they are not valid input
			System.out.println("Numbers must be of the same length");
			return new int[0];
		}
		int[] result = new int[bn1.length];
		for (int i = 0; i < bn1.length; i++) {
			result[i] = (bn1.getInnerArray()[i] + bn2.getInnerArray()[i] > 1) ? 1 : 0; // if sum of elements is greater than 1, result digit is 1, else it is 2
		}
		return result;
	}
	
	public String toString() {
		String s = "";		//instantiate empty string
		for (int i = 0; i < length; i++) {
			s = s + String.valueOf(data[i]);	//traverse binary number, appending digits to the end of the string
		}
		return s;
	}
	
	public void add(BinaryNumber aBinaryNumber) {
		if( length < aBinaryNumber.length) {
			this.prepend(aBinaryNumber.length - length); //prepend zeros to the front of the binary number instantiation
		}else if ( length > aBinaryNumber.length) {
			aBinaryNumber.prepend(length - aBinaryNumber.length); //prepend zeros to the front of the binary number aBinaryNumber
		}
		int carry = 0;
		int sum ;
		for ( int i = aBinaryNumber.length - 1; i >= 0; i--) {
			sum = carry + this.data[i] + aBinaryNumber.data[i]; //add digits and any leftover carry
			aBinaryNumber.data[i] = sum % 2;
			carry = sum / 2;
		}
		if( carry != 0) {		//if there is a carry leftover after entire binary number has been traversed
			int[] nData = new int[aBinaryNumber.length + 1]; 	//create new int array
			nData[0] = carry;									//set first digit to be the carry ( value of 1)
			for (int i = 1; i < nData.length; i++) {
				nData[i] = aBinaryNumber.data[i-1];				// duplicate data to new array
			}
			aBinaryNumber.data = nData; 						//set new data to object
			aBinaryNumber.length = nData.length;
		}
	}
	
	public void prepend(int amount) {			//adds amount zeros to the front of given Binary Number
		int[] temp = new int[length + amount];
		int j = 0;
		for (int i = 0; i < temp.length; i++) {
			if(i < amount) { temp[i]= 0; }		//if less than amount of zeros have been assigned, continue assigning zeros
			else {
				temp[i] = data[j];			// copy the remaining digits from binary number over to new array
				j++;
			}
		}
		this.data = temp; 				// set array to fill binary number data
		this.length = temp.length;
	}
	

}
