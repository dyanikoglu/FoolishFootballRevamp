/*
 * Copyright Samuel Halliday 2009
 * 
 * This file is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This file is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this file.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package uk.me.fommil.ff;

import com.google.common.base.Preconditions;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import uk.me.fommil.ff.GameView.Action;

/**
 * Contains all the physics information regarding a {@link Player} during game play.
 *
 * @author Samuel Halliday
 */
public class PlayerModel {

	private final Player player;
	private final int i;
	private Point location;
	private Collection<Action> actions;
	private Point step;

	/**
	 * @param i
	 * @param player
	 */
	public PlayerModel(int i, Player player) {
		Preconditions.checkArgument(i >= 0 && i < 11, i);
		Preconditions.checkNotNull(player);

		this.i = i;
		this.player = player;
	}

	public void tick(long millis) {
		if (actions == null)
			return;

		int rate = (int) millis / 100;

		int x = 0;
		int y = 0;
		for (Action action : actions) {
			switch (action) {
				case UP:
					y -= 5;
					break;
				case DOWN:
					y += 5;
					break;
				case LEFT:
					x -= 5;
					break;
				case RIGHT:
					x += 5;
					break;
			}
		}
		step = new Point(x, y);
		location = new Point(location.x + rate * x, location.y + rate * y);
	}

	public Rectangle2D getBounds() {
		return new Rectangle.Double(location.getX() - 4, location.getY() - 4, 9, 9);
	}

	public void setActions(Collection<Action> actions) {
		this.actions = actions;
	}

	public void setLocation(Point p) {
		location = p;
	}

	public Point getLocation() {
		return location;
	}

	public Point getLastStep() {
		return step;
	}
}
