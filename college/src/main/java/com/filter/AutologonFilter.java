package com.filter;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutologonFilter implements Filter {

    public AutologonFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

    	/*  || url.endsWith("/img/user_botton.gif") || url.endsWith("/img/user_bottom_l.gif")
				|| url.endsWith("/img/user_bottom_c.gif") || url.endsWith("/img/user_bottom_r.gif")
				|| url.endsWith("/img/user_all_bg.gif") || url.endsWith("/img/user_botton.gif")
				|| url.endsWith("/img/user_main_r.gif") || url.endsWith("/img/user_main_c.gif")
				|| url.endsWith("/img/user_top_c.gif") || url.endsWith("/img/user_top_r.gif")
				|| url.endsWith("/img/user_main_l.gif") || url.endsWith("/img/user_top_l.gif")
				|| url.endsWith("/img/user_login_password.gif") || url.endsWith("/img/user_login_name.gif")*/

		req.setCharacterEncoding("UTF-8");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String url = request.getRequestURI();
		// 初次登录
		if(url.endsWith("login.jsp") || url.endsWith("/") || url.endsWith("login")
				|| url.endsWith("/createImage") || url.endsWith("/css/login.css")
				|| url.endsWith("/img/user_botton.gif") || url.endsWith("/img/user_bottom_l.gif")
				|| url.endsWith("/img/user_bottom_c.gif") || url.endsWith("/img/user_bottom_r.gif")
				|| url.endsWith("/img/user_all_bg.gif") || url.endsWith("/img/user_botton.gif")
				|| url.endsWith("/img/user_main_r.gif") || url.endsWith("/img/user_main_c.gif")
				|| url.endsWith("/img/user_top_c.gif") || url.endsWith("/img/user_top_r.gif")
				|| url.endsWith("/img/user_main_l.gif") || url.endsWith("/img/user_top_l.gif")
				|| url.endsWith("/img/user_login_password.gif") || url.endsWith("/img/user_login_name.gif")
				|| url.endsWith("/jquery.validate.min.js") || url.endsWith("/jquery-1.8.0.min.js")) {
			if(session.getAttribute("userTb1") != null) {
				response.sendRedirect("index.jsp");
			} else {
				Cookie[] cookies = request.getCookies();
				boolean isContinue = false;
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						if ("username".equals(cookie.getName())) {
							isContinue = true;
							break;
						}
					}
				}
				if(isContinue) {
					response.sendRedirect("index.jsp");
				} else {
					chain.doFilter(request, response);
				}
			}
		} else {
			if(session.getAttribute("userTb1") != null) {
				chain.doFilter(request, response);
			} else {
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (int i = 0; i < cookies.length; i++) {
						Cookie cookie = cookies[i];
						if ("username".equals(cookie.getName())) {
							chain.doFilter(request, response);
						} else {
							response.sendRedirect("/login.jsp");
							return;
						}
					}
				} else {
					response.sendRedirect("/login.jsp");
					return;
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	
	}
}
