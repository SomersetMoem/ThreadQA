package model.fakeApiUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthData {
    @JsonProperty("username")
    private String userName;

    @JsonProperty("password")
    private String password;
}
