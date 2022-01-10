package gregsconstruct;

import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.type.DustMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;

@IMaterialHandler.RegisterMaterialHandler
public class FluidTemp implements IMaterialHandler {
    static {
        for (Material mat : Material.MATERIAL_REGISTRY)
            if (mat instanceof DustMaterial && (mat instanceof IngotMaterial || mat.hasFlag(DustMaterial.MatFlags.SMELT_INTO_FLUID))) {
                if (mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0)
                    ((IngotMaterial) mat).setFluidTemperature(((IngotMaterial) mat).blastFurnaceTemperature);
                else
                    ((DustMaterial) mat).setFluidTemperature(500);
            }
    }

    @Override
    public void onMaterialsInit() {
        /*
         * idk what to do here, so ima leave it blank
         * GregTech asked me to do it:
         *
         * > [Client thread/ERROR] [gregtech]: Failed to load material handler class gregsconstruct.FluidTemp from GregsConstruct-1.12.2-1.0.2-UNOFFICIAL.jar: class does not implement IMaterialHandler
         * > [Client thread/ERROR] [gregtech]: Consult the mod author for fix on his side.
         *
         */
    }
}
