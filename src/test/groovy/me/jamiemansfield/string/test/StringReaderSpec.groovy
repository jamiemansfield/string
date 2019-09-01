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

package me.jamiemansfield.string.test

import spock.lang.Specification

/**
 * Tests for {@link me.jamiemansfield.string.StringReader}.
 */
class StringReaderSpec extends Specification {

    static def TEST = 'Hello, World!'

    def "the basics"() {
        given:
        def reader = new me.jamiemansfield.string.StringReader(TEST)

        expect:
        reader.index() == 0 // The index should always be its current position, therefor starts at 0
        reader.remaining() == 13 // There should still be the entire string left to read
        reader.length() == 13
        reader.available()
    }

    def "peeking"() {
        given:
        def reader = new me.jamiemansfield.string.StringReader(TEST)

        expect:
        // Ensure that peek doesn't advance the index
        reader.peek() == (char) 'H'
        reader.peek() == (char) 'H'
        reader.peek(7) == (char) 'W'
    }

    def "advancing"() {
        given:
        def reader = new me.jamiemansfield.string.StringReader(TEST)

        expect:
        // Ensure that advance does advance the index
        reader.advance() == (char) 'H'
        reader.advance() == (char) 'e'
        reader.advance(5) == (char) 'W'
    }

    def "substring-ing"() {
        given:
        def reader = new me.jamiemansfield.string.StringReader(TEST)

        expect:
        reader.substring(7, 12) == 'World'
    }

    def "skipping"() {
        given:
        def reader = new me.jamiemansfield.string.StringReader(TEST)

        expect:
        reader.skip(5)
        reader.peek() == (char) ','
    }

}
