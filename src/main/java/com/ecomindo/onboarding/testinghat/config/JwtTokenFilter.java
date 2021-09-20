package com.ecomindo.onboarding.testinghat.config;

import static java.util.List.of;
import static java.util.Optional.ofNullable;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomindo.onboarding.testinghat.dto.CustomUserDTO;
import com.ecomindo.onboarding.testinghat.utils.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

// import lombok.RequiredArgsConstructor;

@Component
// @RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	@Order(1)
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		// Get authorization header and validate
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(request, response);
			return;
		}

		// Get jwt token and validate
		final String token = header.split(" ")[1].trim();
		if (!jwtTokenUtil.validate(token)) {
			chain.doFilter(request, response);
			return;
		}

		// Get user identity
		CustomUserDTO user = new CustomUserDTO(jwtTokenUtil.getUsername(token), jwtTokenUtil.getAuthorities(token),
				jwtTokenUtil.getFullname(token));

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				ofNullable(user).map(UserDetails::getAuthorities).orElse(of()));

		// Set identity the spring security context
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
	}
}