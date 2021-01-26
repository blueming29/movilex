package movie.rec.movilex.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailSendAction {

	public void emailSendAction(String id, String pw) {
		String from="blueming29@gmail.com";
		String to=id;
		String subject="MOVILEX에서 임시 비밀번호가 전송되었습니다.";
		String content="<img style=\"width: 250px; height: auto;\" src=\"cid:movilexlogoimg\"><br/><br/><br/>"
				+ "회원님의 임시 비밀번호는 <strong style=\"color: rgb(255, 47, 110);\">"+pw+"</strong> 입니다.<br/>"
				+ "위의 임시 비밀번호로 로그인 하신 후 정보수정에서 비밀번호를 재설정 해 주세요.<br/><br/>"
				+ "<a href=\"http://localhost:9005/movilex/main.do\">MOVILEX 바로가기</a>";
		
		
		Properties p=new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", 465);
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		try{
			Authenticator auth=new Gmail();
			Session ses=Session.getInstance(p,auth);
//			ses.setDebug(true);
			MimeMessage msg=new MimeMessage(ses);
			
		    MimeMultipart multipart = new MimeMultipart("related");

	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(content, "text/html; charset=UTF-8");
	   
	        multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			
			String filePath = "C:\\JEONGIN\\spring_setting\\spring_work\\movilex\\src\\main\\webapp\\resources\\image\\movie_logo.png";
			
			DataSource fds = new FileDataSource(filePath);
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-ID","<movilexlogoimg>");
			
			// add it
			multipart.addBodyPart(messageBodyPart);
			   
			msg.setSubject(subject);
			Address fromAddr=new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr=new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			
			msg.setContent(multipart); // HTML 형식
			Transport.send(msg);
			

		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
