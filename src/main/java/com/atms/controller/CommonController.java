package com.atms.controller;

import com.jfinal.core.Controller;

/**
 * CommonController
 */
public class CommonController extends Controller {
	
	public void index() {
		render("/index.jsp");
	}
}
