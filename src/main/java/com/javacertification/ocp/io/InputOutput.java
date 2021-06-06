package com.javacertification.ocp.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutput {

    public void createFileOrDirectory(Path path) throws IOException {
        var file = path.toFile();

        // If the file/directory does not exists, it returns false
        if (file.exists()) {
            file.deleteOnExit();
            return;
        }


        if (file.isDirectory()) {
            file.mkdir(); // If the directory was successfully created, then this line returns true

            return;
        }

        file.createNewFile(); // If the file was successfully created, then this line returns true
    }

    public void writeDataInFile(List<String> data, Path file) throws IOException {
        try (var os = Files.newBufferedWriter(file)) {
            data.forEach(line -> writeString(line, os));
        }
    }

    public void serializeObject(Object object, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            oos.writeObject(object);
            oos.flush();
        }
    }

    public <T> List<T> deserializeObject(String filename, Class<? extends T> clazz) throws IOException, ClassNotFoundException {
        List<Object> o = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            while (true) {
                o.add(ois.readObject());
            }
        } catch (EOFException e) {
            System.err.println("End of file");
        }
        return ((List<T>) o);
    }

    private void writeString(String string, BufferedWriter br) {
        try {
            br.write(string);
            br.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Foo implements Serializable {
        private final int id;
        private final String name;
        private final transient boolean flag;

        public Foo(int id, String name) {
            this.id = id;
            this.name = name;
            flag = true;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public boolean isFlag() {
            return flag;
        }
    }

    interface InputConsole {
        <T> T readLine(final String message);

        <T> T readLine();
    }

    static class ScannerImpl implements InputConsole {

        private final Scanner scanner;

        public ScannerImpl() {
            scanner = new Scanner(System.in);
        }

        public ScannerImpl(final String input) {
            scanner = new Scanner(input);
        }

        @SuppressWarnings("unchecked")
        @Override

        public Object readLine(String message) {
            System.out.println(message);
            return scanner.nextLine();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object readLine() {
            return scanner.nextLine();
        }
    }

    static class ConsoleImpl implements InputConsole {

        private final Console console = System.console();

        public ConsoleImpl() {
            if (null == console) {
                throw new IllegalStateException("The console is not available");
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object readLine(String message) {
            System.out.println(message);
            return console.readLine();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object readLine() {
            return console.readLine();
        }
    }

    static class BufferReaderImpl implements InputConsole {

        private final BufferedReader bufferedReader;

        public BufferReaderImpl(final String input) {
            bufferedReader = new BufferedReader(new StringReader(input));
        }

        public BufferReaderImpl() {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object readLine(String message) {
            System.out.println(message);
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Object readLine() {
            try {
                return bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
