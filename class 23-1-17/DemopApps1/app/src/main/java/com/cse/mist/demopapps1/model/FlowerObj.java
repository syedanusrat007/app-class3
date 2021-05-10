package com.cse.mist.demopapps1.model;
public class FlowerObj {
    private String category;
    private double price;
    private String instructions;
    private String photo;
    private String name;
    private int productId;

 /*  public FlowerObj(String category,double price,String instructions,String photo,String name,int productId){

        this.category =category;
        this.price =price;
        this.instructions =instructions;
        this.photo =photo;
        this.name =name;
        this.productId =productId;

    }*/

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

