package me.libraryaddict.disguise.utilities.sounds;

import lombok.Getter;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.utilities.reflection.ReflectionManager;
import org.bukkit.Sound;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by libraryaddict on 23/05/2020.
 */
public class SoundGroup {
    public enum SoundType {
        CANCEL,
        DEATH,
        HURT,
        IDLE,
        STEP
    }

    @Getter
    private final static HashMap<String, SoundGroup> groups = new HashMap<>();
    private float damageSoundVolume = 1F;
    @Getter
    private final LinkedHashMap<Object, SoundType> disguiseSounds = new LinkedHashMap<>();

    public SoundGroup(String name) {
        groups.put(name, this);
    }

    public void addSound(Object sound, SoundType type) {
        if (sound instanceof Sound) {
            sound = ReflectionManager.getCraftSound((Sound) sound);
        } else if (sound instanceof String) {
            sound = ReflectionManager.createSoundEffect((String) sound);
        } else if (!sound.getClass().getName().equals("SoundEffect")) {
            throw new IllegalArgumentException();
        }

        if (sound == null) {
            return;
        }

        disguiseSounds.put(sound, type);
    }

    public float getDamageAndIdleSoundVolume() {
        return damageSoundVolume;
    }

    public void setDamageAndIdleSoundVolume(float strength) {
        this.damageSoundVolume = strength;
    }

    public Object getSound(SoundType type) {
        if (type == null) {
            return null;
        }

        for (Map.Entry<Object, SoundType> entry : disguiseSounds.entrySet()) {
            if (entry.getValue() != type) {
                continue;
            }

            return entry.getKey();
        }

        return null;
    }

    public SoundType getSound(Object sound) {
        if (sound == null) {
            return null;
        }

        return disguiseSounds.get(sound);
    }

    /**
     * Used to check if this sound name is owned by this disguise sound.
     */
    public SoundType getType(Object sound, boolean ignoreDamage) {
        if (sound == null) {
            return SoundType.CANCEL;
        }

        SoundType soundType = getSound(sound);

        if (soundType == SoundType.DEATH || (ignoreDamage && soundType == SoundType.HURT)) {
            return null;
        }

        return soundType;
    }

    public boolean isCancelSound(String sound) {
        return getSound(sound) == SoundType.CANCEL;
    }

    public static SoundGroup getGroup(Disguise disguise) {
        if (disguise.getSoundGroup() != null) {
            return getGroup(disguise.getSoundGroup());
        }

        return getGroup(disguise.getType().name());
    }

    public static SoundGroup getGroup(String name) {
        return groups.get(name);
    }
}
