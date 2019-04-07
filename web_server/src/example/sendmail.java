package example;


import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class sendmail {
    @WebMethod
    public String sendEmail(String m_url,String m_payload) {
      sendMail_aliyunn test=new sendMail_aliyunn();
      test.sendEmail(m_url,m_payload);
      return "单邮件发送";
    }
    public String sendEmailBatch(String[] url,String payload){
        sendMail_aliyunn test=new sendMail_aliyunn();
        test.sendEmailBatch(url,payload);
        return "多邮件发送";
    }
}
