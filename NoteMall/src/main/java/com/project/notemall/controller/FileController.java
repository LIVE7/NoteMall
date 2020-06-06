package com.project.notemall.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.log4j.Log4j;

/*	pom.xml�� ���� ���ε� ���̺귯�� ���---------
 * 
 * <!-- File Upload -->
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.2.2</version>
		</dependency>
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.4</version>
		</dependency>
 * 
 * -----------------------------------------------
 * WEB-INF/spring/appServlet/servlet-context.xml�� �Ʒ� ���� ����Ѵ�.
 * 
 * <!-- MultipartResolver���� ====================================================
		����: id�� �ݵ�� multipartResolver�� ����ؾ� �Ѵ�.
		�ٸ� ���̵� �ָ� DispatcherServlet�� MultipartResolver�� �ν����� �ʴ´�.	
	 -->
	 <beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	 	<beans:property name="maxUploadSize" value="-1"/>
	 	<!-- -1�� �ָ� ���ε� �뷮 ������ -->
	 	<beans:property name="defaultEncoding" value="UTF-8"/>
	 </beans:bean>
	 
	 --------------------------------------------------------------------------------------------------------------
	 
	 ���ε� ���
	 
	 [1] MultipartFile�� �̿��ϴ� �ٹ�
	 [2] MultipartHttpServletRequest�� �̿��ϴ� ��� => servlet���� 3�̻� �϶� ��� ����
	 	=> �� ���� ������ �Ķ���͸����� �������� ������ ���ε� �ϴ� ��� ������
 * */

@Controller("fileController")
@Log4j
public class FileController {

	@Resource(name="upDir")
	private String upDir;
	
	
	//@RequestMapping(value="/fileUp", method=RequestMethod.POST)
	@PostMapping("/fileUp")
	public String fileUpload(@RequestParam("name") String name, @RequestParam("filename") MultipartFile filename, Model m) {
		log.info("name="+name+", filename="+filename);
		//[1] ���ε��� ���ϸ� �˾Ƴ��� => DB���̺� insert
		String file=filename.getOriginalFilename();
		long fsize=filename.getSize();
		log.info("file="+file+", fsize="+fsize);
		
		//[2] ���ε� ó�� = ������ �ش� ������ ���ε�
		//isEmpty() => ���� ÷�θ� ���� ���� ��� true�� ��ȯ��
		//transferTo() => ���� ���ε� ó��
		try {
			if(!filename.isEmpty()) {
				filename.transferTo(new File(upDir, file));
				log.info("���� ���ε� ����: "+upDir+"���� Ȯ���ϼ���~");
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		m.addAttribute("file1", file);
		m.addAttribute("file1size", fsize);
		m.addAttribute("upDir", upDir);
		
		return "fileup/fileUpload";
	}
	
	@PostMapping("/fileUp2")
	public String fileUpload2(Model m, HttpServletRequest req) {
		//�ۼ��� �Ķ���Ͱ� �ޱ�
		String name=req.getParameter("name");
		log.info("name==="+name);
		
		//÷������ ��� �ޱ�
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		
		List<MultipartFile> fList=mr.getFiles("filename");
		if(fList!=null) {
			for(int i=0;i<fList.size();i++) {
				MultipartFile mf=fList.get(i);
				String param=mf.getName();//�Ķ���͸� (filename)
				String fname=mf.getOriginalFilename();//÷�����ϸ�
				long fsize=mf.getSize();//����ũ��
				log.info("param="+param+", fname="+fname+", fsize="+fsize);
				try {
					mf.transferTo(new File(upDir, fname));
					
					m.addAttribute("file"+(i+1),fname);
					m.addAttribute("file"+(i+1)+"size",fsize);
					m.addAttribute("upDir",upDir);
					
					log.info("���ε� ����");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
		
		return "fileup/fileUpload";
	}//--------------------------------
	/*
	 * ResponseEntityŸ��: �����Ϳ� �Բ� ��� ���� �޽����� �����ϰ��� �� ��
	 * ����Ѵ�. HTTP����� �ٷ�� �� ��� ResponseEntity�� ���� ��������� �����͸�
	 * ������ �� �ִ�.
	 * */
	@RequestMapping(value="/fileDown", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> download(HttpServletRequest req,@RequestHeader("User-Agent")String userAgent,//-ResponseEntity: jsp���̵� �����͸� ��������
			@RequestParam("filename") String fname){
		log.info("fname: "+fname+", userAgent: "+userAgent);
		//���ε�� ���丮�� ������ ������
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/upload");//���� �޸��� ���ε�� ���丮 ������
		
		String path=upDir+File.separator+fname;//������ ������
		FileSystemResource resource=new FileSystemResource(path);
		String downFname = null;//�ٿ�ε���� ���ϸ��� ���ڵ�ó���Ͽ� ����� ����
		if(!resource.exists()) {
			//�ٿ�ε���� ������ �������� �ʴ°��
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		boolean checkIE=(userAgent.indexOf("MSIE"))>-1 ||userAgent.indexOf("Trident")>-1; //-�ͽ��÷η� �̸� true
		log.info("checkIE: "+checkIE);
		try {
			//IE�ΰ��
			if(checkIE) {
				downFname=URLEncoder.encode(fname,"UTF8").replaceAll("\\+", " ");//-ie������ ������ +���� �ٲٱ� ������ �������� �ٲ�
				int i=downFname.lastIndexOf("_");
				downFname=downFname.substring(i+1);
				System.out.println("downFname===="+downFname);	
			}else {
				//�׿� �ΰ��
				downFname=new String(fname.getBytes("UTF-8"),"ISO-8859-1");//-�ѱ� ���ϸ� �����°� ����
				int i=downFname.lastIndexOf("_");//-�ڿ��� ���� ���� _�� ��ġ�� ������ �װ��� ���° �ڸ����� int������ ǥ�� (������ ���Ͼ��ε带 �ϸ� UUID�ڿ� _�� ����)
				downFname=downFname.substring(i+1);//-_���ķ� ���� ���ڿ����� �߶� ǥ���� (UUID�� �߶�����ó�� ����)
				System.out.println("downFname===="+downFname);	
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename="+downFname);
		ResponseEntity entity = new ResponseEntity(resource,headers,HttpStatus.OK);
		return entity;
	}

}
