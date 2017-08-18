package com.virgin.novel.proxy;

import java.lang.reflect.Method;

public interface Handler {
	Object handle(Method m,Object[] objs);
}
