package com.virgin.novel.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class CustomDefinedTag extends TagSupport {
	private static final long serialVersionUID = -3370273759805780156L;
	private String name;
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print(name.substring(0,3));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
