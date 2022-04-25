public class CatInfo {
    private String id;
    private String text;
    private String type;
    private String user;
    private Integer upvotes;

    public CatInfo() {
        //empty contructor
    }

    public CatInfo(String id, String text, String type, String user, int upvotes) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    public CatInfo(String id, String text, String type, String user, Integer upvotes) {
        this(id, text, type, user, upvotes != null ? upvotes.intValue() : 0);
    }

    public int getUpvotes() {
        return upvotes.intValue();
    }

    public String toString() {
        return String.format(
                "CatInfo { \"id\" : %s,\n \"text\" : %s,\n \"type\" : %s,\n \"user\" : %s,\n \"upvotes\" : %d\n }",
                this.id, this.text, this.type, this.user, this.upvotes);
    }
}
