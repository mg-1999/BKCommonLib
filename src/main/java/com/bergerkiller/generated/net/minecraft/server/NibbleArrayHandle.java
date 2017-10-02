package com.bergerkiller.generated.net.minecraft.server;

import com.bergerkiller.mountiplex.reflection.util.StaticInitHelper;
import com.bergerkiller.mountiplex.reflection.declarations.Template;

/**
 * Instance wrapper handle for type <b>net.minecraft.server.NibbleArray</b>.
 * To access members without creating a handle type, use the static {@link #T} member.
 * New handles can be created from raw instances using {@link #createHandle(Object)}.
 */
public abstract class NibbleArrayHandle extends Template.Handle {
    /** @See {@link NibbleArrayClass} */
    public static final NibbleArrayClass T = new NibbleArrayClass();
    static final StaticInitHelper _init_helper = new StaticInitHelper(NibbleArrayHandle.class, "net.minecraft.server.NibbleArray");

    /* ============================================================================== */

    public static NibbleArrayHandle createHandle(Object handleInstance) {
        return T.createHandle(handleInstance);
    }

    public static final NibbleArrayHandle createNew() {
        return T.constr.newInstance();
    }

    public static final NibbleArrayHandle createNew(byte[] data) {
        return T.constr_data.newInstance(data);
    }

    /* ============================================================================== */

    public abstract int get(int x, int y, int z);
    public abstract void set(int x, int y, int z, int nibbleValue);
    public abstract byte[] getData();
    public abstract void setData(byte[] value);
    /**
     * Stores class members for <b>net.minecraft.server.NibbleArray</b>.
     * Methods, fields, and constructors can be used without using Handle Objects.
     */
    public static final class NibbleArrayClass extends Template.Class<NibbleArrayHandle> {
        public final Template.Constructor.Converted<NibbleArrayHandle> constr = new Template.Constructor.Converted<NibbleArrayHandle>();
        public final Template.Constructor.Converted<NibbleArrayHandle> constr_data = new Template.Constructor.Converted<NibbleArrayHandle>();

        public final Template.Field<byte[]> data = new Template.Field<byte[]>();

        public final Template.Method<Integer> get = new Template.Method<Integer>();
        public final Template.Method<Void> set = new Template.Method<Void>();

    }

}

