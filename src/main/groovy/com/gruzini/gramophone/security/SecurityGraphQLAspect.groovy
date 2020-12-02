package com.gruzini.gramophone.security

import com.gruzini.gramophone.exceptions.UnauthenticatedAccessException
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(1)
class SecurityGraphQLAspect {

    @Before("allGraphQLResolverMethods() && isDefinedInApplication() && !isMethodAnnotatedAsUnsecured()")
    void doSecurityCheck() {
        if (SecurityContextHolder.getContext() == null ||
                SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass())) {
            throw new UnauthenticatedAccessException("Sorry, you should log in first to do that!")
        }
    }

    @Before("isMethodAnnotatedAsAdminUnsecured()")
    void doAdminSecurityCheck() {
        if (!isAuthorized()) {
            throw new UnauthenticatedAccessException("Sorry, you do not have enough rights to do that!")
        }
    }

    @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
    private void allGraphQLResolverMethods() {}

    @Pointcut("within(com.gruzini.gramophone..*)")
    private void isDefinedInApplication() {}

    @Pointcut("@annotation(com.gruzini.gramophone.annotations.Unsecured)")
    private void isMethodAnnotatedAsUnsecured() {}

    @Pointcut("@annotation(com.gruzini.gramophone.annotations.AdminSecured)")
    private void isMethodAnnotatedAsAdminUnsecured() {}

    private boolean isAuthorized() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority auth : authorities) {
                if (auth.getAuthority() == "ROLE_ADMIN")
                    return true
            }
        }
        return false
    }

}
