package client.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.entity.*;
@Transactional
@Controller
@RequestMapping("/client")
public class products {
	@Autowired
	SessionFactory factory;

	@RequestMapping("/products/{id}")
	public String product(@PathVariable("id") String id,ModelMap model)
	{
			Session session = factory.getCurrentSession();
			String hql = "FROM Product p WHERE p.category.id LIKE :id ";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			List<Product> listProCate = query.list();
			model.addAttribute("listProCate", listProCate);
			String title=listProCate.get(0).getCategory().getName();
			model.addAttribute("title",title);
			return "client/products";
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
	

