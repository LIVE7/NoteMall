package com.project.notemall.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.project.notemall.domain.PagingVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.service.ShopService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ShopController {

	@Inject
	private ShopService shopSvc;

	/**상품 목록*/
	@GetMapping("/shop")
	public String shopList(Model m, HttpSession ses, HttpServletRequest req,
			@ModelAttribute("paging") PagingVO paging) {
		log.info("pcompany===" + paging);

		// 1. 총게시글 수 가져오기
		int totalCount = this.shopSvc.getTotalCount(paging);

		// 페이징 연산하는 메소드 호출
		paging.setTotalCount(totalCount);/////////
		paging.setPagingBlock(5);// 페이징 블럭 단위 설정
		paging.init(ses);
		//////////////////////////////////////////
		log.info("paging2===" + paging);

		// Map<String,Integer> map=new HashMap<>();
		// 2. 게시목록 가져오기
		List<ProductVO> plist = shopSvc.selectByPcompany(paging);

		log.info("plist===" + plist);

		// 3. 페이지 네비게이션 문자열 가져오기
		String myctx = req.getContextPath();
		String loc = "shop";
		String navi = paging.getPageNavi(myctx, loc);
		System.out.println(navi);
		m.addAttribute("navi", navi);

		m.addAttribute("totalCount", totalCount);
		m.addAttribute("prodList", plist);
		m.addAttribute("paging", paging);

		return "shop/shop";
	}
	
	@GetMapping("/user/shop")
	public String shopListUser(Model m, HttpSession ses, HttpServletRequest req,
			@ModelAttribute("paging") PagingVO paging) {
		log.info("pcompany===" + paging);

		// 1. 총게시글 수 가져오기
		int totalCount = this.shopSvc.getTotalCount(paging);

		// 페이징 연산하는 메소드 호출
		paging.setTotalCount(totalCount);/////////
		paging.setPagingBlock(5);// 페이징 블럭 단위 설정
		paging.init(ses);
		//////////////////////////////////////////
		log.info("paging2===" + paging);

		// Map<String,Integer> map=new HashMap<>();
		// 2. 게시목록 가져오기
		List<ProductVO> plist = shopSvc.selectByPcompany(paging);

		log.info("plist===" + plist);

		// 3. 페이지 네비게이션 문자열 가져오기
		String myctx = req.getContextPath();
		String loc = "shop";
		String navi = paging.getPageNavi(myctx, loc);
		System.out.println(navi);
		m.addAttribute("navi", navi);

		m.addAttribute("totalCount", totalCount);
		m.addAttribute("prodList", plist);
		m.addAttribute("paging", paging);

		return "shop/shop";
	}
	
	@GetMapping("/admin/shop")
	public String shopListAdmin(Model m, HttpSession ses, HttpServletRequest req,
			@ModelAttribute("paging") PagingVO paging) {
		log.info("pcompany===" + paging);

		// 1. 총게시글 수 가져오기
		int totalCount = this.shopSvc.getTotalCount(paging);

		// 페이징 연산하는 메소드 호출
		paging.setTotalCount(totalCount);/////////
		paging.setPagingBlock(5);// 페이징 블럭 단위 설정
		paging.init(ses);
		//////////////////////////////////////////
		log.info("paging2===" + paging);

		// Map<String,Integer> map=new HashMap<>();
		// 2. 게시목록 가져오기
		List<ProductVO> plist = shopSvc.selectByPcompany(paging);

		log.info("plist===" + plist);

		// 3. 페이지 네비게이션 문자열 가져오기
		String myctx = req.getContextPath();
		String loc = "shop";
		String navi = paging.getPageNavi(myctx, loc);
		System.out.println(navi);
		m.addAttribute("navi", navi);

		m.addAttribute("totalCount", totalCount);
		m.addAttribute("prodList", plist);
		m.addAttribute("paging", paging);

		return "shop/shop";
	}

}
