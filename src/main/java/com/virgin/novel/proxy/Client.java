package com.virgin.novel.proxy;


public class Client {
	public static void main(String[] args) {
		try {
			Moveable m = (Moveable) Proxy.newProxyInstance(Moveable.class,new TimeHandler(new Tank()));
			String name = m.getName();
			System.out.println("name="+name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		try {
			Method m = Moveable.class.getMethod("getName",new Class<?>[]{});
			System.out.println(m.getReturnType().getName());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		*/
	}
}
