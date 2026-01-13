package com.gloriane;

public class PersonAction {
    private String printName;
    private PersonAction sendEmail;

    public PersonAction(String printName, PersonAction sendEmail) {
        this.printName = printName;
        this.sendEmail = sendEmail;
    }


    public String getPrintName() {
        return printName;
    }

    public PersonAction getSendEmail() {
        return sendEmail;
    }

    public void setPrintName(String printName) {
        this.printName = printName;
    }

    public void setSendEmail(PersonAction sendEmail) {
        this.sendEmail = sendEmail;
    }
}
