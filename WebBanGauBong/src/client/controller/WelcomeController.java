package client.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
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

import net.sf.ehcache.hibernate.HibernateUtil;
import web.entity.*;
import web.mailer.Mailer;

@Transactional
@Controller
public class WelcomeController {
	User user = new User();
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	@Autowired
	Mailer mailer;

	@RequestMapping("trang-chu")
	public String home(ModelMap model, HttpSession session) {
		Session sessionn = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = sessionn.createQuery(hql);
		List<Product> listPro = query.list();
		model.addAttribute("listPro", listPro);
		model.addAttribute("listProduct", getListNav(0, 8));
		model.addAttribute("totalItem", totalItem() / 8);
		
		return "client/index2";

	}

	@RequestMapping(value = "{page}", method = RequestMethod.GET)
	public String viewProductListByPage(ModelMap model, HttpSession session, @PathVariable("page") int page) {
		model.addAttribute("listProduct", getListNav((page - 1) * 8, 8));
		model.addAttribute("totalItem", totalItem() / 8);
		return "client/index2";
	}

	// Phần account
	// trang thông tin tài khoản
	@RequestMapping("client/account")
	public String account(HttpSession session) {
		session.setAttribute("parama", "kietgoooooo");
		return "client/account";
	}

	// hàm đổi mật khẩu

	@RequestMapping(value = "client/account/changePW", method = RequestMethod.GET)
	public String changePW(ModelMap model) {
		// model.addAttribute("u", new User());
		return "admin/changePW";
	}

	@RequestMapping(value = "client/account/changePW", method = RequestMethod.POST)
	public String changePW(HttpServletRequest req, ModelMap model) {

		String passOld = req.getParameter("passOld");
		String pass = req.getParameter("pass");
		String confirm = req.getParameter("confirm");
		
		System.out.print("\nbat dau\n");
		System.out.print(passOld+"\n");
		System.out.print(pass+"\n");
		System.out.print(confirm+"\n");
		System.out.print("\nket thuc\n");
		
		HttpSession session = req.getSession();
		User us = (User) session.getAttribute("user");

		if (!passOld.equals(us.getPassword())) {
			model.addAttribute("failOldPass", "Mật khẩu cũ không đúng!");
			return "admin/changePW";
		}

		if (!pass.equals(confirm)) {
			model.addAttribute("failConfirmPass", "Mật khẩu không trùng khớp!");
			return "admin/changePW";
		}

		Session sessionn = factory.openSession();
		Transaction t = sessionn.beginTransaction();
		try {
			us.setPassword(pass);
			sessionn.update(us);
			t.commit();
			session.setAttribute("user", us);
			model.addAttribute("successChange", "Đã đổi mật khẩu thành công!");
			System.out.print("\nchange Password thanh cong\n");

		} catch (Exception e) {
			t.rollback();
			System.out.print("\nchange Password thất bại\n");
			model.addAttribute("failChange", "Đổi mật khẩu thất bại!");
			User a=(User)session.getAttribute("user");
			
			//xử lý cho trường hợp nhập khoảng trắng vào 2 pass
			a.setPassword(passOld);
			session.setAttribute("user", a);
			
		} finally {
			sessionn.close();
		}

		return "admin/changePW";
	}

	// chỉnh sửa thông tin cá nhân

	@RequestMapping(value = "client/account/edit", method = RequestMethod.GET)
	public String edit(ModelMap model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User us = (User) session.getAttribute("user");

		model.addAttribute("us", us);
		model.addAttribute("uId", us.getId());
		model.addAttribute("u", new User());
		return "admin/edit";
	}

	@RequestMapping(value = "client/account/edit/{uId}", method = RequestMethod.POST)
	public String edit(ModelMap model, @ModelAttribute("u") User o, @PathVariable("uId") int uId,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		User us = (User) session.getAttribute("user");

		o.setCreated(us.getCreated());
		o.setId(uId);
		o.setUsername(us.getUsername());
		o.setPassword(us.getPassword());
		o.setUserRole(us.getUserRole());

		Session sessionn = factory.openSession();
		Transaction t = sessionn.beginTransaction();
		try {

			sessionn.update(o);
			t.commit();
			session.setAttribute("user", o);
			model.addAttribute("successEditUser", "Thông tin của bạn đã được lưu lại!");

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			model.addAttribute("failEditUser",
					"Chỉnh sửa thất bại! Vui lòng kiểm tra lại thông tin hoặc liên hệ với chúng tôi!");
		} finally {
			sessionn.close();
		}

		return "admin/edit";
	}

