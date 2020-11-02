package com.cdac.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cdac.dto.LoanApply;

@Repository
public class LoanApplyDaoImple implements LoanApplyDao {

	@Autowired
	private HibernateTemplate ht;
	
	@Override
	public void addLoan(LoanApply loanApply) {
		ht.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				
				Transaction tr = session.beginTransaction();
				session.save(loanApply);				
				tr.commit();
				session.flush();
				session.close();
				
				return null;
			}
		});		
	}

	@Override
	public List<LoanApply> showList(String custName) {
		return ht.execute(new HibernateCallback<List<LoanApply>>() {

			@Override
			public List<LoanApply> doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from LoanApply where custName = ? and Status = ?");
					q.setString(0, custName);
				q.setString(1, "Pending");
				List<LoanApply> list = q.list();
				tr.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}

	@Override
	public List<LoanApply> showAllPendingList() {
		return ht.execute(new HibernateCallback<List<LoanApply>>() {

			@Override
			public List<LoanApply> doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from LoanApply where status = ?");
				q.setString(0, "Pending");
				List<LoanApply> list = q.list();
				tr.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}
	
	@Override
	public List<LoanApply> showAllApproveList() {
		return ht.execute(new HibernateCallback<List<LoanApply>>() {

			@Override
			public List<LoanApply> doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from LoanApply where status = ?");
				q.setString(0, "Approve");
				List<LoanApply> list = q.list();
				tr.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}

	@Override
	public List<LoanApply> showAllRejectList() {
		return ht.execute(new HibernateCallback<List<LoanApply>>() {

			@Override
			public List<LoanApply> doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from LoanApply where status = ?");
				q.setString(0, "Reject");
				List<LoanApply> list = q.list();
				tr.commit();
				session.flush();
				session.close();
				return list;
			}
		});
	}

	

	@Override
	public LoanApply showApplyByName(int id) {
		return ht.execute(new HibernateCallback<LoanApply>() {

			@Override
			public LoanApply doInHibernate(Session session) throws HibernateException {
				Transaction tr = session.beginTransaction();
				Query q = session.createQuery("from LoanApply where id = ?");
				q.setInteger(0, id);
				List<LoanApply> la = q.list();
				tr.commit();
				session.flush();
				session.close();
				return la.get(0);
			}
		});
	}

	@Override
	public void updateLoanStatus(LoanApply loanApply) {
		ht.execute(new HibernateCallback<Void>() {

			@Override
			public Void doInHibernate(Session session) throws HibernateException {
				
				Transaction tr = session.beginTransaction();
				
				System.out.println("inside update method: "+loanApply.toString());

				session.update(loanApply);				
				tr.commit();
				session.flush();
				session.close();
				
				return null;
			}
		});		
		
	}

}
