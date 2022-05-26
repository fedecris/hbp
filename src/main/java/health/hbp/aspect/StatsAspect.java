package health.hbp.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatsAspect {

    private static final Logger Log = LoggerFactory.getLogger(StatsAspect.class);

    @Around("execution(public String health.hbp.controller.EdibleController.getAllEdibles(..))")
    public Object execTime(ProceedingJoinPoint joinPoint) {
        try {
            Log.info("Listing Started!");
            long now = System.currentTimeMillis();
            String res = (String)joinPoint.proceed();
            long elapsed = (System.currentTimeMillis() - now);
            Log.info("Listing Finished in " + elapsed + " ms");
            return res;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
