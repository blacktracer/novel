package com.virgin.novel.proxy;

public class Tank implements Moveable {

	@Override
	public void move() {
		System.out.println("moving ...");
	}

	@Override
	public void run(String str) {
		System.out.println(str + " is running ...");
	}

	@Override
	public String getName() {
		return "hi,girl";
	}
}
