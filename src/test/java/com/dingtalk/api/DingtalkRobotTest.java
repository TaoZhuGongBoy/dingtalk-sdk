package com.dingtalk.api;

import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


/**
 * 钉钉机器自定义机器人测试
 */
public class DingtalkRobotTest {
    private static final String secret = "你的secret";
    private static final String webhook = "你的webhook";


    //发送普通文本消息
    @Test
    public void textMessage() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.getEncoder().encode((signData)), "UTF-8"));
        String url = webhook + "&sign=" + sign + "&timestamp=" + timestamp;
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("text");
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent("Hello,wolrd!");
        //  text.setContent("Hello,wolrd!");

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
    public void linkMessage() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.getEncoder().encode((signData)), "UTF-8"));
        String url = webhook + "&sign=" + sign + "&timestamp=" + timestamp;
        DingTalkClient client = new DefaultDingTalkClient(url);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("link");
        OapiRobotSendRequest.Link link = new OapiRobotSendRequest.Link();
        link.setMessageUrl("https://www.dingtalk.com/");
        link.setPicUrl("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2F0d732104-eb9a-4bb7-83b4-cb75d731e7e1%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1681783225&t=58f677fcfd330c2ad8fd68160aa2c079");
        link.setTitle("时代的火车向前开");
        link.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林");
        request.setLink(link);

        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        //at.setAtMobiles(Arrays.asList("13672384534"));//设置@那些人，使用的是手机号
        at.setIsAtAll("false");
        request.setAt(at);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
        System.out.println(response.getErrmsg());
    }

    //发送markdown消息
    @Test
    public void markdownMessage() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.getEncoder().encode((signData)), "UTF-8"));
        String url = webhook + "&sign=" + sign + "&timestamp=" + timestamp;
        DingTalkClient client = new DefaultDingTalkClient(url);        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("markdown");
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle("杭州天气");
        markdown.setText("#### 杭州天气 @156xxxx8827\n" +
                "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                "> ![screenshot](https://gw.alipayobjects.com/zos/skylark-tools/public/files/84111bbeba74743d2771ed4f062d1f25.png)\n" +
                "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n");
        request.setMarkdown(markdown);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
        System.out.println(response.getErrmsg());
    }

    //ActionCard
    @Test
    public void actionCard() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.getEncoder().encode((signData)), "UTF-8"));
        String url = webhook + "&sign=" + sign + "&timestamp=" + timestamp;
        DingTalkClient client = new DefaultDingTalkClient(url);        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("actionCard");
        OapiRobotSendRequest.Actioncard actioncard = new OapiRobotSendRequest.Actioncard();
        actioncard.setTitle("乔布斯 20 年前想打造一间苹果咖啡厅，而它正是 Apple Store 的前身");
        actioncard.setText("![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png) \n" +
                " ### 乔布斯 20 年前想打造的苹果咖啡厅 \n" +
                " Apple Store 的设计正从原来满满的科技感走向生活化，而其生活化的走向其实可以追溯到 20 年前苹果一个建立咖啡馆的计划");
        actioncard.setBtnOrientation("0");
        actioncard.setSingleTitle("阅读全文");
        actioncard.setSingleURL("https://www.dingtalk.com/");
        request.setActionCard(actioncard);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
        System.out.println(response.getErrmsg());
    }

    //feedcard
    @Test
    public void feedCard() throws Exception {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.getEncoder().encode((signData)), "UTF-8"));
        String url = webhook + "&sign=" + sign + "&timestamp=" + timestamp;
        DingTalkClient client = new DefaultDingTalkClient(url);        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype("feedCard");
        OapiRobotSendRequest.Feedcard feedcard = new OapiRobotSendRequest.Feedcard();
        List<OapiRobotSendRequest.Links> links =new ArrayList<>();
        OapiRobotSendRequest.Links link1=new OapiRobotSendRequest.Links();
        link1.setTitle("时代的火车向前开1");
        link1.setMessageURL("https://www.dingtalk.com/");
        link1.setPicURL("https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png");
        links.add(link1);
        feedcard.setLinks(links);
        request.setFeedCard(feedcard);
        OapiRobotSendResponse response = client.execute(request);
        System.out.println(response.getErrcode());
        System.out.println(response.getErrmsg());
    }


}
