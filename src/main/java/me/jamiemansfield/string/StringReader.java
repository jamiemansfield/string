/*
 * This file is part of string, licensed under the MIT License (MIT).
 *
 * Copyright (c) Jamie Mansfield <https://www.jamiemansfield.me/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.jamiemansfield.string;

/**
 * Reader for strings.
 *
 * @author Jamie Mansfield
 * @since 0.1.0
 */
public class StringReader {

    private final String source;
    private int index = 0;

    public StringReader(final String source) {
        this.source = source;
    }

    /**
     * Gets the length of the source.
     *
     * @return The source length
     */
    public int length() {
        return this.source.length();
    }

    /**
     * Gets the count of the remaining characters to be read.
     *
     * @return The remaining character count
     */
    public int remaining() {
        return this.source.length() - this.index;
    }

    /**
     * Gets the index of the character currently being read.
     *
     * @return The current index
     */
    public int index() {
        return this.index;
    }

    /**
     * Establishes whether there is a character to read, with the given offset.
     *
     * @param offset How many characters ahead
     * @return {@code true} if there is a character to read;
     *         {@code false} otherwise
     */
    public boolean readable(final int offset) {
        return this.index + offset <= this.source.length();
    }

    /**
     * Establishes whether there is a character to immediately read.
     *
     * @return {@code true} if there is a character to be read;
     *         {@code false} otherwise
     */
    public boolean available() {
        return this.readable(1);
    }

    /**
     * Advances the index by the given length.
     *
     * @param length How much to advance by
     */
    public void skip(final int length) {
        this.index += length;
    }

    /**
     * Gets the character at the current index.
     *
     * @return The current character
     */
    public char peek() {
        return this.peek(0);
    }

    /**
     * Gets the character at the current index and offset.
     *
     * @param offset The offset of the character
     * @return The character
     */
    public char peek(final int offset) {
        if (!this.readable(offset)) throw new IllegalStateException("No character available to peek at!");
        return this.source.charAt(this.index + offset);
    }

    /**
     * Gets the character at the current index, and increments the index.
     *
     * @return The current character
     */
    public char advance() {
        return this.advance(0);
    }

    /**
     * Gets the character at the current index and offset, and increments the index by the offset.
     *
     * @return The character
     */
    public char advance(final int offset) {
        if (!this.readable(offset)) throw new IllegalStateException("No character available to advance to!");
        return this.source.charAt((this.index += offset + 1) - 1);
    }

    /**
     * Gets a substring of the source.
     *
     * @param start The start index
     * @param end The end index
     * @return The substring'd result
     * @see String#substring(int, int)
     */
    public String substring(final int start, final int end) {
        return this.source.substring(start, end);
    }

}
