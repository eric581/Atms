package com.atms.config.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * BlogInterceptor
 */
public class BlogInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation ai) {
		ai.invoke();
	}
}
