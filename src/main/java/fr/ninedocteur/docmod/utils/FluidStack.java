package fr.ninedocteur.docmod.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.ResourceAmount;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

public class FluidStack {
    public static final Codec<FluidStack> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Registries.FLUID.getCodec().fieldOf("FluidName").forGetter(FluidStack::getFluid),
                    Codec.LONG.fieldOf("Amount").forGetter(FluidStack::getAmount),
                    NbtCompound.CODEC.optionalFieldOf("VariantTag", null).forGetter(fluidStack -> fluidStack.getType().copyNbt()),
                    NbtCompound.CODEC.optionalFieldOf("Tag").forGetter(stack -> Optional.ofNullable(stack.getTag()))
            ).apply(instance, (fluid, amount, variantTag, tag) -> {
                FluidStack stack = new FluidStack(fluid, amount, variantTag);
                tag.ifPresent(stack::setTag);
                return stack;
            })
    );

    public static final FluidStack EMPTY = new FluidStack(FluidVariant.blank(), 0) {
        @Override
        public FluidStack setAmount(long amount) {
            return this;
        }

        @Override
        public void shrink(int amount) {
        }

        @Override
        public void shrink(long amount) {
        }

        @Override
        public void grow(long amount) {
        }

        @Override
        public void setTag(NbtCompound tag) {
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public FluidStack copy() {
            return this;
        }
    };

    private final FluidVariant type;
    @Nullable
    private NbtCompound tag;
    private long amount;

    public FluidStack(FluidVariant type, long amount) {
        this.type = type;
        this.amount = amount;
        this.tag = type.copyNbt();
    }

    public FluidStack(FluidVariant type, long amount, @Nullable NbtCompound tag) {
        this(type, amount);
        this.tag = tag;
    }

    public FluidStack(StorageView<FluidVariant> view) {
        this(view.getResource(), view.getAmount());
    }

    public FluidStack(ResourceAmount<FluidVariant> resource) {
        this(resource.resource(), resource.amount());
    }

    /**
     * Avoid this constructor when possible, may result in NBT loss
     */
    public FluidStack(Fluid type, long amount) {
        this(FluidVariant.of(type instanceof FlowableFluid flowing ? flowing.getStill() : type), amount);
    }

    public FluidStack(Fluid type, long amount, @Nullable NbtCompound nbt) {
        this(FluidVariant.of(type instanceof FlowableFluid flowing ? flowing.getStill() : type, nbt), amount);
        this.tag = nbt;
    }

    public FluidStack(FluidStack copy, long amount) {
        this(copy.getType(), amount);
        if (copy.hasTag()) tag = copy.getTag().copy();
    }

    public FluidStack setAmount(long amount) {
        this.amount = amount;
        return this;
    }

    public void grow(long amount) {
        setAmount(getAmount() + amount);
    }

    public FluidVariant getType() {
        return type;
    }

    public Fluid getFluid() {
        return getType().getFluid();
    }

    public long getAmount() {
        return amount;
    }

    public boolean isEmpty() {
        return amount <= 0 || getType().isBlank();
    }

    public void shrink(int amount) {
        setAmount(getAmount() - amount);
    }

    public void shrink(long amount) {
        setAmount(getAmount() - amount);
    }

    public boolean isFluidEqual(FluidStack other) {
        if (this == other) return true;
        return isFluidEqual(other.getType());
    }

    public boolean isFluidEqual(FluidVariant other) {
        return isFluidEqual(getType(), other);
    }

    public static boolean isFluidEqual(FluidVariant mine, FluidVariant other) {
        if (mine == other) return true;
        if (other == null) return false;

        boolean fluidsEqual = mine.isOf(other.getFluid());

        NbtCompound myTag = mine.getNbt();
        NbtCompound theirTag = other.getNbt();
        boolean tagsEqual = Objects.equals(myTag, theirTag);

        return fluidsEqual && tagsEqual;
    }

    public boolean canFill(FluidVariant var) {
        return isEmpty() || var.isOf(getFluid()) && Objects.equals(var.getNbt(), getType().getNbt());
    }

    public NbtCompound writeToNBT(NbtCompound nbt) {
        nbt.put("Variant", getType().toNbt());
        nbt.putLong("Amount", getAmount());
        if (tag != null)
            nbt.put("Tag", tag);
        return nbt;
    }

    public static FluidStack loadFluidStackFromNBT(NbtCompound tag) {
        FluidStack stack;
        if (tag.contains("FluidName")) { // legacy forge loading
            Fluid fluid = Registries.FLUID.get(new Identifier(tag.getString("FluidName")));
            int amount = tag.getInt("Amount");
            if (tag.contains("Tag")) {
                stack = new FluidStack(fluid, amount, tag.getCompound("Tag"));
            } else {
                stack = new FluidStack(fluid, amount);
            }
        } else {
            NbtCompound fluidTag = tag.getCompound("Variant");
            FluidVariant fluid = FluidVariant.fromNbt(fluidTag);
            stack = new FluidStack(fluid, tag.getLong("Amount"));
            if(tag.contains("Tag", NbtElement.COMPOUND_TYPE))
                stack.tag = tag.getCompound("Tag");
        }

        return stack;
    }

    public void setTag(NbtCompound tag) {
        this.tag = tag;
    }

    @Nullable
    public NbtCompound getTag() {
        return tag;
    }

    public NbtCompound getOrCreateTag() {
        if (tag == null) tag = new NbtCompound();
        return tag;
    }

    public void removeChildTag(String key) {
        if (getTag() == null) return;
        getTag().remove(key);
    }

    public Text getDisplayName() {
        return FluidVariantAttributes.getName(this.type);
    }

    public boolean hasTag() {
        return tag != null;
    }

    public static FluidStack fromBuffer(PacketByteBuf buffer) {
        FluidVariant fluid = FluidVariant.fromPacket(buffer);
        long amount = buffer.readVarLong();
        NbtCompound tag = buffer.readNbt();
        if (fluid.isBlank()) return EMPTY;
        return new FluidStack(fluid, amount, tag);
    }

    public PacketByteBuf toBuffer(PacketByteBuf buffer) {
        return toBuffer(this, buffer);
    }

    public static PacketByteBuf toBuffer(FluidStack stack, PacketByteBuf buffer) {
        stack.getType().toPacket(buffer);
        buffer.writeVarLong(stack.getAmount());
        buffer.writeNbt(stack.tag);
        return buffer;
    }

    public FluidStack copy() {
        NbtCompound tag = getTag();
        if (tag != null) tag = tag.copy();
        return new FluidStack(getType(), getAmount(), tag);
    }
}
