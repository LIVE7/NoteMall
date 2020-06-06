package com.project.notemall.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.notemall.dao.OrderDao;
import com.project.notemall.dao.UserDao;
import com.project.notemall.domain.OrderVO;
import com.project.notemall.domain.ProductVO;
import com.project.notemall.domain.ReceiverVO;

@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;
	/*Ʈ����� ó���� �ʿ��� �޼ҵ忡 @Transactional������̼��� �ٿ��ش�.
	 * (Ʈ������̶�? ���� �۾� �������� ��� �����ϴ���, �ƴ� �ϳ��� �����ϸ�
	 *    ��� ���ó���Ǵ� ��)
	 *    @Transactional �� �ָ� �ش� �޼ҵ峪 ���񽺰������� �Բ� �� �۾� ������ ��
	 *    �ϳ��� �����ϸ� rollback�� �ȴ�. ��� �����ؾ� commit��
	 *    propagation=Propagation.REQUIRED
	 *    : ���� Ʈ������� �ִ� ���¸� �� �� ���� ���󰡰�, ���ٸ� ���ο� Ʈ�������
	 *    �����ϵ��� �ϴ� �ɼ�.
	 * =====transaction-context.xml===============================
	 * <!-- Ʈ����� ���� �ڵ����� ����ϵ��� ���� -->
		<tx:annotation-driven/>
	
		<!-- transactionManager����================================================== -->
		<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
			<property name="dataSource" ref="jndiDataSource"/>
			<!-- <property name="dataSource" ref="dataSource"/> --><!-- =jndiDataSource�� �ȵɶ� -->
		</bean>
	 *============================================================    
	 *
	 *==== WEB-INF/spring/appServlet/servlet-context.xml==========
	 *	<tx:annotation-driven/> <!-- �߰��ϱ�! -->
	 *============================================================
	 *    
	 * */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String orderInsert(OrderVO ovo) {
		// �ֹ�����, �ֹ���ǰ, ������ ������ insert�ϰ�
		// �ֹ���ȣ�� ��ȯ�� ����=>Ʈ����� ó�� �ʿ�
		//1. �ֹ���ȣ ����
		String onum=makeOnum();
		System.out.println("onum?:"+onum);
		ovo.setOnum(onum);//�ֹ���ȣ�� ovo�� ����
		System.out.println("ovo?"+ovo);
		
		//2. �ֹ����� ���� insert
		int n1=this.orderDao.orderDescInsert(ovo);
		System.out.println(n1);
		
		
		//3. �ֹ� ��ǰ���� insert (��ǰ�� ���� ��)
		List<ProductVO> orderList=ovo.getOrderList();
		if(orderList!=null) {
			for(ProductVO prod:orderList) {
				//order_product���̺� insert
				prod.setOnum(onum);
				System.out.println(prod);
				int n2=this.orderDao.orderProductInsert(prod);
				System.out.println(n2);
			}
		}
		
		//4. ������ ���� insert
		ReceiverVO rcv=ovo.getReceiver();
		System.out.println(rcv);
		rcv.setOnum(onum);
		int n3=this.orderDao.receiverInsert(rcv);
		System.out.println(n3);
		
		
		//5. �������Ʈ(opointuse) �ݾ��� �ִٸ� ȸ���� ���ϸ������� ����
		if(ovo.getOpointuse()>0) {					
			userDao.updateMileageDown(ovo);
		}
		
		//6. ��������� ī���� ���ϸ����� ���� ����Ʈ ����
		if(ovo.getPayMethod()==200) {
			userDao.updateMileageUp(ovo);
		}
		
		return onum;
	}
	public String makeOnum() {
		//�����ϰ� ���ĺ� �빮�� 3�� + �ֹ��� "����Ͻú���"
		char ch1=(char)(Math.random()*26+'A');
		char ch2=(char)(Math.random()*26+'A');
		char ch3=(char)(Math.random()*26+'A');
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String str=sdf.format(d);
		String onum=""+ch1+ch2+ch3+str;
		return onum;
	}

	@Override
	public List<OrderVO> getOrderDesc(String onum) {
		return this.orderDao.getOrderDesc(onum);
	}

	@Override
	public List<OrderVO> getUserOrderList(int idx) {
		return this.orderDao.getUserOrderList(idx);
	}

}
