package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo2 {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(Student.newInstance("8", 21));
        studentList.add(Student.newInstance("9", 22));
        studentList.add(Student.newInstance("10", 19));
        studentList.add(Student.newInstance("11", 23));

        List<String> ids = studentList.stream().
                filter(s -> {
                    System.out.println("filter - " + s);
                    return s.getAge() > 20;
                })
                .map(s -> {
                    System.out.println("map - " + s);
                    return s.getName();
                })
                .limit(3)
                .collect(Collectors.toList());


        List<String> names = Arrays.asList(new String[]{"barry", " andy", "ben", "chris", "bill"});

//map and filter are piped and the stream is stored
        Stream<String> namesStream = names.stream()
                .map(n -> {
                    System.out.println("In map - " + n);
                    return n.toUpperCase();
                })
                .filter(upperName -> {
                    System.out.println("In filter - " + upperName);
                    return upperName.startsWith("B");
                });
        namesStream.limit(2).collect(Collectors.toList());
    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Student newInstance(String name, int age) {
        return new Student(name, age);
    }
}
