/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolupteepublic;

import java.util.Objects;

/**
 *
 * @author me
 */
public class image1 {
    
    String name;
    String url;
    String alt;
    String des;
    String maintag;
    String tagchuan;
     String mau;
     String mauDefaut;
     String soluongMau;

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrandnew() {
        return brandnew;
    }

    public void setBrandnew(String brandnew) {
        this.brandnew = brandnew;
    }

    public String getKieuao() {
        return kieuao;
    }

    public void setKieuao(String kieuao) {
        this.kieuao = kieuao;
    }
  
  String brand;
  
  String brandnew;
  
  String kieuao;
  
  String tag;

    public String getTagchuan() {
        return tagchuan;
    }

    public void setTagchuan(String tagchuan) {
        this.tagchuan = tagchuan;
    }

    public String getMaintag() {
        return maintag;
    }

    public void setMaintag(String maintag) {
        this.maintag = maintag;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final image1 other = (image1) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
   

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
    
}
