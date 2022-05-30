package health.hbp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component @Slf4j
public class StatsAspect {

    @Around("execution(public String health.hbp.controller.EdibleController.getAllEdibles(..))")
    public Object execTime(ProceedingJoinPoint joinPoint) {
        try {
            log.info("Listing Started!");
            long now = System.currentTimeMillis();
            String res = (String)joinPoint.proceed();
            long elapsed = (System.currentTimeMillis() - now);
            log.info("Listing Finished in {} ms", elapsed);
            return res;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}