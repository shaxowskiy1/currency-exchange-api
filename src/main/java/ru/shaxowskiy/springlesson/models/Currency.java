package ru.shaxowskiy.springlesson.models;

public class Currency {
    private int id;

    private String code;

    private String fullname;

    private String sign;

    public Currency() {
    }

    public Currency(String code, String fullname, String sign) {
        this.code = code;
        this.fullname = fullname;
        this.sign = sign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", fullname='" + fullname + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
