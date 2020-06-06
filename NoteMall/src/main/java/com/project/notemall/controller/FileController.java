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

/*	pom.xml에 파일 업로드 라이브러리 등록---------
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
 * WEB-INF/spring/appServlet/servlet-context.xml에 아래 빈을 등록한다.
 * 
 * <!-- MultipartResolver설정 ====================================================
		주의: id는 반드시 multipartResolver로 등록해야 한다.
		다른 아이디를 주면 DispatcherServlet이 MultipartResolver로 인식하지 않는다.	
	 -->
	 <beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	 	<beans:property name="maxUploadSize" value="-1"/>
	 	<!-- -1을 주면 업로드 용량 무제한 -->
	 	<beans:property name="defaultEncoding" value="UTF-8"/>
	 </beans:bean>
	 
	 --------------------------------------------------------------------------------------------------------------
	 
	 업로드 방법
	 
	 [1] MultipartFile을 이용하는 바법
	 [2] MultipartHttpServletRequest를 이용하는 방법 => servlet버전 3이상 일때 사용 가능
	 	=> 이 경우는 동일한 파라미터명으로 여러개의 파일을 업로드 하는 경우 유용함
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
		//[1] 업로드한 파일명 알아내기 => DB테이블에 insert
		String file=filename.getOriginalFilename();
		long fsize=filename.getSize();
		log.info("file="+file+", fsize="+fsize);
		
		//[2] 업로드 처리 = 서버에 해당 파일이 업로드
		//isEmpty() => 파일 첨부를 하지 않을 경우 true를 반환함
		//transferTo() => 파일 업로드 처리
		try {
			if(!filename.isEmpty()) {
				filename.transferTo(new File(upDir, file));
				log.info("파일 업로드 성공: "+upDir+"에서 확인하세요~");
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
		//작성자 파라미터값 받기
		String name=req.getParameter("name");
		log.info("name==="+name);
		
		//첨부파일 목록 받기
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest)req;
		
		List<MultipartFile> fList=mr.getFiles("filename");
		if(fList!=null) {
			for(int i=0;i<fList.size();i++) {
				MultipartFile mf=fList.get(i);
				String param=mf.getName();//파라미터명 (filename)
				String fname=mf.getOriginalFilename();//첨부파일명
				long fsize=mf.getSize();//파일크기
				log.info("param="+param+", fname="+fname+", fsize="+fsize);
				try {
					mf.transferTo(new File(upDir, fname));
					
					m.addAttribute("file"+(i+1),fname);
					m.addAttribute("file"+(i+1)+"size",fsize);
					m.addAttribute("upDir",upDir);
					
					log.info("업로드 성공");
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
		}
		
		return "fileup/fileUpload";
	}//--------------------------------
	/*
	 * ResponseEntity타입: 데이터와 함께 헤더 상태 메시지를 전달하고자 할 때
	 * 사용한다. HTTP헤더를 다뤄야 할 경우 ResponseEntity를 통해 헤더정보와 데이터를
	 * 전달하 수 있다.
	 * */
	@RequestMapping(value="/fileDown", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> download(HttpServletRequest req,@RequestHeader("User-Agent")String userAgent,//-ResponseEntity: jsp없이도 데이터만 내보내줌
			@RequestParam("filename") String fname){
		log.info("fname: "+fname+", userAgent: "+userAgent);
		//업로드된 디렉토리의 절대경로 얻어오기
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/upload");//한줄 메모장 업로드된 디렉토리 절대경로
		
		String path=upDir+File.separator+fname;//파일의 절대경로
		FileSystemResource resource=new FileSystemResource(path);
		String downFname = null;//다운로드받을 파일명을 인코딩처리하여 담아줄 변수
		if(!resource.exists()) {
			//다운로드받을 파일이 존재하지 않는경우
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		boolean checkIE=(userAgent.indexOf("MSIE"))>-1 ||userAgent.indexOf("Trident")>-1; //-익스플로러 이면 true
		log.info("checkIE: "+checkIE);
		try {
			//IE인경우
			if(checkIE) {
				downFname=URLEncoder.encode(fname,"UTF8").replaceAll("\\+", " ");//-ie에서는 공백을 +으로 바꾸기 때문에 공백으로 바꿈
				int i=downFname.lastIndexOf("_");
				downFname=downFname.substring(i+1);
				System.out.println("downFname===="+downFname);	
			}else {
				//그외 인경우
				downFname=new String(fname.getBytes("UTF-8"),"ISO-8859-1");//-한글 파일명 깨지는것 막음
				int i=downFname.lastIndexOf("_");//-뒤에서 부터 문자 _의 위치를 가져옴 그것이 몇번째 자리인지 int형으로 표현 (이전에 파일업로드를 하면 UUID뒤에 _를 붙임)
				downFname=downFname.substring(i+1);//-_이후로 나온 문자열부터 잘라서 표현함 (UUID가 잘라진것처럼 보임)
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
