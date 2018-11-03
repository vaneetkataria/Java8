package com.katariasoft.technologies.Java8.references.constructor;

import java.util.function.BiFunction;
import java.util.function.IntFunction;

public class MiscConstructorReferennces {

	public static class Poll {
		private int height;

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public Poll(int height) {
			super();
			this.height = height;
		}

		@Override
		public String toString() {
			return "Poll [height=" + height + "]";
		}

	}

	public static class Car {
		private int colorCode;
		private int prize;

		public Car(int prize, int colorCode) {
			super();
			this.colorCode = colorCode;
			this.prize = prize;
		}

		public int getColor() {
			return colorCode;
		}

		public void setColor(int color) {
			this.colorCode = color;
		}

		public int getPrize() {
			return prize;
		}

		public void setPrize(int prize) {
			this.prize = prize;
		}

		@Override
		public String toString() {
			return "Car [colorCode=" + colorCode + ", prize=" + prize + "]";
		}

	}

	public static void main(String args[]) {
		IntFunction<Poll> pollProvider = Poll::new;
		System.out.println(pollProvider.apply(5));
		BiFunction<Integer, Integer, Car> carProviderv = Car::new;
		System.out.println(carProviderv.apply(5, 10));

	}

}
