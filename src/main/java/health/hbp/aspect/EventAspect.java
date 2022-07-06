package health.hbp.aspect;

import health.hbp.model.Event;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@RequiredArgsConstructor
public class EventAspect {

    final KafkaTemplate<String, Event> kafkaTemplate;

    @Around("execution(* health.hbp.service.UserServiceImpl.register(..))")
    public Object execTime(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            kafkaTemplate.send("eventos", new Event(new Date(), "Register", (String)joinPoint.getArgs()[0]));
        }
    }
}
