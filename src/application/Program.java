package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installments;
import model.services.ContractService;
import model.services.PayPalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the contract data:");
		System.out.print("Number: ");
		Integer number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		String dateStr = sc.next();
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.print("Contract value: ");
		Double totalValue = sc.nextDouble();
		System.out.print("Enter the number of installments: ");
		Integer month = sc.nextInt();
		
		Contract contract = new Contract(number, date, totalValue);
		ContractService service = new ContractService(new PayPalService());
		service.processContract(contract, month);
		
		System.out.println("Installments:");
		for(Installments x : contract.getInstallment()) {
			System.out.println(x.getDueDate() + " - " + x.getAmount());
		}
		
		sc.close();
	}

}
