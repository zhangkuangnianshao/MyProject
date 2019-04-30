package cn.itcast.demo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.SourceType;
import org.junit.Test;

import cn.itcast.manyToMany.StudentModel;
import cn.itcast.manyToMany.TeacherModel;
import cn.itcast.utils.HibernateUtils;

public class SecondDemo02 {

	@Test
	public void test01(){
		 //??????????????
		 Session session1 = HibernateUtils.openSession();
		 TeacherModel tm = (TeacherModel) session1.get(TeacherModel.class, 1);
		 
		 session1.close();
		 System.out.println("----------------------");
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm2 = (TeacherModel) session2.get(TeacherModel.class, 1);
		 
		 session2.close();
		
	}
	
	@Test
	public void test02(){
		 //????????????????????????  hql??sql :Ч?????    ????? ?????????????????????????????????????????ó?????? hibernate??????sql
		
		 Session session1 = HibernateUtils.openSession();
		 List list = session1.createQuery("from TeacherModel").list();
		 
		 session1.close();
		 System.out.println("----------------------");
		 Session session2 = HibernateUtils.openSession();
		/* TeacherModel tm2 = (TeacherModel) session2.get(TeacherModel.class, 1);*/
		 List list2 = session2.createQuery("from TeacherModel").list();
		 
		 session2.close();
		
	}
	
	@Test
	public void test03(){
		 // save ??update
		
		 Session session1 = HibernateUtils.openSession();
		 TeacherModel tm = new TeacherModel();
		 tm.setName("???????");
		 Serializable id = session1.save(tm);
		 session1.close();
		 
		 System.out.println("-----------------------");
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm2 = (TeacherModel)session2.get(TeacherModel.class, id);
		 
		 
		 session2.close();
		
	}
	
	@Test
	public void test04(){
		 // save ??update
		
		 Session session1 = HibernateUtils.openSession();
		 Session session2 = HibernateUtils.openSession();
		 Transaction beginTransaction = session1.beginTransaction();
	
		 TeacherModel tm = new TeacherModel();
		 tm.setName("????12???");
		 tm.setId(1);
		 session1.update(tm);
		 beginTransaction.commit();
		 session1.close();
		 
		 System.out.println("-----------------------");
		 
		
		 TeacherModel tm2 = (TeacherModel)session2.get(TeacherModel.class, 1);
		 
		
		 session2.close();
		
	}
	
	
	@Test
	public void test05(){
		 // ???????????????
		
		 Session session1 = HibernateUtils.openSession();
		 
		 Transaction beginTransaction = session1.beginTransaction();
	
		 TeacherModel tm1 =   (TeacherModel)session1.get(TeacherModel.class, 1);
		 tm1.setName("????1");
		 
		 
		 beginTransaction.commit();
		 session1.close();
		 
		 System.out.println("-----------------------");
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm2 = (TeacherModel)session2.get(TeacherModel.class, 1);
		
		 session2.close();
		
	}
	
	@Test
	public void test06(){
		 // ???????????????
		
		 Session session1 = HibernateUtils.openSession();
		 
	
		 TeacherModel tm1 =   (TeacherModel)session1.get(TeacherModel.class, 1);
		 Set<StudentModel> sms = tm1.getSms();
		 System.out.println(sms);
	     
		 System.out.println("---------------------");
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm2 =   (TeacherModel)session2.get(TeacherModel.class, 1);
		 Set<StudentModel> sms2 = tm2.getSms();
		 System.out.println(sms2);
		
		 session2.close();
		
	}
	
	
	@Test
	public void test07(){
		 // ???????????????
		
		 Session session1 = HibernateUtils.openSession();
		 
		 TeacherModel tm1 =   (TeacherModel)session1.get(TeacherModel.class, 1);
		
		 
		 
		 Query query = session1.createQuery("update TeacherModel set name = 'xc' where id =  1");
	     query.executeUpdate();
		 // ??hibernate?????????  ???sql???????????????(???????sql)
		 
		 System.out.println("-----------------------------");
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm2 =   (TeacherModel)session2.get(TeacherModel.class, 1);
		
		 session2.close();
		
	}
	
	
	@Test
	public void test08(){
		 // ???????????????
		
		 Session session1 = HibernateUtils.openSession();
		 
		 TeacherModel tm1 =   (TeacherModel)session1.get(TeacherModel.class, 1);
		
		 
		 
		 Query query = session1.createQuery("from      TeacherModel");
		 query.setCacheable(true);
		 List list = query.list();
		 System.out.println(list);
		 // ??hibernate?????????  ???sql???????????????(???????sql)
		 
		 System.out.println("-----------------------------");
		 
		 Session session2 = HibernateUtils.openSession();
		 Query query2 = session2.createQuery("from TeacherModel");
		 query2.setCacheable(true);
		 List list2 = query2.list();
		 System.out.println(list2);
		 
		 session2.close();
		
	}
}
