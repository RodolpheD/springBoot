package com.rdeclerck.springbootrestserver.component;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CrossDomainFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {
		httpServletResponse.addHeader("Access-Control-Allow-Origin","*");//roures les URI sont autorisées
		httpServletResponse.addHeader("Access-Control-Allow-Headers", "GET,POST,PUT,DELETE,OPTIONS");
		httpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, contenttype,accept, x-req");
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
