package client.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import web.mailer.Mailer;
import web.entity.Cart;
import web.entity.Category;
import web.entity.Order;
import web.entity.OrderDetail;
import web.entity.Product;
import web.entity.Status;
import web.entity.User;

@Transactional
@Controller
@RequestMapping("/client")
public class checkoutController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Autowired
	ServletContext application;
	@RequestMapping("/checkout")
	public String viewCheckout(ModelMap model, HttpSession session) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("user", new User());
			return "admin/login";
		}
		return "client/checkout";
	}

	@RequestMapping("/checkoutCart")
	public String Checkout(ModelMap model, HttpSession session) {
		float price=0;

		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("user", new User());
			return "admin/login";
		}

		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
		if (cartItems.isEmpty()) {
			cartItems = new HashMap<>();
			model.addAttribute("msgEmptyCart", "Bạn chưa có món hàng nào trong giỏ hàng!");
			return "client/checkout";
		}

		Status status = new Status();
		status.setId(1);
		Order order = new Order();
		order.setCreated(new Date());
		order.setStatus(status);
		order.setUser(user);
		boolean a = createOrder(order);
		if (a) {

			for (Map.Entry<Integer, Cart> entry : cartItems.entrySet()) {
				OrderDetail detail = new OrderDetail();
				detail.setOrder(order);
				detail.setProduct(entry.getValue().getProduct());
				detail.setUnitPrice(entry.getValue().getProduct().getPrice() * entry.getValue().getQuantity());
				detail.setQuantity(entry.getValue().getQuantity());
				price=price+detail.getUnitPrice();

				if (createOrDetail(detail)) {
					
					cartItems = new HashMap<>();
					session.setAttribute("myCartItems", cartItems);
					session.setAttribute("myCartTotal", 0);
					session.setAttribute("myCartNum", 0);
					
				} else {
					model.addAttribute("msgCheckouofFailOrDetail", "Đặt hàng thất bại!");
					return "client/checkout";
				}
			}

		} else {
			model.addAttribute("msgCheckoutofFailOrder", "Đặt hàng thất bại!");
			return "client/checkout";

		}
		listOrder(session, model);
		
		try {
			mailer.send("lenaahgase.got7@gmail.com", user.getEmail(), "Đặt hàng thành công",		
					"Cảm ơn bạn đã đặt hàng tại CoCoStore.<br>Tổng số tiền bạn phải trả là: "+"k"+price+" .<br>Món hàng của bạn đang được chuẩn bị. Chúng tôi sẽ liên hệ với bạn sớm nhất!<br><br> Chân thành cảm ơn quý khách.");
			//model.addAttribute("mgsMail", "Gửi mail thành công!");
		} catch (Exception e) {
			model.addAttribute("msgMail", "Gửi mail thất bại!");
			// TODO: handle exception
		}
		model.addAttribute("msgSuccessOrderr", " Đã đặt hàng thành công. Bên dưới là đơn hàng của bạn.");
		return "client/orderList";
	}

	public boolean createOrDetail(OrderDetail detail) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		boolean a = true;
		try {

			session.save(detail);
			t.commit();
			// model.addAttribute("message", "Thêm mới thành công!");

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			a = false;
			// model.addAttribute("message", "Thêm mới thất bại!!");
		} finally {
			session.close();
		}

		return a;
	}

	public boolean createOrder(Order order) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		boolean a = true;
		try {

			session.save(order);
			t.commit();
			// model.addAttribute("message", "Thêm mới thành công!");

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			a = false;
			// model.addAttribute("message", "Thêm mới thất bại!!");
		} finally {
			session.close();
		}

		return a;
	}

	public Order findOrdCode() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Order";
		Query query = session.createQuery(hql);
		Order findProd = new Order();

		List<Order> listOrder = query.list();

		if (listOrder.size() > 0) {
			findProd = listOrder.get(listOrder.size() - 1);
		} else {
			findProd = null;
		}
		// Kiem Tra co hang co trong CSDL?
		return findProd;
	}

	@RequestMapping("/orderList")
	public String success(ModelMap model, HttpSession session) {
		//User user = new User();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("user", new User());
			return "admin/login";
		}

		listOrder(session, model);
		return "client/orderList";
	}
	
	
	//Danh sách OrderDetail được lọc theo id của Order
	public void listOrder(HttpSession session,ModelMap model)
	{
		listOrOf(model, session);

		Session sessionn = factory.getCurrentSession();
		String hql = "SELECT d.order.id, " + " SUM(d.unitPrice)," + " SUM(d.quantity)" + " FROM OrderDetail d "
				+ " GROUP BY d.order.id";
		Query query = sessionn.createQuery(hql);
		List<Object[]> listOrDetail = query.list();
		model.addAttribute("listOrDetail", listOrDetail);
	}

	public void listOrOf(ModelMap model, HttpSession session) {
		User user = new User();
		user = (User) session.getAttribute("user");
		Session sessionn = factory.getCurrentSession();
		String hql = "FROM Order o WHERE o.user.id = :id AND o.status.id<>0";
		Query query = sessionn.createQuery(hql);
		query.setParameter("id", user.getId());
		List<Order> listOrOf = query.list();
		model.addAttribute("listOrOf", listOrOf);
	}

	// xóa đơn đặt hàng
	@RequestMapping(value = "/order/remove/{orderId}", method = RequestMethod.GET)
	public String removeOrder(ModelMap model, HttpSession session, @PathVariable("orderId") int orderId) {
		
		Status status = new Status();
		status.setId(0);
		//status.setName("Đã hủy");
		Order order = new Order();
		order = findOrderbyId(orderId);
		if(order.getStatus().getId()==0||order.getStatus().getId()==3)
		{
			model.addAttribute("msgFail", "Quý khách không thể hủy đơn hàng này. Vui lòng đọc điều khoản hoặc liên hệ với chúng tôi. Chân thành cảm ơn!");
			listOrder(session, model);
			return"client/orderList";
		}
		
		order.setStatus(status);

		model.addAttribute("msgDeleOrderSuccess", "Hủy đơn hàng thành công!");
		listOrder(session, model);
		return "client/orderList";
	}

	public Order findOrderbyId(int orderId) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		String hql = "FROM Order o WHERE o.id = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", orderId);
		Order obj = (Order) query.uniqueResult();
		return obj;

	}
	@ModelAttribute("listCategory")
	public List<Category> getListCate() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}
	
	@RequestMapping(value = "orderDetail/{orderId}")
	public String getProductList(ModelMap model, @PathVariable("orderId") int orderId) {
		
		model.addAttribute("id", orderId);
		model.addAttribute("listOrderD", listOrderD(orderId));
		return "client/orderDetailList";
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
