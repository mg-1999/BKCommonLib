package com.bergerkiller.bukkit.common.map.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bergerkiller.bukkit.common.math.Matrix4x4;
import com.bergerkiller.bukkit.common.wrappers.BlockRenderOptions;
import com.google.gson.annotations.SerializedName;

/**
 * A block can be in various states (facing, color, type, etc.).
 * When found, a BlockModelState gives information about this.
 */
public class BlockModelState {
    public List<Multipart> multipart;

    // Note: is VariantList because 'normal' (normal distribution) keys use randomness
    // to select a random variant at render time. We always return only the first variant.
    public Map<Condition, VariantList> variants;

    /**
     * Finds all variants that are displayed based on Block data options
     * 
     * @param options of the block
     * @return variants that are displayed
     */
    public VariantList findVariants(BlockRenderOptions options) {
        VariantList result = new VariantList();
        if (this.multipart != null) {
            for (Multipart part : multipart) {
                if (part.when.has(options)) {
                    result.addAll(part.apply);
                }
            }
        } else if (this.variants != null) {
            for (Map.Entry<Condition, VariantList> entry : variants.entrySet()) {
                if (entry.getKey().has(options) && !entry.getValue().isEmpty()) {
                    result.add(entry.getValue().get(0));
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('{');
        if (this.multipart != null && !this.multipart.isEmpty()) {
            result.append("multipart=[");
            boolean first = true;
            for (Multipart part : this.multipart) {
                if (first) {
                    first = false;
                } else {
                    result.append(", ");
                }
                result.append(part);
            }
            result.append("]\n");
        }
        if (this.variants != null && !this.variants.isEmpty()) {
            result.append("variants={");
            for (Map.Entry<Condition, VariantList> entry : this.variants.entrySet()) {
                result.append(entry.getKey()).append(" -> ").append(entry.getValue());
                result.append('\n');
            }
            result.append("}\n");
        }
        result.append('}');
        return result.toString();
    }

    public static class Multipart {
        public Condition when = Condition.ALWAYS;
        public VariantList apply;
    }

    public static class Variant {
        @SerializedName("model")
        public String modelName;

        @SerializedName("x")
        public float rotationX = 0.0f;

        @SerializedName("y")
        public float rotationY = 0.0f;

        @SerializedName("z")
        public float rotationZ = 0.0f;

        public boolean uvlock = false;

        public void update(Model model) {
            if (this.rotationX != 0.0f || this.rotationY != 0.0f || this.rotationZ != 0.0f) {
                for (Model.Element element : model.elements) {
                    if (element.transform == null) {
                        element.transform = new Matrix4x4();
                    }
                    element.transform.translate(8.0, 8.0, 8.0);
                    element.transform.rotateY(-this.rotationY);
                    element.transform.rotateX(-this.rotationX);
                    element.transform.rotateZ(-this.rotationZ);
                    element.transform.translate(-8.0, -8.0, -8.0);
                }
            }
        }

        @Override
        public String toString() {
            return "{modelName=" + this.modelName +
                    ", rotX=" + this.rotationX +
                    ", rotY=" + this.rotationY +
                    ", rotZ=" + this.rotationZ +
                    ", uvlock=" + this.uvlock + "}";
        }
    }

    public static class VariantList extends ArrayList<Variant> {
        private static final long serialVersionUID = 1L;

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append('[');
            boolean first = true;
            for (Variant variant : this) {
                if (first) {
                    first = false;
                } else {
                    result.append(", ");
                }
                result.append("\n  ");
                result.append(variant.toString());
            }
            result.append("\n]");
            return result.toString();
        }
    }

    /**
     * A single condition node in a condition tree hierarchy
     */
    public static class Condition {
        public String key = null;
        public String value = null;
        public Mode mode = Mode.SELF;
        public List<Condition> conditions;

        public static final Condition ALWAYS = new Condition() {
            @Override
            public boolean has(Map<String, String> options) {
                return true;
            }
        };

        public boolean has(Map<String, String> options) {
            if (this.mode == Mode.AND) {
                // AND mode
                if (this.conditions == null || this.conditions.isEmpty()) {
                    return false;
                }
                for (Condition condition : this.conditions) {
                    if (!condition.has(options)) {
                        return false;
                    }
                }
                return true;
            } else if (this.mode == Mode.OR) {
                // OR mode
                if (this.conditions == null || this.conditions.isEmpty()) {
                    return false;
                }
                for (Condition condition : this.conditions) {
                    if (condition.has(options)) {
                        return true;
                    }
                }
                return false;
            } else {
                // 'normal' key is for normal distribution only
                // This one always passes
                if (this.key.equals("normal") && this.value.equals("")) {
                    return true;
                }

                // Check own key-value pair
                String option = options.get(this.key);
                if (option == null) {
                    return false;
                }

                // Values with an OR (|) need each individual part checked
                if (option.contains("|")) {
                    for (String part : option.split("\\|")) {
                        if (part.equals(this.value)) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    return option.equals(this.value);
                }
            }
        }

        @Override
        public String toString() {
            if (this.mode == Mode.SELF) {
                return "{" + this.key + "=" + this.value + "}";
            } else {
                StringBuilder result = new StringBuilder();
                result.append("{");
                boolean first = true;
                for (Condition condition : this.conditions) {
                    if (first) {
                        first = false;
                    } else if (this.mode == Mode.OR) {
                        result.append(" OR ");
                    } else {
                        result.append(" AND ");
                    }
                    result.append(condition.toString());
                }
                result.append("}");
                return result.toString();
            }
        }

        public static enum Mode {
            SELF, AND, OR
        }
    }
}
