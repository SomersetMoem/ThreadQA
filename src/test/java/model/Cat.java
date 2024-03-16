package model;

public class Cat {
    private String name;
    private String model;

    public Cat(String name, String model) {
        this.name = name;
        this.model = model;
    }

    public Cat() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
