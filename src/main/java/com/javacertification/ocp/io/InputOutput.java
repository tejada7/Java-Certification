package com.javacertification.ocp.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

    public <T> T deserializeObject(String filename, Class<? extends T> clazz) throws IOException, ClassNotFoundException {
        Object o;
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            o = ois.readObject();
        }
        return ((T) o);
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
}
