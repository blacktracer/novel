package com.virgin.novel.proxy;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Proxy {
	public static Object newProxyInstance(Class<?> ifce,Handler h) throws Exception{
		String r = "\r";
		String methodStr = "";
		Method[] methods = ifce.getMethods();
		StringBuilder parameterDefinedCode = null;
		StringBuilder parameterCode = null;
		StringBuilder parameterClassCode = null;
		String returnTypeName = null;
		for(Method m : methods){
			returnTypeName = m.getReturnType().getName();
			parameterDefinedCode = new StringBuilder();
			parameterCode = new StringBuilder();
			parameterClassCode = new StringBuilder();
			Class<?>[] parameterTypes = m.getParameterTypes();
			Class<?> paramterType = null;
			for(int i=0; i<parameterTypes.length; i++){
				paramterType = parameterTypes[i];
				parameterDefinedCode.append(paramterType.getName()+" arg"+i+",");
				parameterCode.append("arg"+i+",");
				parameterClassCode.append(paramterType.getName() + ".class,");
			}
			if(parameterTypes.length>0){
				parameterDefinedCode.deleteCharAt(parameterDefinedCode.length()-1);
				parameterCode.deleteCharAt(parameterCode.length()-1);
				parameterClassCode.deleteCharAt(parameterClassCode.length()-1);
			}
			methodStr += 
					"    @Override" + r +
					"    public "+returnTypeName+" "+m.getName()+"("+parameterDefinedCode+") {" + r +
					"        try {" + r +
					"	         Method m = "+ifce.getName()+".class.getMethod(\""+m.getName()+"\",new Class<?>[]{"+parameterClassCode+"});" + r +
					"		     Object[] objs = new Object[]{"+parameterCode+"};" + r +
					"		     "+("void".equals(returnTypeName)?"":"return ("+returnTypeName+")")+" h.handle(m,objs);" + r +
					"        } catch (Exception e) {" + r +
					"	         e.printStackTrace();" + r +
					("void".equals(returnTypeName)?"":
					("	         return null;" + r)) +
					"        }" + r +
					"    }" + r;
		}
		String src = 
				"package com.virgin.novel.proxy;" + r +
				"import java.lang.reflect.Method;" + r +
				"public class $Proxy1 implements "+ifce.getName()+" {" + r +
				"    private Handler h;" + r +
				"    public $Proxy1(Handler h) {" + r +
				"		this.h = h;" + r +
				"    }" + r +
				methodStr +
				"}";
		String fileName = "D:\\study\\com\\virgin\\novel\\proxy\\$Proxy1.java";
		File dir = new File("D:\\study\\com\\virgin\\novel\\proxy");
		dir.mkdirs();
		File f = new File(fileName);
		FileWriter fw = new FileWriter(f);
		fw.write(src);
		fw.flush();
		fw.close();
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fmr = compiler.getStandardFileManager(null, null, null);
		Iterable<? extends JavaFileObject> units = fmr.getJavaFileObjects(fileName);
		CompilationTask task = compiler.getTask(null, fmr, null, null, null, units);
		task.call();
		URL[] urls = new URL[]{new URL("file:/"+"D:\\study\\")};
		URLClassLoader loader = new URLClassLoader(urls);
		Class<?> clazz = loader.loadClass("com.virgin.novel.proxy.$Proxy1");
		Object obj = clazz.getConstructor(Handler.class).newInstance(h);
		loader.close();
		return  obj;
	}
}
