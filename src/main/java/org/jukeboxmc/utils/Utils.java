package org.jukeboxmc.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.jukeboxmc.world.Dimension;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author LucGamesYT
 * @version 1.0
 */
public class Utils {

    public static int blockToChunk( int value ) {
        return value >> 4;
    }

    public static long toLong( int x, int z ) {
        return ( (long) x << 32 ) | ( z & 0xffffffffL );
    }

    public static int fromHashX( long hash ) {
        return (int) ( hash >> 32 );
    }

    public static int fromHashZ( long hash ) {
        return (int) hash;
    }

    public static int log2( int value ) {
        if ( value <= 0 ) throw new IllegalArgumentException();
        return 31 - Integer.numberOfLeadingZeros( value );
    }

    public static float square( float value ) {
        return value * value;
    }

    public static float sqrt( float value ) {
        final float xhalf = value * 0.5F;
        float y = Float.intBitsToFloat( 0x5f375a86 - ( Float.floatToIntBits( value ) >> 1 ) );
        y = y * ( 1.5F - ( xhalf * y * y ) );
        y = y * ( 1.5F - ( xhalf * y * y ) );
        return value * y;
    }

    public static ByteBuf allocate( byte[] data ) {
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer( data.length );
        buf.writeBytes( data );
        return buf;
    }

    public static byte[] getKey( int chunkX, int chunkZ, Dimension dimension, byte key ) {
        if ( dimension.equals( Dimension.OVERWORLD ) ) {
            return new byte[]{
                    (byte) ( chunkX & 0xff ),
                    (byte) ( ( chunkX >>> 8 ) & 0xff ),
                    (byte) ( ( chunkX >>> 16 ) & 0xff ),
                    (byte) ( ( chunkX >>> 24 ) & 0xff ),
                    (byte) ( chunkZ & 0xff ),
                    (byte) ( ( chunkZ >>> 8 ) & 0xff ),
                    (byte) ( ( chunkZ >>> 16 ) & 0xff ),
                    (byte) ( ( chunkZ >>> 24 ) & 0xff ),
                    key
            };
        } else {
            byte dimensionId = dimension.getId();
            return new byte[]{
                    (byte) ( chunkX & 0xff ),
                    (byte) ( ( chunkX >>> 8 ) & 0xff ),
                    (byte) ( ( chunkX >>> 16 ) & 0xff ),
                    (byte) ( ( chunkX >>> 24 ) & 0xff ),
                    (byte) ( chunkZ & 0xff ),
                    (byte) ( ( chunkZ >>> 8 ) & 0xff ),
                    (byte) ( ( chunkZ >>> 16 ) & 0xff ),
                    (byte) ( ( chunkZ >>> 24 ) & 0xff ),
                    (byte) ( dimensionId & 0xff ),
                    (byte) ( ( dimensionId >>> 8 ) & 0xff ),
                    (byte) ( ( dimensionId >>> 16 ) & 0xff ),
                    (byte) ( ( dimensionId >>> 24 ) & 0xff ),
                    key
            };
        }
    }

    public static byte[] getSubChunkKey( int chunkX, int chunkZ, Dimension dimension, byte key, byte subChunk ) {
        if ( dimension.equals( Dimension.OVERWORLD ) ) {
            return new byte[]{
                    (byte) ( chunkX & 0xff ),
                    (byte) ( ( chunkX >>> 8 ) & 0xff ),
                    (byte) ( ( chunkX >>> 16 ) & 0xff ),
                    (byte) ( ( chunkX >>> 24 ) & 0xff ),
                    (byte) ( chunkZ & 0xff ),
                    (byte) ( ( chunkZ >>> 8 ) & 0xff ),
                    (byte) ( ( chunkZ >>> 16 ) & 0xff ),
                    (byte) ( ( chunkZ >>> 24 ) & 0xff ),
                    key,
                    subChunk
            };
        } else {
            byte dimensionId = dimension.getId();
            return new byte[]{
                    (byte) ( chunkX & 0xff ),
                    (byte) ( ( chunkX >>> 8 ) & 0xff ),
                    (byte) ( ( chunkX >>> 16 ) & 0xff ),
                    (byte) ( ( chunkX >>> 24 ) & 0xff ),
                    (byte) ( chunkZ & 0xff ),
                    (byte) ( ( chunkZ >>> 8 ) & 0xff ),
                    (byte) ( ( chunkZ >>> 16 ) & 0xff ),
                    (byte) ( ( chunkZ >>> 24 ) & 0xff ),
                    (byte) ( dimensionId & 0xff ),
                    (byte) ( ( dimensionId >>> 8 ) & 0xff ),
                    (byte) ( ( dimensionId >>> 16 ) & 0xff ),
                    (byte) ( ( dimensionId >>> 24 ) & 0xff ),
                    key,
                    subChunk
            };
        }
    }

    public static void writeFile( File file, InputStream content ) throws IOException {
        if ( content == null ) {
            throw new IllegalArgumentException( "Content must not be null!" );
        }

        if ( !file.exists() ) {
            file.createNewFile();
        }

        FileOutputStream stream = new FileOutputStream( file );

        byte[] buffer = new byte[1024];
        int length;
        while ( ( length = content.read( buffer ) ) != -1 ) {
            stream.write( buffer, 0, length );
        }
        content.close();
        stream.close();
    }
}
