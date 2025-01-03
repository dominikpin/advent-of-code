package year2023.day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

	public Part2() throws FileNotFoundException {
		File inputPath = new File(System.getProperty("user.dir"), "input.txt");
		ArrayList<double[]> hailStones = new ArrayList<>();
		Scanner myReader = new Scanner(inputPath);
		while (myReader.hasNext()) {
			parseInput(myReader.nextLine(), hailStones);
		}
		myReader.close();
		System.out.println((long) calculateRockPositionAndVelocity(hailStones));
	}

	private static void parseInput(String line, ArrayList<double[]> hailStones) {
		String[] split1 = line.split(" @ ");
		double[] newHailStone = new double[6];
		for (int i = 0; i < split1.length; i++) {
			String[] split2 = split1[i].split(", ");
			for (int j = 0; j < split2.length; j++) {
				newHailStone[i * 3 + j] = (double) Long.parseLong(split2[j].trim());
			}
		}
		hailStones.add(newHailStone);
	}

	private static double calculateRockPositionAndVelocity(ArrayList<double[]> hailStones) {
		double[] p1 = {
				hailStones.get(1)[0] - hailStones.get(0)[0],
				hailStones.get(1)[1] - hailStones.get(0)[1],
				hailStones.get(1)[2] - hailStones.get(0)[2],
		};
		double[] v1 = {
				hailStones.get(1)[3] - hailStones.get(0)[3],
				hailStones.get(1)[4] - hailStones.get(0)[4],
				hailStones.get(1)[5] - hailStones.get(0)[5],
		};
		double[] p2 = {
				hailStones.get(2)[0] - hailStones.get(0)[0],
				hailStones.get(2)[1] - hailStones.get(0)[1],
				hailStones.get(2)[2] - hailStones.get(0)[2],
		};
		double[] v2 = {
				hailStones.get(2)[3] - hailStones.get(0)[3],
				hailStones.get(2)[4] - hailStones.get(0)[4],
				hailStones.get(2)[5] - hailStones.get(0)[5],
		};
		double t1 = -(calculateDotProduct(calculateCrossProduct(p1, p2), v2))
				/ (calculateDotProduct(calculateCrossProduct(v1, p2), v2));
		double t2 = -(calculateDotProduct(calculateCrossProduct(p1, p2), v1))
				/ (calculateDotProduct(calculateCrossProduct(p1, v2), v1));

		double[] c1 = new double[] {
				hailStones.get(1)[0] + hailStones.get(1)[3] * t1,
				hailStones.get(1)[1] + hailStones.get(1)[4] * t1,
				hailStones.get(1)[2] + hailStones.get(1)[5] * t1,
		};
		double[] c2 = new double[] {
				hailStones.get(2)[0] + hailStones.get(2)[3] * t2,
				hailStones.get(2)[1] + hailStones.get(2)[4] * t2,
				hailStones.get(2)[2] + hailStones.get(2)[5] * t2,
		};
		double[] v = new double[] {
				(c2[0] - c1[0]) / (t2 - t1),
				(c2[1] - c1[1]) / (t2 - t1),
				(c2[2] - c1[2]) / (t2 - t1),
		};
		double[] p = new double[] {
				c1[0] - t1 * v[0],
				c1[1] - t1 * v[1],
				c1[2] - t1 * v[2],
		};
		return p[0] + p[1] + p[2];
	}

	private static double[] calculateCrossProduct(double[] hailStone1, double[] hailStone2) {
		return new double[] {
				hailStone1[1] * hailStone2[2] - hailStone1[2] * hailStone2[1],
				hailStone1[2] * hailStone2[0] - hailStone1[0] * hailStone2[2],
				hailStone1[0] * hailStone2[1] - hailStone1[1] * hailStone2[0]
		};
	}

	private static double calculateDotProduct(double[] hailStone1, double[] hailStone2) {
		return hailStone1[0] * hailStone2[0] + hailStone1[1] * hailStone2[1] + hailStone1[2] * hailStone2[2];
	}
}