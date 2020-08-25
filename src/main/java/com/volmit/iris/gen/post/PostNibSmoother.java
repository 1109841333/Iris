package com.volmit.iris.gen.post;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator.ChunkData;

import com.volmit.iris.gen.PostBlockChunkGenerator;
import com.volmit.iris.util.DontObfuscate;
import com.volmit.iris.util.IrisPostBlockFilter;

@Post("nib-smoother")
public class PostNibSmoother extends IrisPostBlockFilter
{
	@DontObfuscate
	public PostNibSmoother(PostBlockChunkGenerator gen, int phase)
	{
		super(gen, phase);
	}

	@DontObfuscate
	public PostNibSmoother(PostBlockChunkGenerator gen)
	{
		this(gen, 0);
	}

	@Override
	public void onPost(int x, int z, int currentPostX, int currentPostZ, ChunkData currentData)
	{
		int g = 0;
		int h = highestTerrainBlock(x, z);
		int ha = highestTerrainBlock(x + 1, z);
		int hb = highestTerrainBlock(x, z + 1);
		int hc = highestTerrainBlock(x - 1, z);
		int hd = highestTerrainBlock(x, z - 1);
		g += ha == h - 1 ? 1 : 0;
		g += hb == h - 1 ? 1 : 0;
		g += hc == h - 1 ? 1 : 0;
		g += hd == h - 1 ? 1 : 0;

		if(g >= 3)
		{
			BlockData bc = getPostBlock(x, h, z, currentPostX, currentPostZ, currentData);
			BlockData b = getPostBlock(x, h + 1, z, currentPostX, currentPostZ, currentData);
			Material m = bc.getMaterial();

			if(m.isSolid())
			{
				setPostBlock(x, h, z, b, currentPostX, currentPostZ, currentData);
				updateHeight(x, z, h - 1);
			}
		}
	}
}
