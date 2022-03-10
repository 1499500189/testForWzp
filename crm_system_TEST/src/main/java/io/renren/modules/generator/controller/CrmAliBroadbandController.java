package io.renren.modules.generator.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import io.renren.common.utils.R;
import io.renren.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2022 年 03 月 07 日
 */
@RestController
@RequestMapping("/generator/alipaybroadband")
@Api(tags = "支付宝小程序的宽带使用")
public class CrmAliBroadbandController extends AbstractController {


    @GetMapping("/list")
    public R   alitest(){

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode("4b203fe6c11548bcabd8da5bb087a83b");
        request.setRefreshToken("201208134b203fe6c11548bcabd8da5bb087a83b");
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return  R.ok();
    }

    @GetMapping("/save")
    public R   NetworkAccessSave(){
        //调用service  之后直接进行保存
        return  R.ok();
    }
}
