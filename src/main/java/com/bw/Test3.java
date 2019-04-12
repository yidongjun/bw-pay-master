package com.bw;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.bw.controller.AlipayController;
import com.bw.utils.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
@Configurable
@EnableScheduling
    public class Test3 {
    final static Logger log = LoggerFactory.getLogger(AlipayController.class);

    @Scheduled(fixedDelay = 1000)
        public void test(){
            System.out.println("test..");
        log.info("test");
        }
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors() );
    }

}
