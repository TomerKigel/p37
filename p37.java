import java.util.*;

public class p37 {
	public static void main(String[] args) {
		Vector<Long> normal_primes = new Vector<Long>();
		Vector<Long> primes = new Vector<Long>();
		populate_primes(primes,normal_primes);
		long sum = 0;
		for (int i = 4; i < primes.size(); i++) {
			if(cut_prime_left(primes.elementAt(i),normal_primes) &&  cut_prime_right(primes.elementAt(i),normal_primes))
			{
				sum += primes.elementAt(i);
			}
		}
		System.out.println(sum);
	}
	
	
	public static void populate_primes(Vector<Long> primes,Vector<Long> normal_primes)
	{
		for (int i = 2; i <= 799997; i++) {
			boolean prime = true;
			for (long j = 2; j <= Math.sqrt(i); j++) {
				if(i%j == 0)
					prime = false;
			}
			if(prime)
			{
				normal_primes.add((long) i);
				int size = size_of_num(i);
				long t_num = (long) Math.pow(10, size-1);
				long nu = i/t_num;
				long cf = i % 10;
				if(normal_primes.contains(cf) && normal_primes.contains(nu)) {
					primes.add((long)i);
				}
			}
		}
	}	
	static boolean cut_prime_left(long prime,Vector<Long> primes)
	{
		if(!primes.contains(prime))
			return false;
		else
		{
			int size = size_of_num(prime);
			if(size == 1)
				return true;
			long t_num = (long) Math.pow(10, size-1);
			int last_digit = (int) (prime/t_num);
			t_num *= last_digit;
			prime -= t_num;
			return cut_prime_left(prime,primes);
		}
	}
	
	static boolean cut_prime_right(long prime,Vector<Long> primes)
	{
		if(!primes.contains(prime))
			return false;
		else
		{
			int size = size_of_num(prime);
			if(size == 1)
				return true;
			prime /= 10;
			return cut_prime_right(prime, primes);
		}
	}
	
	static int size_of_num(long num)
	{
		long t_num = num;
		int counter = 0;
		while(t_num >= 1)
		{
			t_num/=10;
			counter++;
		}
		return counter;
	}
}
