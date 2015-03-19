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

import model.TGong;
import db.DB;

public class gong_servlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
        if(type.endsWith("gongUpd"))
        {
        	gongUpd(req, res);
        }
        if(type.endsWith("gongDel"))
        {
        	gongDel(req, res);
        }
        if(type.endsWith("gongAdd"))
        {
        	gongAdd(req, res);
        }
        if(type.endsWith("gongMana"))
        {
        	gongMana(req, res);
        }
		if(type.endsWith("seleGong"))
		{
			seleGong(req, res);
		}
	}
	public void gongDel(HttpServletRequest req,HttpServletResponse res)
	{
		long id = Long.parseLong(req.getParameter("id"));
		String del = "yes";
		String sql = "update t_gong set del = ? where id=?";
	
		Object[] params={del,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "供应商信息删除成功!");
		req.setAttribute("path", "gong?type=gongMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);	
	}
	public void gongUpd(HttpServletRequest req,HttpServletResponse res)
	{
		long id = Long.parseLong(req.getParameter("id"));
		String bianhao = req.getParameter("bianhao");
		String mingcheng = req.getParameter("mingcheng");
		String dizhi = req.getParameter("dizhi");
		String lianxiren = req.getParameter("lianxiren");
		String dianhua = req.getParameter("dianhua");
		String email = req.getParameter("email");
		String beizhu = req.getParameter("beizhu");
		
		String sql = "update t_gong set bianhao=?,mingcheng=?,dizhi=?,lianxiren=?,dianhua=?,email=?,beizhu=? where id=?";
		
		Object[] params={bianhao,mingcheng,dizhi,lianxiren,dianhua,email,beizhu,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "供应商信息修改成功!");
		req.setAttribute("path", "gong?type=gongMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);		
	}
	public void gongAdd(HttpServletRequest req,HttpServletResponse res)
	{
		long id = CmUtil.getPkId();
		String bianhao = req.getParameter("bianhao");
		String mingcheng = req.getParameter("mingcheng");
		String dizhi = req.getParameter("dizhi");
		String lianxiren = req.getParameter("lianxiren");
		String dianhua = req.getParameter("dianhua");
		String email = req.getParameter("email");
		String beizhu = req.getParameter("beizhu");
		String del = "no";
		
		String sql = "insert into t_gong values (?,?,?,?,?,?,?,?,?)";
		
		Object[] params={id,bianhao,mingcheng,dizhi,lianxiren,dianhua,email,beizhu,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "供应商信息添加成功!");
		req.setAttribute("path", "gong?type=gongMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	public void gongMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List gongList = new ArrayList();
		
		String sql = "select * from t_gong where del='no'";
		
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TGong gong = new TGong();
				
				gong.setId(rs.getLong("id"));
				gong.setBianhao(rs.getString("bianhao"));
				gong.setMingcheng(rs.getString("mingcheng"));
				gong.setDizhi(rs.getString("dizhi"));
				gong.setLianxiren(rs.getString("lianxiren"));
				gong.setDianhua(rs.getString("dianhua"));
				gong.setEmail(rs.getString("email"));
				gong.setBeizhu(rs.getString("beizhu"));
				
				gongList.add(gong);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongList", gongList);
		req.getRequestDispatcher("admin/gong/gongMana.jsp").forward(req, res);		
	}
	
	public void seleGong(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List gongList = new ArrayList();
		
		String sql = "select * from t_gong where del='no'";
		
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TGong gong = new TGong();
				
				gong.setId(rs.getLong("id"));
				gong.setBianhao(rs.getString("bianhao"));
				gong.setMingcheng(rs.getString("mingcheng"));
				gong.setDizhi(rs.getString("dizhi"));
				gong.setLianxiren(rs.getString("lianxiren"));
				gong.setDianhua(rs.getString("dianhua"));
				gong.setEmail(rs.getString("email"));
				gong.setBeizhu(rs.getString("beizhu"));
				
				gongList.add(gong);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongList", gongList);
		req.getRequestDispatcher("admin/caigou/gongSele.jsp").forward(req, res);	
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
