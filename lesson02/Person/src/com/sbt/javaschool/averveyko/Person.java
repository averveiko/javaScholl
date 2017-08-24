package com.sbt.javaschool.averveyko;

public class Person {
    protected final boolean man;
    protected final String name;
    protected Person spouse;

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife.
     * Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */
    public boolean marry(Person person) {
        if (this.spouse == person) return false; //already married
        if (this.man == person.man) return false; //equals man

        if (this.spouse != null) {
            this.divorce();
        }

        this.spouse = person;
        person.marry(this);

        return true;
    }

    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (this.spouse != null) {
            Person exSpouse = this.spouse;
            this.spouse = null;
            System.out.println(this.name + " divorce");
            exSpouse.divorce();
            return true;
        }
        return false;
    }
}

