/**
 * DS2_ModularCalculatorStatic.java, Nikola Vetnic
 * 
 * Staticka implementacija.
 * 
 * Jednostavna klasa koja resava probleme iz modularnog racuna na predmetu 
 * Diskretne strukture 2 u kojima se trazi da se nadje prirodan broj za k-
 * oji dati izraz vazi. Resavanje se sastoji iz nalazenja perioda ponavlj-
 * anja poslednjih cifara prilikom stepenovanja brojeva po zadatom modulu,
 * analiziranje perioda i kompilaciju konacnog resenja i proveru istog.
 * 
 * Klasa sadrzi i dopunske metode koji se mogu koristiti za resavanje zad-
 * ataka drugog tipa iz iste oblasti. Ove metode su javne.
 */

class DS2_ModularCalculatorStatic {
	
	
	static int gcd(int a, int b) {
		while (a != b) {
			if (a > b) {
				a -= b;
			} else {
				b -= a; 
			}
		}
		return a;
	}	// greatest common denominator
	
	
	static int lcd(int a, int b) {
		return Math.abs(a * b) / gcd(a, b);
	}	// lowest common divisor
	
	
	static int lastDigit(int n, int pow, int mod) {
		
		int lastDigit = 1;
		
		for (int i = 1; i <= pow; i++) {
			lastDigit = (lastDigit * n) % mod;
		}
		
		return lastDigit;
	}	// calculates last digit of n^pow in base mod
	
	
	static int findPeriod(int n, int mod) {
		
		int[] lastDigit = new int[50];
		lastDigit[0] = n % mod;
		
		int hitCount = 0;
		int guessLen = 0;
		boolean found = false;
		
		int i = 1;
		
		while (!found && i < lastDigit.length) {
			
			lastDigit[i] = (lastDigit[i-1] * n) % mod;
			
			if (lastDigit[0] == lastDigit[i]) {
				guessLen = i;
			}
			
			if (i == 2 * guessLen - 1) {
				for (int j = guessLen; j <= i; j++)
					if (lastDigit[j] == lastDigit[j - guessLen])
						hitCount++;
				
				found = hitCount == guessLen;
			}
			i++;
		}
		return guessLen;
	}	// finds period of last digit repetition in base mod for n
	
	
	private static int calculate(int[] input, int[] factor, int c, int mod, int deg) {
		
		int[] lastDigit = new int[input.length];
		
		for (int j = 0; j < input.length; j++) {
			lastDigit[j] = lastDigit((lastDigit(input[j], deg, mod) * factor[j]), 1, mod);
			if (lastDigit[j] < 0) lastDigit[j] += mod;
		}
		
		int sum = 0;
		
		for (int j = 0; j < input.length; j++)
			sum += lastDigit[j];
			
		sum += c;
		
		if (lastDigit(sum, 1, mod) < 0) return lastDigit(sum, 1, mod) + mod;
		
		return lastDigit(sum, 1, mod);
	}
	
	
	private static void checkPattern(int[] input, int[] factor, int c, int mod, int n0, int cmp) {
		
		int zeros = 0;
		
		for (int i = 0; i < cmp; i++)
			if (calculate(input, factor, c, mod, i) == 0) zeros++;
		
		if (zeros == cmp) {				// when one should find degree(s) for which expr is divisible with mod
			System.out.println("Statement is true for any n, n ∈ ℕ.");
		} else if (zeros == 1) {		// when there is a single zero in period parameter cmp
			System.out.println("Solution: n = " + n0 + " + " + cmp + " * k, k ∈ ℕ");
		} else if (zeros != 0 && cmp % zeros == 0) {
			if (n0 == cmp / zeros) 		// when period is shorter than forwarded cmp and n0 can be excluded
				System.out.println("Solution: n = " + (cmp / zeros) + " * k, k ∈ ℕ");
			else  						// when period is shorter than forwarded cmp
				System.out.println("Solution: n = " + n0 + " + " + (cmp / zeros) + " * k, k ∈ ℕ");
		} else {						// when there is no simple or discernible pattern
			System.out.println("No simple pattern found - see if you can find one: ");
			
			for (int i = 1; i <= cmp * 7; i++) {
				System.out.print(calculate(input, factor, c, mod, i) + "\t");
				if (i % cmp == 0) System.out.println();
			}
		}
	}
	
	
	static void solveProblem(int[] input, int[] factor, int c, int mod) {
		
		printProblem(input, factor, c, mod);
		
		int n0 = -1;
		
		int[] p = new int[input.length];
		p[0] = findPeriod(input[0], mod);
		int cmp = p[0];
		
		for (int i = 1; i < p.length; i++) {
			p[i] = findPeriod(input[i], mod);
			cmp = lcd(cmp, p[i]);
		}
		
		int deg = 1;
		int[] lastDigit = new int[input.length];
		
		while (n0 == -1 && deg <= cmp) {
			int sum = calculate(input, factor, c, mod, deg);
			if (sum == 0) n0 = deg;
			deg++;
		}
		
		checkPattern(input, factor, c, mod, n0, cmp);
	}	// solves the problem
	
	
	private static void printProblem(int[] input, int[] factor, int c, int mod) {
		
		String output = mod + " | " + factor[0] + " * " + input[0] + "^n ";
		
		for (int i = 1; i < input.length; i++) {
			if (factor[i] > 0)
				output += "+ " + Math.abs(factor[i]) + " * " + input[i] + "^n ";
			else 
				output += "- " + Math.abs(factor[i]) + " * " + input[i] + "^n ";
		}
		
		if (c != 0) {
			if (c < 0) output += "- ";
			else output += "+ ";
			
			output += Math.abs(c);
		}
		
		System.out.println(output);
	}	// prints out the problem
	
	
	public static void main(String[] args) {
		
		// Vezbe08_Zadatak1
		solveProblem(new int[] {25, 4}, new int[] {5, 2}, 0, 7);
		System.out.println();
		
		
		// Vezbe08_Zadatak7
		solveProblem(new int[] {2}, new int[] {1}, -1, 7);
		System.out.println();
		
		// Vezbe08_Zadatak8
		solveProblem(new int[] {2}, new int[] {1}, 1, 7);
		System.out.println();
		
		// Vezbe08_Zadatak9
		solveProblem(new int[] {49, 9}, new int[] {7, 13}, 0, 10);
		System.out.println();
		
		// 20180608							[DS2] 2017-2018 Kolokvijum_Popravni01
		solveProblem(new int[] {3}, new int[] {1}, 6, 11);
		System.out.println();
		
		// 20180903							[DS2] 2017-2018 Kolokvijum_Popravni02
		solveProblem(new int[] {5, 4}, new int[] {5, 1}, -7, 11);
		System.out.println();
			
		// 20180919							[DS2] 2017-2018 Kolokvijum_Popravni03
		solveProblem(new int[] {5, 3}, new int[] {1, -1}, -2, 13);
		System.out.println();	
		
		// 20190830							[DS2] 2018-2019 Kolokvijum_Popravni02
		solveProblem(new int[] {5, 7}, new int[] {25, 7}, -14, 18);
		System.out.println();
		
		// 20190913							[DS2] 2018-2019 Kolokvijum_Popravni03
		solveProblem(new int[] {5, 4}, new int[] {3, 24}, -12, 21);
		System.out.println();
		
		// 20190913, mod 11 instead 21		[DS2] 2018-2019 Kolokvijum_Popravni03
		solveProblem(new int[] {5, 4}, new int[] {3, 24}, -12, 11);
		System.out.println();
		
		// 20190606							[DS2] 2018-2019 Kolokvijum02
		solveProblem(new int[] {(int) Math.pow(12, 4), (int) Math.pow(8, 4)}, new int[] {(int) Math.pow(12, 3), -8}, -10, 30);
		System.out.println();
	}
}
