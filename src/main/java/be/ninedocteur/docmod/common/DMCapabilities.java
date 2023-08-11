package be.ninedocteur.docmod.common;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class DMCapabilities {

    public static final Capability<ITardis> TARDIS = CapabilityManager.get(new CapabilityToken<ITardis>(){});

    public static void register(RegisterCapabilitiesEvent event){
        event.register(ITardis.class);
    }
}
