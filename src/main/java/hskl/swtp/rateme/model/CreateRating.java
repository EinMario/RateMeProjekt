package hskl.swtp.rateme.model;

public class CreateRating {
    String user;
    String txt;
    int stars;
    String pic;
    String pos;

    public CreateRating() {
    }

    public CreateRating(String user, String txt, int stars, String pic, String pos) {
        this.user = user;
        this.txt = txt;
        this.stars = stars;
        this.pic = pic;
        this.pos = pos;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
