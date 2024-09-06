package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installments;

public class ContractService {
	private OnlinePaymentServices service;

	public ContractService(OnlinePaymentServices service) {
		this.service = service;
	}
	
	public void processContract(Contract contract, Integer months) {
		for(int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			Double interest = service.interest(contract.getTotalValue()/months, i);
			Double paymentFee = service.paymentFee(contract.getTotalValue()/months + interest);
			Double amount = contract.getTotalValue()/months;
			amount += paymentFee + interest;
			contract.getInstallment().add(new Installments(dueDate, amount));
		}
	}
}
