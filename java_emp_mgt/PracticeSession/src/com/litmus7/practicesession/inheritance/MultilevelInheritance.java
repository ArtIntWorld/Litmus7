package com.litmus7.practicesession.inheritance;

class Reptile extends Animal {
	void body() {
		System.out.println("It have scales.");
	}
}

class Snake extends Reptile {
	void move() {
		System.out.println("It has no limbs to move.");
	}
}

public class MultilevelInheritance {

	public static void main(String[] args) {
			Snake snake = new Snake();
			snake.kingdom();
			snake.body();
			snake.move();
	}

}
