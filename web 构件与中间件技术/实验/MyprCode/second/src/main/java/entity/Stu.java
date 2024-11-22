package entity;


/**
 * 1. @ClassName Stu
 * 2. @Description TODO
 * 3. @Author huo
 * 4. @Date 2024/5/16 8:54
 */


public class Stu {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    String id;
    String name;
    String classname;

}
