/**
 * DS2_ModularCalculator.java, Nikola Vetnic
 * 
 * Jednostavna klasa koja resava probleme iz modularnog racuna na pred-
 * metu Diskretne strukture 2 u kojima se trazi da se nadje prirodan b-
 * roj za koji dati izraz vazi. Resavanje se sastoji iz nalazenja peri-
 * oda ponavljanja poslednjih cifara prilikom stepenovanja brojeva pre-
 * ma zadatom modulu, analiziranje perioda i kompilaciju konacnog rese-
 * nja i proveru istog.
 * 
 * Klasa sadrzi dopunske metode koji se mogu koristiti za resavanje za-
 * dataka drugog tipa iz iste oblasti. Ove metode su javne.
 */

class DS2_ModularCalculator {
	
	public static void main(String[] args) {
		
		// Vezbe08_Zadatak1
		MathProblem p01 = new MathProblem(new int[] {25, 4}, new int[] {5, 2}, 0, 7);
		p01.solveProblem();
		System.out.println();
		
		// Vezbe08_Zadatak7
		MathProblem p02 = new MathProblem(new int[] {2}, new int[] {1}, -1, 7);
		p02.solveProblem();
		System.out.println();
		
		// Vezbe08_Zadatak8
		MathProblem p03 = new MathProblem(new int[] {2}, new int[] {1}, 1, 7);
		p03.solveProblem();
		System.out.println();
		
		// Vezbe08_Zadatak9
		MathProblem p04 = new MathProblem(new int[] {49, 9}, new int[] {7, 13}, 0, 10);
		p04.solveProblem();
		System.out.println();
		
		// 20180608							[DS2] 2017-2018 Kolokvijum_Popravni01
		MathProblem p05 = new MathProblem(new int[] {3}, new int[] {1}, 6, 11);
		p05.solveProblem();
		System.out.println();
		
		// 20180903							[DS2] 2017-2018 Kolokvijum_Popravni02
		MathProblem p06 = new MathProblem(new int[] {5, 4}, new int[] {5, 1}, -7, 11);
		p06.solveProblem();
		System.out.println();
			
		// 20180919							[DS2] 2017-2018 Kolokvijum_Popravni03
		MathProblem p07 = new MathProblem(new int[] {5, 3}, new int[] {1, -1}, -2, 13);
		p07.solveProblem();
		System.out.println();	
		
		// 20190830							[DS2] 2018-2019 Kolokvijum_Popravni02
		MathProblem p08 = new MathProblem(new int[] {5, 7}, new int[] {25, 7}, -14, 18);
		p08.solveProblem();
		System.out.println();
		
		// 20190913							[DS2] 2018-2019 Kolokvijum_Popravni03
		MathProblem p09 = new MathProblem(new int[] {5, 4}, new int[] {3, 24}, -12, 21);
		p09.solveProblem();
		System.out.println();
		
		// 20190913, mod 11 instead 21		[DS2] 2018-2019 Kolokvijum_Popravni03
		MathProblem p10 = new MathProblem(new int[] {5, 4}, new int[] {3, 24}, -12, 11);
		p10.solveProblem();
		System.out.println();
		
		// 20190606							[DS2] 2018-2019 Kolokvijum02
		MathProblem p11 = new MathProblem(new int[] {(int) Math.pow(12, 4), (int) Math.pow(8, 4)}, new int[] {(int) Math.pow(12, 3), -8}, -10, 30);
		p11.solveProblem();
		System.out.println();
		
		// 20200530							[DS2] Kolokvijum02_053
		MathProblem p12 = new MathProblem(new int[] {16, 125}, new int[] {4, 25}, -2, 11);
		p12.solveProblem();
		
		// 20200530							[DS2] Kolokvijum02_???
		MathProblem p13 = new MathProblem(new int[] {5, 4}, new int[] {25, 4}, -8, 9);
		p13.solveProblem();
		
		// 20200530							[DS2] Kolokvijum02_???
		MathProblem p14 = new MathProblem(new int[] {49, 3}, new int[] {7, 9}, -4, 11);
		p14.solveProblem();
		
		// 20200530							[DS2] Kolokvijum02_???
		MathProblem p15 = new MathProblem(new int[] {3, 16}, new int[] {1, 4}, -5, 10);
		p15.solveProblem();
		
		// 20180919							[DS2] 2017-2018 Kolokvijum_Popravni03, Zadatak 5
		System.out.println("Last digit of 17^19^(1+2+...+2018): " 
							+ MathProblem.lastDigit(17, MathProblem.lastDigit(19, MathProblem.lastDigit(2019 * 1009, 1, 4), 4), 10));
		System.out.println();
							
		// 20190621							[DS2] 2018-2019 Kolokvijum_Popravni01							 
		System.out.println("Last digit of 1942^(1943^1944 + 2019!) + (1+2+...2019)/2: " 
							+ MathProblem.lastDigit(MathProblem.lastDigit(1942, MathProblem.lastDigit(1943, MathProblem.lastDigit(1944, 1, 4), 4) 
							+ 0, 10) + MathProblem.lastDigit(2019 * 505, 1, 10), 1, 10));
		System.out.println();
		
	}
}

