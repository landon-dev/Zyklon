package respectful.rapist.client.module.modules;

import respectful.rapist.client.mapping.Mappings;
import respectful.rapist.client.module.Module;
import respectful.rapist.client.util.Config;
import respectful.rapist.client.util.Random;
import respectful.rapist.client.util.Timer;

public class AutoClicker extends Module {
    public float minCPS = 9.0F, maxCPS = 14.0F;
    public int[] itemWhitelist = {267, 276, 272, 283, 268};
    public boolean reqItem, held;
    private Timer timer = new Timer();

    public AutoClicker() {
        super(19, "AutoClicker", "FF0000", false);
    }

    @Override
    public void onTick() {
        if (Config.safe(reqItem, itemWhitelist, true)) {
            if (timer.elapsed((long) Random.nextFloat(1000.0F / minCPS, 1000.0F / maxCPS)) && !held) {
                Mappings.KeyBinding.setKeyBindState(-100, true);
                Mappings.KeyBinding.onTick(-100);
                held = true;
                timer = new Timer();
            } else if (timer.elapsed(Random.nextInt(15, 30)) && held) {
                Mappings.KeyBinding.setKeyBindState(-100, false);
                held = false;
            }
        }
    }
}
