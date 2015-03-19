package servlet;

import gongong.CmUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TGoods;
import db.DB;

public class goods_servlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		if(type.endsWith("toAddGoods"))
		{
			toAddGoods(req, res);
		}
		if(type.endsWith("toUpdGoods"))
		{
			toUpdGoods(req, res);
		}
        if(type.endsWith("goodsUpd"))
        {
        	goodsUpd(req, res);
        }
        if(type.endsWith("goodsDel"))
        {
        	goodsDel(req, res);
        }
        if(type.endsWith("goodsAdd"))
        {
        	goodsAdd(req, res);
        }
		if(type.endsWith("goodsMana"))
		{
			goodsziMana(req, res);
		}
	}
	public void goodsDel(HttpServletRequest req,HttpServletResponse res)
	{
		long id = Long.parseLong(req.getParameter("id"));
		String del = "yes";
		String sql = "update t_goods set del = ? where id=?";
	
		Object[] params={del,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "商品信息删除成功!");
		req.setAttribute("path", "goods?type=goodsMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);	
	}
	public void goodsUpd(HttpServletRequest req,HttpServletResponse res)
	{
		long id = Long.parseLong(req.getParameter("id"));
		String bianhao = req.getParameter("bianhao");
		String mingcheng = req.getParameter("mingcheng");
		String leibie = req.getParameter("leibie");
		String pinpai = req.getParameter("pinpai");
		String xinghao = req.getParameter("xinghao");
		String guige = req.getParameter("guige");
		String beizhu = req.getParameter("beizhu");
			
		String sql = "update t_goods set bianhao=?,mingcheng=?,leibie=?,pinpai=?,xinghao=?,guige=?,beizhu=? where id=?";
		
		Object[] params={bianhao,mingcheng,leibie,pinpai,xinghao,guige,beizhu,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "商品信息修改成功!");
		req.setAttribute("path", "goods?type=goodsMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);		
	}
	public void goodsAdd(HttpServletRequest req,HttpServletResponse res)
	{
		long id = CmUtil.getPkId();
		String bianhao = req.getParameter("bianhao");
		String mingcheng = req.getParameter("mingcheng");
		String leibie = req.getParameter("leibie");
		String pinpai = req.getParameter("pinpai");
		String xinghao = req.getParameter("xinghao");
		String guige = req.getParameter("guige");
		String beizhu = req.getParameter("beizhu");
		String del = "no";
		
		String sql = "insert into t_goods values (?,?,?,?,?,?,?,?,?)";
		
		Object[] params={id,bianhao,mingcheng,leibie,pinpai,xinghao,guige,beizhu,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "商品信息添加成功!");
		req.setAttribute("path", "goods?type=goodsMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void toUpdGoods(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		TGoods goods = new TGoods();
		
		long id = Long.parseLong(req.getParameter("id"));
		String sql = "select * from t_goods where id = ?";
		
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				goods.setId(rs.getLong("id"));
				goods.setBianhao(rs.getString("bianhao"));
				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setLeibie(rs.getString("leibie"));
				goods.setPinpai(rs.getString("pinpai"));
				goods.setXinghao(rs.getString("xinghao"));
				goods.setGuige(rs.getString("guige"));
				goods.setBeizhu(rs.getString("beizhu"));
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goods", goods);
		req.setAttribute("lxList", getLxList());
		req.getRequestDispatcher("admin/goods/goodsEditPre.jsp").forward(req, res);
	}
	public void toAddGoods(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		req.setAttribute("lxList", getLxList());
		req.getRequestDispatcher("admin/goods/goodsAdd.jsp").forward(req, res);		
	}
	public void goodsziMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List goodsList = new ArrayList();
		
		String sql = "select * from t_goods where del='no'";
		
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TGoods goods = new TGoods();
				
				goods.setId(rs.getLong("id"));
				goods.setBianhao(rs.getString("bianhao"));
				goods.setMingcheng(rs.getString("mingcheng"));
				goods.setLeibie(rs.getString("leibie"));
				goods.setPinpai(rs.getString("pinpai"));
				goods.setXinghao(rs.getString("xinghao"));
				goods.setGuige(rs.getString("guige"));
				goods.setBeizhu(rs.getString("beizhu"));
				
				goodsList.add(goods);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("goodsList", goodsList);
		req.getRequestDispatcher("admin/goods/goodsMana.jsp").forward(req, res);		
	}
	
	//获取设备类型列表
	private List getLxList()
	{
		List result = new ArrayList();
		
		result.add("打印机");
		result.add("笔记本");
		result.add("复印机");
		
		return result;
	}	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
}
