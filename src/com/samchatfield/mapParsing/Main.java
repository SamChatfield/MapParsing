package com.samchatfield.mapParsing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main extends JPanel {
	
	private static final long serialVersionUID = 895612050776703409L;

	public Main() {
		setPreferredSize(new Dimension(800, 800));
		File mapFile = new File("src/com/samchatfield/mapParsing/res/map.png");
		File tilesFile = new File("src/com/samchatfield/mapParsing/res/tiles.png");
		
		TileData tileData = new TileData(tilesFile);
		
		MapData map = new MapData(mapFile, tileData);
		MapView mapView = new MapView(tileData, map);
		
		setLayout(new BorderLayout());
		add(mapView, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Map Rendering");
		f.setSize(900, 900);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		f.add(new Main());
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
	}
	
}
