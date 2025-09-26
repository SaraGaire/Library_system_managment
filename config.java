package com.example.library.config;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class ApiKeyFilter implements Filter {
@Value("${API_KEY:dev-demo-key-CHANGE_ME}")
private String expected;


@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
throws IOException, ServletException {
HttpServletRequest req = (HttpServletRequest) request;
String header = req.getHeader("X-API-KEY");
if (req.getRequestURI().startsWith("/auth") || req.getMethod().equals("OPTIONS")) {
chain.doFilter(request, response);
return;
}
if (expected.equals(header)) {
chain.doFilter(request, response);
} else {
((HttpServletResponse) response).sendError(401, "Invalid API key");
}
}
}
