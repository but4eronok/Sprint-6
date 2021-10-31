package com.kirill.mvc.filters

import java.time.Instant
import java.util.logging.Logger
import javax.servlet.FilterChain
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["/*"])
class LoggingFilter: HttpFilter() {
    override fun doFilter(req: HttpServletRequest?, resp: HttpServletResponse?, chain: FilterChain?) {
        val LOG = Logger.getLogger(LoggingFilter::class.java.name)

        LOG.info( "protocol ${req?.protocol}")
        LOG.info("port ${req?.serverPort}")
        LOG.info("date ${Instant.now()}")
        LOG.info("Тип ${req?.method}")
        LOG.info("URL ${req?.requestURL}")

        chain!!.doFilter(req, resp)
    }
}