package com.samchatfield.mapParsing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TileData {
	
	public static final int IMAGE_SIZE = 8;
	
	public static final int PATH                 = 0;
	public static final int GRASS                = 1;
	public static final int WALL_ISLAND          = 2;
	public static final int WALL_END_UP          = 3;
	public static final int WALL_JUNC_4          = 4;
	public static final int WALL_END_DOWN        = 5;
	public static final int WALL_END_LEFT        = 6;
	public static final int WALL_END_RIGHT       = 7;
	public static final int WALL_JUNC_2V         = 8;
	public static final int WALL_JUNC_2H         = 9;
	public static final int WALL_LEFT            = 10;
	public static final int WALL_RIGHT           = 11;
	public static final int WALL_UP              = 12;
	public static final int WALL_DOWN            = 13;
	public static final int WALL_LJUNC_UPLEFT    = 14;
	public static final int WALL_LJUNC_UPRIGHT   = 15;
	public static final int WALL_LJUNC_DOWNLEFT  = 16;
	public static final int WALL_LJUNC_DOWNRIGHT = 17;
	public static final int WALL_MIDDLE          = 18;
	public static final int WALL_CORN_UPLEFT     = 19;
	public static final int WALL_CORN_UPRIGHT    = 20;
	public static final int WALL_CORN_DOWNLEFT   = 21;
	public static final int WALL_CORN_DOWNRIGHT  = 22;

	private final int tileSize;
	private Map<Integer, BufferedImage> tiles;
	private Set<Integer> obstacleTiles;
	
	public TileData(File tilesFile) {
		tileSize = 32;
		tiles = new HashMap<>();
		obstacleTiles = new HashSet<>();
		initObstacleTiles();
		try {
			BufferedImage tilesImage = PngParser.getImage(tilesFile);
			makeTiles(tilesImage);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
	
	private void initObstacleTiles() {
		for (int i = 2; i <= 22; i++) {
			obstacleTiles.add(i);
		}
	}
	
	private void makeTiles(BufferedImage tilesImage) {
		for (int i = 0; i < 64; i++) {
			int x = i % IMAGE_SIZE;
			int y = i / IMAGE_SIZE;
			BufferedImage thisImage = tilesImage.getSubimage(x * tileSize, y * tileSize, tileSize, tileSize);
			tiles.put(i, thisImage);
		}
	}
	
	public BufferedImage tileOf(Node n) {
		return tileOf(n.getType());
	}
	
	public BufferedImage tileOf(int nodeType) {
		return tiles.get(nodeType);
	}

	public int getTileSize() {
		return tileSize;
	}

}
