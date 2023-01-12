package com.test.demo;

public class Recursive {

	public static void main(String[] args) {
		Recursive.Person p1 = new Recursive.Person();
		Recursive.Person p2 = new Recursive.Person();
		Recursive.Person p3 = new Recursive.Person();
		Recursive.Person p4 = new Recursive.Person();
		Recursive.Person p5 = new Recursive.Person();
		p1.setName("Albert");
		p2.setName("Bebert");
		p3.setName("Charles");
		p4.setName("Dominique");
		p5.setName("Ibrahima");
		p1.setChild(p2);
		p2.setChild(p3);
		p3.setChild(p4);
		p4.setChild(p2);
		p5.setChild(p4);
		Recursive.printPerson(p1);
	}

	static void printPerson(Person p) {
		Person x = new Person();
		int k = 0;
		x = p;
		if (p != null) {
			//System.out.println("Pere " + p.getName());
			do {
				System.out.println("Pere " + p.getName());
				//System.out.println("Fils " + p.getChild().getName());
				p = p.getChild();
				k++;
			} while (x!=p.getChild() && x.getChild().getName()!=p.getName() && k!=0);
		}
	}

	static void printPersonRecursively(Person p) {
		if (p != null) {
			System.out.println(p.getName());
			printPersonRecursively(p.getChild());
		}
	}

	static class Person {
		private String name;
		private Person child;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Person getChild() {
			return child;
		}

		public void setChild(Person child) {
			this.child = child;
		}
	}
}
