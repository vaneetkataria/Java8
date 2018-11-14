package com.katariasoft.technologies.Java8.interfaces.funtional.predefined.supplier;

import java.util.function.Supplier;

public class OtpSupplierProvider {

	private static final String empty = "";

	private Supplier<String> eightDigitOtpProvider = eightDigitOtpProviderImpl();

	private Supplier<String> eightDigitOtpProviderImpl() {
		return () -> {
			byte[] allNumericDigits = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			String otp = "";
			for (byte b = 0; b < 8; b++) {
				byte randomDigit = allNumericDigits[(byte) (Math.random() * 10)];
				otp = String.join(empty, otp, String.valueOf(randomDigit));
			}
			return otp;
		};
	}

	public Supplier<String> getEightDigitOtpProvider() {
		return eightDigitOtpProvider;
	}

	public static void main(String args[]) {
		OtpSupplierProvider otpSupplierProvider = new OtpSupplierProvider();
		for (byte b = 0; b < 20; b++)
			System.out.println("OTP generated is :" + otpSupplierProvider.eightDigitOtpProvider.get());
	}

}
