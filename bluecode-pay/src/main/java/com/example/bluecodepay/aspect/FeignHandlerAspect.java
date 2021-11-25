package com.example.bluecodepay.aspect;

import com.example.bluecodepay.exception.custom.*;
import feign.FeignException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeignHandlerAspect {

    @Around("@annotation(com.example.bluecodepay.annotation.FeignHandler)")
    public Object handler(ProceedingJoinPoint javaPoint) throws Throwable {
        try {
            javaPoint.proceed();
            return null;
        } catch (FeignException.InternalServerError InternalServerError) {
            throw new BlucodeFeignInternalException(InternalServerError.contentUTF8());
        } catch (FeignException.GatewayTimeout gatewayTimeout) {
            throw new BluecodeFeignTimeoutException(gatewayTimeout.contentUTF8());
        } catch (FeignException.Unauthorized unauthorized) {
            throw new BluecodeFeignUnauthorizedException(unauthorized.contentUTF8());
        } catch (FeignException.BadRequest badRequest) {
            throw new BluecodeFeignBadRequestException(badRequest.contentUTF8());
        } catch (FeignException.UnsupportedMediaType unsupportedMediaType) {
            throw new BluecodeFeignUnsupportedMediaTypeException(unsupportedMediaType.contentUTF8());
        } catch (FeignException feignException) {
            throw new BlucodeFeignInternalException(feignException.contentUTF8());
        }
    }
}
