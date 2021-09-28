package umn.ac.id.week07b_40112;

import java.io.Serializable;

public class SumberVideo implements Serializable {
    private String judul,keterangan,videoURI;
    public SumberVideo(String jd, String ket, String uri){
        this.judul = jd;
        this.keterangan = ket;
        this.videoURI = uri;
    }

    public String getJudul() {
        return this.judul;
    }

    public String getKeterangan() {
        return this.keterangan;
    }

    public String getVideoURI() {
        return this.videoURI;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public void setVideoURI(String videoURI) {
        this.videoURI = videoURI;
    }

    public String toString(){
        return this.getJudul() + " => " + this.getKeterangan();
    }
}
