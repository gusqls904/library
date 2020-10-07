package com.library.wol.service;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.library.wol.model.EmailVO;


@Service("emailService")
public class EmailService {

	@Autowired

	protected JavaMailSender mailSender;

	public boolean sendMail(EmailVO email) throws Exception {

		try {

			MimeMessage msg = mailSender.createMimeMessage();

			msg.setSubject(email.getSubject());

			// �씪諛� �뀓�뒪�듃留� �쟾�넚�븯�젮�뒗 寃쎌슦
			msg.setText(email.getContent());

			// HTML 而⑦뀗痢좊�� �쟾�넚�븯�젮硫�.
			//msg.setContent("<a href='https://www.naver.com/'>�겢由�</a>", "text/html;charset=utf-8");
			msg.setRecipient(RecipientType.TO, new InternetAddress(email.getReceiver()));//�닔�떊�옄 setting

			mailSender.send(msg);

			return true;

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return false;

	}
	
	  public String numberGen(int len, int dupCd ) {
	        
	        Random rand = new Random();
	        String numStr = ""; //난수가 저장될 변수
	        
	        for(int i=0;i<len;i++) {
	            
	            //0~9 까지 난수 생성
	            String ran = Integer.toString(rand.nextInt(10));
	            
	            if(dupCd==1) {
	                //중복 허용시 numStr에 append
	                numStr += ran;
	            }else if(dupCd==2) {
	                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
	                if(!numStr.contains(ran)) {
	                    //중복된 값이 없으면 numStr에 append
	                    numStr += ran;
	                }else {
	                    //생성된 난수가 중복되면 루틴을 다시 실행한다
	                    i-=1;
	                }
	            }
	        }
	        return numStr;
	    }
	

}