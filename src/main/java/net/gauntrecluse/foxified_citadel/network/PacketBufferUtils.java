package net.gauntrecluse.foxified_citadel.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;

/**
 * Utilities for interacting with PacketBuffer.
 *
 * @author cpw
 * @since 1.0.0
 */
/*Imported directly from Citadel with some formatting changes and a few notes.
Could not find any specific links or source for this Class in relation to cpw.
* cpw's GitHub can be found here, though: https://github.com/cpw; go give him a look, he's pretty cool.
-GauntRecluse*/
public class PacketBufferUtils {

    /**
     * @param toCount The number to analyze
     * @return The number of bytes it will take to write it (max. 5)
     */
    public static int varIntByteCount(int toCount) {
        return (toCount & 0xFFFFFF80) == 0 ? 1 : ((toCount & 0xFFFFC000) == 0 ? 2 : ((toCount & 0xFFE00000) == 0 ? 3 : ((toCount & 0xF0000000) == 0 ? 4 : 5)));
    }

    /**
     * Read a varInt from the supplied buffer.
     *
     * @param buf The buffer to read from
     * @param maxSize The maximum length of bytes to read
     * @return The integer
     */
    public static int readVarInt(ByteBuf buf, int maxSize) {
        Validate.isTrue(maxSize< 6 && maxSize > 0, "VarInt length is between 1 and 5, not %d", maxSize);
        int i = 0;
        int j = 0;
        byte b0;

        do {
            b0 = buf.readByte();
            i |= (b0 & 127) << j++ * 7;

            if(j > maxSize) {
                throw new RuntimeException("VarInt too big");
            }
        } while((b0 & 128) == 128);

        return i;
    }

    /**
     * An extended length short. Used by custom payload packets to extend size.
     */
    public static int readVarShort(ByteBuf buf) {
        int low = buf.readUnsignedShort();
        int high = 0;
        if((low & 0x8000) != 0) {
            low = low & 0x7FFF;
            high = buf.readUnsignedByte();
        }
        return ((high & 0xFF) << 15) | low;
    }

    public static void writeVarShort(ByteBuf buf, int toWrite) {
        int low = toWrite & 0x7FFF;
        int high = (toWrite & 0x7F80000) >> 15;
        if(high != 0) {
            low = low | 0x8000;
        }
        buf.writeShort(low);
        if(high != 0) {
            buf.writeByte(high);
        }
    }

    /**
     * Write an integer to the buffer using variable length encoding. The maxSize constrains
     * how many bytes (and therefore maximum number) that will be written.
     *
     * @param to The buffer to write to
     * @param toWrite The integer to write
     * @param maxSize The maximum number of bytes to use
     */
    public static void writeVarInt(ByteBuf to, int toWrite, int maxSize) {
        Validate.isTrue(varIntByteCount(toWrite) <= maxSize, "Integer is too big for %d bytes", maxSize);
        while ((toWrite & -128) != 0) {
            to.writeByte(toWrite & 127 | 128);
            toWrite >>>= 7;
        }
        to.writeByte(toWrite);
    }

    /**
     * Read a UTF8 String from the byte buffer.
     * It is encoded as {@code <varint length>[<UTF8 char bytes>]} <br>
     * Note by GauntRecluse, the code portion here wasn't noted as code and was just showing up as brackets when rendered, added the @code tag to fix.
     * @param from The buffer to read from
     * @return The string
     */
    public static String readUTF8String(ByteBuf from) {
        int len = readVarInt(from, 2);
        String str = from.toString(from.readerIndex(), len, StandardCharsets.UTF_8);
        from.readerIndex(from.readerIndex() + len);
        return str;
    }

    /**
     * Write a String with UTF8 byte encoding to the buffer.
     * It is encoded as {@code <varint length>[<UTF8 char bytes>]} <br>
     * Note by GauntRecluse, the code portion here wasn't noted as code and was just showing up as brackets when rendered, added the @code tag to fix.
     *
     * @param to The buffer to write to
     * @param string The string to write
     */
    public static void writeUTF8String(ByteBuf to, String string) {
        byte[] utf8bytes = string.getBytes(StandardCharsets.UTF_8);
        Validate.isTrue(varIntByteCount(utf8bytes.length) < 3, "The string is too long for this encoding.");
        writeVarInt(to, utf8bytes.length, 2);
        to.writeBytes(utf8bytes);
    }

    //NOTE: This javadoc section below was as-is in the Citadel mod's file equivalent to this. Left untouched.
    /**
     * Write an {@link ItemStack} using minecraft compatible encoding.
     *
     * @param to    The buffer to write to
     * @param stack The itemstack to write

    public static void writeItemStack(ByteBuf to, ItemStack stack) {
    FriendlyByteBuf pb = new FriendlyByteBuf(to);
    pb.writeSt(stack);
    } */

    /**
     * Read an {@link ItemStack} from the byte buffer provided. It uses the minecraft encoding.
     *
     * @param from The buffer to read from
     * @return The itemstack read

    public static ItemStack readItemStack(ByteBuf from) {
    FriendlyByteBuf pb = new FriendlyByteBuf(from);
    try {
    return pb.readItem();
    } catch (Exception e) {
    // Unpossible?
    throw new RuntimeException(e);
    }
    } */




    /**
     * Write an {@link CompoundTag} to the byte buffer. It uses the minecraft encoding.
     *
     * @param to  The buffer to write to
     * @param tag The tag to write
     */
    public static void writeTag(ByteBuf to, CompoundTag tag) {
        FriendlyByteBuf pb = new FriendlyByteBuf(to);
        pb.writeNbt(tag);
    }

    /**
     * Read an {@link CompoundTag} from the byte buffer. It uses the minecraft encoding.
     *
     * @param from The buffer to read from
     * @return The read tag
     */
    @Nullable
    public static CompoundTag readTag(ByteBuf from) {
        FriendlyByteBuf pb = new FriendlyByteBuf(from);
        try {
            return pb.readNbt();
        } catch(Exception e) {
            //Unpossible? |||||||| NOTE:from GauntRecluse, the Unpossible? comment was there from the Citadel mod's version.
            throw new RuntimeException(e);
        }
    }
}
