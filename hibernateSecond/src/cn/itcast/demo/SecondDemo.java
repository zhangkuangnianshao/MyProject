package cn.itcast.demo;

import org.hibernate.Session;
import org.junit.Test;


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
public class SecondDemo {
	
	 @Test
	 public void test01(){
		 /*
		  * �������裺 1.��session1 ��������
		  * 		2.��session2  ��session2�л�ȡ����
		  * 		3.����ܻ�õ���֤��������ݴӶ��������л�ȡ��
		  * */
		 Session session1 = HibernateUtils.openSession();
		 TeacherModel tm1 = (TeacherModel) session1.get(TeacherModel.class,1);
		 System.out.println(tm1);
		 session1.close();
		 System.out.println("---------------------");
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm2 = (TeacherModel) session2.get(TeacherModel.class,1);
		 System.out.println(tm2);
		 session2.close();
		 
	 }
	 
	 @Test
	 public void test02(){
		 /*
		  * �������裺  ʲô���Ĳ�����Ӱ���������
		  * 	    GET/LOAD  HQL  SQL QBC
		  * 1.ͨ��hql ��ѯ����   
		  * 2.�ر�session ���ٴ�session�����»������
		  * 
		  * ֤��hql ��Ӱ���������
		  * hibernate �����߱�����sql������(hibernate ֻ����ƴװ  )-->ֻ�����ݿ����������
		  * 
		  * hibernate ֻ��ͨ��oid�ķ�ʽ�����л����ѯ
		  * */
		 Session session1 = HibernateUtils.openSession();
		/* List list  = session1.createQuery("from TeacherModel").list();*/
		 List list = session1.createCriteria(TeacherModel.class).list();
		 System.out.println(list);
		 System.out.println("------------");
		 session1.close();
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm = (TeacherModel) session2.get(TeacherModel.class, 1);
		// List list2  = session2.createQuery("from TeacherModel").list();
		 
		 session2.close();
	 }
	 
	 @Test
	 public void test03(){
		 /*
		  * ���� �� ��Ӻ��޸ķ����Ƿ��Ӱ���������
		  * 1.����session1 ���һ������,�ر�session1
		  * 2.��session2,��session2�в�ѯ���ݣ����û�з���sql֤��������Դ�ڶ�������
		  * 								���������sql��֤����Ӳ�����Ӱ���������
		  * */
		 Session session1 = HibernateUtils.openSession();
		 TeacherModel tm = new TeacherModel();
		 tm.setName("��ʦ3");
		 Serializable id = session1.save(tm);
		 
		 System.out.println("------------");
		 session1.close();
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm2 = (TeacherModel) session2.get(TeacherModel.class, id);
		 
		 session2.close();
	 }
	 
	 
	 @Test
	 public void test04(){
		 /*
		  * ���� ���޸ķ����Ƿ��Ӱ���������
		  * 1.����session1 ���һ������,�ر�session1
		  * 2.��session2,��session2�в�ѯ���ݣ����û�з���sql֤��������Դ�ڶ�������
		  * 								���������sql��֤���޸Ĳ�����Ӱ���������
		  * */
		 Session session1 = HibernateUtils.openSession();
		 Transaction transaction = session1.beginTransaction();
		 TeacherModel tm = new TeacherModel();
		 tm.setName("Ф��ʦ");
		 tm.setId(2);
		 session1.update(tm);
		 transaction.commit();
		 session1.close();
		 System.out.println("------------");
		
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm2 = (TeacherModel) session2.get(TeacherModel.class, 3);
		 
		 session2.close();
	 }
	 
	 
	 @Test
	 public void test05(){
		 /*
		  * ���� �������Ƿ��Ӱ���������
		  * 1.����session1 ���һ������,�ر�session1
		  * 2.��session2,��session2�в�ѯ���ݣ����û�з���sql֤��������Դ�ڶ�������
		  * 								���������sql��֤�����ղ�����Ӱ���������
		  * ������Ӱ���������
		  * 		������������ԭ���Ƿ������ݣ����ԭ���Ķ��������������ݣ���ô�޸Ĳ�����˳���ŰѶ��������е����ݽ����޸�
		  * 							  �������������û�����ݣ���ô��������Ӱ���������
		  * */
		 Session session1 = HibernateUtils.openSession();
		 Transaction transaction = session1.beginTransaction();
		 TeacherModel tm2 = (TeacherModel) session1.get(TeacherModel.class, 2);
		 tm2.setName("����ʦ");
		 
		 transaction.commit();
		 session1.close();
		 System.out.println("---------------------------");
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel tm3 = (TeacherModel) session2.get(TeacherModel.class, 2);
		 
		 session2.close();
	 }
	 
	 @Test
	 public void test06(){
		 /*
		  * ��������󻺴����ͼ��ϻ�����
		  * 
		  **/
		 Session session1 = HibernateUtils.openSession();
		 TeacherModel  tm = (TeacherModel) session1.get(TeacherModel.class, 1);
		 Set<StudentModel> sms = tm.getSms();
		 System.out.println(sms);
		 session1.close();
		 System.out.println("-------------------");
		 
		 Session session2 = HibernateUtils.openSession();
		 TeacherModel  tm2 = (TeacherModel) session2.get(TeacherModel.class, 1);
		 Set<StudentModel> sms2 = tm2.getSms();
		 System.out.println(sms2);
		 session2.close();
	 }
	 
	 
	 @Test
	 public void test07(){
		 /*
		  * ����ʱ���
		  *    1.����hql����update ����
		  **/
		 Session session1 = HibernateUtils.openSession();
		 Transaction transaction = session1.beginTransaction();
		 
		 TeacherModel tm1 = (TeacherModel) session1.get(TeacherModel.class, 1);
		 
		 Query query = session1.createQuery("update TeacherModel set name = 'ï��' where id  = 1");
		 transaction.commit();
		 System.out.println("-------------------------");
		 Session session2 = HibernateUtils.openSession();
		 
		 TeacherModel tm2 = (TeacherModel) session2.get(TeacherModel.class, 1);
		 
		 query.executeUpdate();
		
		 session1.close();
	 }
	 
	 @Test
	 public void test08(){
		 /*
		  * ���Բ�ѯ����
		  **/
		 Session session1 = HibernateUtils.openSession();
		 Query query = session1.createQuery("from TeacherModel");
		 //����hibernateҪʹ�ò�ѯ����
		 query.setCacheable(true);
		 query.list();
		 
		 System.out.println("-------------");
		 
		 Session session2 = HibernateUtils.openSession();
		 
		 Query query2 = session2.createQuery("from TeacherModel");
		 query2.setCacheable(true);
		 query2.list();
		 
		
		 session1.close();
	 }
}
