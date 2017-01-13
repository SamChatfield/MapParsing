package com.samchatfield.mapParsing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MapData {
	
	public static final Color PATH                 = new Color(255, 255, 255);
	public static final Color GRASS                = new Color(50, 160, 0);
	public static final Color WALL_ISLAND          = new Color(0, 0, 0);
	public static final Color WALL_END_UP          = new Color(1, 1, 1);
	public static final Color WALL_JUNC_4          = new Color(2, 2, 2);
	public static final Color WALL_END_DOWN        = new Color(3, 3, 3);
	public static final Color WALL_END_LEFT        = new Color(4, 4, 4);
	public static final Color WALL_END_RIGHT       = new Color(5, 5, 5);
	public static final Color WALL_JUNC_2V         = new Color(6, 6, 6);
	public static final Color WALL_JUNC_2H         = new Color(7, 7, 7);
	public static final Color WALL_LEFT            = new Color(8, 8, 8);
	public static final Color WALL_RIGHT           = new Color(9, 9, 9);
	public static final Color WALL_UP              = new Color(10, 10, 10);
	public static final Color WALL_DOWN            = new Color(11, 11, 11);
	public static final Color WALL_LJUNC_UPLEFT    = new Color(12, 12, 12);
	public static final Color WALL_LJUNC_UPRIGHT   = new Color(13, 13, 13);
	public static final Color WALL_LJUNC_DOWNLEFT  = new Color(14, 14, 14);
	public static final Color WALL_LJUNC_DOWNRIGHT = new Color(15, 15, 15);
	public static final Color WALL_MIDDLE          = new Color(16, 16, 16);
	public static final Color WALL_CORN_UPLEFT     = new Color(17, 17, 17);
	public static final Color WALL_CORN_UPRIGHT    = new Color(18, 18, 18);
	public static final Color WALL_CORN_DOWNLEFT   = new Color(19, 19, 19);
	public static final Color WALL_CORN_DOWNRIGHT  = new Color(20, 20, 20);
	
	private int w, h;
	private Node[][] nodes;
	private BufferedImage tiles;
	private final TileData tdata;

	public MapData(File mapFile, TileData tdata) {
		this.tdata = tdata;
		try {
			generateMap(PngParser.getImage(mapFile));
		} catch (IOException | MapFileException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

	private void generateMap(BufferedImage map) throws MapFileException {
		w = map.getWidth();
		h = map.getHeight();
		
		nodes = new Node[w][h];
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				int rgb = map.getRGB(x, y);
				Color c = new Color(rgb);
				
				if (c.equals(PATH)) {
					nodes[x][y] = new Node(x, y, TileData.PATH);
				} else if (c.equals(GRASS)) {
					nodes[x][y] = new Node(x, y, TileData.GRASS);
				} else if (c.equals(WALL_ISLAND)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_ISLAND);
				} else if (c.equals(WALL_END_UP)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_END_UP);
				} else if (c.equals(WALL_JUNC_4)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_JUNC_4);
				} else if (c.equals(WALL_END_DOWN)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_END_DOWN);
				} else if (c.equals(WALL_END_LEFT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_END_LEFT);
				} else if (c.equals(WALL_END_RIGHT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_END_RIGHT);
				} else if (c.equals(WALL_JUNC_2V)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_JUNC_2V);
				} else if (c.equals(WALL_JUNC_2H)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_JUNC_2H);
				} else if (c.equals(WALL_LEFT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_LEFT);
				} else if (c.equals(WALL_RIGHT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_RIGHT);
				} else if (c.equals(WALL_UP)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_UP);
				} else if (c.equals(WALL_DOWN)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_DOWN);
				} else if (c.equals(WALL_LJUNC_UPLEFT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_LJUNC_UPLEFT);
				} else if (c.equals(WALL_LJUNC_UPRIGHT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_LJUNC_UPRIGHT);
				} else if (c.equals(WALL_LJUNC_DOWNLEFT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_LJUNC_DOWNLEFT);
				} else if (c.equals(WALL_LJUNC_DOWNRIGHT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_LJUNC_DOWNRIGHT);
				} else if (c.equals(WALL_MIDDLE)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_MIDDLE);
				} else if (c.equals(WALL_CORN_UPLEFT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_CORN_UPLEFT);
				} else if (c.equals(WALL_CORN_UPRIGHT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_CORN_UPRIGHT);
				} else if (c.equals(WALL_CORN_DOWNLEFT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_CORN_DOWNLEFT);
				} else if (c.equals(WALL_CORN_DOWNRIGHT)) {
					nodes[x][y] = new Node(x, y, TileData.WALL_CORN_DOWNRIGHT);
				}
				else {
					throw new MapFileException("Invalid colour in map text file");
				}
			}
		}
		printNodes();
	}
	
	private BufferedImage tileImageFor(int nodeType) {
		int tsize = tdata.getTileSize();
		int x = nodeType % (tiles.getWidth() / tsize);
		int y = nodeType / (tiles.getWidth() / tsize);
		System.out.println(x + "," + y + " for " + nodeType);
		return tiles.getSubimage(x * tsize, y * tsize, tsize, tsize);
	}

	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}

	public Node[][] getNodes() {
		return nodes;
	}

	private void printNodes() {
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				System.out.print(nodes[x][y].getType() + " ");
			}
			System.out.println();
		}
	}

}
