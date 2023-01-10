package com.pythonstrup.demo.common.aop;

import com.pythonstrup.demo.domain.article.entity.Article;
import com.pythonstrup.demo.domain.log.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAop {

    private final LogService logService;

    @Pointcut("execution(* com.pythonstrup.demo.domain.article.service.*.*(..))")
    private void cut() {}

    @Pointcut("execution(* com.pythonstrup.demo.domain.article.service.ArticleService.save(..))")
    private void articleSave() {}

    @Before("cut()")
    public void beforeLog(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        log.info("--------------method name = {} --------------", method.getName());

        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) log.info("no parameter");
        for (Object arg: args) {
            log.info("parameter type = {}", arg.getClass().getSimpleName());
            log.info("parameter value = {}", arg);
        }
    }

    @AfterReturning(value = "articleSave()", returning = "article")
    public void afterReturnLog(JoinPoint joinPoint, Article article) {
        Method method = getMethod(joinPoint);
        log.info("--------------method name = {} --------------", method.getName());

        String logContents = "title: " + article.getTitle() + ", contents: " + article.getContent() + " 작성";
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logService.saveLog(logContents, username);
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}
