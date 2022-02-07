package INHERITANCE.Animals;

public class Tomcat extends Cat{
    private static String CAT_GENDER = "Male";
    public Tomcat(String name, int age) {
        super(name, age, CAT_GENDER);
    }

    @Override
    public String produceSound(){
        return "MEOW";
    }
}
