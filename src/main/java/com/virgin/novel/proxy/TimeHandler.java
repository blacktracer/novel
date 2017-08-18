package com.virgin.novel.proxy;

import java.lang.reflect.Method;

public class TimeHandler implements Handler{
	private Object target;
	
	public TimeHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object handle(Method m,Object[] objs) {
		try {
			long start = System.currentTimeMillis();
			System.out.println("start="+start);
			Object result = m.invoke(target,objs);
			long end = System.currentTimeMillis();
			System.out.println("end="+end);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
