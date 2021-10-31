package com.kirill.mvc.servlets

import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/login"])
class LoginServlet : HttpServlet() {
    private val login = "kirill"
    private val password = "kirill"

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.getRequestDispatcher("/login.html").forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        if (req.getParameter("login") == login && req.getParameter("password") == password) {
            resp.addCookie(Cookie("auth", System.currentTimeMillis().toString()))
            resp.sendRedirect("/app/list")
        } else
            resp.sendRedirect("/login")
    }
}