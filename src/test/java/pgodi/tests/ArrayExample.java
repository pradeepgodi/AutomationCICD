package pgodi.tests;

public class ArrayExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[] = { 1, 2, 3 };
		String sarr[] = { "a", "b" };

		System.out.println(arr.length);
		System.out.println(sarr.length);

		int m[][] = { { 2, 4, 5 }, { 3, 4, 7 }, { 1, 2, 9 } };

		int minNum = m[0][0];
		int maxNum = m[0][0];

		System.out.println("before  in the array = " + minNum);
		for (int outer = 0; outer < 3; outer++) {
			for (int inner = 0; inner < 3; inner++) {
				if (m[outer][inner] < minNum) {
					minNum = m[outer][inner];
				} else {
					maxNum= m[outer][inner];
				}
			}

		}
		System.out.println("smallest in the array = " + minNum);
		System.out.println("max in the array = " + maxNum);
	}
}
