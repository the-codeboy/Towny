package com.palmergames.bukkit.towny.database.dbHandlers;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.database.handler.LoadHandler;
import com.palmergames.bukkit.towny.database.handler.SaveHandler;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownBlock;
import com.palmergames.bukkit.towny.object.TownyWorld;
import com.palmergames.bukkit.towny.database.handler.LoadContext;
import com.palmergames.bukkit.towny.database.handler.SaveContext;
import com.palmergames.bukkit.towny.database.handler.SerializationHandler;
import com.palmergames.bukkit.towny.database.handler.SQLData;

public class TownBlockHandler implements LoadHandler<TownBlock>, SaveHandler<TownBlock> {

	@Override
	public TownBlock loadString(LoadContext context, String str) {
		
		String[] townBlockElements = str.split(",");
		try {
			TownyWorld world = getWorld(townBlockElements[0]);

			try {
				int x = Integer.parseInt(townBlockElements[1]);
				int z = Integer.parseInt(townBlockElements[2]);
				return world.getTownBlock(x, z);
			} catch (NumberFormatException e) {
				TownyMessaging.sendErrorMsg("[Warning] homeBlock tried to loadString invalid location.");
			} catch (NotRegisteredException e) {
				TownyMessaging.sendErrorMsg("[Warning] homeBlock tried to loadString invalid TownBlock.");
			}

		} catch (NotRegisteredException e) {
			TownyMessaging.sendErrorMsg("[Warning] homeBlock tried to loadString invalid world.");
		}
		
		return null;
	}

	@Override
	public TownBlock loadSQL(LoadContext context, Object result) {
		return null;
	}

	private TownyWorld getWorld(String name) throws NotRegisteredException {

		TownyWorld world = TownyUniverse.getInstance().getWorldMap().get(name.toLowerCase());

		if (world == null)
			throw new NotRegisteredException("World not registered!");

		return world;
	}
	
	@Override
	public String getFileString(SaveContext context, TownBlock obj) {
		return "";
	}

	@Override
	public SQLData getSQL(SaveContext context, TownBlock obj) {
		return null;
	}
}
