package com.test.util;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.testng.annotations.Test;

public class TestSendEmail {
  @Test
  public void f() throws Exception {
	  String to = "shaojie.zhang@100credit.com";
	    String from = "shaojie.zhang@100credit.com";
	    final String username = "shaojie.zhang@100credit.com";//change accordingly
	    final String password = "lhr1881131";//change accordingly
      String host = "imap.100credit.com";

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", "25");

	    Session session = Session.getInstance(props,
	       new javax.mail.Authenticator() {
	          protected PasswordAuthentication getPasswordAuthentication() {
	             return new PasswordAuthentication(username, password);
	          }
		});
	    String str=null;
	    StringBuffer failedCases=new StringBuffer();
	    Map<String,Object> map=ReadXML.readx();
	    List<String> l1=(List<String>)map.get("failedCases");
	    if(l1!=null) {
	    	for(int i=0;i<l1.size();i++) {
	    		str="<tr>\n" + 
	    		"     <td width=\"8%\" class=\"btbg font-center\">"+l1.get(i).split("at")[1]+"</td>\n" + 
	    		"     <td width=\"8%\" class=\"btbg font-center\">"+l1.get(i).split("at")[0]+"</td>\n" + 
	    		"    </tr>";
	    		failedCases.append(str);
	    	}
	    }else {
	    	failedCases.append("<tr>\n" + 
	    		"     <td width=\"8%\" class=\"btbg font-center\">无</td>\n" + 
	    		"     <td width=\"8%\" class=\"btbg font-center\">无</td>\n" + 
	    		"    </tr>");
	    }
	    
	    String color=null;
	    if("成功".equals(map.get("state"))) {
	    	color="color:blue";
	    }else {
	    	color="color:#F00";
	    }
	    try {
	       Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   message.setRecipients(Message.RecipientType.TO,
	              InternetAddress.parse(to));
		   message.setSubject("AutoTesting Result");
//		   String skipped="0";
//		   if(ReadXML.readx().get("skipped")!=null) {
//			   skipped=ReadXML.readx().get("skipped").toString();
//		   }
		   message.setContent(
				   
		"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n" + 
		"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
		"<head>\n" + 
		"<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n" + 
		"<title>TestNG Report</title>\n" + 
		"<style>\n" + 
		"    @charset \"utf-8\";\n" + 
		"    /* CSS Document */\n" + 
		"    \n" + 
		"\n" + 
		".font-center{ text-align:center}\n" + 
		".btbg{background:#e9faff !important;}\n" + 
		".biaoti{\n" + 
		"    font-family: 微软雅黑;\n" + 
		"    font-size: 26px;\n" + 
		"    font-weight: bold;\n" + 
		"    border-bottom:1px dashed #CCCCCC;\n" + 
		"    color: #255e95;\n" + 
		"}\n" + 
		".titfont {\n" + 
		"    \n" + 
		"    font-family: 微软雅黑;\n" + 
		"    font-size: 16px;\n" + 
		"    font-weight: bold;\n" + 
		"    color: #255e95;\n" + 
		"    background: url(../images/ico3.gif) no-repeat 15px center;\n" + 
		"    background-color:#e9faff;\n" + 
		"}\n" + 
		"</style>\n" + 
		"\n" + 
		"\n" + 
		"</head>\n" + 
		"<body>\n" + 
		"    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\n" + 
		"        <tr>\n" + 
		"            <td align=\"center\" class=\"biaoti\" height=\"60\">自动化测试结果报告</td>\n" + 
		"        </tr>\n" + 
		"    </table>\n" + 
		"    <h1 padding: 10px 0;><span style="+color+">测试结果 -"+ map.get("state")+"&nbsp;&nbsp;</span></h1>\n" + 
		"       <span style=\"color:gray;font-size:10px\";>(本邮件是程序自动下发的，请勿回复，谢谢！！)</span>\n" + 
		"       <h2>Test Summary:<span style=\"float:right; font-size:17px; padding-left:15px\";>"+map.get("startTime")  +"</span></h2>\n" + 
		"    \n" + 
		"    <table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"4\" bgcolor=\"#cccccc\" margin-top=\"13px\" align=\"center\">\n" + 
		"        <thread>\n" + 
		"        <tr>\n" + 
		"            <th width=\"10%\" class=\"btbg font-center titfont\" rowspan=\"2\">Tests</th>\n" + 
		"            <th width=\"10%\" class=\"btbg font-center titfont\" rowspan=\"2\">Passed</th>\n" + 
		"            <th width=\"10%\" class=\"btbg font-center titfont\" rowspan=\"2\">Failed</th>\n" + 
		"            <th width=\"10%\" class=\"btbg font-center titfont\" rowspan=\"2\">Skipped</th>\n" + 
		"            <th width=\"10%\" class=\"btbg font-center titfont\" rowspan=\"2\">Duration(s)</th>\n" + 
		"        </tr>\n" + 
		"        </thread>\n" + 
		"        <tbody>\n" + 
		"        <tr>\n" + 
		"            <td width=\"8%\" class=\"btbg font-center\">"+map.get("tests")+"</td>\n" + 
		"            <td width=\"8%\" class=\"btbg font-center\">"+map.get("passed")+"</td>\n" + 
		"            <td width=\"8%\" class=\"btbg font-center\">"+map.get("failed")+"</td>\n" + 
		"            <td width=\"8%\" class=\"btbg font-center\">"+map.get("skipped")+"</td>\n" + 
		"            <td width=\"8%\" class=\"btbg font-center\">"+map.get("duration")+"</td>\n" + 
		"        </tr>\n" + 
		"        </tbody>\n" + 
		"    </table>\n" + 
		"    <h2>Failed Details:</h2>\n" + 
		"    \n" + 
		"    <table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"4\" bgcolor=\"#cccccc\" margin-top=\"13px\" align=\"center\">\n" + 
		"        <thread>\n" + 
		"            <tr>\n" + 
		"                <th width=\"10%\" class=\"btbg font-center titfont\" >Failed_Methods</th>\n" + 
		"                <th width=\"10%\" class=\"btbg font-center titfont\" >Failed_Information</th>\n" + 
		"            </tr>\n" + 
		"        </thread>\n" + 
		"        <tbody>\n" + 
		"            \n" + 
		        failedCases+
		"        </tbody>\n" + 
		"    </table>\n" + 
		"\n" + 
		"</body>\n" + 
		"</html>\n" + 
		"" 
				   
				   ,
	             "text/html;charset=UTF-8");

		   // Send message
		   Transport.send(message);

		   System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
		   e.printStackTrace();
		   throw new RuntimeException(e);
	      }
	   }
  }
