package com.tasktracker.tasktracker.logging;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;



@Component
public class RequestLoggingFilter extends OncePerRequestFilter {


    private static final Logger logger =
            LoggerFactory.getLogger(
                    RequestLoggingFilter.class
            );



    @Override
    protected void doFilterInternal(

            HttpServletRequest request,

            HttpServletResponse response,

            FilterChain filterChain

    )
            throws ServletException, IOException {



        long startTime =
                System.currentTimeMillis();



        String requestId =
                UUID.randomUUID()
                        .toString();



        try {


            filterChain.doFilter(
                    request,
                    response
            );


        }
        finally {



            long duration =
                    System.currentTimeMillis()
                            - startTime;



            String jsonLog = String.format(
                    "{\"timestamp\":\"%s\",\"level\":\"INFO\",\"message\":\"HTTP Request Completed\",\"request_id\":\"%s\",\"method\":\"%s\",\"path\":\"%s\",\"status\":%d,\"duration_ms\":%d}",
                    LocalDateTime.now(),
                    requestId,
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    duration
            );

            logger.info(jsonLog);


        }


    }


}