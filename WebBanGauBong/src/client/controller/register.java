package client.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.entity.Category;
import web.entity.User;
@Transactional
@Controller
@RequestMapping("client")
public class register {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(ModelMap model) {
		model.addAttribute("user", new User());
		return "client/register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest req,ModelMap model,@Validated @ModelAttribute("user") User user ,BindingResult errors) {
		
		
		if(errors.hasErrors())
		{
			model.addAttribute("error", "Vui lòng sửa các lỗi bên dưới!");
			return "client/register";
			
		}
		
		
		String pass=req.getParameter("repassword");
	if(!user.getPassword().equals(pass))
	{
		model.addAttribute("errorsPass", "mật khẩu không trùng khớp!");
		return "client/register";
	}
		
		
		
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		try 
		{
			user.setCreated(new Date());
			user.setUserRole("client");
			session.save(user);
			t.commit();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			model.addAttribute("msgRe", "Tạo thất bại do thông tin tài khoản không hợp lệ. Vui lòng xem lại!!!");
			return "client/register";
		}
		finally {
			session.close();
		}
	
		model.addAttribute("msgSu", "Chúc mừng bạn đã tạo tài khoản thành công!<br>VUi lòng đăng nhập lại tại đây!");
		
		return "admin/login";
	}
	@ModelAttribute("listCategory")
	public List<Category> getListCate() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}

}
