package storagecraft.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import storagecraft.tile.TileGrid;

public class SlotGridCraftingResult extends SlotCrafting
{
	private IInventory craftingMatrix;
	private TileGrid grid;

	public SlotGridCraftingResult(EntityPlayer player, IInventory craftingMatrix, IInventory craftingResult, TileGrid grid, int id, int x, int y)
	{
		super(player, craftingMatrix, craftingResult, id, x, y);

		this.craftingMatrix = craftingMatrix;
		this.grid = grid;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
	{
		ItemStack[] matrixSlots = new ItemStack[craftingMatrix.getSizeInventory()];

		for (int i = 0; i < craftingMatrix.getSizeInventory(); ++i)
		{
			if (craftingMatrix.getStackInSlot(i) != null)
			{
				matrixSlots[i] = craftingMatrix.getStackInSlot(i).copy();
			}
		}

		super.onPickupFromSlot(player, stack);

		grid.onCrafted(matrixSlots);
	}
}
