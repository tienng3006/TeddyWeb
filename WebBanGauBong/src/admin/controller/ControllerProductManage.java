package admin.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import web.entity.Category;
import web.entity.Order;
import web.entity.Product;
import web.entity.User;

@Controller
@Transactional
@RequestMapping("/admin/product/")
public class ControllerProductManage {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String viewProductList(ModelMap model) {
		model.addAttribute("listProduct", getListNav(0, 10));
		model.addAttribute("totalItem", totalItem() / 10);
		return "admin/productList";
	}

	@RequestMapping(value = "list/{page}", method = RequestMethod.GET)
	public String viewProductListByPage(ModelMap model, HttpSession session, @PathVariable("page") int page) {
		model.addAttribute("listProduct", getListNav((page - 1) * 10, 10));
		model.addAttribute("totalItem", totalItem() / 10);
		return "admin/productList";
	}

	//hàm thêm sản phẩm
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String addProduct(ModelMap model, HttpSession session) {
		model.addAttribute("product", new Product());
		model.addAttribute("listCategory", listCate());
		return "admin/product_formAdd";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addProduct(ModelMap model,@RequestParam("attachment") MultipartFile photo,@ModelAttribute("product") Product product
			) throws IllegalStateException, IOException {
		product.setCreated(new Date());
		try {
			String path = context.getRealPath("/resources/client/images/" + photo.getOriginalFilename());
			photo.transferTo(new File(path));
		}
		catch (Exception ex) {
			
		}
		
		Session session1 = factory.openSession();
		Transaction t = session1.beginTransaction();
		try {
			product.setPhoto(photo.getOriginalFilename());
			session1.save(product);
			t.commit();
			
			model.addAttribute("successAdd", "Tạo thành công.");
			model.addAttribute("product", new Product());
			model.addAttribute("listCategory", listCate());
			
		} catch (Exception e) {
			e.printStackTrace();
			// return "admin/product_formAdd";
			t.rollback();
			model.addAttribute("failAdd", "Tạo thất bại");
			
		}finally {
			session1.close();
		}
		return "admin/product_formAdd";

	}
	
	@RequestMapping(value = "edit/{productId}", method = RequestMethod.GET)
	public String editPro(ModelMap model, @PathVariable("productId") int productId) {
		Product p;
		p = findById(productId);
//		Product pp = new Product();
//		pp.setId(p.getId());
		model.addAttribute("p", p);
		model.addAttribute("productId", productId);
		model.addAttribute("product", new Product());
		model.addAttribute("listCategory", listCate());
		return "admin/product_formEdit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editPro(ModelMap model, @ModelAttribute("product") Product product,
			@RequestParam("attachment") MultipartFile photo) {

		if (photo.isEmpty()) {
			model.addAttribute("msgEmpty", "Vui lòng chọn File !");
			model.addAttribute("listProduct", getListNav(0, 10));
			model.addAttribute("totalItem", totalItem() / 10);
		
			return "admin/productList";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			product.setPhoto(photo.getOriginalFilename());
			product.setCreated(new Date());
			session.update(product);
			model.addAttribute("successEdit", "Chỉnh sửa thành công!");
			
			t.commit();

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			//model.addAttribute("failEdit", "Chỉnh sửa thất bại! Vui lòng kiểm tra lại thông tin!");
			//model.addAttribute("product", new Product());
			//model.addAttribute("listCategory", listCate());
			model.addAttribute("listProduct", getListNav(0, 10));
			model.addAttribute("totalItem", totalItem() / 10);
			return "admin/productList";
		} finally {
			session.close();
		}

		try {
			String path = context.getRealPath("/resources/client/images/" + photo.getOriginalFilename());
			photo.transferTo(new File(path));
		} catch (Exception e) {
			model.addAttribute("failFile", "Lỗi upload file ảnh!");
			model.addAttribute("listProduct", getListNav(0, 10));
			model.addAttribute("totalItem", totalItem() / 10);
			return "admin/productList";
		}

		//model.addAttribute("product", new Product());
		model.addAttribute("listProduct", getListNav(0, 10));
		model.addAttribute("totalItem", totalItem() / 10);
		//model.addAttribute("listCategory", listCate());
		return "admin/productList";
	}

	@RequestMapping("remove/{productId}")
	public String removePro(ModelMap model, @PathVariable("productId") int productId) {
		Product pp = new Product();
		Product p = findById(productId);
		if (p != null) {

			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				pp.setId(productId);
				session.delete(pp);
				model.addAttribute("successRemove", "Đã xóa thành công!");
				t.commit();

			} catch (Exception e) {
				// TODO: handle exception
				model.addAttribute("failRemovee", "Xóa thất bại!");
				t.rollback();

			} finally {
				session.close();
			}

		} else {
			model.addAttribute("failRemove", "Sản phẩm này không tồn tại! :(");
			return "admin/productList";
		}

		model.addAttribute("listProduct", getListNav(0, 10));
		model.addAttribute("totalItem", totalItem() / 10);
		return "admin/productList";
	}

	public void deletePro(Product p) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {

			session.delete(p);
			t.commit();

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();

		} finally {
			session.close();
		}
	}

	public Product findById(int productId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product p WHERE p.id LIKE :id ";
		Query query = session.createQuery(hql);
		query.setParameter("id", productId);
		Product obj = (Product) query.uniqueResult();
		return obj;
	}

	public boolean createPro(Product product) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			product.setCreated(new Date());
			session.save(product);
			t.commit();

		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			return false;
		} finally {
			session.close();
		}

		return true;
	}

	public List<Category> listCate() {

		Session sessionn = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = sessionn.createQuery(hql);
		List<Category> listCates = query.list();
		return listCates;
	}

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

	@ModelAttribute("listCategory")
	public List<Category> getListCate() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}

}
