package pl.lodz.ssbd.interceptors;

import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Jakub Kępa 180582
 */
public class DziennikZdarzenInterceptor {

    @Resource
    private SessionContext sessionContext;
    private static final Logger logger = Logger.getLogger(DziennikZdarzenInterceptor.class.getName());

    @AroundInvoke
    public Object logujWywolanie(InvocationContext invocationContext) throws Exception {
        StringBuilder log = new StringBuilder();
        log.append("Metoda: ");
        log.append(invocationContext.getMethod().getName());
        log.append(" | Użytkownik: ");
        log.append(sessionContext.getCallerPrincipal().getName());
        log.append(" | Parametry: ");
        if (invocationContext.getParameters() != null) {
            for (Object param : invocationContext.getParameters()) {
                if (param == null) {
                    log.append("null ");
                } else {
                    log.append(param.toString());
                    log.append(" ");
                }
            }
        } else {
            log.append("bez parametrów");
        }

        Object result;
        try {
            result = invocationContext.proceed();
        } catch (Exception e) {
            log.append(" | Wystąpił wyjątek: ");
            log.append(e.toString());
            logger.info(log.toString());
            throw e;
        }

        log.append(" | Wartość zwrócona: ");
        if (result == null) {
            log.append("null");
        } else {
            log.append(result.toString());
        }

        logger.info(log.toString());
        return result;
    }
}
