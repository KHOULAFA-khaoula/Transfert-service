package com.ensa.transfertservice.proxies;

import com.ensa.transfertservice.bean.SmsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name="SMS-SERVICE", url="localhost:9050/api/v1/sms" )
public interface MicroServiceNotificationProxy {
    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest);
}
