package com.ttProject.quercus.test;

public class TestClass {
	private String data;
	public TestClass(String data) {
		this.data = data;
	}
	public void test() {
		System.out.println("test is called...");
	}
	@Override
	public String toString() {
		return data;
	}
}
