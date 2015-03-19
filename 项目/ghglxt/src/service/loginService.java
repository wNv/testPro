package service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import model.TAdmin;
import model.TCaozuoyuan;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import db.DB;

public class loginService
{
	public String login(String userName,String userPw,int userType)
	{
		String result="no";
		
		if(userType==0)//ÏµÍ³¹ÜÀíÔ±µÇÂ½
		{
			String sql="select * from t_admin where userName=? and userPw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 TAdmin admin=new TAdmin();
					 admin.setUserId(rs.getInt("userId"));
					 admin.setUserName(rs.getString("userName"));
					 admin.setUserPw(rs.getString("userPw"));
					 WebContext ctx = WebContextFactory.get(); 
					 HttpSession session=ctx.getSession(); 
					 session.setAttribute("userType", 0);
		             session.setAttribute("admin", admin);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
			
		}
		
		if(userType==1)
		{
			String sql="select * from t_caozuoyuan where userName=? and userPw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try 
			{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false)
				{
					 result="no";
				}
				else
				{
					 result="yes";
					 
					 TCaozuoyuan caozuoyuan = new TCaozuoyuan();
					 
					 caozuoyuan.setUserId(rs.getInt("userId"));
					 caozuoyuan.setUserName(rs.getString("userName"));
					 caozuoyuan.setUserPw(rs.getString("userPw"));
					 caozuoyuan.setXingming(rs.getString("xingming"));
					 
					 WebContext ctx = WebContextFactory.get(); 
					 HttpSession session=ctx.getSession(); 
					 session.setAttribute("userType", 1);
		             session.setAttribute("caozuoyuan", caozuoyuan);
				}
				rs.close();
			} 
			catch (SQLException e)
			{
				System.out.println("µÇÂ¼Ê§°Ü£¡");
				e.printStackTrace();
			}
			finally
			{
				mydb.closed();
			}
			
		}
		return result;
	}

	public String adminPwEdit(String userPwNew)
	{
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		TAdmin admin=(TAdmin)session.getAttribute("admin");
		
		String sql="update t_admin set userPw=? where userId=?";
		Object[] params={userPwNew,admin.getUserId()};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		return "yes";
	}
	
    public String yonghuPwEdit(String userPwNew)
    {
		WebContext ctx = WebContextFactory.get(); 
		HttpSession session=ctx.getSession(); 
		TCaozuoyuan caozuoyuan = (TCaozuoyuan)session.getAttribute("caozuoyuan");
		
		String sql="update t_caozuoyuan set userPw=? where userId=?";
		Object[] params={userPwNew,caozuoyuan.getUserId()};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		
		return "yes";
    }
    
    public String yonghuCheck(String loginName,String loginPass){
    	String result = "no";
    	String sql = "select 1 from t_yonghu where loginName = ? and loginPass = ?";
    	Object[] params={loginName,loginPass};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		try 
		{
			ResultSet rs=mydb.getRs();
			
			if(rs.next())
			{
				result="yes";
			}
			rs.close();
		} 
		catch (SQLException e)
		{
			System.out.println("µÇÂ¼Ê§°Ü£¡");
			e.printStackTrace();
		}
		finally
		{
			mydb.closed();
		}
    	return result;
    }
}
