package com.bw.service.impl;

import java.util.Date;
import java.util.List;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bw.mapper.FlowMapper;
import com.bw.mapper.OrdersMapper;
import com.bw.pojo.Flow;
import com.bw.pojo.Orders;
import com.bw.service.OrdersService;
import com.bw.utils.OrderStatusEnum;


@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private FlowMapper flowMapper;
	
	private Sid sid;



	public void saveOrder(Orders order) {

		System.out.println("order==="+order);
		System.out.println("ordersMapper=="+ordersMapper);
		ordersMapper.insert(order);
	}

	public Orders getOrderById(String orderId) {
		return ordersMapper.selectByPrimaryKey(orderId);
	}

	public List<Orders> findAliQueryList() {
		return ordersMapper.selectQueryList();
	}

	public void reconverOrder(String orderNum) {
		this.ordersMapper.reconverOrder(orderNum);
	}


	public void updateOrderStatus(String orderId, String alpayFlowNum, String paidAmount,String payType) {
		
		Orders order = getOrderById(orderId);
		if (order.getOrderStatus().equals(OrderStatusEnum.WAIT_PAY.key)) {
			order = new Orders();
			order.setId(orderId);
			order.setOrderStatus(OrderStatusEnum.PAID.key);
			order.setPaidTime(new Date());
			order.setPaidAmount(paidAmount);
			
			ordersMapper.updateByPrimaryKeySelective(order);
			
			order = getOrderById(orderId);
			
			String flowId = sid.nextShort();
			Flow flow = new Flow();
			flow.setId(flowId);
			flow.setFlowNum(alpayFlowNum);
			flow.setBuyCounts(order.getBuyCounts());
			flow.setCreateTime(new Date());
			flow.setOrderNum(orderId);
			flow.setPaidAmount(paidAmount);
			flow.setPaidMethod(1);
			flow.setProductId(order.getProductId());
			flow.setPayType(payType);
			flowMapper.insertSelective(flow);
		}
	}

}
