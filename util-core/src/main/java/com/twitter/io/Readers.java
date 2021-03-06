/* Copyright 2015 Twitter, Inc. */
package com.twitter.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import com.twitter.concurrent.AsyncStream;
import com.twitter.util.Future;

import scala.collection.JavaConverters;
import scala.runtime.BoxedUnit;

/**
 * Java APIs for Reader.
 *
 * @see com.twitter.io.Reader
 */
public final class Readers {

  private Readers() { throw new IllegalStateException(); }

  public static <A> Reader<A> newEmptyReader() {
    return Reader$.MODULE$.<A>empty();
  }

  /**
   * See {@code com.twitter.io.Reader.fromBuf}.
   */
  public static Reader<Buf> newBufReader(Buf buf, int chunkSize) {
    return Reader$.MODULE$.fromBuf(buf, chunkSize);
  }

  /**
   * See {@code com.twitter.io.Reader.readAll}.
   */
  public static Future<Buf> readAll(Reader<Buf> r) {
    return Reader$.MODULE$.readAll(r);
  }

  /**
   * See {@code com.twitter.io.Reader.concat}.
   */
  public static Reader<Buf> concat(AsyncStream<Reader<Buf>> readers) {
    return Reader$.MODULE$.concat(readers);
  }

  /**
   * See {@code com.twitter.io.Reader.copy}.
   */
  public static Future<BoxedUnit> copy(Reader<Buf> r, Writer<Buf> w) {
    return Reader$.MODULE$.copy(r, w);
  }

  /**
   * See {@code com.twitter.io.Reader.copyMany}.
   */
  public static Future<BoxedUnit> copyMany(AsyncStream<Reader<Buf>> readers, Writer<Buf> w) {
    return Reader$.MODULE$.copyMany(readers, w);
  }

  /**
   * See {@code com.twitter.io.Reader.fromFile()}.
   */
  public static Reader<Buf> newFileReader(File f, int chunkSize) throws FileNotFoundException {
    return Reader$.MODULE$.fromFile(f, chunkSize);
  }

  /**
   * See {@code com.twitter.io.Reader.fromStream()}.
   */
  public static Reader<Buf> newInputStreamReader(InputStream in, int chunkSize) {
    return Reader$.MODULE$.fromStream(in, chunkSize);
  }

  /**
   * See [[com.twitter.io.Reader$#fromSeq(scala.collection.Seq)]]
   */
  public static <A> Reader<A> fromSeq(List<A> list) {
    return Reader$.MODULE$.fromSeq(JavaConverters.asScalaIteratorConverter(list.iterator())
      .asScala().toSeq());
  }

  /**
   * See [[com.twitter.io.Reader$#fromSeq(scala.collection.Seq)]]
   */
  public static <A> Reader<A> fromSeq(Stream<A> stream) {
    return Reader$.MODULE$.fromSeq(JavaConverters.asScalaIteratorConverter(stream.iterator())
      .asScala().toSeq());
  }

  /**
   * See [[com.twitter.io.Reader#toAsyncStream]]
   */
  public static <A> AsyncStream<A> toAsyncStream(Reader<A> reader) {
    return Reader$.MODULE$.toAsyncStream(reader);
  }
}
