package com.fx.structurePattren.decoratorpattern.c;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


class MyBufferedReader extends Reader {
    private final Reader in;

    public MyBufferedReader(Reader in) {
        this.in = in;
    }

    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = in.read();
            if (n == '\n') {
                break;
            }
            sb.append((char) n);
        }
        return sb.toString();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}


public class StreamTest {
    public static void main(String[] args) throws IOException {
        Reader fileReader = new FileReader("F:\\1.txt");
        MyBufferedReader myBufferedReader = new MyBufferedReader(fileReader);
        String line = myBufferedReader.readLine();
        System.out.println(line);
        String line2 = myBufferedReader.readLine();
        System.out.println(line2);
    }
}
