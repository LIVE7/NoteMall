package com.project.notemall.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.notemall.domain.ProductVO;
import com.project.notemall.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ProductController {
	
	@Inject
	private ShopService shopSvc;
	
	//pspec(HIT,NEW,BEST)���� ��ǰ���� ������ �����ִ� ��Ʈ�ѷ�
	@GetMapping("/prodPspec")
	public String productPspec(Model m, @RequestParam(defaultValue="NEW") String pspec) {
		log.info("pspec===" + pspec);
		List<ProductVO> plist = shopSvc.selectByPspec(pspec);
		
		log.info("plist==="+plist);
		
		
		m.addAttribute("pspec",pspec);
		m.addAttribute("prodList", plist);
		return "shop/mallNew";
	}
	
	/**��ǰ ����*/
	@GetMapping("/prodView")
	public String productDetail(Model m,@RequestParam(defaultValue="0") int pnum) {
		if(pnum==0) {
			
			return "redirect:index";
		}
		//������ ��ǰ ������ �������� �޼ҵ� ȣ�� selectByPnum(pnum)
		
		ProductVO prod = this.shopSvc.selectByPnum(pnum);
		
		
		//�𵨿� ������ ��ü ����
		m.addAttribute("prod",prod);
		
		return "shop/prodDetail";
	}
}