	@RequestMapping("contact")
	public String account() {
		// session.setAttribute("parama", "cocostore");
		return "client/contact";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String viewlogin(ModelMap model, HttpSession session) {
		user = (User) session.getAttribute("user");
		if (user == null) {
			model.addAttribute("user", new User());
			return "admin/login";
		}

		return "redirect:client/account.htm";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String viewlogin(ModelMap model, HttpSession session, @ModelAttribute("user") User user) {

		Session sessionn = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = sessionn.createQuery(hql);
		List<User> listAcc = query.list();

		for (User ktra : listAcc) {
			if (user.getUsername().equals(ktra.getUsername()) && user.getPassword().equals(ktra.getPassword())) {
				this.user = ktra;
				if (this.user.getUserRole().equals("admin")) {

					session.setAttribute("user", this.user);
					return "redirect:/admin/quan-ly.htm";
				} else {

					session.setAttribute("user", this.user);

					return "redirect:/trang-chu.htm";

				}
			}

		}

		model.addAttribute("errorLogin", "Sai thông tin đăng nhập!");
		return "admin/login";

	}

	@RequestMapping("client/logout")
	public String logout(HttpSession session, HttpServletRequest rq) {
		session = rq.getSession();
		User u = new User();
		u = (User) session.getAttribute("user");
		System.out.println(u.getUsername() + " - " + u.getPassword() + "-" + u.getId());
		session.removeAttribute("user");
		return "redirect:/trang-chu.htm";

	}

	@RequestMapping("client/404")
	public String error() {
		return "client/404";

	}

	public float totalPrice(HashMap<Integer, Cart> cartItems) {
		float count = 0;
		for (Map.Entry<Integer, Cart> list : cartItems.entrySet()) {
			count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
		}
		return count;
	}

	@RequestMapping(value = "client/single/{id}")
	public String viewProduct(ModelMap model, @PathVariable("id") int id) {
		model.addAttribute("product", findProById(id));
		return "client/single";
	}

	public Product findProById(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.id LIKE :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Product obj = (Product) query.uniqueResult();
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

	// phân trang

	public int totalItem() {
		// TODO Auto-generated method stub
		Session sessionn = factory.getCurrentSession();
		String hql = "SELECT COUNT(p) FROM Product p";
		Query query = sessionn.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}

	public List<Product> getListNav(int i, int j) {

		Session sessionn = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = sessionn.createQuery(hql);
		query.setFirstResult(i);
		query.setMaxResults(j);
		List<Product> listPro = query.list();
		return listPro;
	}

	@RequestMapping(value = "forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap model) {
		model.addAttribute("us", new User());
		return "admin/forget";
	}

	@RequestMapping(value = "forgotPassword", method = RequestMethod.POST)
	public String forget(ModelMap model, @ModelAttribute("us") User u) {

		Session sessionn = factory.getCurrentSession();
		String hql = "FROM User";
		Query query = sessionn.createQuery(hql);
		List<User> listAcc = query.list();

		for (User ktra : listAcc) {
			if (u.getUsername().equals(ktra.getUsername()) && u.getEmail().equals(ktra.getEmail())) {

				//model.addAttribute("success", "Đổi mật khẩu thành công, Vui lòng xem thư trong email của bạn!");
				
				
				Session session = factory.openSession();
				Transaction t = session.beginTransaction();

				User userP = findUserbyUsername(u.getUsername());
				//thử việc tạo thêm 1 biến u và áp userP vào u, rồi update u
				//trường hợp bên dưới update lỗi nhưng vẫn thay đổi database?
				int code = (int) Math.floor(((Math.random() * 8999999) + 1000000));
				String kq = String.valueOf(code);
				try {
					userP.setPassword(kq);
					session.update(userP);
					t.commit();
					
					
				} catch (Exception e) {
					t.rollback();
					
				} finally {
					session.close();
				}

				// gửi email
				try {
					mailer.send("lenaahgase.got7@gmail.com", userP.getEmail(), "Đổi mật khẩu thành công",
							"Cảm ơn bạn đã sử dụng dịch vụ tại CoCoStore <br> Mật khẩu mới là: "  + kq
									+ " .<br>Chân thành cảm ơn quý khách.");
					// model.addAttribute("mgsMail", "Gửi mail thành công!");
					System.out.print("Gửi email thanh cong!");
				} catch (Exception e) {
					model.addAttribute("msgMail", "Lỗi gửi email thất bại!!");
					System.out.print("gửi email không thành công");
					//return "admin/forget";
					// TODO: handle exception
				}

				model.addAttribute("success", "Đổi mật khẩu thành công, Vui lòng xem thư trong email của bạn!");
				return "admin/forget";

			}

		}

		model.addAttribute("fail", "Sai thông tin!!");
		return "admin/forget";

	}

	public User findUserbyUsername(String uname) {
		// TODO Auto-generated method stub
		Session session = factory.getCurrentSession();
		String hql = "FROM User o WHERE o.username = :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", uname);
		User obj = (User) query.uniqueResult();
		return obj;

	}
}