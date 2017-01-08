package com.icode.bookmarks.filter;

import com.icode.bookmarks.data.AccountRepository;
import com.icode.bookmarks.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Radu on 1/8/2017.
 */
public class ValidateUserInterceptor extends HandlerInterceptorAdapter {

    private static final String USER_ID_PARAM = "userId";

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (pathVariables.containsKey(USER_ID_PARAM)) {
            String userId = (String) pathVariables.get(USER_ID_PARAM);
            validateUser(userId);
        }

        return super.preHandle(request, response, handler);
    }


    private void validateUser(String userId) {
        this.accountRepository.findByUsername(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
