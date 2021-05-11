package client.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;

import web.entity.*;

@Transactional
@Controller
@RequestMapping(value = "/client/cart")
public class cartController {
	@Autowired
	SessionFactory factory;

	@RequestMapping()
	public String cart(ModelMap model, HttpSession session, HttpServletRequest rq) {
		HashMap<Integer, Cart> cartItems = new HashMap<>();
		cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			model.addAttribute("msgCart", "Giỏ hàng đang rỗng!");
			cartItems = new HashMap<>();
		}

		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "client/cart";
	}

	@RequestMapping(value = "/add/{productId}", method = RequestMethod.GET)
	public String viewAdd(ModelMap model, HttpSession session, @PathVariable("productId") int productId) {
		HashMap<Integer, Cart> cartItems = new HashMap<>();
		cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}

		Product product = findById(productId);
		if (product != null) {
			if (cartItems.containsKey(productId)) {
				Cart item = cartItems.get(productId);
				item.setProduct(product);
				item.setQuantity(item.getQuantity() + 1);
				cartItems.put(productId, item);
			} else {
				Cart item = new Cart();
				item.setProduct(product);
				item.setQuantity(1);
				cartItems.put(productId, item);
			}
		}

		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "client/cart";
	}

	@RequestMapping(value = "/addQ/{productId}", method = RequestMethod.POST)
	public String viewAddQ(ModelMap model, HttpSession session, @PathVariable("productId") int productId,
			@RequestParam(value = "quant", defaultValue = "0", required = true) Integer quant) {
		HashMap<Integer, Cart> cartItems = new HashMap<>();
		cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		if (quant == 0) {
			Product product = findById(productId);
			if (product != null) {
				if (cartItems.containsKey(productId)) {
					Cart item = cartItems.get(productId);
					item.setProduct(product);
					item.setQuantity(item.getQuantity() + quant);
					cartItems.put(productId, item);
				} else {
					Cart item = new Cart();
					item.setProduct(product);
					item.setQuantity(quant);
					cartItems.put(productId, item);
				}
			}
		} else {
			Product product = findById(productId);
			if (product != null) {
				if (cartItems.containsKey(productId)) {
					Cart item = cartItems.get(productId);
					item.setProduct(product);
					item.setQuantity(item.getQuantity() + quant);
					cartItems.put(productId, item);
				} else {
					Cart item = new Cart();
					item.setProduct(product);
					item.setQuantity(quant);
					cartItems.put(productId, item);
				}
			}
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "client/cart";
	}

	public Product findById(int productId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.id LIKE :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", productId);
		Product obj = (Product) query.uniqueResult();
		return obj;
	}

	@RequestMapping(value = "/sub/{productId}", method = RequestMethod.GET)
	public String viewUpdate(ModelMap mm, HttpSession session, @PathVariable("productId") int productId) {
		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
		if (cartItems.isEmpty()) {
			cartItems = new HashMap<>();
		}
		session.setAttribute("myCartItems", cartItems);
		return "client/cart";
	}

	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.GET)
	public String viewRemove(ModelMap model, HttpSession session, @PathVariable("productId") int productId) {
		HashMap<Integer, Cart> cartItems = (HashMap<Integer, Cart>) session.getAttribute("myCartItems");
		if (cartItems == null) {
			cartItems = new HashMap<>();
		}
		if (cartItems.containsKey(productId)) {
			cartItems.remove(productId);
		}
		session.setAttribute("myCartItems", cartItems);
		session.setAttribute("myCartTotal", totalPrice(cartItems));
		session.setAttribute("myCartNum", cartItems.size());
		return "redirect:/client/cart.htm";
	}

	public float totalPrice(HashMap<Integer, Cart> cartItems) {
		float count = 0;
		for (Map.Entry<Integer, Cart> list : cartItems.entrySet()) {
			count += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
		}
		return count;
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