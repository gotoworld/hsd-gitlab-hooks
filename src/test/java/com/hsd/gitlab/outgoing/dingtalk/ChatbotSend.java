package com.hsd.gitlab.outgoing.dingtalk;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.hsd.gitlab.utils.HttpClientUtils;
 
 
public class ChatbotSend {
 
    public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=dd3cfd11d2169fea3a897fe7fb8a59a806e8dc42239a917269a1d3cdc0d94ca4";
 
    /**
     * 
     *{
     *    "msgtype": "text", 
     *    "text": {
     *        "content": "我就是我, 是不一样的烟火"
     *    }, 
     *    "at": {
     *        "atMobiles": [
     *            "156xxxx8827", 
     *            "189xxxx8325"
     *        ], 
     *        "isAtAll": false
     *    }
     *}
     *
     * @version Oct 9, 201710:09:13 AM
     * @author Ford.CHEN
     * @throws Exception
     */
    @Test
    public void textMessage() throws Exception{
 
        HttpClient httpclient = HttpClients.createDefault();
 
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
 
        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"我就是我, 是不一样的烟火\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
 
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
    
    /**
     * 
     *{
     *     "msgtype": "markdown",
     *     "markdown": {
     *         "title":"杭州天气",
     *         "text": "#### 杭州天气 @156xxxx8827\n" +
     *                 "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
     *                 "> ![screenshot](http://image.jpg)\n"  +
     *                 "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n"
     *     },
     *    "at": {
     *        "atMobiles": [
     *            "156xxxx8827", 
     *            "189xxxx8325"
     *        ], 
     *        "isAtAll": false
     *    }
     *}
     *
     * @version Oct 9, 201710:09:48 AM
     * @author Ford.CHEN
     * @throws Exception
     */
    @Test
    public void markdownMessage() throws Exception{
        HttpClient httpclient = HttpClients.createDefault();
 
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
 
        String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"杭州天气\", \"text\":\"#### 杭州天气 @156xxxx8827\\n   > 9度，西北风1级，\\n\\n   > 空气良89，相对温度73%\\n\\n   > ![screenshot](http://www.baidu.com/img/bd_logo1.png)\\n  > ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \\n\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        httppost.setEntity(se);
 
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        } 
    }
    
    /**
     * 
     * Method Description
     * @version Oct 10, 20178:44:12 AM
     * @author Ford.CHEN
     * @throws Exception
     */
    @Test
    public void markdownMessageByUtils() throws Exception{
        String textMsg = "{ \"msgtype\": \"markdown\", \"markdown\": {\"title\": \"杭州天气\", \"text\":\"#### 杭州天气 @156xxxx8827\\n   > 9度，西北风1级，\\n\\n   > 空气良89，相对温度73%\\n\\n   > ![screenshot](http://www.baidu.com/img/bd_logo1.png)\\n  > ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \\n\"}}";
        
        HttpClientUtils.post(textMsg, WEBHOOK_TOKEN);
    }
}