class MathProblem {

	int[] 	input;
	int[] 	factor;
	int 	c;
	int		mod;
	
	
	// =-=-=
	
	
	public MathProblem(int[] input, int[] factor, int c, int mod) {
		this.input = input;
		this.factor = factor;
		this.c = c;
		this.mod = mod;
	}
	
	
	// =-=-=
	
	
	private static int gcd(int a, int b) {
		while (a != b) {
			if (a > b) {
				a -= b;
			} else {
				b -= a; 
			}
		}
		return a;
	}	// greatest common denominator
	
	
	private static int lcd(int a, int b) {
		return Math.abs(a * b) / gcd(a, b);
	}	// lowest common divisor
	
	
	public static int lastDigit(int n, int pow, int mod) {
		
		int lastDigit = 1;
		
		for (int i = 1; i <= pow; i++) {
			lastDigit = (lastDigit * n) % mod;
		}
		
		return lastDigit;
	}	// calculates last digit of n^pow in base mod
	
	
	public static int findPeriod(int n, int mod) {
		
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
	
	
	private int calculate(int deg) {
		
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
	}	// calculates last digit IRO mod for given deg(ree)
	
	
	public void solveProblem() {
		
		/**
		 * data input is structured thusly: int[] - a sequence of inte-
		 * gers raised to the n-th power restructured in such a way th-
		 * at the same natural number n is used for all integers; int[]
		 * - a sequence of factors multiplying the integers stated pre-
		 * viously; int - a constant present at the end of the express-
		 * ion; int - mod by which we are calculating the result;
		 * 
		 * example:
		 *		original problem:	11 | 4^(2n + 1) + 5^(3n + 2) - 2
		 * 		rewritten:			11 | 4 * 16^n + 25 * 125^n - 2
		 * paramaters:
		 * 		(new int[] {16, 125}, new int[] {4, 25}, -2, 11)
		 */			
		
		System.out.println(this);
		
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
			int sum = calculate(deg);
			if (sum == 0) n0 = deg;
			deg++;
		}
		
		analyzeResult(n0, cmp);
		System.out.println();
	}	// solves the problem
	
	
	private void analyzeResult(int n0, int cmp) {
		
		int zeros = 0;
		
		for (int i = 0; i < cmp; i++)
			if (calculate(i) == 0) zeros++;
		
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
				System.out.print(calculate(i) + "\t");
				if (i % cmp == 0) System.out.println();
			}
		}
	}	// analyzes result and prints the solution out
	
	
	public String toString() {
		
		if (input.length == 0) {
			return "Empty object.";
		} else {
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
			return output;
		}
	}
}
