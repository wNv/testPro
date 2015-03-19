package servlet;

import gongong.CmUtil;
import gongong.DateUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TCaigou;
import model.TShenqing;
import db.DB;

public class caigou_servlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
        if(type.endsWith("sqList"))
        {
        	sqList(req, res);
        }
        if(type.endsWith("toAddCg"))
        {
        	toAddCg(req, res);
        }
        if(type.endsWith("cgAdd"))
        {
        	cgAdd(req, res);
        }
        if(type.endsWith("caigouMana"))
        {
        	caigouMana(req, res);
        }
	}
	
	public void cgAdd(HttpServletRequest req,HttpServletResponse res)
	{
		long id = CmUtil.getPkId();
		long goodsId = Long.parseLong(req.getParameter("goodsId"));
		long shenqingId = Long.parseLong(req.getParameter("shenqingId"));
		long gongId = Long.parseLong(req.getParameter("gongId"));
		String caigoushijian = req.getParameter("caigoushijian");
		String shuliang = req.getParameter("shuliang");
		String caigoujia = req.getParameter("caigoujia");
		String beizhu = req.getParameter("beizhu");
		
		String sql = "insert into t_caigou values (?,?,?,?,?,?,?,?)";
		
		Object[] params={id,goodsId,gongId,caigoushijian,shuliang,caigoujia,beizhu,shenqingId};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "采购信息添加成功!");
		req.setAttribute("path", "caigou?type=caigouMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	public void toAddCg(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		TShenqing shenqing = new TShenqing();
		
		long sqid = Long.parseLong(req.getParameter("sqid"));
		String sql = "select ta.*,tb.mingcheng,tb.leibie from t_shenqing ta ,t_goods tb where ta.goods_id = tb.id and ta.id=?";
		
		Object[] params={sqid};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				shenqing.setId(rs.getLong("id"));
				shenqing.setGoodsId(rs.getLong("goods_id"));
				shenqing.setSpmc(rs.getString("mingcheng"));
				shenqing.setSplb(rs.getString("leibie"));
				shenqing.setShuliang(rs.getString("shuliang"));
				shenqing.setShenqingshijian(rs.getString("shenqingshijian"));
				shenqing.setBeizhu(rs.getString("beizhu"));
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();

		GregorianCalendar gc = new GregorianCalendar();
		String riqi = DateUtils.formatDate2Str(gc.getTime(), "yyyy-MM-dd");
		
		req.setAttribute("riqi", riqi);
		req.setAttribute("shenqing", shenqing);
		req.getRequestDispatcher("admin/caigou/caigouAdd.jsp").forward(req, res);			
	}
	public void sqList(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List sqList = new ArrayList();
		
		String sql = "select ta.*,tb.mingcheng,tb.leibie,isnull(tc.id,0)sfcg from t_shenqing ta " +
					 "left join t_goods tb on ta.goods_id = tb.id " +
					 "left join t_caigou tc on ta.id=tc.shenqing_id where ta.zhuangtai=1";
		
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TShenqing shenqing = new TShenqing();
				
				shenqing.setId(rs.getLong("id"));
				shenqing.setSpmc(rs.getString("mingcheng"));
				shenqing.setSplb(rs.getString("leibie"));
				shenqing.setShuliang(rs.getString("shuliang"));
				shenqing.setShenqingshijian(rs.getString("shenqingshijian"));
				shenqing.setBeizhu(rs.getString("beizhu"));
				shenqing.setSfcg(rs.getLong("sfcg"));
				
				sqList.add(shenqing);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();

		req.setAttribute("sqList", sqList);
		req.getRequestDispatcher("admin/caigou/sqList.jsp").forward(req, res);	
	}
	
	public void caigouMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List cgList = new ArrayList();
		String sql = "select ta.*,tb.mingcheng ghs,tc.mingcheng spmc,tc.leibie " +
					 "from t_caigou ta,t_gong tb,t_goods tc where ta.gong_id = tb.id and ta.goods_id = tc.id";
		
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				TCaigou caigou = new TCaigou();
				
				caigou.setId(rs.getLong("id"));
				caigou.setSpmc(rs.getString("spmc"));
				caigou.setSplb(rs.getString("leibie"));
				caigou.setGhsmc(rs.getString("ghs"));
				caigou.setCaigoushijian(rs.getString("caigoushijian"));
				caigou.setShuliang(rs.getString("shuliang"));
				caigou.setCaigoujia(rs.getString("caigoujia"));
				caigou.setBeizhu(rs.getString("beizhu"));
				
				cgList.add(caigou);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("cgList", cgList);
		req.getRequestDispatcher("admin/caigou/caigouMana.jsp").forward(req, res);			
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
