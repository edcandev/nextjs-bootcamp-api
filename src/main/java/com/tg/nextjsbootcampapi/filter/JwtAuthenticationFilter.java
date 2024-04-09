package com.tg.nextjsbootcampapi.filter;

import com.tg.nextjsbootcampapi.jwt.JwtService;
import com.tg.nextjsbootcampapi.service.UserDetailServiceImpl;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailServiceImpl  userDetailService;

  public JwtAuthenticationFilter(JwtService jwtService, UserDetailServiceImpl userDetailService) {
    this.jwtService = jwtService;
    this.userDetailService = userDetailService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    String authHeader = request.getHeader("Authorization");

    if(Objects.isNull(authHeader) || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authHeader.substring(7);

    String email = null;

    try {

    email = jwtService.extractUsername(token);

    } catch (SignatureException ex) {
      // TODO: Exc handling...
      System.out.println("[EXCEPTION CACHED]");
    }

    System.out.println("[EMAIL] - " + email);

    if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      UserDetails userDetails = userDetailService.loadUserByUsername(email);


      if(jwtService.isValid(token, userDetails)) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities()
        );

        authenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

      }

    }

    filterChain.doFilter(request, response);

  }

}
