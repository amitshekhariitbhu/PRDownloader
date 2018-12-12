package com.downloader.internal.stream;


import java.io.IOException;

public interface FileDownloadOutputStream {

    /**
     * Writes <code>len</code> bytes from the specified byte array
     * starting at offset <code>off</code> to this file.
     */
    void write(byte b[], int off, int len) throws IOException;

    /**
     * Flush all buffer to system and force all system buffers to synchronize with the underlying
     * device.
     */
    void flushAndSync() throws IOException;

    /**
     * Closes this output stream and releases any system resources associated with this stream. The
     * general contract of <code>close</code> is that it closes the output stream. A closed stream
     * cannot perform output operations and cannot be reopened.
     */
    void close() throws IOException;

    /**
     * Sets the file-pointer offset, measured from the beginning of this file, at which the next
     * read or write occurs.  The offset may be set beyond the end of the file.
     */
    void seek(long offset) throws IOException, IllegalAccessException;

    /**
     * Sets the length of this file.
     */
    void setLength(final long newLength) throws IOException, IllegalAccessException;

}