package nineTileAStarSolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class AStarSearch {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int test;
		System.out.println("Enter 1 to set initial state randomly, else enter 0");
		test = in.nextInt();
		NineTile initialState;
		if (test == 1) {
			initialState = generateInitialState();
			while (!initialState.isSolvable()) {
				initialState = generateInitialState();
			}
		} else {
			initialState = setInitialState();
		}
		if (initialState.isSolvable()) {
			initialState.printNineTile();
			Node solutionNode = aStarSearchOnNineTile(initialState);
			String path = solutionNode.getPath();
			for (int i = 0; i < path.length(); i++) {
				if (path.substring(i, i + 1).equals("T")) {
					System.out.println("Move the blank one step Up");
				} else if (path.substring(i, i + 1).equals("B")) {
					System.out.println("Move the blank one step Down");
				} else if (path.substring(i, i + 1).equals("L")) {
					System.out.println("Move the blank one step Left");
				} else if (path.substring(i, i + 1).equals("R")) {
					System.out.println("Move the blank one step Right");
				}
			}
		} else {
			System.out.println("You entered an unsolvable configuration");
		}
		in.close();
	}

	public static Node aStarSearchOnNineTile(NineTile initialState) {
		List<Node> fringeList = new ArrayList<Node>();
		Set<Node> expandedList = new HashSet<Node>();
		Node initialNode = new Node(initialState, 0, 0, "S");
		fringeList.add(initialNode);
		boolean done = false;
		Node solutionNode = null;
		while (!done) {
			int minIndex = findLeastTotalCostNode(fringeList);
			Node leastCostNode = fringeList.remove(minIndex);
			if (leastCostNode.getNodeNineTile().isGoalState()) {
				done = true;
				solutionNode = leastCostNode;
			} else {
				NineTile leastCostNodeNineTile = leastCostNode.getNodeNineTile();
				expandedList.add(leastCostNode);
				if (leastCostNodeNineTile.getBlankY() > 0) {
					NineTile moveTopNineTile = new NineTile(leastCostNodeNineTile.getNineTile());
					moveTopNineTile.moveBlankTop();
					Node moveTopNode = new Node(moveTopNineTile, leastCostNode.getDepth() + 1,
							leastCostNode.getPathCost() + 1, leastCostNode.getPath() + "T");
					if (!expandedList.contains(moveTopNode) && !fringeList.contains(moveTopNode)) {
						fringeList.add(moveTopNode);
					}
				}

				if (leastCostNodeNineTile.getBlankY() < 2) {
					NineTile moveBottomNineTile = new NineTile(leastCostNodeNineTile.getNineTile());
					moveBottomNineTile.moveBlankBottom();
					Node moveBottomNode = new Node(moveBottomNineTile, leastCostNode.getDepth() + 1,
							leastCostNode.getPathCost() + 1, leastCostNode.getPath() + "B");
					if (!expandedList.contains(moveBottomNode) && !fringeList.contains(moveBottomNode)) {
						fringeList.add(moveBottomNode);
					}
				}

				if (leastCostNodeNineTile.getBlankX() > 0) {
					NineTile moveLeftNineTile = new NineTile(leastCostNodeNineTile.getNineTile());
					moveLeftNineTile.moveBlankLeft();
					Node moveLeftNode = new Node(moveLeftNineTile, leastCostNode.getDepth() + 1,
							leastCostNode.getPathCost() + 1, leastCostNode.getPath() + "L");
					if (!expandedList.contains(moveLeftNode) && !fringeList.contains(moveLeftNode)) {
						fringeList.add(moveLeftNode);
					}
				}

				if (leastCostNodeNineTile.getBlankX() < 2) {
					NineTile moveRightNineTile = new NineTile(leastCostNodeNineTile.getNineTile());
					moveRightNineTile.moveBlankRight();
					Node moveRightNode = new Node(moveRightNineTile, leastCostNode.getDepth() + 1,
							leastCostNode.getPathCost() + 1, leastCostNode.getPath() + "R");
					if (!expandedList.contains(moveRightNode) && !fringeList.contains(moveRightNode)) {
						fringeList.add(moveRightNode);
					}
				}
			}
		}
		return solutionNode;
	}

	public static int findLeastTotalCostNode(List<Node> fringeList) {
		int minIndex, minCost;
		minIndex = 0;
		minCost = fringeList.get(0).getTotalCost();
		for (int i = 1; i < fringeList.size(); i++) {
			if (fringeList.get(i).getTotalCost() < minCost) {
				minCost = fringeList.get(i).getTotalCost();
				minIndex = i;
			}
		}
		return minIndex;
	}

	public static NineTile generateInitialState() {
		int[][] nineTile = new int[3][3];
		boolean[] generated = new boolean[9];
		Random randomGenerator = new Random();
		for (int i = 0; i < 9; i++) {
			generated[i] = false;
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int randomNumber = randomGenerator.nextInt(9);
				while (generated[randomNumber]) {
					randomNumber = randomGenerator.nextInt(9);
				}
				nineTile[i][j] = randomNumber;
				generated[randomNumber] = true;
			}
		}

		return new NineTile(nineTile);
	}

	public static NineTile setInitialState() {
		int[][] nineTile = new int[3][3];
		nineTile[0][0] = 1;
		nineTile[0][1] = 5;
		nineTile[0][2] = 4;

		nineTile[1][0] = 7;
		nineTile[1][1] = 8;
		nineTile[1][2] = 0;

		nineTile[2][0] = 3;
		nineTile[2][1] = 2;
		nineTile[2][2] = 6;

		return new NineTile(nineTile);
	}

}
