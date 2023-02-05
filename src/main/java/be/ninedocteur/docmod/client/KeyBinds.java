package be.ninedocteur.docmod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {
    public static final String KEY_CATEGORY = "key.category.docmod";
    public static final String KEY_DEVMODE = "key.docmod.dev_mod";

    public static final String KEY_ENTER = "key.docmod.enter";

    public static final String KEY_DEBUG = "key.docmod.debug";

    public static final String CATEGORY = "keys.jobs.category";
    public static final String OPEN_GUI = "keys.jobs.open_gui";

    public static final KeyMapping JOBS_KEY = new KeyMapping(OPEN_GUI, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, CATEGORY);

    public static final KeyMapping DEV_MODE_KEY = new KeyMapping(KEY_DEVMODE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_U, KEY_CATEGORY);

    public static final KeyMapping ENTER_KEY = new KeyMapping(KEY_ENTER, KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_ENTER, KEY_CATEGORY);

    public static final KeyMapping DEBUG_MODE_KEY = new KeyMapping(KEY_DEBUG, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F4, KEY_CATEGORY);

}
