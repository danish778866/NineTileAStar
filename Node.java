package nineTileAStarSolution;

public class Node {

	private NineTile nodeNineTile;
	private int depth;
	private int pathCost;
	private int heuristicCost;
	private String path;
	private int totalCost;
	
	public Node(NineTile nodeNineTile, int depth, int pathCost, String path) {
		this.nodeNineTile = nodeNineTile;
		this.depth = depth;
		this.pathCost = pathCost;
		this.path = path;
		this.heuristicCost = this.nodeNineTile.findManhattanDistance();
		this.totalCost = this.pathCost + this.heuristicCost;
	}
	
	public int getPathCost() {
		return pathCost;
	}
	
	public void printNode() {
		System.out.println("Depth: " + depth);
		System.out.println("Path Cost: " + pathCost);
		System.out.println("Heuristic Cost: " + heuristicCost);
		System.out.println("Path: " + path);
		System.out.println("Total Cost: " + totalCost);
		System.out.println("Nine Tile COnfiguration: ");
		nodeNineTile.printNineTile();
	}
	
	public int getTotalCost() {
		return totalCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeNineTile == null) ? 0 : nodeNineTile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (nodeNineTile == null) {
			if (other.nodeNineTile != null)
				return false;
		} else if (!nodeNineTile.equals(other.nodeNineTile))
			return false;
		return true;
	}

	public NineTile getNodeNineTile() {
		return nodeNineTile;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public String getPath() {
		return path;
	}
}
