package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.WeightedRandom</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class WeightedRandomHandle extends Template.Handle {
    /** @See {@link WeightedRandomClass} */
    public static final WeightedRandomClass T = new WeightedRandomClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(WeightedRandomHandle.class, "net.minecraft.server.WeightedRandom");

    /* ============================================================================== */

    public static WeightedRandomHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    /* ============================================================================== */

    /**
     * Stores class members for <b>net.minecraft.server.WeightedRandom</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class WeightedRandomClass extends Template.Class<WeightedRandomHandle> {
    }


    /**
     * Instance wrapper handle for type <b>net.minecraft.server.WeightedRandom.WeightedRandomChoice</b>.
     * To access members without creating a handle type, use the static {@link #T} member.
     * New handles can be created from raw instances using {@link #createHandle(Object)}.
     */
    public abstract static class WeightedRandomChoiceHandle extends Template.Handle {
        /** @See {@link WeightedRandomChoiceClass} */
        public static final WeightedRandomChoiceClass T = new WeightedRandomChoiceClass();
        static final StaticInitHelper _init_helper = new StaticInitHelper(WeightedRandomChoiceHandle.class, "net.minecraft.server.WeightedRandom.WeightedRandomChoice");

        /* ============================================================================== */

        public static WeightedRandomChoiceHandle createHandle(Object handleInstance) {
            return T.createHandle(handleInstance);
        }

        /* ============================================================================== */

        public abstract int getChance();
        public abstract void setChance(int value);
        /**
         * Stores class members for <b>net.minecraft.server.WeightedRandom.WeightedRandomChoice</b>.
         * Methods, fields, and constructors can be used without using Handle Objects.
         */
        public static final class WeightedRandomChoiceClass extends Template.Class<WeightedRandomChoiceHandle> {
            public final Template.Field.Integer chance = new Template.Field.Integer();

        }

    }

}

