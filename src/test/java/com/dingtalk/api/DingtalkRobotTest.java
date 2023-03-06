package com.dingtalk.api;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Base64;


/**
 * <p> 钉钉机器自定义机器人测试 </p>
 *
 * @author vchar fred
 * @version 1.0
 * @create_date 2019/7/24 21:44
 */
public class DingtalkRobotTest {
    private static final String secret = "";

    private static final String webhook = "";



    //发送普通文本消息
    @Test
    public void textMessage() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.getEncoder().encode((signData)), "UTF-8"));
        String url=webhook+"&sign=" + sign + "&timestamp=" + timestamp;
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("测试文本消息");
        request.setText(text);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll("false");//设置@所有的人
        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
        System.out.println(response.getErrmsg());
    }

    //发送link消息
    @Test
    public void linkMessage() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=2324980345ufnklasjnfaiopuw3rnq9o32874yq29035ryhvq2");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("https://www.dingtalk.com/");
        link.setPicUrl("");
        link.setTitle("时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
        request.setLink(link);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setAtMobiles(Arrays.asList("13672384534"));//设置@那些人，使用的是手机号
        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
        System.out.println(response.getErrmsg());
    }

    //发送markdown消息
    @Test
    public void markdownMessage() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=2324980345ufnklasjnfaiopuw3rnq9o32874yq29035ryhvq2");
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("杭州天气");
        markdown.setText("#### 杭州天气 @156xxxx8827\n" +
                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                "> ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n"  +
                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
        System.out.println(response.getErrmsg());
    }





}
