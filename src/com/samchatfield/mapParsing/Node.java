package com.samchatfield.mapParsing;

public class Node {
	
	private final int x, y, type;

	public Node(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return x + "," + y + ",type:" + type;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		Node node = (Node) o;
		
		if (x != node.x)
			return false;
		if (y != node.y)
			return false;
		if (type != node.type)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		result = 31 * result + type;
		return result;
	}
	
}
