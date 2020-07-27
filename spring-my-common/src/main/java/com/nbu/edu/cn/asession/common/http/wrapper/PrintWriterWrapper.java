package com.nbu.edu.cn.asession.common.http.wrapper;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.util.Locale;

@Slf4j
public class PrintWriterWrapper extends PrintWriter {

    private PrintWriter printWriter;
    private ASessionHttpResponseWrapper responseWrapper;

    public PrintWriterWrapper(PrintWriter printWriter, ASessionHttpResponseWrapper responseWrapper) {
        super(printWriter);
        this.printWriter = printWriter;
        this.responseWrapper = responseWrapper;
    }

    @Override
    public void flush() {
        responseWrapper.doOnResponseCommitted();
        printWriter.flush();
    }

    @Override
    public void close() {
        responseWrapper.doOnResponseCommitted();
        printWriter.close();
    }

    @Override
    public boolean checkError() {
        return printWriter.checkError();
    }

    @Override
    public void write(int c) {
        responseWrapper.trackContentLength(c);
        printWriter.write(c);
    }

    @Override
    public void write(char[] buf, int off, int len) {
        responseWrapper.trackContentLength(len);
        printWriter.write(buf, off, len);
    }

    @Override
    public void write(char[] buf) {
        responseWrapper.trackContentLength(buf);
        printWriter.write(buf);
    }

    @Override
    public void write(String s, int off, int len) {
        responseWrapper.trackContentLength(len);
        printWriter.write(s, off, len);
    }

    @Override
    public void write(String s) {
        responseWrapper.trackContentLength(s);
        printWriter.write(s);
    }

    @Override
    public void print(boolean b) {
        responseWrapper.trackContentLength(b);
        printWriter.println(b);
    }

    @Override
    public void print(char c) {
        responseWrapper.trackContentLength(c);
        printWriter.println(c);
    }

    @Override
    public void print(int i) {
        responseWrapper.trackContentLength(i);
        printWriter.println(i);
    }

    @Override
    public void print(long l) {
        responseWrapper.trackContentLength(l);
        printWriter.println(l);
    }

    @Override
    public void print(float f) {
        responseWrapper.trackContentLength(f);
        printWriter.println(f);
    }

    @Override
    public void print(double d) {
        responseWrapper.trackContentLength(d);
        printWriter.println(d);
    }

    @Override
    public void print(char[] s) {
        responseWrapper.trackContentLength(s);
        printWriter.println(s);
    }

    @Override
    public void print(String s) {
        responseWrapper.trackContentLength(s);
        printWriter.println(s);
    }

    @Override
    public void print(Object obj) {
        responseWrapper.trackContentLength(obj);
        printWriter.println(obj);
    }

    @Override
    public void println() {
        responseWrapper.trackContentLengthLn();
    }

    @Override
    public void println(boolean x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public void println(char x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public void println(int x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public void println(long x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public void println(float x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public void println(double x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public void println(char[] x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public void println(String x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public void println(Object x) {
        responseWrapper.trackContentLength(x);
        responseWrapper.trackContentLengthLn();
        printWriter.println(x);
    }

    @Override
    public PrintWriter printf(String format, Object... args) {
        return printWriter.printf(format, args);
    }

    @Override
    public PrintWriter printf(Locale l, String format, Object... args) {
        return printWriter.printf(l, format, args);
    }

    @Override
    public PrintWriter format(String format, Object... args) {
        return printWriter.format(format, args);
    }

    @Override
    public PrintWriter format(Locale l, String format, Object... args) {
        return printWriter.format(l, format, args);
    }

    @Override
    public PrintWriter append(CharSequence csq) {
        responseWrapper.checkContentLength(csq.length());
        return printWriter.append(csq);
    }

    @Override
    public PrintWriter append(CharSequence csq, int start, int end) {
        responseWrapper.checkContentLength(end - start);
        return printWriter.append(csq,start,end);
    }

    @Override
    public PrintWriter append(char c) {
        responseWrapper.trackContentLength(c);
        return printWriter.append(c);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
