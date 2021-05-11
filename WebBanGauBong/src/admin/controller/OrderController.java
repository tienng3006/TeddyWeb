package admin.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.entity.Category;
import web.entity.Order;
import web.entity.OrderDetail;
import web.entity.Product;
import web.entity.Status;
import web.entity.User;

@Controller
@Transactional
@RequestMapping("/admin/order/")
public class OrderController {

	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewProductList(ModelMap model) {
		listOrder(model);

		return "admin/orderList";
	}

	// Danh sách OrderDetail được lọc theo id của Order
	public void listOrder(ModelMap model) {

		Session sessionn = factory.getCurrentSession();
		String hql = "SELECT d.order.id,d.order.user.name,d.order.user.email,d.order.created,d.order.status.name, "
				+ " SUM(d.unitPrice)" + " FROM OrderDetail d "
				+ " GROUP BY d.order.id ,d.order.user.name,d.order.user.email,d.order.created,d.order.status.name ORDER by d.order.created DESC";
		Query query = sessionn.createQuery(hql);
		List<Object[]> listOrDetail = query.list();
		model.addAttribute("listOrDetail", listOrDetail);
	}

	// xóa đơn hàng
	@RequestMapping("remove/{orderId}")
	public String removeOrder(ModelMap model, @PathVariable("orderId") int orderId) {
		Order pp = new Order();
		Order p = findOrderById(orderId);
		if (p != null) {

			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				pp.setId(orderId);
				session.delete(pp);
				model.addAttribute("successRemoveOrder", "Đã xóa thành công!");
				t.commit();

			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("failRemoveeOrder", "Xóa thất bại!");
				t.rollback();

			} finally {
				session.close();
			}

		} else {
			model.addAttribute("failRemoveUser", "Hóa đơn này không tồn tại! :(");
			listOrder(model);
			return "admin/orderList";
		}
		listOrder(model);
		return "admin/orderList";
	}

	public Order findOrderById(int orderId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order p WHERE p.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", orderId);
		Order obj = (Order) query.uniqueResult();
		return obj;
	}

	// chỉnh sửa order

	@RequestMapping(value = "edit/{orderId}", method = RequestMethod.GET)
	public String editOrder(ModelMap model, @PathVariable("orderId") int orderId) {
		// Product p;
		// p = findById(productId);
		// Product pp = new Product();
		// pp.setId(p.getId());
		Order o = new Order();
		o = findOrderById(orderId);
		model.addAttribute("oid", orderId);
		model.addAttribute("o", o);
		model.addAttribute("order", new Order());
		model.addAttribute("listStatus", listStatus());
		return "admin/order_formEdit";
	}

	@RequestMapping(value = "edit/{orderId}", method = RequestMethod.POST)
	public String editOrder(ModelMap model, @ModelAttribute("order") Order o, @PathVariable("orderId") int orderId) {
		
		
		Order od=findOrderById(orderId);
		o.setCreated(od.getCreated());
		o.setId(orderId);
		o.setUser(od.getUser());
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			
			session.update(o);
			t.commit();
			model.addAttribute("successEditOrder", "Chỉnh sửa đơn hàng thành công!");

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			model.addAttribute("failEditOrder", "Chỉnh sửa đơn hàng thất bại! Vui lòng kiểm tra lại thông tin!");
			listOrder(model);
			return "admin/orderList";
		} finally {
			session.close();
		}

		listOrder(model);
		return "admin/orderList";
	}

	public List<Status> listStatus() {

		Session sessionn = factory.getCurrentSession();
		String hql = "FROM Status";
		Query query = sessionn.createQuery(hql);
		List<Status> listStatus = query.list();
		return listStatus;
	}
	
	
	
	
	
	
	
	
	
	//chi tiết hóa đơn
	
	//
	@RequestMapping(value = "orderDetail/{orderId}")
	public String getProductList(ModelMap model, @PathVariable("orderId") int orderId) {
		
		model.addAttribute("id", orderId);
		model.addAttribute("listOrderD", listOrderD(orderId));
		return "admin/orderDetailList";
	}
	//Lấy OrderDetail trả về listOrderDetail
	public List<OrderDetail> listOrderD(int orderId) {

		Session sessionn = factory.getCurrentSession();
		String hql = "FROM OrderDetail d WHERE d.order.id = :orderId";
		Query query = sessionn.createQuery(hql);
		query.setParameter("orderId", orderId);
		return query.list();
	}


}
