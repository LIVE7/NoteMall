package com.project.notemall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.notemall.dao.AdminDao;
import com.project.notemall.domain.CategoryVO;
import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.domain.SumVO;

@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private AdminDao adminDao;

	@Override
	public int addUpCategory(CategoryVO cvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addDownCategory(CategoryVO cvo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUpCategory(int upcg_code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDownCategory(int downcg_code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CategoryVO> getUpCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryVO> getDownCategory(int upcg_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalProduct() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addProduct(ProductVO prod) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductVO> listProduct(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> getOrderListByMonth(String month) {
		return this.adminDao.getOrderListByMonth(month);
	}

	@Override
	public void manageOrder(String onum, String colName, String colVal) {
		Map<String,String> map=new HashMap<>();
		map.put("onum", onum);
		map.put("colName",colName);
		map.put("colVal",colVal);
		this.adminDao.manageOrder2(map);
	}

	@Override
	public List<SumVO> getSumYear() {
		return this.adminDao.getSumYear();
	}

	@Override
	public List<SumVO> getSumMonth(String year) {
		return this.adminDao.getSumMonth(year);
	}

	@Override
	public int getUserSumPoint(int midx_fk) {
		// TODO Auto-generated method stub
		return 0;
	}

}
