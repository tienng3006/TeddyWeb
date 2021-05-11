package admin.controller;

import java.util.List;

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
import javax.servlet.http.HttpSession;

@Controller
@Transactional
public class Welcome {

	@Autowired
	SessionFactory factory;

	@RequestMapping("admin/quan-ly")
	public String manage(ModelMap model) {
		model.addAttribute("proTotal", totalItem());
		model.addAttribute("userTotal", totalUser());
		model.addAttribute("orTotal", totalOrder());
		hotPro(model);
		return "admin/index";
	}

	public int totalItem() {
		// TODO Auto-generated method stub
		Session sessionn = factory.getCurrentSession();
		String hql = "SELECT COUNT(p) FROM Product p";
		Query query = sessionn.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}

	public int totalUser() {
		// TODO Auto-generated method stub
		Session sessionn = factory.getCurrentSession();
		String hql = "SELECT COUNT(u) FROM User u WHERE u.userRole LIKE 'client' ";
		Query query = sessionn.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}

	public int totalOrder() {
		// TODO Auto-generated method stub
		Session sessionn = factory.getCurrentSession();
		String hql = "SELECT COUNT(o) FROM Order o WHERE o.status.id = 1 ";
		Query query = sessionn.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}

	public void hotPro(ModelMap model) {
		// TODO Auto-generated method stub
		Session sessionn = factory.getCurrentSession();
		String hql = "SELECT d.product.name,d.product.category.name,d.product.price, SUM(d.quantity) FROM OrderDetail d WHERE d.order.status.id !=0 GROUP BY d.product.name,d.product.category.name,d.product.price ORDER BY SUM(d.quantity) DESC ";
		Query query = sessionn.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(4);
		List<Object[]> list=query.list();
		model.addAttribute("top4", list);
		//Long count = (Long) query.uniqueResult();
		//return count.intValue();
	}

}
