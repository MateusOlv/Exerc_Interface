package model.services;

public interface OnlinePaymentServices {
	Double paymentFee(Double amount);
	Double interest(Double amount, Integer months);
}
