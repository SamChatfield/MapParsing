package com.samchatfield.mapParsing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class MapView extends JPanel {

	private static final long serialVersionUID = 3511162477906406980L;

	private final TileData tdata;
	private final MapData map;

	public MapView(TileData tdata, MapData map) {
		this.tdata = tdata;
		this.map = map;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		drawNodes(g2d);
	}

	private void drawNodes(Graphics2D g2d) {
		// Map width and height
		int mw = map.getWidth();
		int mh = map.getHeight();

		// Scale factor map dimensions -> view dimensions
		double sf = tdata.getTileSize();
		Rectangle2D[][] nodeRects = new Rectangle2D.Double[mw][mh];

		for (int y = 0; y < mh; y++) {
			for (int x = 0; x < mw; x++) {
				Node n = map.getNodes()[x][y];
				nodeRects[x][y] = new Rectangle2D.Double(n.getX() * sf, n.getY() * sf, sf, sf);

				g2d.drawImage(tdata.tileOf(n), (int) (n.getX() * sf), (int) (n.getY() * sf), null);
				g2d.setColor(Color.BLACK);
			}
		}
	}

}
