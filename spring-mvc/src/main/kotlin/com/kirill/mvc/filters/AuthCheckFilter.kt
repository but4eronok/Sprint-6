package com.kirill.mvc.filters

import java.time.Instant
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["/app/*", "/api/*"])
class AuthCheckFilter: Filter {
    override fun doFilter(req: ServletRequest?, resp: ServletResponse?, chain: FilterChain?) {
        val request = req as HttpServletRequest
        val response = resp as HttpServletResponse
        val cookies = request.cookies

        if (cookies != null) {
            for (cookie in cookies) {
                if (cookie.name == "auth" && cookie.value < Instant.now().toEpochMilli().toString()) {
                    chain?.doFilter(req, resp)
                } else {
                    response.sendRedirect("/login")
                }
            }
        } else {
            response.sendRedirect("/login")
        }
    }
}