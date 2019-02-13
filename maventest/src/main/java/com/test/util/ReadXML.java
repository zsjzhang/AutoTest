package com.test.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {
	public static Map<String,Object> readx() throws Exception {
//		 time:5 , tests:4 , errors:0 , skiped:3 , failures:1
		try {   
			File file= new File("surefire-reports/TEST-TestSuite.xml"); 
			if(!file.exists()) {
				file= new File("target/surefire-reports/TEST-TestSuite.xml");
			}
			long buildTime=file.lastModified();
			SimpleDateFormat da=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(file);  
			NodeList booklist = doc.getElementsByTagName("testsuite");
			Node book = booklist.item(0);
			NamedNodeMap bookmap = book.getAttributes();
			
			String duration=bookmap.getNamedItem("time").getNodeValue();
            String startTime=da.format(new Date(buildTime -
            		(int)(Double.parseDouble(duration)*1000)));
            String endTime=da.format(new Date(buildTime));
            String tests=bookmap.getNamedItem("tests").getNodeValue()!=null?
            		bookmap.getNamedItem("tests").getNodeValue():"0";
            String failures=doc.getElementsByTagName("failure").getLength()+"";
            String skipped=doc.getElementsByTagName("skipped").getLength()+"";
            		
            
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("state", "成功");
            List<String> l1=new ArrayList<String>();
            List<String> l2=new ArrayList<String>();
            if(!"0".equals(skipped) || !"0".equals(failures)) {
            	map.put("state", "失败");
            	if(!"0".equals(failures) ) {
	            	NodeList ll = doc.getElementsByTagName("failure");
	            	for(int i=0;i<ll.getLength();i++) {
	            		Node book1 = ll.item(i);
	            		System.out.println(book1.getTextContent().trim());
	            		l1.add(book1.getTextContent().trim());
	            	}
	            	map.put("failedCases",l1);
            	}
            	if(!"0".equals(skipped) ) {
	            	NodeList lll = doc.getElementsByTagName("skipped");
	            	for(int i=0;i<lll.getLength();i++) {
	            		Node book3 = lll.item(i);
	            		l2.add(book3.getTextContent().trim());
	            	}
	            	map.put("skippedCases",l2);
            	}
            }
            
            map.put("startTime",startTime);
            map.put("endTime",endTime);
            map.put("duration", duration);
            map.put("tests",tests);
            map.put("passed",(Integer.parseInt(tests)-Integer.parseInt(failures)-
            		Integer.parseInt(skipped)));
            map.put("skipped",skipped);
            map.put("failed",failures);
            
            return map;
		} catch (Exception e) {   
			e.printStackTrace();
		    throw new Exception("读取文件出错");
		}   
	}
	public static void main(String[] args) throws Exception {
	    Map<String,Object> map=readx();
	    System.out.println(map.get("startTime"));
	    System.out.println(map.get("endTime"));
	    System.out.println(map.get("tests"));
	    System.out.println(map.get("success"));
	    System.out.println(map.get("skiped"));
	    System.out.println(map.get("failed"));
	    System.out.println(map.get("time"));
	    System.out.println(map.get("state"));
	    List<String> l1=(List<String>)map.get("failedCases");
	    List<String> l2=(List)map.get("skippedCases");
	    System.out.println(l1.get(0));
	    System.out.println(l2);
	}
}
