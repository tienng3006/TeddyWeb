package client.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.entity.Product;

@Transactional
@Controller
public class SearchController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value="search" )
	public String home(HttpServletRequest request,ModelMap model) {
		
		String search=request.getParameter("search");
		Session session=factory.getCurrentSession();
		String hql = "FROM Product WHERE name LIKE 'Tissot%'";
		Query query = session.createQuery(hql);
		query.setParameter("search", search);
		List<Product> listSearch = query.list();
		model.addAttribute("listSearch", listSearch);
		return"client/search";
	}
	
}
