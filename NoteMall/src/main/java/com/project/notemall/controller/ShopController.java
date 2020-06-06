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

	/**��ǰ ���*/
	@GetMapping("/shop")
	public String shopList(Model m, HttpSession ses, HttpServletRequest req,
			@ModelAttribute("paging") PagingVO paging) {
		log.info("pcompany===" + paging);

		// 1. �ѰԽñ� �� ��������
		int totalCount = this.shopSvc.getTotalCount(paging);

		// ����¡ �����ϴ� �޼ҵ� ȣ��
		paging.setTotalCount(totalCount);/////////
		paging.setPagingBlock(5);// ����¡ �� ���� ����
		paging.init(ses);
		//////////////////////////////////////////
		log.info("paging2===" + paging);

		// Map<String,Integer> map=new HashMap<>();
		// 2. �Խø�� ��������
		List<ProductVO> plist = shopSvc.selectByPcompany(paging);

		log.info("plist===" + plist);

		// 3. ������ �׺���̼� ���ڿ� ��������
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

		// 1. �ѰԽñ� �� ��������
		int totalCount = this.shopSvc.getTotalCount(paging);

		// ����¡ �����ϴ� �޼ҵ� ȣ��
		paging.setTotalCount(totalCount);/////////
		paging.setPagingBlock(5);// ����¡ �� ���� ����
		paging.init(ses);
		//////////////////////////////////////////
		log.info("paging2===" + paging);

		// Map<String,Integer> map=new HashMap<>();
		// 2. �Խø�� ��������
		List<ProductVO> plist = shopSvc.selectByPcompany(paging);

		log.info("plist===" + plist);

		// 3. ������ �׺���̼� ���ڿ� ��������
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

		// 1. �ѰԽñ� �� ��������
		int totalCount = this.shopSvc.getTotalCount(paging);

		// ����¡ �����ϴ� �޼ҵ� ȣ��
		paging.setTotalCount(totalCount);/////////
		paging.setPagingBlock(5);// ����¡ �� ���� ����
		paging.init(ses);
		//////////////////////////////////////////
		log.info("paging2===" + paging);

		// Map<String,Integer> map=new HashMap<>();
		// 2. �Խø�� ��������
		List<ProductVO> plist = shopSvc.selectByPcompany(paging);

		log.info("plist===" + plist);

		// 3. ������ �׺���̼� ���ڿ� ��������
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
