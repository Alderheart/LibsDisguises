package me.libraryaddict.disguise.disguisetypes.watchers;

import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.utilities.reflection.NmsAddedIn;
import me.libraryaddict.disguise.utilities.reflection.NmsVersion;
import org.bukkit.inventory.MainHand;

public class InsentientWatcher extends LivingWatcher {
    public InsentientWatcher(Disguise disguise) {
        super(disguise);
    }

    public MainHand getMainHand() {
        return getInsentientFlag(2) ? MainHand.RIGHT : MainHand.LEFT;
    }

    public void setMainHand(MainHand mainHand) {
        setInsentientFlag(2, mainHand == MainHand.RIGHT);
        sendData(MetaIndex.INSENTIENT_META);
    }

    public boolean isAI() {
        return getInsentientFlag(1);
    }

    public void setAI(boolean ai) {
        setInsentientFlag(1, ai);
        sendData(MetaIndex.INSENTIENT_META);
    }

    private void setInsentientFlag(int i, boolean flag) {
        byte b0 = getData(MetaIndex.INSENTIENT_META);

        if (flag) {
            setData(MetaIndex.INSENTIENT_META, (byte) (b0 | i));
        } else {
            setData(MetaIndex.INSENTIENT_META, (byte) (b0 & i));
        }
    }

    private boolean getInsentientFlag(int i) {
        return (getData(MetaIndex.INSENTIENT_META) & i) != 0;
    }

    @NmsAddedIn(val = NmsVersion.v1_14)
    public boolean isEnraged() {
        return getInsentientFlag(4);
    }

    @NmsAddedIn(val = NmsVersion.v1_14)
    public void setEnraged(boolean enraged) {
        setInsentientFlag(4, enraged);
        sendData(MetaIndex.INSENTIENT_META);
    }
}
