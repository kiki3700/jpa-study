package value;

import value.domain.Address;

public class Compare {
	public static void main(String[] args) {
		int a = 10;
		int b= 10;
		System.out.println("a == b :"+ (a==b));
		
		Address address1 = new Address("서울", "대방", "1234");
		Address address2 = new Address("서울", "대방", "1234");
		
		System.out.println("address1 equal address2 : "+(address1.equals(address2)));
				
	}
}
