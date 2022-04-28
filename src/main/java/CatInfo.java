import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @JsonCreator
    public CatInfo(
            @JsonProperty("id") String id,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user,
            @JsonProperty("upvotes") Integer upvotes) {
        this(id, text, type, user, upvotes != null ? upvotes.intValue() : 0);
    }

    @JsonGetter("upvotes")
    public int getUpvotes() {
        return upvotes.intValue();
    }

    public String toString() {
        return String.format(
                "CatInfo { \"id\" : %s,\n \"text\" : %s,\n \"type\" : %s,\n \"user\" : %s,\n \"upvotes\" : %d\n }",
                this.id, this.text, this.type, this.user, this.upvotes);
    }
}
