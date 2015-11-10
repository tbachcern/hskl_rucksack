package test;

import java.util.Arrays;

import kpp.CElement;

import org.junit.Test;

import util.CUtils;

public class Test_Utils {

	@Test
	public void test_sort() {
		int n = 10_000;
		CElement[] test = new CElement[n];
		for( int i = 0; i < n; i++){
			test[i] = new CElement(	(int)(Math.random()*20) +1,
									(int)(Math.random()*10) +1);
		}
		
		long start = System.nanoTime();
		test = CUtils.sortByEfficiency(test);
		
		System.out.println(System.nanoTime() - start);
//		System.out.println(Arrays.toString(test));
		
	}
	
}
