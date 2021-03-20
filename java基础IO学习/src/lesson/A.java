package lesson;

public class A{
    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher t = new Teacher("张三",32);
        Student s1 = new Student("李四",12,t);
        Student s2 = (Student) s1.clone();
        System.out.println(s1);
        System.out.println(s2);
        t.setName("张");
        System.out.println(s1);
        System.out.println(s2);
    }

}
class Teacher implements Cloneable{
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Teacher(String name, int age) {
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
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}

class Student implements Cloneable{

    private String name;
    private int age;
    private Teacher teacher;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", teacher=" + teacher +
                '}';
    }

    public Student(String name, int age, Teacher teacher) {
        this.name = name;
        this.age = age;
        this.teacher = teacher;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public Object clone() throws CloneNotSupportedException{
        Student student = (Student) super.clone();
        //现在将Teacher对象复制一份并重新set进来
        student.setTeacher((Teacher) student.getTeacher().clone());
        return student;
    }
}
