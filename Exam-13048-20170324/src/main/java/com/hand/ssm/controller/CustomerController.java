package com.hand.ssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hand.ssm.dto.Address;
import com.hand.ssm.dto.Customer;
import com.hand.ssm.dto.Page;
import com.hand.ssm.dto.PageResult;
import com.hand.ssm.service.CustomerService;


@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService custService;
	
	@RequestMapping("/selectByPage")
    public String selectByPage(HttpServletRequest req,Page page){
		/*Page page = new Page();*/
		page.setEveryPage(15);
		int currentPage;
		if (req.getParameter("currentPage") == null){
			currentPage = 1;
		}else{
			System.out.println(">>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<"+req.getParameter("currentPage"));
			currentPage = Integer.valueOf(req.getParameter("currentPage"));
		}
		page.setCurrentPage(currentPage);
		PageResult pg = custService.selectByPage(page);
		
		page = pg.getPage();
		List<Customer> list1 = (ArrayList<Customer>)pg.getList();
		req.setAttribute("total", page.getTotalPage());
		req.setAttribute("page", page);
		req.setAttribute("list", list1);
		return "show";
    }
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest req,Customer cust ,Map<String, Object> map){
			
		String firstName = req.getParameter("user");
		String password = req.getParameter("pswd");
		List<Customer> list = custService.login(firstName);
		
		if (list.size() != 0 && firstName.equals(password)){
			//地址下拉框
			List<Address> site = custService.site();
			req.getSession().setAttribute("site", site);
			
			//过滤登录
			req.getSession().setAttribute("logon", "loginSuccess");
			
	        //用户名和密码等于数据库firstName，则登录成功
			/*map.put("list", custService.select(cust));*/
			
			
			//获取登录用户名
			req.getSession().setAttribute("success", firstName);
	        /*return "show";*/
			return "redirect:/selectByPage";
	    }
	        return "login";
    }
	
	@RequestMapping("/quit")
    public String quit(HttpServletRequest req){
		
		if(req.getSession() == null){  
			return "login";  
        }  
          
		req.getSession().removeAttribute("success");  
        return "login";
    }
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req,Page page,Map<String, Object> map){
		Customer cust=new Customer();
		int id=Integer.parseInt(req.getParameter("id"));
		custService.delete(id);
		/*map.put("list", custService.select(cust));
		return "show";*/
		return "redirect:/selectByPage";
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest req,Customer cust,Map<String, Object> map){
		List<Address> site = custService.site();
		req.setAttribute("site", site);
		
		cust.setFirstName(req.getParameter("fName"));
		cust.setLastName(req.getParameter("lName"));
		cust.setEmail(req.getParameter("email")); 
		cust.setAddressId(Integer.parseInt(req.getParameter("addressId")));
		custService.insert(cust);
		/*map.put("list", custService.select(cust));
		return "show";*/
		return "redirect:/selectByPage";
	}
	
	@RequestMapping("/edit")
	public String edit(HttpServletRequest req,Map<String, Object> map){
		List<Address> site = custService.site();
		req.setAttribute("site", site);
		
		Customer cust=new Customer();
		int id=Integer.parseInt(req.getParameter("id"));
		cust = custService.edit(id);
		req.setAttribute("edit", cust);
		return "update";
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest req,Customer cust,Map<String, Object> map){
		cust.setCustomerId(Integer.parseInt(req.getParameter("id")));
		cust.setFirstName(req.getParameter("fName"));
		cust.setLastName(req.getParameter("lName"));
		cust.setEmail(req.getParameter("email")); 
		cust.setAddressId(Integer.parseInt(req.getParameter("addressId")));
		custService.update(cust);
		/*map.put("list", custService.select(cust));
		return "show";*/
		return "redirect:/selectByPage";
	}
}
