package gregsconstruct;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.type.*;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.init.Items;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

import java.util.ArrayList;

import static gregtech.api.unification.material.Materials.*;

public class Materials {
    public static Logger logger = LogManager.getLogger(GregsConstruct.MODID);
    private static final ArrayList<slimeknights.tconstruct.library.materials.Material> ingotMaterials = new ArrayList<>();
    private static final ArrayList<IngotMaterial> GtIngotMaterials = new ArrayList<>();
    private static final ArrayList<slimeknights.tconstruct.library.materials.Material> gemMaterials = new ArrayList<>();
    private static final ArrayList<GemMaterial> GtGemMaterials = new ArrayList<>();

    public static void preInit() {
        for (Material mat : Material.MATERIAL_REGISTRY) {
            if (mat instanceof IngotMaterial) {
                if (mat != Iron && mat != Cobalt && mat != Copper && mat != Bronze && mat != Lead && mat != Electrum && mat != Silver && mat != Steel && mat != PigIron) {
                    if (((SolidMaterial) mat).toolDurability > 0) {
                        ingotMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(true).setCraftable(false));
                        GtIngotMaterials.add((IngotMaterial) mat);
                    } else
                        TinkerRegistry.integrate(((IngotMaterial) mat).getMaterialFluid(), upperCase(mat));
                }
                if (((IngotMaterial) mat).blastFurnaceTemperature <= 0) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), ((IngotMaterial) mat).getMaterialFluid(), (int) (144 * ((IngotMaterial) mat).oreMultiplier * Config.oreToIngotRatio));
                }
            }
            if (mat instanceof GemMaterial && ((GemMaterial) mat).toolDurability > 0) {
                gemMaterials.add(new slimeknights.tconstruct.library.materials.Material(mat.toString(), mat.materialRGB).setCastable(false).setCraftable(true));
                GtGemMaterials.add((GemMaterial) mat);
            }
            if (mat instanceof DustMaterial && !(mat instanceof IngotMaterial)) {
                DustMaterial dust = (DustMaterial) mat;
                if (dust.directSmelting != null) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.ore, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreSand, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreRedgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreNetherrack, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreMarble, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreGravel, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreEndstone, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBlackgranite, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.oreBasalt, mat).toString(), dust.directSmelting.getMaterialFluid(), (int) (144 * dust.oreMultiplier * Config.oreToIngotRatio));
                } else if (dust.hasFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID) && mat != Glass && mat != Ice) {
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dust, mat).toString(), dust.getMaterialFluid(), 144);
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustSmall, mat).toString(), dust.getMaterialFluid(), 36);
                    TinkerRegistry.registerMelting(new UnificationEntry(OrePrefix.dustTiny, mat).toString(), dust.getMaterialFluid(), 16);
                }
            }
        }

        for (int i = 0; i < ingotMaterials.size(); i++) {
            slimeknights.tconstruct.library.materials.Material mat = ingotMaterials.get(i);
            logger.debug("Registering " + mat.identifier + " as a material ingot");

            if (TinkerRegistry.getMaterial(mat.identifier) != slimeknights.tconstruct.library.materials.Material.UNKNOWN) {
                // warn
                ModContainer registeredBy = TinkerRegistry.getTrace(mat);
                logger.warn(String.format("Skipping ingot material \"%s\": It was already registered by %s", mat.identifier, registeredBy.getName()));
                continue;
            }

            IngotMaterial GtMat = GtIngotMaterials.get(i);
            mat.addCommonItems(upperCase(GtMat));
            mat.setFluid(GtMat.getMaterialFluid());
            mat.addItemIngot(new UnificationEntry(OrePrefix.ingot, GtMat).toString());
            mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.ingot, GtMat));
            TinkerRegistry.addMaterial(mat);
            TinkerRegistry.addMaterialStats(mat, new HeadMaterialStats((int) (GtMat.toolDurability * 0.8), GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel), new HandleMaterialStats((GtMat.harvestLevel - 0.5f) / 2, GtMat.toolDurability / 3), new ExtraMaterialStats(GtMat.toolDurability / 4));
            TinkerRegistry.integrate(mat, mat.getFluid(), upperCase(GtMat));
            logger.debug("Registered " + mat.identifier);
        }


        for (int i = 0; i < gemMaterials.size(); i++) {

            slimeknights.tconstruct.library.materials.Material mat = gemMaterials.get(i);

            logger.debug("Registering " + mat.identifier + " as a material gem");

            if (TinkerRegistry.getMaterial(mat.identifier) != slimeknights.tconstruct.library.materials.Material.UNKNOWN) {
                // warn
                ModContainer registeredBy = TinkerRegistry.getTrace(mat);
                logger.warn(String.format("Skipping gem material \"%s\": It was already registered by %s", mat.identifier, registeredBy.getName()));
                continue;
            }

            GemMaterial GtMat = GtGemMaterials.get(i);
            mat.addCommonItems(upperCase(GtMat));
            mat.addItemIngot(new UnificationEntry(OrePrefix.gem, GtMat).toString());
            mat.setRepresentativeItem(OreDictUnifier.get(OrePrefix.gem, GtMat));
            TinkerRegistry.addMaterial(mat);
            TinkerRegistry.addMaterialStats(mat, new HeadMaterialStats(GtMat.toolDurability, GtMat.toolSpeed, GtMat.toolAttackDamage, GtMat.harvestLevel), new HandleMaterialStats(GtMat.harvestLevel - 0.5f, GtMat.toolDurability / 4), new ExtraMaterialStats(GtMat.toolDurability / 100));
            TinkerRegistry.integrate(mat, upperCase(GtMat));
            logger.debug("Registered " + mat.identifier);
        }

        TinkerRegistry.registerAlloy(Brass.getFluid(4), Copper.getFluid(3), Zinc.getFluid(1));
        TinkerRegistry.registerAlloy(Cupronickel.getFluid(2), Copper.getFluid(1), Nickel.getFluid(1));
        TinkerRegistry.registerAlloy(RedAlloy.getFluid(1), Copper.getFluid(1), Redstone.getFluid(4));
        TinkerRegistry.registerAlloy(Brass.getFluid(4), AnnealedCopper.getFluid(3), Zinc.getFluid(1));
        TinkerRegistry.registerAlloy(Cupronickel.getFluid(2), AnnealedCopper.getFluid(1), Nickel.getFluid(1));
        TinkerRegistry.registerAlloy(RedAlloy.getFluid(1), AnnealedCopper.getFluid(1), Redstone.getFluid(4));
        TinkerRegistry.registerAlloy(TinAlloy.getFluid(2), Iron.getFluid(1), Tin.getFluid(1));
        TinkerRegistry.registerAlloy(Invar.getFluid(3), Iron.getFluid(1), Nickel.getFluid(1));
        TinkerRegistry.registerAlloy(TinAlloy.getFluid(2), WroughtIron.getFluid(1), Tin.getFluid(1));
        TinkerRegistry.registerAlloy(Invar.getFluid(3), WroughtIron.getFluid(1), Nickel.getFluid(1));
        TinkerRegistry.registerAlloy(BatteryAlloy.getFluid(5), Lead.getFluid(4), Antimony.getFluid(1));
        TinkerRegistry.registerAlloy(SolderingAlloy.getFluid(10), Tin.getFluid(9), Antimony.getFluid(1));
        TinkerRegistry.registerAlloy(Magnalium.getFluid(3), Aluminium.getFluid(2), Magnesium.getFluid(1));
        TinkerRegistry.registerAlloy(CobaltBrass.getFluid(9), Brass.getFluid(7), Aluminium.getFluid(1), Sodium.getFluid(1));

        TinkerRegistry.registerMelting("dustGlass", Glass.getMaterialFluid(), 1000);
        TinkerRegistry.registerMelting("dustQuartzite", Glass.getMaterialFluid(), 1000);
        TinkerRegistry.registerMelting("plateGlass", Glass.getMaterialFluid(), 1000);
        TinkerRegistry.registerMelting(Items.GLASS_BOTTLE, Glass.getMaterialFluid(), 1000);
        TinkerRegistry.registerMelting("gemGlass", Glass.getMaterialFluid(), 1000);
    }

    private static String upperCase(Material mat) {
        return mat.toCamelCaseString().substring(0, 1).toUpperCase() + mat.toCamelCaseString().substring(1);
    }
}
