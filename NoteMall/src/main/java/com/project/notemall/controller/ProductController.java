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
	
	//pspec(HIT,NEW,BEST)별로 상품정보 가져와 보여주는 컨트롤러
	@GetMapping("/prodPspec")
	public String productPspec(Model m, @RequestParam(defaultValue="NEW") String pspec) {
		log.info("pspec===" + pspec);
		List<ProductVO> plist = shopSvc.selectByPspec(pspec);
		
		log.info("plist==="+plist);
		
		
		m.addAttribute("pspec",pspec);
		m.addAttribute("prodList", plist);
		return "shop/mallNew";
	}
	
	/**상품 정보*/
	@GetMapping("/prodView")
	public String productDetail(Model m,@RequestParam(defaultValue="0") int pnum) {
		if(pnum==0) {
			
			return "redirect:index";
		}
		//서비스의 상품 상세정보 가져오는 메소드 호출 selectByPnum(pnum)
		
		ProductVO prod = this.shopSvc.selectByPnum(pnum);
		
		
		//모델에 가져온 객체 저장
		m.addAttribute("prod",prod);
		
		return "shop/prodDetail";
	}
}
