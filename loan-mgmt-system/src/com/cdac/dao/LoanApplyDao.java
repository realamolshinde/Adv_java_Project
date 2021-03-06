package com.cdac.dao;

import java.util.List;

import com.cdac.dto.LoanApply;

public interface LoanApplyDao {
	public void addLoan(LoanApply loanApply);
	public List<LoanApply> showList(String custName);
	public List<LoanApply> showAllPendingList();
	public List<LoanApply> showAllApproveList();
	public List<LoanApply> showAllRejectList();
	public LoanApply showApplyByName(int id);
	public void updateLoanStatus(LoanApply loanApply);
}
