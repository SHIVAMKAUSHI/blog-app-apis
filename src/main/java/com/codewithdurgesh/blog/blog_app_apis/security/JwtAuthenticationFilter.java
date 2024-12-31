package com.codewithdurgesh.blog.blog_app_apis.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        1. get token

        String requestToken = request.getHeader("Authorization");

//        Bearer Token
        System.out.println(requestToken);
        String username=null;
        String token=null;
        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try{
                username=this.jwtHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException e)
            {
                System.out.println("Unable to get Jwt Token");
            }catch (ExpiredJwtException e){
                System.out.println("Jwt token has expired");
            }catch (MalformedJwtException e){
                System.out.println("Invalid jwt");
            }
        }else {
            System.out.println("Jwt token does not starts with Bearer");
        }
//once we get the token
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtHelper.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }else {
                System.out.println("Invalid jwt token");
            }
        }else {
            System.out.println("username is null or context is not null");
        }




        //    Authorization= Bearer wghbsafhsohg
//        String requestHeader = request.getHeader("Authorization");
////        logger.info("Header:{}", requestHeader);
//        String username=null;
//        String token=null;
//
//        if (requestHeader!=null && requestHeader.startsWith("Bearer")){
//            token=requestHeader.substring(7);
//            try{
//                username=this.jwtHelper.getUsernameFromToken(token);
//
//            }catch (IllegalArgumentException e){
//                logger.info("Illegal Argument while fetching the username !!");
//                e.printStackTrace();
//            } catch (ExpiredJwtException e){
//                logger.info("Given jwt token is expired !!");
//                e.printStackTrace();
//            } catch (MalformedJwtException e){
//                logger.info("Some changed has done in token !! Invalid Token");
//                e.printStackTrace();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        } else {
//            logger.info("invalid Header Value !! ");
//        }
//
//        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
//
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
//            if(validateToken){
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }else {
//            logger.info("Validation failed !!");
//        }

        filterChain.doFilter(request,response);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.equals("/api/v1/auth/login"); // Exclude /login from JWT filter
    }
}
