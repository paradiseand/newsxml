package org.crazyit.news.io;

import java.io.*;
import java.text.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
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
	//获取系统当前时间
	long date = System.currentTimeMillis();
	public Dom4jWrite()
	{
		//创建SAXReader解析器
		reader = new SAXReader();
		//忽略元素的开始标签、结束标签之间的空白
		reader.setStripWhitespaceText(true);
		//将元素之间相邻的文本内容合并处理
		reader.setMergeAdjacentText(true);
	}

	//把新公告作为节点添加到原来的list.xml文件中
	public void addElement(String path, String title, String user, 
		String content) throws Exception
	{
		Document doc = reader.read(new File(path));
		Element root = doc.getRootElement();
		//添加新的<公告.../>节点
		Element note = root.addElement("公告");
		Element noteTitle = note.addElement("标题");
		noteTitle.setText(title);
		Element noteUser = note.addElement("作者");
		noteUser.setText(user);
		Element noteDate = note.addElement("发布时间");
		String dateTime = date(date);
		noteDate.setText(dateTime + "");
		Element noteContent = note.addElement("内容");
		//将content字符串以换行符号（\r\n）分割成多个字符串
		String[] contents = content.split("\\r\\n");
		//将新闻内容中换行替换成HTML换行符<br />。
		for (int i = 0 ; i < contents.length ; i ++)
		{
			noteContent.addCDATA(contents[i]);
			//每添加一个字符串之后，再添加一个空的br元素
			if (i != contents.length - 1)
			{
				noteContent.addElement("br");
			}
		}
		Element noteFile = note.addElement("文件名");
		noteFile.setText(date + "");
		OutputFormat format = new OutputFormat("	", true, "GBK"); 
		FileWriter fw = new FileWriter(path);
		XMLWriter writer = new XMLWriter(fw , format); 
		//将修改过的XML文档重新写入磁盘文件中
		writer.write(doc);  
		fw.close();
	}
	//为保存新公告的评论创建一个新的XML文档，以当前系统时间作为文件名
	public void addNews(String title, String user, 
		String content, String path) throws Exception
	{
		DocumentFactory factory = new DocumentFactory();
		Document doc = factory.createDocument();
		//添加处理指令
		doc.addProcessingInstruction(
			"xml-stylesheet type=\"text/xsl\" href=\"replys.xslt\"", "");
		//为XML文档创建根元素
		Element root = doc.addElement("评论列表");
		root.add(new Namespace("xsi" , "http://www.w3.org/2001/XMLSchema-instance"));
		root.addAttribute("xsi:noNamespaceSchemaLocation" , "replys.xsd");
		//新增一个<公告.../>子节点
		Element note = root.addElement("公告");
		//为<公告.../>节点依次添加4个子节点
		Element noteTitle = note.addElement("标题");
		noteTitle.setText(title);
		Element noteUser = note.addElement("作者");
		noteUser.setText(user);
		Element noteDate = note.addElement("发布时间");
		String dateTime = date(date);
		noteDate.setText(dateTime + "");
		Element noteContent = note.addElement("内容");
		String[] contents = content.split("\\r\\n");
		//将公告内容中换行替换成HTML换行符<br />。
		for (int i = 0 ; i < contents.length ; i ++)
		{
			noteContent.addCDATA(contents[i]);
			if (i != contents.length - 1)
			{
				noteContent.addElement("br");
			}
		}
		//将新建的XML文档写到磁盘文件中
		OutputFormat format = new OutputFormat("	", true, "GBK"); 
		int index = path.lastIndexOf("\\");
		String filePath = path.substring(0, index);
		FileWriter fw = new FileWriter(filePath + "\\WEB-INF\\xml\\" 
			+ date + ".xml");
		XMLWriter writer = new XMLWriter(fw , format); 
		writer.write(doc);  
		fw.close();
	}
	//把评论内容写入相应的xml文件中，即向其中添加一个<评论.../>节点
	public void addReply(String filePath, String title, String user, 
		String content) throws Exception
	{
		//以指定XML文档创建Document
		Document doc = reader.read(new File(filePath));
		//获取XML文档的根节点
		Element root = doc.getRootElement();
		//添加<评论.../>节点
		Element note = root.addElement("评论");
		//为<评论.../>节点依次添加4个子节点
		Element noteTitle = note.addElement("标题");
		noteTitle.setText(title);
		Element noteUser = note.addElement("作者");
		noteUser.setText(user);
		Element noteDate = note.addElement("评论时间");
		String dateTime = date(date);
		noteDate.setText(dateTime + "");
		Element noteContent = note.addElement("内容");
		String[] contents = content.split("\\r\\n");
		//将新闻内容中换行替换成HTML换行符<br />。
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
		//将修改过的XML文档重新写入磁盘文件中。
		writer.write(doc);  
		fw.close();
	}
	
	
	//用户登录
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
				//System.out.println("账号:"+element2.getData());
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
	
	//注册新用户
	public int addUser(String id,String name,String password,String path) throws Exception{
		
		Document document = reader.read(new File(path));
		Element root = document.getRootElement();
		boolean ok = true;
		List list = root.elements();
		for(Object object :list){
			
			Element element = (Element) object;
			List list2 = element.elements();
			Element element2 = (Element) list2.get(0);
			System.out.println("账号:"+element2.getData());
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
			//将修改过的XML文档重新写入磁盘文件中
			writer.write(document);  
			fw.close();
			return 1;
		}else{
			
			return 0;
		}
		
		

		
	}
	
	//根据long型的数据格式化成日期/时间
	private String date(long date)
	{
		Date dateTime = new Date(date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		return df.format(dateTime);
	}
}