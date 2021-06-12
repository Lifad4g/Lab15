import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HillClimbing {
	
	//Cities data
	public double[][] cities;
	public Integer[] tour;
	public int totalIter;
	public int restratIter;
			
	public HillClimbing(double[][] dataMatrix, int iter) {
		int length = dataMatrix.length;
		boolean ok = true;
		
		for (int i = 0; i < dataMatrix.length; i++) {
		for(int j=0;j < dataMatrix[i].length;++j)
		{
			if (/*dataMatrix==null || */ iter<1) ok = false;
		}
		}
		if (!(dataMatrix == null) && ok)
		{
			cities = dataMatrix;
			totalIter = iter;
			restratIter = (int) totalIter/10000;
			tour = new Integer[length];
		}
		else {
			System.out.println("Invalid input");
		}
		
		
		RRHC();
		printSolution();
	}
	
	
	
	private void genRandSolution() {
		for (int i = 0; i < tour.length; i++) {
			tour[i] = i;
		}
		tour = shuffleArray(tour);
		
	}
	
	static public Integer[] shuffleArray(Integer[] array) {
		
		Random random = new Random();
        for (int i = 1; i < array.length; i++) {
            int randomValue = random.nextInt(array.length - 1) +1;
            int randomElement = array[randomValue];
            array[randomValue] = array[i];
            array[i] = randomElement;
        }
		return array;		
	}

	private void printSolution() {
		String output;
		output = Integer.toString(tour[0]);
		for (int i = 1; i < tour.length; i++) {
			output += " -> " + Integer.toString(tour[i]);
		}
		System.out.println(output);
	}

	private double getFitness(Integer [] tour) {
		double s = 0;
		for (int i = 0; i < tour.length-1; i++) {
			int a = tour[i];
			int b = tour[i+1];
			s += cities[a][b];
		}
		s += cities[tour[0]][tour[tour.length-1]];
		return s;
	}



	private void RMHC() {
		for(int i = 0; i<totalIter/restratIter; i++) {
			Integer[] old = tour.clone();
			smallChange();
			if(getFitness(tour)>getFitness(old)) {
				tour = old.clone();
				}
		}
		System.out.println("RMHC Fitness: " + getFitness(tour));
	}
	
	private void RRHC() {
		genRandSolution();
		for (int i = 0; i < restratIter; i++) {
			Integer[] old = tour.clone();
			genRandSolution();
			RMHC();
			if(getFitness(tour)>getFitness(old)) {
				tour = old.clone();
				}
			System.out.println("Final Fitness: " + getFitness(tour));
		}
		
	}

	private void smallChange() {
		Random random = new Random();
		int randomValue = 0;
		int randomValue2 = 0;
		while(randomValue == randomValue2) {
		randomValue = random.nextInt(cities.length -1) + 1;
		randomValue2 = random.nextInt(cities.length - 1) +1;
		}
        int randomElement = tour[randomValue];
        tour[randomValue] = tour[randomValue2];
        tour[randomValue2] = randomElement;
	}


	
	
	
	
	
	
}

	
