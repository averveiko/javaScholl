package ru.sbt.averveyko.stream;

public class CartoonPerson extends Person {
    private String cartoonName;

    public CartoonPerson(String name, int age, Gender gender, String cartoonName) {
        super(name, age, gender);
        this.cartoonName = cartoonName;
    }

    public String getCartoonName() {
        return cartoonName;
    }

    public void setCartoonName(String cartoonName) {
        this.cartoonName = cartoonName;
    }

    @Override
    public String toString() {
        return "CartoonPerson{" +
                "cartoonName='" + cartoonName + '\'' +
                "} " + super.toString();
    }
}
