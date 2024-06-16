package pgodi.pageobjects;

import java.util.Arrays;
import java.util.List;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String[] a = {"a","b"};
		
		List<String> s =Arrays.asList(a);
		long c= s.stream().filter(f -> f.equals("a")).count();
		System.out.println(c);
	}

}
