package example;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import java.util.regex.*;

class sendMail_aliyunn {
    //sendEmail(String_url,String_payload)
    public void sendEmail(String url,String payload)  {//邮件地址为_url，内容为_payload
        if (!validateEmailAddress(url))
            return;
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIF7cRoJ1wRbCm", "1P2LUALEoUqAQxfwkVA12NfKpcdzYW");
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
        //try {
        //DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
        //} catch (ClientException e) {
        //e.printStackTrace();
        //}
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            // request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("752256693@mail.xialiangwei.cn");
            request.setFromAlias("xlw");
            request.setAddressType(1);
            request.setTagName("test");
            request.setReplyToAddress(true);
            request.setToAddress(url);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("邮件主题");
            request.setHtmlBody(payload);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public boolean validateEmailAddress(String url){
        String pattern="^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        return Pattern.matches(pattern, url);
    }

    public void sendEmailBatch(String[] url,String payload) //批量发送邮件
    {
        String urls=new String();
        for (int i=0;i< url.length;i++) {
            if (!validateEmailAddress(url[i]))
                return;
            else
            {
                if (i==url.length-1)
                    urls+=url[i];
                else
                    urls+=url[i]+",";
            }
        }
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的"cn-hangzhou"替换为"ap-southeast-1"、或"ap-southeast-2"。
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIF7cRoJ1wRbCm", "1P2LUALEoUqAQxfwkVA12NfKpcdzYW");
        IAcsClient client = new DefaultAcsClient(profile);
        SingleSendMailRequest request = new SingleSendMailRequest();
        try {
            //request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
            request.setAccountName("752256693@mail.xialiangwei.cn");
            request.setFromAlias("xlw");
            request.setAddressType(1);
            request.setTagName("test");
            request.setReplyToAddress(true);
            request.setToAddress(urls);
            //可以给多个收件人发送邮件，收件人之间用逗号分开，批量发信建议使用BatchSendMailRequest方式
            //request.setToAddress("邮箱1,邮箱2");
            request.setSubject("邮件主题 ");
            request.setHtmlBody(payload);
            SingleSendMailResponse httpResponse = client.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args) {
        sendMail test=new sendMail();
        test.sendEmailBatch(new String[]{"752256693@qq.com","2249992633@qq.com"},"123456879");
    }*/
}