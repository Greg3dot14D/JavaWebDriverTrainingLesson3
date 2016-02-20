package ru.greg3d;

import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class testAssertion {

	@BeforeGroups(groups = "test")
	public void doBeforeTest(){
		System.out.println("before test");
	}
	
	@AfterGroups(groups = "test2")
	public void doBeforeTest2(){
		System.out.println("after test2");
	}
	
	@Test(groups="test")
	public void test() {
		//Assert.ignoreEquals(1, 2, "123");
		Assert.assertFalse(true);
		System.out.println("test - test");
	}
	
	@Test(groups="test2")
	public void test2() {
		System.out.println("test - test2");
	}

	
	@Test(groups="test2")
	public void test2_2() {
		System.out.println(String.format("a[%d]/div",1));
	}
	
	@Test
	public void test3() {
		System.out.println("test - test3");
	}
}
