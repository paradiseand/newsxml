package org.crazyit.news.io;

import java.io.*;
import java.text.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class Dom4jWrite
{
	private SAXReader reader;
	//��ȡϵͳ��ǰʱ��
	long date = System.currentTimeMillis();
	public Dom4jWrite()
	{
		//����SAXReader������
		reader = new SAXReader();
		//����Ԫ�صĿ�ʼ��ǩ��������ǩ֮��Ŀհ�
		reader.setStripWhitespaceText(true);
		//��Ԫ��֮�����ڵ��ı����ݺϲ�����
		reader.setMergeAdjacentText(true);
	}

	//���¹�����Ϊ�ڵ���ӵ�ԭ����list.xml�ļ���
	public void addElement(String path, String title, String user, 
		String content) throws Exception
	{
		Document doc = reader.read(new File(path));
		Element root = doc.getRootElement();
		//����µ�<����.../>�ڵ�
		Element note = root.addElement("����");
		Element noteTitle = note.addElement("����");
		noteTitle.setText(title);
		Element noteUser = note.addElement("����");
		noteUser.setText(user);
		Element noteDate = note.addElement("����ʱ��");
		String dateTime = date(date);
		noteDate.setText(dateTime + "");
		Element noteContent = note.addElement("����");
		//��content�ַ����Ի��з��ţ�\r\n���ָ�ɶ���ַ���
		String[] contents = content.split("\\r\\n");
		//�����������л����滻��HTML���з�<br />��
		for (int i = 0 ; i < contents.length ; i ++)
		{
			noteContent.addCDATA(contents[i]);
			//ÿ���һ���ַ���֮�������һ���յ�brԪ��
			if (i != contents.length - 1)
			{
				noteContent.addElement("br");
			}
		}
		Element noteFile = note.addElement("�ļ���");
		noteFile.setText(date + "");
		OutputFormat format = new OutputFormat("	", true, "GBK"); 
		FileWriter fw = new FileWriter(path);
		XMLWriter writer = new XMLWriter(fw , format); 
		//���޸Ĺ���XML�ĵ�����д������ļ���
		writer.write(doc);  
		fw.close();
	}
	//Ϊ�����¹�������۴���һ���µ�XML�ĵ����Ե�ǰϵͳʱ����Ϊ�ļ���
	public void addNews(String title, String user, 
		String content, String path) throws Exception
	{
		DocumentFactory factory = new DocumentFactory();
		Document doc = factory.createDocument();
		//��Ӵ���ָ��
		doc.addProcessingInstruction(
			"xml-stylesheet type=\"text/xsl\" href=\"replys.xslt\"", "");
		//ΪXML�ĵ�������Ԫ��
		Element root = doc.addElement("�����б�");
		root.add(new Namespace("xsi" , "http://www.w3.org/2001/XMLSchema-instance"));
		root.addAttribute("xsi:noNamespaceSchemaLocation" , "replys.xsd");
		//����һ��<����.../>�ӽڵ�
		Element note = root.addElement("����");
		//Ϊ<����.../>�ڵ��������4���ӽڵ�
		Element noteTitle = note.addElement("����");
		noteTitle.setText(title);
		Element noteUser = note.addElement("����");
		noteUser.setText(user);
		Element noteDate = note.addElement("����ʱ��");
		String dateTime = date(date);
		noteDate.setText(dateTime + "");
		Element noteContent = note.addElement("����");
		String[] contents = content.split("\\r\\n");
		//�����������л����滻��HTML���з�<br />��
		for (int i = 0 ; i < contents.length ; i ++)
		{
			noteContent.addCDATA(contents[i]);
			if (i != contents.length - 1)
			{
				noteContent.addElement("br");
			}
		}
		//���½���XML�ĵ�д�������ļ���
		OutputFormat format = new OutputFormat("	", true, "GBK"); 
		int index = path.lastIndexOf("\\");
		String filePath = path.substring(0, index);
		FileWriter fw = new FileWriter(filePath + "\\WEB-INF\\xml\\" 
			+ date + ".xml");
		XMLWriter writer = new XMLWriter(fw , format); 
		writer.write(doc);  
		fw.close();
	}
	//����������д����Ӧ��xml�ļ��У������������һ��<����.../>�ڵ�
	public void addReply(String filePath, String title, String user, 
		String content) throws Exception
	{
		//��ָ��XML�ĵ�����Document
		Document doc = reader.read(new File(filePath));
		//��ȡXML�ĵ��ĸ��ڵ�
		Element root = doc.getRootElement();
		//���<����.../>�ڵ�
		Element note = root.addElement("����");
		//Ϊ<����.../>�ڵ��������4���ӽڵ�
		Element noteTitle = note.addElement("����");
		noteTitle.setText(title);
		Element noteUser = note.addElement("����");
		noteUser.setText(user);
		Element noteDate = note.addElement("����ʱ��");
		String dateTime = date(date);
		noteDate.setText(dateTime + "");
		Element noteContent = note.addElement("����");
		String[] contents = content.split("\\r\\n");
		//�����������л����滻��HTML���з�<br />��
		for (int i = 0 ; i < contents.length ; i ++)
		{
			noteContent.addCDATA(contents[i]);
			if (i != contents.length - 1)
			{
				noteContent.addElement("br");
			}
		}
		OutputFormat format = new OutputFormat("	", true, "GBK"); 
		FileWriter fw = new FileWriter(filePath);
		XMLWriter writer = new XMLWriter(fw , format); 
		//���޸Ĺ���XML�ĵ�����д������ļ��С�
		writer.write(doc);  
		fw.close();
	}
	
	
	//�û���¼
	 public String login(String id,String password,String path) throws Exception {
		
		 Document document = reader.read(new File(path));
			Element root = document.getRootElement();
			boolean ok = false;
			List list = root.elements();
			String name = "";
			for(Object object :list){
				
				Element element = (Element) object;
				List list2 = element.elements();
				Element element2 = (Element) list2.get(0);
				//System.out.println("�˺�:"+element2.getData());
				Element element3 = (Element) list2.get(2);
				if(id.equals(element2.getData()) && password.equals(element3.getData())){
					 ok = true;
					 Element element4 = (Element) list2.get(1);
					 name = (String) element4.getData();
			         break;
				}else{
					
					
				}
			}
			
			if(ok){
				return name;
			}else{
				return "";
			}
			
	}
	
	//ע�����û�
	public int addUser(String id,String name,String password,String path) throws Exception{
		
		Document document = reader.read(new File(path));
		Element root = document.getRootElement();
		boolean ok = true;
		List list = root.elements();
		for(Object object :list){
			
			Element element = (Element) object;
			List list2 = element.elements();
			Element element2 = (Element) list2.get(0);
			System.out.println("�˺�:"+element2.getData());
			if(id.equals(element2.getData())){
				ok = false;
				break;
			}
		}
		
		if(ok){
			
			Element user = root.addElement("user");
			Element userid = user.addElement("id");
			Element username = user.addElement("name");
			Element userpassword = user.addElement("password");
			userid.setText(id);
			username.setText(name);
			userpassword.setText(password);
			
			OutputFormat format = new OutputFormat("	", true, "GBK"); 
			FileWriter fw = new FileWriter(path);
			XMLWriter writer = new XMLWriter(fw , format); 
			//���޸Ĺ���XML�ĵ�����д������ļ���
			writer.write(document);  
			fw.close();
			return 1;
		}else{
			
			return 0;
		}
		
		

		
	}
	
	//����long�͵����ݸ�ʽ��������/ʱ��
	private String date(long date)
	{
		Date dateTime = new Date(date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		return df.format(dateTime);
	}
}