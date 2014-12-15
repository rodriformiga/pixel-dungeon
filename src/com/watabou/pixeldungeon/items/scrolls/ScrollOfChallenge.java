/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.items.scrolls;

import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;
import com.watabou.pixeldungeon.Assets;
import com.watabou.pixeldungeon.Dungeon;
import com.nyrds.pixeldungeon.ml.R;
import com.watabou.pixeldungeon.actors.buffs.Invisibility;
import com.watabou.pixeldungeon.actors.mobs.Mob;
import com.watabou.pixeldungeon.effects.Speck;
import com.watabou.pixeldungeon.utils.GLog;

public class ScrollOfChallenge extends Scroll {

	{
		name = Game.getVar(R.string.ScrollOfChallenge_Name);
	}
	
	@Override
	protected void doRead() {
		
		for (Mob mob : Dungeon.level.mobs) {
			mob.beckon( curUser.pos );
		}
		
		GLog.w(Game.getVar(R.string.ScrollOfChallenge_Info1));
		setKnown();
		
		curUser.sprite.centerEmitter().start( Speck.factory( Speck.SCREAM ), 0.3f, 3 );		
		Sample.INSTANCE.play( Assets.SND_CHALLENGE );
		Invisibility.dispel();
		
		curUser.spendAndNext( TIME_TO_READ );
	}
	
	@Override
	public String desc() {
		return Game.getVar(R.string.ScrollOfChallenge_Info);
	}
}
