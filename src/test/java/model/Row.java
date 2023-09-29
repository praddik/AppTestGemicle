package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Row {
    @JsonProperty("id")
    int id;
    @JsonProperty("color")
    String color;
    @JsonProperty("value1")
    int value1;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;

        if (id != row.id) return false;
        if (value1 != row.value1) return false;
        if (value2 != row.value2) return false;
        return color != null ? color.equals(row.color) : row.color == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + value1;
        result = 31 * result + value2;
        return result;
    }

    @JsonProperty("value2")
    int value2;
}
